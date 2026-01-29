package com.biblioteca.db;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class ValidadorXML {

    public static boolean validar(String xml, String xsd) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xml)));

            System.out.println("Validación correcta del XML.");
            return true;

        } catch (Exception e) {
            System.out.println("Error de validación del XML:");
            System.out.println(e.getMessage());
            return false;
        }
    }
}