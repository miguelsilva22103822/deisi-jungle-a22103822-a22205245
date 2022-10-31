package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class GameManager {
    String[][] especies;
    Mapa mapa;

    public GameManager() {

    }

    //funções obrigatórias
    public String[][] getSpecies() {
        String[] elefante = {"E", "Elefante", "elephant.png"};
        String[] leao = {"L", "Leão", "lion.png"};
        String[] tartaruga = {"T", "Tartaruga", "turtle.png"};
        String[] passaro = {"P", "Pássaro", "bird.png"};
        String[] tarzan = {"Z", "Tarzan", "tarzan.png"};

        String[][] especies = {elefante, leao, tartaruga, passaro, tarzan};

        return especies;
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

        if(!validarPlayersInfo(playersInfo)) {
            return false;
        }

        if (jungleSize < (2 * playersInfo.length)) {
            return false;
        }

        if (initialEnergy < 0) {
            return false;
        }

        mapa = new Mapa(jungleSize);
        mapa.initializeMap(playersInfo, initialEnergy);
        //poe jogadores na primeira casa do mapa, mas os jogadores não têm especie ainda, acho que deviam ter mas é complidado

        return true;
    }

    public int[] getPlayerIds(int squareNr) {

        if (squareNr <= 0 || squareNr > mapa.getNrCasas()) {
            return new int[0];
        }

        return mapa.getCasa(squareNr).getIDsJogadores();
    }


    public String[] getSquareInfo(int squareNr) {
        return null;
    }

    public String[] getPlayerInfo(int playerId) {
        return null;
    }

    public String[] getCurrentPlayerInfo() {
        return null;
    }

    public String[][] getPlayersInfo() {
        return null;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        return false;
    }

    public String[] getWinnerInfo() {
        return null;
    }

    public ArrayList<String> getGameResults() {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }

    public String whoIsTaborda() {
        return null;
    }

    //funções para organizar
    public boolean validarPlayersInfo(String[][] playersInfo) {

        if(playersInfo.length < 2 || playersInfo.length > 4) {
            return false;
        }

        ArrayList<String> species = speciesToArrayList(getSpecies());

        List<String> ids = new ArrayList<>();

        for (String[] player : playersInfo) {
            if (!(isStringNumeric(player[0])) || Integer.parseInt(player[0]) < 0) {
                return false;
            }

            ids.add(player[0]);

            if (player[1] == null || player[1].equals("")) {
                return false;
            }
            //
            if (!species.contains(player[2])) {
                return false;
            }

        }

        //id's têm que ser todos diferentes
        for (int i = 0; i < ids.size(); i++) {
            for (int j = 0; j < ids.size(); j++) {
                if (i != j) {
                    if (ids.get(i).equals(ids.get(j))) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    public ArrayList<String> speciesToArrayList(String[][] species) {

        ArrayList<String> speciesArrayList = new ArrayList<>();

        if (species == null || species.length == 0) {
            return null;
        }

        for (int i = 0; i < species.length; i++) {
            speciesArrayList.add(species[i][0]);
        }

        return speciesArrayList;
    }

    public boolean isStringNumeric(String string) {

        if (string == null) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {

            if (string.charAt(i) > '9' || string.charAt(i) < '0') {
                return false;
            }

        }

        return true;
    }
}