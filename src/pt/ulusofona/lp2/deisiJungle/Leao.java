package pt.ulusofona.lp2.deisiJungle;

class Leao extends Especie {
    private final int energiaInicial = 80;
    private final int consumoEnergia = 2;
    private final int energiaDescanso = 10;
    private final int[] velocidade = new int[]{4, 5, 6};

    public Leao() {
        super("L", "Leão", "lion.png");
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
