import java.util.HashMap;
import java.util.Scanner;

public class Main {
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
        String filename;
        filename = scan.next();
        System.out.println("Insira o numero da conta: ");
        String conta;
        conta = scan.next();
        HashMap<String, String> field_map = new HashMap<>();
        field_map.put("conta", conta);
        Template template = new Template(filename, field_map);

        System.out.println("TEST FIND: DOCUMENT NAME:");
        filename = scan.next();
        template.find_values(filename);
    }

}
