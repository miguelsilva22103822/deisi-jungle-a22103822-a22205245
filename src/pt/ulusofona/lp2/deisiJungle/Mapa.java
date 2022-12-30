package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;
import java.util.Arrays;

class Mapa {
    private ArrayList<Casa> casas;
    private int tamanhoMax;

    public Mapa(int nrCasas) {
        this.casas = new ArrayList<>();

        for (int i = 0; i < nrCasas; i++) {
            this.casas.add(new Casa());
        }

        this.tamanhoMax = nrCasas;

        casas.get(cIndex(nrCasas)).setAsMeta();
    }

    public void initializeMap(String[][] playersInfo) {

        for (String[] player : playersInfo) {
            casas.get(cIndex(1)).addJogador(Integer.parseInt(player[0]));
        }
    }

    public void initialzeMapFood(String[][] foodsInfo) {
        if (foodsInfo == null) {
            return;
        }

        for (String[] food : foodsInfo) {
            casas.get(cIndex(Integer.parseInt(food[1]))).addAlimento(food[0]);
        }
    }

    public int getNrCasas() {
        return tamanhoMax;
    }

    public int[] getPlayerIds(int nrCasa) {
        return casas.get(cIndex(nrCasa)).getIDsJogadores();
    }

    public String[] getSquareInfo(int nrCasa, int numJogada) {
        return casas.get(cIndex(nrCasa)).getInfo(numJogada);
    }

    public void removeJogadorFromCasa(int idJogador, int nrCasa) {
        casas.get(cIndex(nrCasa)).removeJogador(idJogador);
    }

    public void addPlayerToCasa(int idJogador, int nrCasa) {
        casas.get(cIndex(nrCasa)).addJogador(idJogador);
    }

    public int nrJogadoresInCasa(int nrCasa) {
        return casas.get(cIndex(nrCasa)).nrJogadores();
    }

    public int getJogadorIDMenorInCasa(int nrCasa) {
        return casas.get(cIndex(nrCasa)).jogadorIDMenor();
    }

    public void sortIDsCasa(int nrCasa) {
        casas.get(cIndex(nrCasa)).sortIDs();
    }

    public int findNrCasaContaining(int playerID) {
        for (int i = 0; i < casas.size(); i++) {
            if (casas.get(i).containsJogador(playerID)) {
                return i + 1;
            }
        }
        return -1;
    }

    private int cIndex(int nrCasa) {
        return nrCasa - 1;
    }

    public String getIdAlimentoCasa(int nrCasa) {

        return casas.get(cIndex(nrCasa)).getIDAlimento();
    }

    public Alimento getAlimentoCasa (int nrCasa) {
        return casas.get(cIndex(nrCasa)).getAlimento();
    }

    public String getSaveInfo() {
        StringBuilder text = new StringBuilder();

        for (Casa casa : casas) {
            text.append(Arrays.toString(casa.getSaveInfo()));
            text.append("\n");
        }
        return text.toString();
    }

    public void loadGame(ArrayList<String> casas) {
        this.casas = new ArrayList<>();



        for (int i = 0; i < casas.size(); i++) {
            ArrayList<Integer> iDsJogadores = new ArrayList<>();
            Alimento alimento = null;

            String[] infoCasas = StringUtil.separarString(casas.get(i));
            String nomeAlimento = infoCasas[0];

            switch (nomeAlimento) {
                case "Agua" -> alimento = new Agua();
                case "Bananas" -> alimento = new CachoBananas();
                case "Carne" -> alimento = new Carne();
                case "Cogumelo Magico" -> alimento = new Cogumelo();
                case "Erva" -> alimento = new Erva();
            }

            if (infoCasas.length > 1 && !infoCasas[1].equals(" ")) {
                for (int o = 1; o < infoCasas.length; o++) {
                    iDsJogadores.add(Integer.valueOf(infoCasas[o].trim()));
                }
            }

            this.casas.add(new Casa(iDsJogadores, alimento));
        }

        this.tamanhoMax = casas.size();

        this.casas.get(cIndex(casas.size())).setAsMeta();
    }
}