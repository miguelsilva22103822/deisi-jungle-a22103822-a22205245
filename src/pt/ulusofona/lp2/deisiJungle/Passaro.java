package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

class Passaro extends Especie {
    public Passaro(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Passaro(String id, String nome) {
        super(id, nome);
    }

    public int getEnergiaInicial() {
        return 70;
    }

    public int getConsumoEnergia() {
        return 4;
    }

    public int getEnergiaDescanso() {
        return 50;
    }

    public IntStream getVelocidade() {
        return IntStream.range(5, 6);
    }
}
