package com.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import com.biblioteca.db.ConexionExistDB;

public class FormularioEliminar extends JFrame {

    public FormularioEliminar(ConexionExistDB conexion) {
        super("Eliminar documento");

        JTextField txtId = new JTextField(10);
        JButton btnEliminar = new JButton("Eliminar");

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                conexion.eliminarDocumentoExistDB("/db/biblioteca/documentos", id);
                JOptionPane.showMessageDialog(this, "Documento eliminado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar documento: " + ex.getMessage());
            }
        });

        setLayout(new GridLayout(2, 2));
        add(new JLabel("ID del documento:"));
        add(txtId);
        add(btnEliminar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}