package pt.ulusofona.lp2.deisiJungle;

class Tartaruga extends Especie {
    private final int energiaInicial = 150;
    private final int consumoEnergia = 1;
    private final int energiaDescanso = 5;
    private final String velocidade = "1..3";

    public Tartaruga() {
        super("T", "Tartaruga", "turtle.png");
    }

    public int getEnergiaInicial() {
        return energiaInicial;
    }

    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    public int getEnergiaDescanso() {
        return energiaDescanso;
    }

    public String getVelocidade() {
        return velocidade;
    }
}
