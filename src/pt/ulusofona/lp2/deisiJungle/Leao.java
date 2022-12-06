package pt.ulusofona.lp2.deisiJungle;

class Leao extends Especie {
    private final int energiaInicial = 80;
    private final int consumoEnergia = 2;
    private final int energiaDescanso = 10;
    private final String velocidade = "4..6";

    public Leao() {
        super("L", "Leão", "lion.png");
    }

    @Override
    public String getDieta() {
        return "c";
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
