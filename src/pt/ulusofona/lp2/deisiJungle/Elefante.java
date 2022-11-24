package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

class Elefante extends Especie {
    //private final int energiaInicial = 180;
    //private final int consumoEnergia = 4;
    //private final int energiaDescanso = 10;
    //private final IntStream velocidade = IntStream.range(1, 6);

    public Elefante(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Elefante(String id, String nome) {
        super(id, nome);
    }

    public int getEnergiaInicial() {
        return 180;
    }

    public int getConsumoEnergia() {
        return 4;
    }

    public int getEnergiaDescanso() {
        return 10;
    }

    public IntStream getVelocidade() {
        return IntStream.range(1, 6);
    }
}
