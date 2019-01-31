import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Field {
    private ArrayList<Integer> lines;
    private ArrayList<Integer> cols;
    public final String NAME;

    public Field(String name, String filename, String value){
        this.NAME = name;
        this.cols = new ArrayList<>();
        this.lines = new ArrayList<>();
        init_location(filename, value);
    }

    private ArrayList getCols() {
        return cols;
    }

    private ArrayList getLines() {
        return lines;
    }

    public String find(ArrayList<String[]> strings) {

        int line_num = lines.get(0);
        int col_num = cols.get(0);

        String value = strings.get(line_num)[col_num];

        return value;
    }

    private void init_location(String filename, String value){
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
            strings.add(rows[i].split(" "));
        }
        for(int row = 0 ; row < strings.size() ; row ++){
            for(int col = 0 ; col < strings.get(row).length; col ++){
                if(strings.get(row)[col].contains(value)){
                    addNew(row, col);
                }
            }
        }
    }

    public void updateLocation(String filename, String value){

    }

    private void addNew(int line, int col){
       getLines().add(line);
        getCols().add(col);
    }
}
