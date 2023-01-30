package pt.ulusofona.lp2.deisiJungle;

class FrutoSeco extends Alimento {

    private final int energiaHerbivoro = 35;
    private final double energiaOmnivoro = 0.1;
    private final int energiaCarnivoro = 15;


    public FrutoSeco() {
        super("f", "Fruto Seco", "frutoSeco.png");
    }

    @Override
    public String getToolTip(int numJogada) {
        return  "Fruto Seco : + 5 energia";
    }

    @Override
    public int calcularEnergia(int energia, String dieta, int quantidadeComeu, int numJogada) {

        if (dieta.equals("nuv")){
            return energia - 2;
        }

        if (dieta.equals("h")){
            return energia + energiaHerbivoro;
        }

        if (dieta.equals("o")){
            return (int)(energia + (energiaOmnivoro * energia));
        }

        if (dieta.equals("c")){
            return energia - energiaCarnivoro;
        }

        return -1;
    }

    @Override
    public String[] getSaveInfo() {
        return new String[] {"Fruto Seco"};
    }

}
