package pt.ulusofona.lp2.deisiJungle;

class Passaro extends Especie {
    private final int energiaInicial = 70;
    private final int consumoEnergia = 4;
    private final int energiaDescanso = 50;
    private final int[] velocidade = new int[]{5, 6};

    public Passaro() {
        super("P", "Pássaro", "bird.png");
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
