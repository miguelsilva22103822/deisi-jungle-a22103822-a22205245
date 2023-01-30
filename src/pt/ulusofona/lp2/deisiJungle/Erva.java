package pt.ulusofona.lp2.deisiJungle;

class Erva extends Alimento {

    private final int energiaErva = 20;

    public Erva() {
        super("e", "Erva", "grass.png");
    }

    @Override
    public String getToolTip(int numJogada) {
        return "Erva : +- 20 energia";
    }

    @Override
    public int calcularEnergia(int energia , String dieta,int quantidadeComeu, int numJogada) {

        if (dieta.equals("nuv")){
            return energia - 2 ;
        }

        if (dieta.equals("h") || dieta.equals("o")){
            return energia + energiaErva;
        }

        if (dieta.equals("c")){
            return energia - energiaErva;
        }

        return -1;
    }

    @Override
    public String[] getSaveInfo() {
        return new String[] {"Erva"};
    }

}
