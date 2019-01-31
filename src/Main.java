import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, Template> templates;


    private static void writeTemplate(Template template){
        try{
            FileOutputStream fos = new FileOutputStream(new File("~/Desktop/Brit/PDF_reader/templates.ser"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(template);

            oos.close();
            fos.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void readTemplates(){
        try {
            FileInputStream fis =  new FileInputStream(new File("~/Desktop/Brit/PDF_reader/templates.ser"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            Template temp = (Template) ois.readObject();
            while(temp != null){
                templates.put(temp.getType(), temp);
                temp = (Template) ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        templates = new HashMap<>();
        readTemplates();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do documento: ");
        String filename = scan.next();
        System.out.println("Insira o tipo do documento: ");
        String type = scan.next();
        if(templates.keySet().contains(type)){
            templates.get(type).find_values(filename);
        }
        else {
            System.out.println("Insira o numero da conta: ");
            String conta;
            conta = scan.next();
            HashMap<String, String> field_map = new HashMap<>();
            field_map.put("conta", conta);
            Template template = new Template(type, filename, field_map);
            templates.put(type, template);
            writeTemplate(template);
        }
    }


}
