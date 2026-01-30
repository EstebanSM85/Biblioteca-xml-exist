package com.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import com.biblioteca.db.ConexionExistDB;

public class FormularioSubirXML extends JFrame {

    public FormularioSubirXML(ConexionExistDB conexion) {
        super("Subir XML local");

        JButton btnSeleccionar = new JButton("Seleccionar XML y subir");

        btnSeleccionar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int opcion = chooser.showOpenDialog(this);

            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = chooser.getSelectedFile();
                try {
                    String contenido = Files.readString(archivo.toPath());
                    conexion.añadirDocumentoExistDB(
                            "/db/biblioteca/documentos",
                            contenido,
                            "documentos.xml"
                    );
                    JOptionPane.showMessageDialog(this, "XML subido correctamente a exist-db");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al subir XML: " + ex.getMessage());
                }
            }
        });

        setLayout(new FlowLayout());
        add(btnSeleccionar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}