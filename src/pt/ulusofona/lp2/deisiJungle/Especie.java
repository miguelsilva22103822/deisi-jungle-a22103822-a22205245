package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Especie {
    int id;
    String nome;
    BufferedImage imagem;

    public Especie(int id, String nome, BufferedImage imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }
}