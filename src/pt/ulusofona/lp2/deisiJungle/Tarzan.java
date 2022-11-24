package pt.ulusofona.lp2.deisiJungle;

class Tarzan extends Especie {
    private final int energiaInicial = 70;
    private final int consumoEnergia = 2;
    private final int energiaDescanso = 20;
    private final int[] velocidade = new int[]{1, 2, 3, 4, 5, 6};

    public Tarzan() {
        super("Z", "Tarzan","tarzan.png");
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
