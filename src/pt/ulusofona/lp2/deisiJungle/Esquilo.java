package pt.ulusofona.lp2.deisiJungle;

class Esquilo  extends Especie {
    private final int energiaInicial = 120;
    private final int consumoEnergia = 8;
    private final int energiaDescanso = 15;
    private final String velocidade = "2..6";

    public Esquilo() {
        super("Q", "Esquilo", "esquilo.png");
    }



    @Override
    public String getDieta() {
        return "h";
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
