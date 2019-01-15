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

    /**
     * Constructor, requires all the information that should be present in every document
     * @param conta
     * @param nome
     * @param ativo
     * @param descricao
     * @param valor_bruto
     * @param imposto
     * @param valor_liquido
     */
    public Entry(String conta, String nome, String ativo, String descricao, String valor_bruto, String imposto, String valor_liquido){
        this.conta = conta;
        this.nome = nome;
        this.ativo = ativo;
        this.descricao = descricao;
        this.valor_bruto = valor_bruto;
        this.imposto = imposto;
        this.valor_liquido = valor_liquido;
    }

    /**
     * Setter for data_emissao, value that may not be present in some files
     * @param data_emissao
     */
    public void setData_emissao(String data_emissao) {
        this.data_emissao = data_emissao;
    }

    /**
     * Setter for data_vencimento, value that may not be present in some files
     * @param data_vencimento
     */
    public void setData_vencimento(String data_vencimento){
        this.data_vencimento = data_vencimento;
    }

    /**
     * Setter for CNPJ, value that may not be present in some files
     * @param CNPJ
     */
    public void setCNPJ(String CNPJ){
        this.CNPJ = CNPJ;
    }

    /**
     * Method to send the fields to a database after it has been checked with the user
     */
    public void sendToDB() {
    }
}
