package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Erva extends Alimento {
    private final String toolTip = "Erva : +- 20 energia";

    public Erva(String id, String nome, BufferedImage imagem) {
        super(id, nome, imagem);
    }

    public Erva(String id, String nome) {
        super(id, nome);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }


}
