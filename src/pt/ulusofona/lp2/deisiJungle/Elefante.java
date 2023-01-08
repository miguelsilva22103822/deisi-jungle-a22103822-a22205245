package pt.ulusofona.lp2.deisiJungle;

class Elefante extends Especie {

    public Elefante() {
        super("E", "Elefante", "elephant.png");
        energiaInicial = 180;
        consumoEnergia = 4;
        energiaDescanso = 10;
        velocidadeMin = 1;
        velocidadeMax = 6;
    }

    @Override
    public String getDieta() {
        return "h";
    }

}
