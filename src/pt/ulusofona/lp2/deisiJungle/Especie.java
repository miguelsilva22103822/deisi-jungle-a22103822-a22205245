package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Especie {
    String id;
    String nome;
    BufferedImage imagem;

    public Especie(String id, String nome, BufferedImage imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public Especie(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}