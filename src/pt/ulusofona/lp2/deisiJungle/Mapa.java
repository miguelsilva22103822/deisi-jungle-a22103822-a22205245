package pt.ulusofona.lp2.deisiJungle;

class Mapa {
    Casa[] casas;

    public Mapa(int nrCasas) {
        this.casas = new Casa[nrCasas];
    }

    public void initializeMap(String[][] playersInfo, int initialEnergy) {
        casas[0] = new Casa();

        for (String[] player : playersInfo) {
            casas[0].addJogador(Integer.parseInt(player[0]), player[1], Integer.parseInt(player[2]),  initialEnergy);
        }

    }

}