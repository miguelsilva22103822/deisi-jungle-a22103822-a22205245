package pt.ulusofona.lp2.deisiJungle;

class Esquilo  extends Especie {

    public Esquilo() {
        super("Q", "Esquilo", "esquilo.png");
        energiaInicial = 120;
        consumoEnergia = 8;
        energiaDescanso = 40;
        velocidadeMin = 2;
        velocidadeMax = 6;
    }

    @Override
    public String getDieta() {
        return "h";
    }

}
