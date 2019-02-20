import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entry {
    private String conta;
    private String nome;
    private String ativo;
    private String descricao;
    private String data_emissao; // may not have it
    private String data_vencimento; // may not have it
    private String CNPJ; // may not have it
    private String valor_bruto;
    private String imposto;
    private String valor_liquido;
    private  List<String> items;
    private HashMap<String, Field> fields;

    public Entry(boolean hasDataEmissao, boolean hasDataVencimento,
                 boolean hasCNPJ){
        this.items = new ArrayList<>();
        items.add("conta");
        items.add("nome");
        items.add("ativo");
        items.add("descricao");
        items.add("valor bruto");
        items.add("imposto");
        items.add("valor liquido");
        if(hasDataEmissao){
            items.add("Data de emissāo");
        }
        if(hasDataVencimento){
            items.add("Data de vencimento");
        }
        if(hasCNPJ){
            items.add("CNPJ");
        }

    }

    public List<String> getItems(){
        return items;
    }

    /**
     * Method to send the fields to a database after it has been checked with the user
     */
    public void sendToDB() {
    }
}
