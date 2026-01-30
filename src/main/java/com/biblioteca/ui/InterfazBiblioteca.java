package com.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import com.biblioteca.db.ConexionExistDB;

public class InterfazBiblioteca extends JFrame {

    private ConexionExistDB conexion;

    public InterfazBiblioteca() {
        super("Biblioteca - Gestión XML");

        conexion = new ConexionExistDB();

        JButton btnLeer = new JButton("Leer documentos");
        JButton btnCrear = new JButton("Crear documento");
        JButton btnEliminar = new JButton("Eliminar documento");
        JButton btnSubir = new JButton("Subir XML local");
        JButton btnConsultas = new JButton("Consultas XQuery");
        JButton btnSalir = new JButton("Salir");

        btnLeer.addActionListener(e -> new VentanaLeer());
        btnCrear.addActionListener(e -> new FormularioCrear(conexion));
        btnEliminar.addActionListener(e -> new FormularioEliminar(conexion));
        btnSubir.addActionListener(e -> new FormularioSubirXML(conexion));
        btnConsultas.addActionListener(e -> new VentanaConsultas());
        btnSalir.addActionListener(e -> System.exit(0));

        setLayout(new GridLayout(6, 1, 10, 10));
        add(btnLeer);
        add(btnCrear);
        add(btnEliminar);
        add(btnSubir);
        add(btnConsultas);
        add(btnSalir);

        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}