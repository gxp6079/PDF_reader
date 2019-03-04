import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, SavedTemplate> templates;

    private static SavedTemplate readTemplates(String type){
        try {
            FileInputStream fis =  new FileInputStream(new File(type + ".ser"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            SavedTemplate temp = (SavedTemplate) ois.readObject();

            return temp;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * load templates from file
     * --prompt user for file and its type
     *
     * if the type(template) already exists:
     *      create a new entry and populate it with the fields
     *      ask for confirmation
     *      if confirmed:
     *          send to DB
     *      else:
     *          fix specified fields
     *          send to DB
     * else:
     *      request for all of the data
     *      make new template
     *      send to DB
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do documento: ");
        String filename = scan.nextLine();
        System.out.println("Insira o tipo do documento: ");
        String type = scan.nextLine();
        SavedTemplate template = readTemplates(type);
        if (template != null) {
            template.find_values(filename);
        }
        else {
            MakeTemplate makeTemplate = new MakeTemplate(type, filename);
            Entry ent = new Entry(false, false, false);
            for(String item : ent.getItems()){
                System.out.println("Insira " + item);
                String toGet = scan.nextLine();
                makeTemplate.add_field(item, toGet);
            }
            /*
            System.out.println("Insira " + ent.getItems().get(3));
            String toGet = scan.nextLine();
            makeTemplate.add_field(ent.getItems().get(3), toGet);
            */
            makeTemplate.save();
        }
    }


}
