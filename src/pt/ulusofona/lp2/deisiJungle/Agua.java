package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Agua extends Alimento {
    private final String toolTip = "Agua : + 10U|20% energia";

    public Agua() {
        super("a","Agua","water.png");
    }


    @Override
    public String getToolTip() {
        return toolTip;
    }

    @Override
    public int calcularEnergia(int energia, String dieta,int quantidadeComeu, int numJogada) {

        if (dieta.equals("h")){
            return energia + 15;
        }

        if (dieta.equals("o")){
            return (int)(energia + (0.2 * energia));
        }

        if (dieta.equals("c")){
            return energia + 15;
        }

        return -1;
    }

    @Override
    public void estragarCarne(int numJogada) {}

}
