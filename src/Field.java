import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Field implements Serializable {
    private ArrayList<Integer> lines;
    private ArrayList<Integer> cols;
    private ArrayList<String> followed;
    public final String NAME;

    public Field(String name, String value, ArrayList<String[]> file_content){
        this.NAME = name;
        this.cols = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.followed = new ArrayList<>();
        init_location(file_content, value);
    }

    private ArrayList getCols() {
        return cols;
    }

    private ArrayList getLines() {
        return lines;
    }


    public String find(ArrayList<String[]> strings) {
        String value = strings.get(lines.get(0))[cols.get(0)];

        for (int f = cols.get(0) + 1 ; f < strings.get(lines.get(0)).length ; f++){
            if(!followed.contains(strings.get(lines.get(0))[f])) {
                value += " " + strings.get(lines.get(0))[f];
            }
            else{
                break;
            }
        }

        for (int i = 1; i < lines.size(); i++) {
            String curr = strings.get(lines.get(i))[cols.get(i)];
            for (int f = cols.get(i) ; f < strings.get(lines.get(i)).length ; f++){
                if(!followed.contains(strings.get(lines.get(i))[f])) {
                    curr += " " + strings.get(lines.get(i))[f];
                }
                else{
                    break;
                }
            }
            String common = stringCompare(value, curr);

            value = common;
        }

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

    private void init_location(ArrayList<String[]> file_content, String value){
        String[] words = value.split(" ");
        int cur = 0;
        int cur_col = 0;
        for(int row = 0 ; row < file_content.size() ; row ++){
            for(int col = 0 ; col < file_content.get(row).length; col ++){
                String debug1 = file_content.get(row)[col];
                String debug2 = words[cur];
                if (file_content.get(row)[col].equals(words[cur])) {
                    if(cur == 0) {
                        cur_col = col;
                    }
                    cur += 1;
                }
                else{
                    cur = 0;
                }
                if(cur == words.length) {
                    addNew(row, cur_col);
                    cur = 0;
                    if(col + 1 < file_content.get(row).length){
                        followed.add(file_content.get(row)[col + 1]);
                    }
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
