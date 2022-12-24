package pt.ulusofona.lp2.deisiJungle;

class Passaro extends Especie {
    private final int energiaInicial = 70;
    private final int consumoEnergia = 4;
    private final int energiaDescanso = 50;
    private final String velocidade = "5..6";

    public Passaro() {
        super("P", "Passaro", "bird.png");
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
