package pt.ulusofona.lp2.deisiJungle;

class Agua extends Alimento {

    public Agua() {
        super("a","Agua","water.png");
    }


    @Override
    public int getQuantidadeBananas() {
        return 0;
    }

    @Override
    public String getToolTip(int numJogada) {
        return "Agua : + 15U|20% energia";
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



}
