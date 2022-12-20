package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;

class Erva extends Alimento {

    public Erva() {
        super("e", "Erva", "grass.png");
    }


    @Override
    public String getToolTip() {
        return "Erva : +- 20 energia";
    }

    @Override
    public int calcularEnergia(int energia , String dieta,int quantidadeComeu, int numJogada) {

        if (dieta.equals("h")){
            return energia + 20;
        }

        if (dieta.equals("o")){
            return energia + 20;
        }

        if (dieta.equals("c")){
            return energia - 20;
        }

        return -1;
    }

    @Override
    public void estragarCarne(int numJogada) {}

}
