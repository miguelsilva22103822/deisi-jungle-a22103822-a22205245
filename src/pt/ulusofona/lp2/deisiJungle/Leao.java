package pt.ulusofona.lp2.deisiJungle;

class Leao extends Especie {

    public Leao() {
        super("L", "Leao", "lion.png");
        energiaInicial = 80;
        consumoEnergia = 2;
        energiaDescanso = 10;
        velocidadeMin = 4;
        velocidadeMax = 6;
    }

    @Override
    public String getDieta() {
        return "c";
    }

}
