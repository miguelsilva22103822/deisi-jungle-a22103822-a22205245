package pt.ulusofona.lp2.deisiJungle;

class Erva extends Alimento {

    public Erva() {
        super("e", "Erva", "grass.png");
    }


    @Override
    public String getToolTip(int numJogada) {
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


}
