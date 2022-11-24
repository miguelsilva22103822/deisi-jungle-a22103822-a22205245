package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

class Leao extends Especie {
    public Leao(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Leao(String id, String nome) {
        super(id, nome);
    }

    public int getEnergiaInicial() {
        return 80;
    }

    public int getConsumoEnergia() {
        return 2;
    }

    public int getEnergiaDescanso() {
        return 10;
    }

    public IntStream getVelocidade() {
        return IntStream.range(4, 6);
    }
}
