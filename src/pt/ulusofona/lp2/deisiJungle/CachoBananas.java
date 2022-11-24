package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class CachoBananas extends Alimento {
    private final String toolTip = "Bananas : <N> : + 40 energia";

    public CachoBananas(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public CachoBananas(String id, String nome) {
        super(id, nome);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
