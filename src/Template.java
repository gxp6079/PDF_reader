import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public abstract class Template implements Serializable {

    String type;

    Map<String, Field> fields;

    public ArrayList<String[]> load_file(String filename) {
        File file = new File(filename);
        String text;
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String[] rows = text.split("\n");
        ArrayList<String[]> strings = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            strings.add(rows[i].split(" "));
        }
        return strings;
    }
}
