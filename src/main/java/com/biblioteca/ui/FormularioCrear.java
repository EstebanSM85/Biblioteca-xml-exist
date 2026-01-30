package com.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import com.biblioteca.db.ConexionExistDB;

public class FormularioCrear extends JFrame {

    public FormularioCrear(ConexionExistDB conexion) {
        super("Crear documento");

        JTextField txtId = new JTextField(10);
        JTextField txtTitulo = new JTextField(20);
        JTextField txtAutor = new JTextField(20);
        JTextField txtEditorial = new JTextField(20);
        JTextField txtFecha = new JTextField(10);
        JTextField txtGenero = new JTextField(15);
        JTextField txtDescripcion = new JTextField(30);

        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                String editorial = txtEditorial.getText();
                String fecha = txtFecha.getText();
                String genero = txtGenero.getText();
                String descripcion = txtDescripcion.getText();

                String contenido = "<contenido>Contenido generado automáticamente</contenido>";

                String fragmento =
                    "<documento id=\"" + id + "\">" +
                        "<titulo>" + titulo + "</titulo>" +
                        "<autor>" + autor + "</autor>" +
                        "<editorial>" + editorial + "</editorial>" +
                        "<fecha_publicacion>" + fecha + "</fecha_publicacion>" +
                        "<genero>" + genero + "</genero>" +
                        "<descripcion>" + descripcion + "</descripcion>" +
                        contenido +
                    "</documento>";

                conexion.insertarDocumentoEnXML(
                        "/db/biblioteca/documentos",
                        "documentos.xml",
                        fragmento
                );

                JOptionPane.showMessageDialog(this, "Documento creado correctamente");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al crear documento: " + ex.getMessage());
            }
        });

        setLayout(new GridLayout(8, 2));
        add(new JLabel("ID:")); add(txtId);
        add(new JLabel("Título:")); add(txtTitulo);
        add(new JLabel("Autor:")); add(txtAutor);
        add(new JLabel("Editorial:")); add(txtEditorial);
        add(new JLabel("Fecha (YYYY-MM-DD):")); add(txtFecha);
        add(new JLabel("Género:")); add(txtGenero);
        add(new JLabel("Descripción:")); add(txtDescripcion);
        add(btnGuardar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}