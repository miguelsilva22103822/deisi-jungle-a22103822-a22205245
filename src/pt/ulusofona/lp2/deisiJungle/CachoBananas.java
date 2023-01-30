package pt.ulusofona.lp2.deisiJungle;

class CachoBananas extends Alimento {
    private int  quantidadeBananas = 3;
    private final int energiaBanana = 40;


    public CachoBananas() {
        super("b", "Bananas", "bananas.png");
    }


    @Override
    public int getQuantidadeBananas() {
        return quantidadeBananas;
    }

    @Override
    public String getToolTip(int numJogada) {

        return "Bananas : " + quantidadeBananas + " : + 40 energia";
    }

    @Override
    public int calcularEnergia(int energia, String dieta, int quantidadeComeu, int numJogada) {

        if (dieta.equals("nuv")){
            return energia - 2 ;
        }

        if (!dieta.equals("h") && !dieta.equals("o") && !dieta.equals("c")){
            return -1;
        }


        if (quantidadeBananas == 0){
            return energia;
        }

        if(quantidadeComeu < 1) {
            --quantidadeBananas;
            return energia + energiaBanana;
        }

        --quantidadeBananas;
        return energia - energiaBanana;
    }

    @Override
    public String[] getSaveInfo() {
        return new String[] {"Bananas", String.valueOf(quantidadeBananas)};
    }

}
