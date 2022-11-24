package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class CachoBananas extends Alimento {
    private final String toolTip = "Bananas : <N> : + 40 energia";

    public CachoBananas() {
        super("b", "CachoBananas", "bananas.png");
    }

    public CachoBananas(String id, String nome, String imagem) {
        super(id, nome, imagem);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
