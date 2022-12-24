package pt.ulusofona.lp2.deisiJungle;

import java.util.Random;

class Cogumelo extends Alimento {

    private final int numGerado;

    public Cogumelo() {
        super("m", "Cogumelo Magico","mushroom.png");

        Random numeroAleatorio = new Random();

        numGerado = numeroAleatorio.nextInt(10,50);
    }

    @Override
    public int getQuantidadeBananas() {
        return 0;
    }

    @Override
    public String getToolTip(int numJogada) {
        return "Cogumelo Magico: +- " + numGerado + "% energia";
    }

    @Override
    public int calcularEnergia(int energia, String dieta,int quantidadeComeu, int numJogada) {

        if (!dieta.equals("h") && !dieta.equals("o") && !dieta.equals("c")){
            return -1;
        }

        if (numJogada % 2 != 0) {

            return (int) Math.round(energia + (numGerado / 100.0) * energia);
        }

        return (int) Math.round(energia - (numGerado / 100.0) * energia);

    }

}
