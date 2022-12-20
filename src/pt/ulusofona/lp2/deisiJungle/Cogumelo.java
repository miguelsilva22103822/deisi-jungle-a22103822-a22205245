package pt.ulusofona.lp2.deisiJungle;

import java.util.Random;

class Cogumelo extends Alimento {

    private int numGerado;

    public Cogumelo() {
        super("m", "Cogumelo Magico","mushroom.png");

        Random numeroAleatorio = new Random();
        int numEscolhido = numeroAleatorio.nextInt(10,50);

        numGerado = numEscolhido;
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

        if (numJogada % 2 == 0) {
            return (energia + ((numGerado / 100) * energia));
        }

        return (energia - ((numGerado / 100) * energia));
    }

    @Override
    public void estragarCarne(int numJogada) {
    }

}
