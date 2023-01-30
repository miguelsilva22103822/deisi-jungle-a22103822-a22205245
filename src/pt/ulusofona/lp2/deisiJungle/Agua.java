package pt.ulusofona.lp2.deisiJungle;

class Agua extends Alimento {
    private final int energiaHerbivoroECcarnivoro = 15;
    private final double energiaOmnivoro = 0.2;


    public Agua() {
        super("a","Agua","water.png");
    }

    @Override
    public String getToolTip(int numJogada) {
        return "Agua : + 15U|20% energia";
    }

    @Override
    public int calcularEnergia(int energia, String dieta,int quantidadeComeu, int numJogada) {

        if (dieta.equals("h") || dieta.equals("c")){
            return energia + energiaHerbivoroECcarnivoro;
        }

        if (dieta.equals("o")){
            return (int)(energia + (energiaOmnivoro * energia));
        }

        return -1;
    }

    @Override
    public String[] getSaveInfo() {
        return new String[] {"Agua"};
    }
}
