package pt.ulusofona.lp2.deisiJungle;

class Passaro extends Especie {

    public Passaro() {
        super("P", "Passaro", "bird.png");
        energiaInicial = 70;
        consumoEnergia = 4;
        energiaDescanso = 50;
        velocidadeMin = 5;
        velocidadeMax = 6;
    }

    @Override
    public String getDieta() {
        return "o";
    }

}
