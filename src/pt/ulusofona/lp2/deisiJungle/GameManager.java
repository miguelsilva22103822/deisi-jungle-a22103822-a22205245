package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class GameManager {
    HashMap<String, Especie> especies;
    Mapa mapa;
    HashMap<Integer, Jogador> jogadores;
    int[] iDsJogadores;
    int indiceJogadorAtual;

    public GameManager() {
        this.jogadores = new HashMap<>();
        this.especies = new HashMap<>();

        this.especies.put(new Elefante().getId(), new Elefante());
        this.especies.put(new Leao().getId(), new Leao());
        this.especies.put(new Tartaruga().getId(), new Tartaruga());
        this.especies.put(new Passaro().getId(), new Passaro());
        this.especies.put(new Tarzan().getId(), new Tarzan());

        indiceJogadorAtual = 0;
    }

    //funções obrigatórias
    public String[][] getSpecies() {
        String[] elefante = new Elefante().getInfo();
        String[] leao = new Leao().getInfo();
        String[] tartaruga = new Tartaruga().getInfo();
        String[] passaro = new Passaro().getInfo();
        String[] tarzan = new Tarzan().getInfo();

        return new String[][]{elefante, leao, tartaruga, passaro, tarzan};
    }

    public String[][] getFoodTypes() {
        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) {
        if(!validarPlayersInfo(playersInfo)) {
            return new InitializationError("playersInfo inválido");
        }

        if (jungleSize < (2 * playersInfo.length)) {
            return new InitializationError("O mapa tem menos de duas posições por jogador");
        }

        mapa = new Mapa(jungleSize);
        mapa.initializeMap(playersInfo);

        for (String[] player : playersInfo) {
            Jogador tempJogador = new Jogador(Integer.parseInt(player[0]), player[1], player[2]);
            //ainda não sei se a energia vai ficar no jogdador ou na especie, se ficar no jogador
            //vai ser preciso dar set à energia inicial aqui com base na energia inicial da espécie
            jogadores.put(tempJogador.getID(), tempJogador);
        }

        saveIDsJogadores();

        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo) {
        return createInitialJungle(jungleSize, playersInfo, null);
    }

    /*public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

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
    }*/

    public int[] getPlayerIds(int squareNr) {

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return new int[0];
        }

        return mapa.getPlayerIds(squareNr);
    }

    public String[] getSquareInfo(int squareNr) {

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return null;
        }

        return mapa.getSquareInfo(squareNr);
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

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        return null;
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

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        if (!bypassValidations) {
            if (nrSquares < 1 || nrSquares > 6) {
                updateCurrentPlayer();
                //return false;
            }
        }

        if (jogadores.get(getIDJogadorAtual()).getEnergia() < 2) {
            updateCurrentPlayer();
            //return false;
        }

        jogadores.get(getIDJogadorAtual()).spendEnergy();

        int nrCasaAtual = mapa.findNrCasaContaining(getIDJogadorAtual());

        mapa.removeJogadorFromCasa(getIDJogadorAtual(), nrCasaAtual);

        int casaDestino = nrCasaAtual + nrSquares;

        if (casaDestino > mapa.getNrCasas()) {
            casaDestino = mapa.getNrCasas();
        }

        mapa.addPlayerToCasa(getIDJogadorAtual(), casaDestino);

        updateCurrentPlayer();
        //return true;

        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "batata");
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
        JPanel panel=new JPanel();
        panel.setBackground(Color.getHSBColor(0f, 0f, 0.1f));

        JLabel label1 = new JLabel("<html><font face=\"Big Money-sw\" size=\"10px\" color=\"#FF7A59\"><pre>Ana Weng : 22205245\nMiguel Silva : 22103822\n" +
                " __        _______    ______  \n" +
                "/  |      /       \\  /      \\ \n" +
                "$$ |      $$$$$$$  |/$$$$$$  |\n" +
                "$$ |      $$ |__$$ |$$____$$ |\n" +
                "$$ |      $$    $$/  /    $$/ \n" +
                "$$ |      $$$$$$$/  /$$$$$$/  \n" +
                "$$ |_____ $$ |      $$ |_____ \n" +
                "$$       |$$ |      $$       |\n" +
                "$$$$$$$$/ $$/       $$$$$$$$/ \n" +
                "                              \n" +
                "                              \n" +
                "                              \n</pre></font></html>");

        label1.setForeground(Color.getHSBColor(0.7f, 0.6f, 1f));
        label1.setFont(new Font(label1.getFont().getFontName(), Font.BOLD, 14));
        panel.add(label1, Component.LEFT_ALIGNMENT);

        return panel;
    }

    public String whoIsTaborda() {
        return "wrestling";
    }

    public boolean saveGame(File file) {
        return true;
    }

    public boolean loadGame(File file) {
        return true;
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

            if (player[2].equals("Z")) {
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