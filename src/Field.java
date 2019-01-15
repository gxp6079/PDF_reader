import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Field {
    private ArrayList lines;
    private ArrayList cols;
    private String name;

    public Field(String name, String filename, String value){
        this.name = name;
        find(filename, value);
    }

    public ArrayList getCols() {
        return cols;
    }

    public ArrayList getLines() {
        return lines;
    }

    public void find(String filename, String value){
        File file =  new File(filename);
        String text;
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String[] rows = text.split("\n");
        ArrayList<String[]> strings = new ArrayList<>();
        for(int i = 0 ; i < rows.length ; i++){
            String row = rows[i];
            strings.add(i, row.split(" "));
        }
        for(int row = 0 ; row < rows.length ; row ++){
            for(int col = 0 ; col < strings.size() ; col ++){
                if(strings.get(col)[row] == value){
                    getCols().add(col);
                    getLines().add(row);
                }
            }
        }
    }

    public void addNew(int line, int col){
        lines.add(line);
        cols.add(col);
    }
}
