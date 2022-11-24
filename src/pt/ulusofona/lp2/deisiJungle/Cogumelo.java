package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Cogumelo extends Alimento {
    private final String toolTip = "Cogumelo Magico: +- <N>% energia";

    public Cogumelo() {
        super("m", "Cogumelo","mushroom.png");
    }

    public Cogumelo(String id, String nome, String imagem) {
        super(id, nome, imagem);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
