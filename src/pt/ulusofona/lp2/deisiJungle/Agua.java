package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Agua extends Alimento {
    private final String toolTip = "Agua : + 10U|20% energia";

    public Agua() {
        super("a","Agua","water.png");
    }

    public Agua(String id, String nome, String imagem) {
        super(id, nome, imagem);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
