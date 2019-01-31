import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Template implements Serializable {

    private String type;
    private Map<String, Field> fields;

    /**
     * For the creation of a Template we need a file and a map to each field name and value
     * it should call create_Field for every field name
     * @param filename
     * @param fields_map
     */
    public Template(String type, String filename, Map<String, String> fields_map){
        this.type = type;
        this.fields = new HashMap<>();
        create_Fields(filename, fields_map);
    }

    /**
     * Method: create_Field
     * This method finds every occurrence of the field_value and assigns it to a new Field
     * @param filename
     * @param fields_map
     * @return Field
     */
    public Field create_Fields(String filename, Map<String, String> fields_map){
        for(String field_name : fields_map.keySet()){
            Field field = new Field(field_name, filename ,fields_map.get(field_name));
            fields.put(field_name, field);
        }
        return null;
    }

    public String getType() {
        return type;
    }

    /**
     * Method: fix
     * This method finds every occurrence of the field_value and assigns it to the existing field as new
     * possible locations for future documents
     * @param filename
     * @param field_name
     * @param field_value
     */
    public void updateField(String filename, String field_name, String field_value){
        fields.get(field_name).updateLocation(filename, field_value);
    }

    public void find_values(String filename) {
        File file = new File(filename);
        String text;
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String[] rows = text.split("\n");
        ArrayList<String[]> strings = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            strings.add(rows[i].split(" "));
        }
        for (Field f : fields.values()) {
            String value = f.find(strings);
            System.out.println(f.NAME + ": " + value);
        }
    }

}
