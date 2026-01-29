package com.biblioteca.main;

import com.biblioteca.db.ConexionExistDB;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ConexionExistDB conexion = new ConexionExistDB();

        boolean salir = false;

        while (!salir) {

            System.out.println("\n=== MENÚ BIBLIOTECA (eXist-db) ===");
            System.out.println("1. Leer documentos");
            System.out.println("2. Añadir documento");
            System.out.println("3. Eliminar documento");
            System.out.println("4. Subir XML local a eXist-db");
            System.out.println("5. Consultas XQuery");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    conexion.leerDocumentos("/db/biblioteca/documentos");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    System.out.print("Editorial: ");
                    String editorial = sc.nextLine();

                    System.out.print("Fecha publicación (YYYY-MM-DD): ");
                    String fecha = sc.nextLine();

                    System.out.print("Género: ");
                    String genero = sc.nextLine();

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    // Contenido generado automáticamente
                    String contenidoXML = "<contenido>Contenido generado automáticamente</contenido>";

                    String fragmento =
                        "<documento id=\"" + id + "\">" +
                            "<titulo>" + titulo + "</titulo>" +
                            "<autor>" + autor + "</autor>" +
                            "<editorial>" + editorial + "</editorial>" +
                            "<fecha_publicacion>" + fecha + "</fecha_publicacion>" +
                            "<genero>" + genero + "</genero>" +
                            "<descripcion>" + descripcion + "</descripcion>" +
                            contenidoXML +
                        "</documento>";

                    conexion.insertarDocumentoEnXML("/db/biblioteca/documentos", "documentos.xml", fragmento);
                    break;

                case 3:
                    System.out.print("ID del documento a eliminar: ");
                    int idEliminar = sc.nextInt();
                    conexion.eliminarDocumentoExistDB("/db/biblioteca/documentos", idEliminar);
                    break;

                case 4:
                    conexion.crearColeccion("biblioteca");
                    conexion.crearColeccion("biblioteca/documentos");
                    conexion.subirDocumento("/db/biblioteca/documentos", "documentos.xml",
                            "src/main/resources/documentos.xml");
                    break;

                case 5:
                    submenuConsultas(sc, conexion);
                    break;

                case 6:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        sc.close();
        System.out.println("Programa finalizado.");
    }


    private static void submenuConsultas(Scanner sc, ConexionExistDB conexion) {

        boolean volver = false;

        while (!volver) {

            System.out.println("\n=== CONSULTAS XQUERY ===");
            System.out.println("1. Títulos ordenados alfabéticamente");
            System.out.println("2. Buscar por género = Novela");
            System.out.println("3. Publicados después de 1950");
            System.out.println("4. Buscar palabra 'joven' en descripción");
            System.out.println("5. Mostrar documento con ID = 4");
            System.out.println("6. Contar documentos");
            System.out.println("7. Volver");
            System.out.print("Opción: ");

            int op = sc.nextInt();
            sc.nextLine();

            String consulta = null;

            switch (op) {

                case 1:
                    consulta = "for $d in /biblioteca/documento order by $d/titulo return $d/titulo";
                    break;

                case 2:
                    consulta = "for $d in /biblioteca/documento[genero = 'Novela'] return $d/titulo";
                    break;

                case 3:
                    consulta = "for $d in /biblioteca/documento[xs:date(fecha_publicacion) > xs:date('1950-01-01')] return $d/titulo";
                    break;

                case 4:
                    consulta = "for $d in /biblioteca/documento[contains(lower-case(descripcion), 'joven')] return $d/titulo";
                    break;

                case 5:
                    consulta = "for $d in /biblioteca/documento[@id = 4] return $d";
                    break;

                case 6:
                    consulta = "count(/biblioteca/documento)";
                    break;

                case 7:
                    volver = true;
                    continue;

                default:
                    System.out.println("Opción no válida.");
                    continue;
            }

            conexion.ejecutarConsulta("/db/biblioteca/documentos", consulta);
        }
    }
}