package pt.ulusofona.lp2.deisiJungle;

class Carne extends Alimento {

    boolean carneEstragada = false;

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
            if (carneEstragada) { //12 jogadas depois carne passa ser toxica, ent é /2 da energia atual
                return energia / 2;
            }
            return energia + 50;
        }

        return -1;

    }


}
