package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

class Mapa {
    ArrayList<Casa> casas;
    int tamanhoMax;

    public Mapa(int nrCasas) {
        this.casas = new ArrayList<>();
        for (int i = 0; i < nrCasas; i++) {
            this.casas.add(new Casa());
        }
        this.tamanhoMax = nrCasas;

    }

    public void initializeMap(String[][] playersInfo, int initialEnergy) {

        for (String[] player : playersInfo) {
            casas.get(0).addJogador(Integer.parseInt(player[0]), player[1], player[2], initialEnergy);
        }
    }

    public int getNrCasas() {
        return tamanhoMax;
    }

    public Casa getCasa(int nrCasa) {

        //nrCasa--; idk anymore
        return casas.get(nrCasa);
    }

}