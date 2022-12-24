package pt.ulusofona.lp2.deisiJungle;


abstract class Alimento {
    private final String id;
    private final String nome;
    private final String nomeImagem;

    public Alimento(String id, String nome, String nomeImagem) {
        this.id = id;
        this.nome = nome;
        this.nomeImagem = nomeImagem;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem (){
        return nomeImagem;
    }

    public String[] getFoodInfo() {

        String[] info = new String[3];
        info[0] = getId();
        info[1] = getNome();
        info[2] = getImagem();

        return info;
    }

    public boolean eCarne () {
        return id.equals("c");

    }

    abstract public int getQuantidadeBananas ();

    abstract public String getToolTip(int numJogada);

    abstract public int calcularEnergia(int energia , String dieta, int quantidadeComeu, int numJogada);



}
