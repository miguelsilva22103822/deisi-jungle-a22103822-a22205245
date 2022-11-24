package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Cogumelos extends Alimento {
    private final String toolTip = "Cogumelo Magico: +- <N>% energia";

    public Cogumelos(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Cogumelos(String id, String nome) {
        super(id, nome);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
