import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MakeTemplate extends Template {

    private String filename;

    private ArrayList<String[]> file_content;

    /**
     * For the creation of a Template we need a file and a map to each field name and value
     * it should call create_Field for every field name
     * @param filename
     */
    public MakeTemplate(String type, String filename){
        this.type = type;
        this.fields = new HashMap<>();
        this.filename = filename;
        this.file_content = super.load_file(filename);
    }


    public void add_field(String name, String value) {
        Field f = new Field(name, value, this.file_content);
        this.fields.put(name, f);
    }

    public void save() {
        Template toSave = new SavedTemplate(this.type, this.fields);
        String filename = this.type + ".ser";
        try {
            FileOutputStream fos = new FileOutputStream(new File(filename));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toSave);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }


}
