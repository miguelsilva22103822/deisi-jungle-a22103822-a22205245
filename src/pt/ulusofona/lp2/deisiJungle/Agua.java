package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Agua extends Alimento {
    private final String toolTip = "Agua : + 10U|20% energia";

    public Agua(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Agua(String id, String nome) {
        super(id, nome);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }
}
