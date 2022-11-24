package pt.ulusofona.lp2.deisiJungle;

abstract class Especie {
    private final String id;
    private final String nome;
    private final String nomeImagem;

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

    public String[] getInfo() {

        String[] info = new String[3];
        info[0] = getId();
        info[1] = getNome();
        info[2] = getNomeImagem();

        return info;
    }


    abstract public int getEnergiaInicial();

    abstract public int getConsumoEnergia();

    abstract public int getEnergiaDescanso();

    abstract public int[] getVelocidade();



    // não sei se se pode usar intStream
    //não sei se as classes podem ter constantes mágicas
}