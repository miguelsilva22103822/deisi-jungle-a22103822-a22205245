package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class CachoBananas extends Alimento {
    private final String toolTip = "Bananas : <N> : + 40 energia";
    private int  quantidadeBananas = 3;

    public CachoBananas() {
        super("b", "Bananas", "bananas.png");
    }


    @Override
    public String getToolTip() {
        return toolTip;
    }

    @Override
    public int calcularEnergia(int energia, String dieta, int quantidadeComeu, int numJogada) {

        if (!dieta.equals("h") && !dieta.equals("o") && !dieta.equals("c")){
            return -1;
        }

        if (quantidadeBananas == 0){
            return energia;
        }

        if(quantidadeComeu < 1) {
            --quantidadeBananas;
            return energia + 40;
        }

        --quantidadeBananas;
        return energia - 40;

    }

    @Override
    public void estragarCarne(int numJogada) {}


}
