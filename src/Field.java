import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Field implements Serializable {
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
        String value = strings.get(lines.get(0))[cols.get(0)];

        for (int i = 1; i < lines.size(); i++) {
            String curr = strings.get(lines.get(i))[cols.get(i)];
            String common = stringCompare(value, curr);

            value = common;
        }

        //String value = strings.get(line_num)[col_num];

        return value;
    }


    public static String stringCompare(String word1, String word2) {
        if (word1.contains(word2)) {
            return word2;
        } else if (word2.contains(word1)) {
            return word1;
        }

        int start1 = 0;
        int end1 = 1;
        int start2 = word2.length()-1;
        int end2 = word2.length();
        int iter = 1;
        int size_min = min(word1.length(), word2.length());
        int size_max = max(word1.length(), word2.length());

        String complete = "";
        while (end2 != 0) {
            String currMatch = "";
            String split1 = word1.substring(start1, end1);
            String split2 = word2.substring(start2, end2);

            for (int i = 0; i < split1.length(); i++) {
                if (split1.charAt(i) == split2.charAt(i)) currMatch += split1.charAt(i);
            }
            if(word1.length() > word2.length()){
                start1 = ((iter >= size_min) ? iter - (size_min - 1) : 0);
                end2 = word2.length() - (((iter >= size_max) ? iter - (size_max - 1) : 0));
            }
            else{
                start1 = ((iter >= size_max) ? iter - (size_max - 1) : 0);
                end2 = word2.length() - (((iter >= size_min) ? iter - (size_min - 1) : 0));
            }
            end1 += (iter >= word1.length()) ? 0 : 1;
            start2 -= (start2 > 0) ? 1 : 0;
            iter++;
            complete = (complete.length() > currMatch.length()) ? complete : currMatch;
        }


        return complete;
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
