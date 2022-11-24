package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

class Tartaruga extends Especie {
    public Tartaruga(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Tartaruga(String id, String nome) {
        super(id, nome);
    }

    public int getEnergiaInicial() {
        return 150;
    }

    public int getConsumoEnergia() {
        return 1;
    }

    public int getEnergiaDescanso() {
        return 5;
    }

    public IntStream getVelocidade() {
        return IntStream.range(1, 3);
    }
}
