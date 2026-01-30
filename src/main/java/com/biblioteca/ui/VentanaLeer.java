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

public class VentanaLeer extends JFrame {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String USUARIO = "admin";
    private static final String PASSWORD = "admin";
    private static final String COLECCION = "/db/biblioteca/documentos";

    private JTextArea areaTexto;

    public VentanaLeer() {
        super("Documentos en la biblioteca");

        areaTexto = new JTextArea(20, 50);
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        add(scroll, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        cargarDocumentos();
    }

    private void cargarDocumentos() {
        try {
            Class<?> cl = DatabaseImpl.class;
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);

            Collection col = DatabaseManager.getCollection(URI + COLECCION, USUARIO, PASSWORD);

            if (col == null) {
                areaTexto.setText("La colección no existe: " + COLECCION);
                return;
            }

            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            String consulta = "for $d in /biblioteca/documento return concat($d/@id, ' - ', $d/titulo/text())";

            ResourceSet resultado = servicio.query(consulta);
            ResourceIterator iter = resultado.getIterator();

            StringBuilder sb = new StringBuilder();
            while (iter.hasMoreResources()) {
                Resource r = iter.nextResource();
                sb.append(r.getContent().toString()).append("\n");
            }

            areaTexto.setText(sb.toString());

        } catch (Exception e) {
            areaTexto.setText("Error al leer documentos:\n" + e.getMessage());
        }
    }
}