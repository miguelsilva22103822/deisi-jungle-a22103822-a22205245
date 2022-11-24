package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Carne extends Alimento {
    private final String toolTip = "Carne : +- 50 energia : <N> jogadas";

    public Carne() {
        super("c", "Carne", "meat.png");
    }

    public Carne(String id, String nome, String imagem) {
        super(id, nome, imagem);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
