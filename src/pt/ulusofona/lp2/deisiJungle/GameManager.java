package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.*;

class GameManager {
    String[][] especies;
    Mapa mapa;
    HashMap<Integer, Jogador> jogadores;
    int[] iDsJogadores;
    int indiceJogadorAtual;

    public GameManager() {
        this.jogadores = new HashMap<>();
        indiceJogadorAtual = 0;
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

        for (String[] player : playersInfo) {
            jogadores.put(Integer.parseInt(player[0]),
                    new Jogador(Integer.parseInt(player[0]), player[1], player[2], initialEnergy));
        }

        saveIDsJogadores();

        //Os jogadores ficam guardados em dois sitios, isto é mau, depois resolve-se.
        //A melhor solução talvez seja as casas guardarem apenas o id do jogador.

        return true;
    }

    public int[] getPlayerIds(int squareNr) {

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return new int[0];
        }

        return mapa.getCasa(squareNr).getIDsJogadores();
    }

    public String[] getSquareInfo(int squareNr) {

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return null;
        }

        return mapa.getCasa(squareNr).getInfo();
    }

    public String[] getPlayerInfo(int playerId) {
        if (!(jogadores.containsKey(playerId))) {
            return null;
        }

        return jogadores.get(playerId).getInfo();
    }

    public String[] getCurrentPlayerInfo() {
        int iDJogadorAtual = iDsJogadores[indiceJogadorAtual];

        return jogadores.get(iDJogadorAtual).getInfo();
    }

    public String[][] getPlayersInfo() {

        if(iDsJogadores == null || iDsJogadores.length == 0) {
            return null;
        }

        String [][] playersInfo = new String[iDsJogadores.length][4];

        for (int i = 0 ; i < iDsJogadores.length ; i ++) {
            String[] player = jogadores.get(iDsJogadores[i]).getInfo();
            playersInfo[i] = player;
        }

        return playersInfo;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        if (!bypassValidations) {

            if (nrSquares < 1 || nrSquares > 6) {
                updateCurrentPlayer();
                return false;
            }

            if (jogadores.get(getIDJogadorAtual()).getEnergia() < 2) {
                updateCurrentPlayer();
                return false;
            }
        }

        jogadores.get(getIDJogadorAtual()).spendEnergy();

        int nrCasaAtual = mapa.findNrCasaContaining(getIDJogadorAtual());

        mapa.getCasa(nrCasaAtual).removeJogador(getIDJogadorAtual());

        int casaDestino = nrCasaAtual + nrSquares;

        if (casaDestino > mapa.getNrCasas()) {
            casaDestino = mapa.getNrCasas();
        }

        mapa.getCasa(casaDestino).addJogador(getIDJogadorAtual());

        updateCurrentPlayer();
        return true;
    }

    public String[] getWinnerInfo() {

        if (mapa.getCasa(mapa.getNrCasas()).nrJogadores() > 0 ){
            return jogadores.get(mapa.getCasa(mapa.getNrCasas()).jogadorIDMenor()).getInfo();
        }

        if (todosSemEnergia()){
            for (int i = mapa.getNrCasas(); i > 1 ; i--){
                if (mapa.getCasa(i).nrJogadores() > 0 ){
                    return jogadores.get(mapa.getCasa(i).jogadorIDMenor()).getInfo();
                }
            }
        }
        return null;
    }

    public ArrayList<String> getGameResults() {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }

    public String whoIsTaborda() {
        return "wrestling";
    }

    //funções auxiliares
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

    public void saveIDsJogadores() {
        iDsJogadores = new int[jogadores.size()];

        int i = 0;
        for (Jogador jogador : jogadores.values()) {
            iDsJogadores[i] = jogador.getID();
            i++;
        }
        Arrays.sort(iDsJogadores);
    }

    public void updateCurrentPlayer() {
        indiceJogadorAtual++;
        if (indiceJogadorAtual >= iDsJogadores.length) {
            indiceJogadorAtual = 0;
        }
    }

    public int getIDJogadorAtual() {
        return iDsJogadores[indiceJogadorAtual];
    }

    public boolean todosSemEnergia () {
        for (Jogador jogador : jogadores.values()) {
            if (jogador.getEnergia() > 2){
                return false;
            }
        }
        return true;
    }

}