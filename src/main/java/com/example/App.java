package com.example;
import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.*;
import technology.tabula.extractors.BasicExtractionAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class App {
    public static void main(String[] args) {
        try {
            // Cargar el archivo PDF
            File file = new File("C:\\\\\\\\Users\\\\\\\\johnm\\\\\\\\OneDrive\\\\\\\\Documentos\\\\\\\\programacion\\\\\\\\prueba-pdf\\\\\\\\nuevo4-1.pdf");
            PDDocument document = PDDocument.load(file);

            // Crear un objeto ObjectExtractor
            ObjectExtractor oe = new ObjectExtractor(document);
            PageIterator iterator = oe.extract();

            // Crear un objeto BasicExtractionAlgorithm
            BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();

            // Iterar sobre las páginas
            while (iterator.hasNext()) {
                Page page = iterator.next();

                // Extraer las tablas de la página
                List<Table> tables = bea.extract(page);

                // Iterar sobre las tablas
                for (Table table : tables) {
                    // Iterar sobre las filas de la tabla
                    for (List<RectangularTextContainer> row : table.getRows()) {
                        StringBuilder sb = new StringBuilder();
                        // Iterar sobre las celdas de la fila
                        for (RectangularTextContainer cell : row) {
                            sb.append(cell.getText()).append(",");
                        }
                        String finalText = sb.toString().replace(",,", "::").replace(",", ":").replace("::", ",,");
                        System.out.println(finalText);
                    }
                }
            }

            // Cerrar el documento PDF
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

