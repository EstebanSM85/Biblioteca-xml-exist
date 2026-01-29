package com.biblioteca.db;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

public class ConexionExistDB {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String USUARIO = "admin";
    private static final String PASSWORD = "admin";

    public ConexionExistDB() {
        try {
            Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);
        } catch (Exception e) {
            System.out.println("Error al inicializar el driver de eXist-db");
            e.printStackTrace();
        }
    }

    public void crearColeccion(String nombre) {
        try {
            Collection root = DatabaseManager.getCollection(URI + "/db", USUARIO, PASSWORD);
            Collection nueva = root.getChildCollection(nombre);

            if (nueva == null) {
                root.getService("CollectionManagementService", "1.0")
                    .getClass()
                    .getMethod("createCollection", String.class)
                    .invoke(root.getService("CollectionManagementService", "1.0"), nombre);

                System.out.println("Colección creada: " + nombre);
            } else {
                System.out.println("La colección ya existe: " + nombre);
            }

        } catch (Exception e) {
            System.out.println("Error al crear la colección");
            e.printStackTrace();
        }
    }

    public void eliminarColeccion(String nombre) {
        try {
            Collection root = DatabaseManager.getCollection(URI + "/db", USUARIO, PASSWORD);
            root.getService("CollectionManagementService", "1.0")
                .getClass()
                .getMethod("removeCollection", String.class)
                .invoke(root.getService("CollectionManagementService", "1.0"), nombre);

            System.out.println("Colección eliminada: " + nombre);

        } catch (Exception e) {
            System.out.println("Error al eliminar la colección");
            e.printStackTrace();
        }
    }

    public void subirDocumento(String coleccion, String nombreArchivo, String rutaLocal) {
        try {
            Collection col = DatabaseManager.getCollection(URI + coleccion, USUARIO, PASSWORD);

            if (col == null) {
                System.out.println("La colección no existe: " + coleccion);
                return;
            }

            XMLResource res = (XMLResource) col.createResource(nombreArchivo, "XMLResource");
            res.setContent(new java.io.File(rutaLocal));
            col.storeResource(res);

            System.out.println("Documento subido: " + nombreArchivo);

        } catch (Exception e) {
            System.out.println("Error al subir el documento");
            e.printStackTrace();
        }
    }

    public void ejecutarConsulta(String coleccion, String consulta) {
        try {
            Collection col = DatabaseManager.getCollection(URI + coleccion, USUARIO, PASSWORD);

            if (col == null) {
                System.out.println("La colección no existe: " + coleccion);
                return;
            }

            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            org.xmldb.api.base.ResourceSet resultado = servicio.query(consulta);

            org.xmldb.api.base.ResourceIterator iter = resultado.getIterator();
            while (iter.hasMoreResources()) {
                Resource r = iter.nextResource();
                System.out.println(r.getContent());
            }

        } catch (XMLDBException e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }
    }
    
    public void leerDocumentos(String coleccion) {
        try {
            Collection col = DatabaseManager.getCollection(URI + coleccion, USUARIO, PASSWORD);

            if (col == null) {
                System.out.println("La colección no existe: " + coleccion);
                return;
            }

            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");

            String consulta = "for $d in /biblioteca/documento return concat($d/@id, ' - ', $d/titulo/text())";

            org.xmldb.api.base.ResourceSet resultado = servicio.query(consulta);
            org.xmldb.api.base.ResourceIterator iter = resultado.getIterator();

            while (iter.hasMoreResources()) {
                Resource r = iter.nextResource();
                System.out.println(r.getContent());
            }

        } catch (Exception e) {
            System.out.println("Error al leer documentos desde eXist-db");
            e.printStackTrace();
        }        
    }
    
    public void añadirDocumentoExistDB(String coleccion, String xmlDocumento, String nombre) {
        try {
            Collection col = DatabaseManager.getCollection(URI + coleccion, USUARIO, PASSWORD);

            if (col == null) {
                System.out.println("La colección no existe: " + coleccion);
                return;
            }

            XMLResource res = (XMLResource) col.createResource(nombre, "XMLResource");
            res.setContent(xmlDocumento);
            col.storeResource(res);

            System.out.println("Documento guardado correctamente en eXist-db.");

        } catch (Exception e) {
            System.out.println("Error al añadir documento en eXist-db");
            e.printStackTrace();
        }
    }
    
    public void eliminarDocumentoExistDB(String coleccion, int id) {
        try {
            Collection col = DatabaseManager.getCollection(URI + coleccion, USUARIO, PASSWORD);

            if (col == null) {
                System.out.println("La colección no existe: " + coleccion);
                return;
            }

            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            servicio.setProperty("indent", "yes");

            String consulta =
                "update delete doc('documentos.xml')/biblioteca/documento[@id = " + id + "]";

            servicio.query(consulta);

            System.out.println("Documento eliminado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al eliminar documento en eXist-db");
            e.printStackTrace();
        }
    }
    
    public void insertarDocumentoEnXML(String coleccion, String nombreArchivo, String xmlFragmento) {
        try {
            Collection col = DatabaseManager.getCollection(URI + coleccion, USUARIO, PASSWORD);

            if (col == null) {
                System.out.println("La colección no existe: " + coleccion);
                return;
            }

            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            servicio.setProperty("indent", "yes");

            String consulta = "update insert " + xmlFragmento + " into doc('" + nombreArchivo + "')/biblioteca";

            servicio.query(consulta);

            System.out.println("Documento insertado dentro de " + nombreArchivo);

        } catch (Exception e) {
            System.out.println("Error al insertar documento en XML de eXist-db");
            e.printStackTrace();
        }
    }
}