package pt.ulusofona.lp2.deisiJungle;

class Tarzan extends Especie {
    private final int energiaInicial = 70;
    private final int consumoEnergia = 2;
    private final int energiaDescanso = 20;
    private final String velocidade = "1..6";

    public Tarzan() {
        super("Z", "Tarzan","tarzan.png");
    }

    @Override
    public String getDieta() {
        return "o";
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
