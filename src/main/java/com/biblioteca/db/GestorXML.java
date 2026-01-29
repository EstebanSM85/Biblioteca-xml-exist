package com.biblioteca.db;

import com.biblioteca.model.Documento;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorXML {

    private String rutaXML;

    public GestorXML(String rutaXML) {
        this.rutaXML = rutaXML;
    }

    private Document cargarDocumento() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(rutaXML));
    }

    private void guardarDocumento(Document doc) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(rutaXML)));
    }

    public List<Documento> leerDocumentos() {
        List<Documento> lista = new ArrayList<>();

        try {
            Document doc = cargarDocumento();
            NodeList nodos = doc.getElementsByTagName("documento");

            for (int i = 0; i < nodos.getLength(); i++) {
                Element e = (Element) nodos.item(i);

                int id = Integer.parseInt(e.getAttribute("id"));
                String titulo = e.getElementsByTagName("titulo").item(0).getTextContent();
                String autor = e.getElementsByTagName("autor").item(0).getTextContent();
                String editorial = e.getElementsByTagName("editorial").item(0).getTextContent();
                String fecha = e.getElementsByTagName("fecha_publicacion").item(0).getTextContent();
                String genero = e.getElementsByTagName("genero").item(0).getTextContent();
                String descripcion = e.getElementsByTagName("descripcion").item(0).getTextContent();

                Node contenidoNode = e.getElementsByTagName("contenido").item(0);
                String contenidoXML = contenidoNode.getTextContent().trim();

                lista.add(new Documento(id, titulo, autor, editorial, fecha, genero, descripcion, contenidoXML));
            }

        } catch (Exception ex) {
            System.out.println("Error al leer documentos: " + ex.getMessage());
        }

        return lista;
    }

    public void añadirDocumento(Documento d) {
        try {
            Document doc = cargarDocumento();
            Element raiz = doc.getDocumentElement();

            Element nuevo = doc.createElement("documento");
            nuevo.setAttribute("id", String.valueOf(d.getId()));

            Element t = doc.createElement("titulo");
            t.setTextContent(d.getTitulo());
            nuevo.appendChild(t);

            Element a = doc.createElement("autor");
            a.setTextContent(d.getAutor());
            nuevo.appendChild(a);

            Element ed = doc.createElement("editorial");
            ed.setTextContent(d.getEditorial());
            nuevo.appendChild(ed);

            Element f = doc.createElement("fecha_publicacion");
            f.setTextContent(d.getFechaPublicacion());
            nuevo.appendChild(f);

            Element g = doc.createElement("genero");
            g.setTextContent(d.getGenero());
            nuevo.appendChild(g);

            Element des = doc.createElement("descripcion");
            des.setTextContent(d.getDescripcion());
            nuevo.appendChild(des);

            Element cont = doc.createElement("contenido");
            cont.setTextContent(d.getContenidoXML());
            nuevo.appendChild(cont);

            raiz.appendChild(nuevo);
            guardarDocumento(doc);

            System.out.println("Documento añadido correctamente.");

        } catch (Exception ex) {
            System.out.println("Error al añadir documento: " + ex.getMessage());
        }
    }

    public void eliminarDocumento(int id) {
        try {
            Document doc = cargarDocumento();
            NodeList nodos = doc.getElementsByTagName("documento");

            for (int i = 0; i < nodos.getLength(); i++) {
                Element e = (Element) nodos.item(i);

                if (Integer.parseInt(e.getAttribute("id")) == id) {
                    e.getParentNode().removeChild(e);
                    break;
                }
            }

            guardarDocumento(doc);
            System.out.println("Documento eliminado.");

        } catch (Exception ex) {
            System.out.println("Error al eliminar documento: " + ex.getMessage());
        }
    }
}