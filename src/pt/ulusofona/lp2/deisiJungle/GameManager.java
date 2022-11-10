package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.*;

public class GameManager {
    HashMap<String, Especie> especies;
    Mapa mapa;
    HashMap<Integer, Jogador> jogadores;
    int[] iDsJogadores;
    int indiceJogadorAtual;

    public GameManager() {
        this.jogadores = new HashMap<>();
        this.especies = new HashMap<>();

        String[][] especies = getSpecies();
        for (int i = 0; i < especies.length; i++) {
            this.especies.put(especies[i][0], new Especie(especies[i][0], especies[i][1]));
        }

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

        return true;
    }

    public int[] getPlayerIds(int squareNr) {

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return new int[0];
        }

        return mapa.getPlayerIds(squareNr);
        //return mapa.getCasa(squareNr).getIDsJogadores();
    }

    public String[] getSquareInfo(int squareNr) {

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return null;
        }

        return mapa.getSquareInfo(squareNr);
        //return mapa.getCasa(squareNr).getInfo();
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
                //updateCurrentPlayer(); No enunciado diz que o turno deve sempre mudar, mas se fizer isso dá erro.
                return false;
            }
        }

        if (jogadores.get(getIDJogadorAtual()).getEnergia() < 2) {
            updateCurrentPlayer();
            return false;
        }

        jogadores.get(getIDJogadorAtual()).spendEnergy();

        int nrCasaAtual = mapa.findNrCasaContaining(getIDJogadorAtual());

        mapa.removeJogadorFromCasa(getIDJogadorAtual(), nrCasaAtual);
        //mapa.getCasa(nrCasaAtual).removeJogador(getIDJogadorAtual());

        int casaDestino = nrCasaAtual + nrSquares;

        if (casaDestino > mapa.getNrCasas()) {
            casaDestino = mapa.getNrCasas();
        }

        mapa.addPlayerToCasa(getIDJogadorAtual(), casaDestino);
        //mapa.getCasa(casaDestino).addJogador(getIDJogadorAtual());

        updateCurrentPlayer();
        return true;
    }

    public String[] getWinnerInfo() {

        if (mapa.nrJogadoresInCasa(mapa.getNrCasas()) > 0){
            return jogadores.get(mapa.getJogadorIDMenorInCasa(mapa.getNrCasas())).getInfo();
            //return jogadores.get(mapa.getCasa(mapa.getNrCasas()).jogadorIDMenor()).getInfo();
        }

        if (todosSemEnergia()){
            for (int i = mapa.getNrCasas() - 1; i >= 1 ; i--){
                if (mapa.nrJogadoresInCasa(i) > 0 ) {
                    return jogadores.get(mapa.getJogadorIDMenorInCasa(i)).getInfo();
                    //return jogadores.get(mapa.getCasa(i).jogadorIDMenor()).getInfo();
                }
            }
        }
        return null;
    }

    public ArrayList<String> getGameResults() {
        ArrayList <String> listaResultados = new ArrayList<>();
        ArrayList <Integer> iDsOrdenados = new ArrayList<>();

        for (int i = mapa.getNrCasas(); i >= 1 ; i--){
            if (mapa.nrJogadoresInCasa(i) > 0) {
                mapa.sortIDsCasa(i);
                //mapa.getCasa(i).sortIDs();
                for (int id : mapa.getPlayerIds(i)) {
                    iDsOrdenados.add(id);
                }
            }
        }

        for (int i = 0; i < iDsOrdenados.size(); i++) {
            Jogador jogador = jogadores.get(iDsOrdenados.get(i));
            String playerResult = "#" + (i+1) + " " + jogador.getNome() + ", "
                    + especies.get(jogador.getIdEspecie()).getNome() + ", "
                    + mapa.findNrCasaContaining(jogador.getID());

            listaResultados.add(playerResult);
        }

        return listaResultados;
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

        int nrTarzans = 0;

        for (String[] player : playersInfo) {
            if (!(isStringNumeric(player[0])) || Integer.parseInt(player[0]) < 0) {
                return false;
            }

            ids.add(player[0]);

            if (player[1] == null || player[1].equals("")) {
                return false;
            }

            if (!species.contains(player[2])) {
                return false;
            }

            if (player[2].equals("T")) {
                nrTarzans++;
            }
        }

        if (nrTarzans > 1) {
            return false;
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
            if (jogador.getEnergia() >= 2){
                return false;
            }
        }
        return true;
    }

}