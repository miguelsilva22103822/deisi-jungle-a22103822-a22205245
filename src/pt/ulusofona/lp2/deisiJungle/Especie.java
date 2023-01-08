package pt.ulusofona.lp2.deisiJungle;

abstract class Especie {
    private final String id;
    private final String nome;
    private final String nomeImagem;
    protected int energiaInicial;
    protected int consumoEnergia;
    protected int energiaDescanso;
    protected int velocidadeMin;
    protected int velocidadeMax;

    public Especie(String id, String nome, String nomeImagem) {

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

    public String getNomeImagem() {
        return nomeImagem;
    }

    String[] getInfo() {

        String[] info = new String[7];
        info[0] = getId();
        info[1] = getNome();
        info[2] = getNomeImagem();
        info[3] = String.valueOf(getEnergiaInicial());
        info[4] = String.valueOf(getConsumoEnergia());
        info[5] = String.valueOf(getEnergiaDescanso());
        info[6] = getVelocidade();

        return info;
    }

    abstract public String getDieta();

    public int getEnergiaInicial() {
        return energiaInicial;
    }

    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    public int getEnergiaDescanso() {
        return energiaDescanso;
    }

    public int getVelocidadeMin() {
        return velocidadeMin;
    }

    public int getVelocidadeMax() {
        return velocidadeMax;
    }

    public String getVelocidade() {
        return velocidadeMin + ".." + velocidadeMax;
    }
}