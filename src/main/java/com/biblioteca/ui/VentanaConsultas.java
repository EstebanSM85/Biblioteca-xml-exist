package com.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.base.Database;
import org.exist.xmldb.DatabaseImpl;

public class VentanaConsultas extends JFrame {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String USUARIO = "admin";
    private static final String PASSWORD = "admin";
    private static final String COLECCION = "/db/biblioteca/documentos";

    private JTextArea areaResultado;
    private JComboBox<String> comboConsultas;
    private JButton btnEjecutar;

    public VentanaConsultas() {
        super("Consultas XQuery");

        comboConsultas = new JComboBox<>(new String[]{
                "1. Títulos ordenados alfabéticamente",
                "2. Documentos cuyo género es Novela",
                "3. Documentos publicados después de 1950",
                "4. Búsqueda por palabra 'joven' en descripción",
                "5. Mostrar documento con ID = 4",
                "6. Contar el número total de documentos"
        });

        btnEjecutar = new JButton("Ejecutar");
        areaResultado = new JTextArea(15, 50);
        areaResultado.setEditable(false);

        btnEjecutar.addActionListener(e -> ejecutarConsultaSeleccionada());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(comboConsultas, BorderLayout.CENTER);
        panelSuperior.add(btnEjecutar, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void ejecutarConsultaSeleccionada() {
        int indice = comboConsultas.getSelectedIndex();
        String consulta = null;

        switch (indice) {
            case 0:
                consulta = "for $d in /biblioteca/documento order by $d/titulo return $d/titulo";
                break;
            case 1:
                consulta = "for $d in /biblioteca/documento[genero = 'Novela'] return $d/titulo";
                break;
            case 2:
                consulta = "for $d in /biblioteca/documento[fecha_publicacion > xs:date('1950-01-01')] return $d/titulo";
                break;
            case 3:
                consulta = "for $d in /biblioteca/documento[contains(lower-case(descripcion), 'joven')] return $d/titulo";
                break;
            case 4:
                consulta = "for $d in /biblioteca/documento[@id = 4] return $d";
                break;
            case 5:
                consulta = "count(/biblioteca/documento)";
                break;
        }

        if (consulta != null) {
            ejecutarConsultaXQuery(consulta);
        }
    }

    private void ejecutarConsultaXQuery(String consulta) {
        try {
            Class<?> cl = DatabaseImpl.class;
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);

            Collection col = DatabaseManager.getCollection(URI + COLECCION, USUARIO, PASSWORD);

            if (col == null) {
                areaResultado.setText("La colección no existe: " + COLECCION);
                return;
            }

            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet resultado = servicio.query(consulta);
            ResourceIterator iter = resultado.getIterator();

            StringBuilder sb = new StringBuilder();
            while (iter.hasMoreResources()) {
                Resource r = iter.nextResource();
                sb.append(r.getContent().toString()).append("\n");
            }

            areaResultado.setText(sb.toString());

        } catch (Exception e) {
            areaResultado.setText("Error al ejecutar consulta:\n" + e.getMessage());
        }
    }
}