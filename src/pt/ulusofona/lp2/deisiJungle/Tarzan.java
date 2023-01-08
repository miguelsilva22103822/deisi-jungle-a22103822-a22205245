package pt.ulusofona.lp2.deisiJungle;

class Tarzan extends Especie {

    public Tarzan() {
        super("Z", "Tarzan","tarzan.png");
        energiaInicial = 70;
        consumoEnergia = 2;
        energiaDescanso = 20;
        velocidadeMin = 1;
        velocidadeMax = 6;
    }

    @Override
    public String getDieta() {
        return "o";
    }

}
