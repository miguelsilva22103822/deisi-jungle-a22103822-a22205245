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
        return null;
    }

    @Override
    public int calcularEnergia(int energia, String dieta, int quantidadeComeu, int numJogada) {
        return 0;
    }
}
