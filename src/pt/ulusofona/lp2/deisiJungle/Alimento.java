package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

abstract class Alimento {
    private final String id;
    private final String nome;
    private BufferedImage imagem;

    public Alimento(String id, String nome, BufferedImage imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public Alimento(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    abstract public String getToolTip();
}
