package pt.ulusofona.lp2.deisiJungle;

class Elefante extends Especie {
    private final int energiaInicial = 180;
    private final int consumoEnergia = 4;
    private final int energiaDescanso = 10;
    private final int[] velocidade = new int[]{1, 2, 3, 4, 5, 6};

    public Elefante() {
        super("E", "Elefante", "elephant.png");
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

    public int[] getVelocidade() {
        return velocidade;
    }
}
