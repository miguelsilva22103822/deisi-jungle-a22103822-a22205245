package pt.ulusofona.lp2.deisiJungle;

import java.awt.image.BufferedImage;
import java.util.Random;

class Cogumelo extends Alimento {
    private final String toolTip = "Cogumelo Magico: +- <N>% energia";

    public Cogumelo() {
        super("m", "Cogumelo","mushroom.png");
    }

    public Cogumelo(String id, String nome, String imagem) {
        super(id, nome, imagem);
    }

    @Override
    public String getToolTip() {
        return toolTip;
    }

    @Override
    public int calcularEnergia(int energia, String dieta,int quantidadeComeu, int numJogada) {

        if (!dieta.equals("h") && !dieta.equals("o") && !dieta.equals("c")){
            return -1;
        }

        Random numeroAleatorio = new Random();
        int numEscolhido = numeroAleatorio.nextInt(10,49);

        if (numJogada % 2 == 0) {
            return energia + (numEscolhido * energia);
        }

        return energia - (numEscolhido * energia);
    }

    @Override
    public void estragarCarne(int numJogada) {
    }

}
