import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class SavedTemplate extends Template implements Serializable {

    public SavedTemplate(String type, Map<String, Field> fields) {
        this.type = type;
        this.fields = fields;
    }

    public void find_values(String filename) {
        ArrayList<String[]> strings = super.load_file(filename);
        for (Field f : this.fields.values()) {
            String value = f.find(strings);
            System.out.println(f.NAME + ": " + value);
        }
    }

}
