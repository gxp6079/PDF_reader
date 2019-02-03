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
        String filename = scan.next();
        System.out.println("Insira o tipo do documento: ");
        String type = scan.next();
        SavedTemplate template = readTemplates(type);
        if (template != null) {
            template.find_values(filename);
        }
        else {
            System.out.println("Insira o numero da conta: ");
            String conta;
            conta = scan.next();
            MakeTemplate makeTemplate = new MakeTemplate(type, filename);
            makeTemplate.add_field("conta", conta);
            makeTemplate.save();
        }
    }


}
