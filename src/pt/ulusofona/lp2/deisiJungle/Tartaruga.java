package pt.ulusofona.lp2.deisiJungle;

class Tartaruga extends Especie {

    public Tartaruga() {
        super("T", "Tartaruga", "turtle.png");
        energiaInicial = 150;
        consumoEnergia = 1;
        energiaDescanso = 5;
        velocidadeMin = 1;
        velocidadeMax = 3;
    }

    @Override
    public String getDieta() {
        return "o";
    }

}
