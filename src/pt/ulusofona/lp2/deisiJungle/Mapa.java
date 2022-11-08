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

        getCasa(nrCasas).setAsMeta();
    }
    //não respeita principios de encapsulamento
    public void initializeMap(String[][] playersInfo, int initialEnergy) {

        for (String[] player : playersInfo) {
            getCasa(1).addJogador(Integer.parseInt(player[0]));
        }
    }

    public int getNrCasas() {
        return tamanhoMax;
    }
    //não respeita principios de encapsulamento
    public Casa getCasa(int nrCasa) {
        nrCasa--;
        return casas.get(nrCasa);
    }

    public int findNrCasaContaining(int playerID) {
        for (int i = 0; i < casas.size(); i++) {
            if (casas.get(i).containsJogador(playerID)) {
                return i + 1;
            }
        }
        return -1;
    }

    public int nrJogadoresCasa(int nrCasa) {
        return casas.get(nrCasa - 1).nrJogadores();
    }

}