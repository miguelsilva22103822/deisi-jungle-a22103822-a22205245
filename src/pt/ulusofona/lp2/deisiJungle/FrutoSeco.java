package pt.ulusofona.lp2.deisiJungle;

class FrutoSeco extends Alimento {

    public FrutoSeco() {
        super("f", "Fruto Seco", "frutoSeco.png");
    }


    @Override
    public int getQuantidadeBananas() {
        return 0;
    }

    @Override
    public String getToolTip(int numJogada) {
        return  "Fruto Seco : + 5 energia";
    }

    @Override
    public int calcularEnergia(int energia, String dieta, int quantidadeComeu, int numJogada) {

        if (dieta.equals("h")){
            return energia + 35;
        }

        if (dieta.equals("o")){
            return (int)(energia + (0.1 * energia));
        }

        if (dieta.equals("c")){
            return energia - 15;
        }

        return -1;
    }

}
