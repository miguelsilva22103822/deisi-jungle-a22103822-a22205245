package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

class Tarzan extends Especie {
    public Tarzan(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Tarzan(String id, String nome) {
        super(id, nome);
    }

    public int getEnergiaInicial() {
        return 70;
    }

    public int getConsumoEnergia() {
        return 2;
    }

    public int getEnergiaDescanso() {
        return 20;
    }

    public IntStream getVelocidade() {
        return IntStream.range(1, 6);
    }
}
