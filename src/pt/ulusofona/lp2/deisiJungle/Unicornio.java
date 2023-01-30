package pt.ulusofona.lp2.deisiJungle;

public class Unicornio extends Especie{

    public Unicornio() {
        super("U","Unicórnio","unicorn.png");
        energiaInicial = 200;
        consumoEnergia = 8;
        energiaDescanso = 20;
        velocidadeMin = 3;
        velocidadeMax = 6;
    }

    @Override
    public String getDieta() {
        return "nuv";
    }
}
