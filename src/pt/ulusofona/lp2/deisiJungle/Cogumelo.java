package pt.ulusofona.lp2.deisiJungle;

import java.util.Random;

class Cogumelo extends Alimento {

    private final int numGerado;

    public Cogumelo() {
        super("m", "Cogumelo Magico","mushroom.png");

        Random numeroAleatorio = new Random();

        numGerado = numeroAleatorio.nextInt(10,50); //será 9 a 50? ou 9 a 51?, ou 10 a 51?
    }

    @Override
    public int getQuantidadeBananas() {
        return -1;
    }

    @Override
    public String getToolTip(int numJogada) {
        return  "Cogumelo Magico : +-" + numGerado + " % energia";
    }

    @Override
    public int calcularEnergia(int energia, String dieta,int quantidadeComeu, int numJogada) {

        if (!dieta.equals("h") && !dieta.equals("o") && !dieta.equals("c")){
            return -1;
        }

        if (numJogada % 2 != 0) {

            return  energia + (int) ((numGerado / 100.0) * energia);
        }

        return energia -  (int) ((numGerado / 100.0) * energia);

    }

    @Override
    public String[] getSaveInfo() {
        return new String[] {"Cogumelo Magico", String.valueOf(numGerado)};
    }


}
