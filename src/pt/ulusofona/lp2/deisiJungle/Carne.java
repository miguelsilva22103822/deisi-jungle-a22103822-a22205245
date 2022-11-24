package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Carne extends Alimento {
    private final String toolTip = "Carne : +- 50 energia : <N> jogadas";

    public Carne(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Carne(String id, String nome) {
        super(id, nome);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
