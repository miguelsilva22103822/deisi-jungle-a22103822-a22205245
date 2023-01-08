package pt.ulusofona.lp2.deisiJungle;

class Carne extends Alimento {
    private final int energiaParaMetade = 2;
    private final int energiaMenorDeNrJogada = 50;


    public Carne() {
        super("c", "Carne", "meat.png");
    }

    @Override
    public String getToolTip(int numJogada) {
        if (numJogada > 12 ) {
            return "Carne toxica";
        }
        return "Carne : + 50 energia : " + numJogada + " jogadas";
    }


    @Override
    public int calcularEnergia(int energia, String dieta, int quantidadeComeu, int numJogada) {

        if (dieta.equals("h")){
            return energia;
        }

        if (dieta.equals("o") || dieta.equals("c")){
            if (numJogada > 12 ) { //12 jogadas depois carne passa ser toxica, ent é /2 da energia atual
                return energia / energiaParaMetade;
            }
            return energia + energiaMenorDeNrJogada;
        }

        return -1;

    }

    @Override
    public String[] getSaveInfo() {
        return new String[] {"Carne"};
    }

}
