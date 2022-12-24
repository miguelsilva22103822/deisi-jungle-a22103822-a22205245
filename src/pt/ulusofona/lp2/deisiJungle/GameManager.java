package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GameManager {
    private HashMap<String, Especie> especies;
    private Mapa mapa;
    private HashMap<Integer, Jogador> jogadores;
    private int[] iDsJogadores;
    private int indiceJogadorAtual;
    private int numJogada;

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

    //funções obrigatórias--------------------------------------------------------------

    public String[][] getSpecies() {

        String[] elefante = new Elefante().getInfo();
        String[] leao = new Leao().getInfo();
        String[] tartaruga = new Tartaruga().getInfo();
        String[] passaro = new Passaro().getInfo();
        String[] tarzan = new Tarzan().getInfo();

        return new String[][]{elefante, leao, tartaruga, passaro, tarzan};
    }

    public String[][] getFoodTypes() {

        String[] cachoBanana = new CachoBananas().getFoodInfo();
        String[] agua = new Agua().getFoodInfo();
        String[] cogumelo = new Cogumelo().getFoodInfo();
        String[] erva = new Erva().getFoodInfo();
        String[] carne = new Carne().getFoodInfo();

        return new String[][]{cachoBanana, agua, cogumelo, erva, carne};
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) {

        if(!validarPlayersInfo(playersInfo)) {
            return new InitializationError("playersInfo inválido");
        }

        if (jungleSize < (2 * playersInfo.length)) {
            return new InitializationError("O mapa tem menos de duas posições por jogador");
        }

        if (!validarfoodsInfo(foodsInfo, jungleSize)) {
            return new InitializationError("foodsInfo inválido");
        }

        mapa = new Mapa(jungleSize);
        mapa.initializeMap(playersInfo);
        mapa.initialzeMapFood(foodsInfo);

        for (String[] player : playersInfo) {
            Jogador tempJogador = new Jogador(Integer.parseInt(player[0]), player[1], player[2]);
            jogadores.put(tempJogador.getID(), tempJogador);
        }

        saveIDsJogadores();

        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo) {
        return createInitialJungle(jungleSize, playersInfo, null);
    }

    public int[] getPlayerIds(int squareNr) {
        if (mapa == null) {
            return new int[0];
        }

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return new int[0];
        }

        return mapa.getPlayerIds(squareNr);
    }

    public String[] getSquareInfo(int squareNr) {

        if (mapa == null) {
            return null;
        }

        if (squareNr < 1 || squareNr > mapa.getNrCasas()) {
            return null;
        }



        return mapa.getSquareInfo(squareNr,numJogada);
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
        return jogadores.get(iDsJogadores[indiceJogadorAtual]).getInfoEnergy(nrPositions);
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
            if (nrSquares < -6 || nrSquares > 6) {
                updateJogada();
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }
            if (!jogadores.get(getIDJogadorAtual()).movementIsValid(nrSquares)) {
                updateJogada();
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }
        }

        if (!jogadores.get(getIDJogadorAtual()).hasEnergy(nrSquares)) {
            updateJogada();
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }

        int nrCasaAtual = mapa.findNrCasaContaining(getIDJogadorAtual());

        mapa.removeJogadorFromCasa(getIDJogadorAtual(), nrCasaAtual);

        int casaDestino = nrCasaAtual + nrSquares;

        if (casaDestino > mapa.getNrCasas()) {
            casaDestino = mapa.getNrCasas();
        }

        if (casaDestino < 0) {
            casaDestino = 1;
        }

        jogadores.get(getIDJogadorAtual()).updateEnergyMovement(nrSquares);

        mapa.addPlayerToCasa(getIDJogadorAtual(), casaDestino);

        if (mapa.getIdAlimentoCasa(casaDestino) != null && !(jogadores.get(getIDJogadorAtual()).getDieta().equals("h")
                && mapa.getAlimentoCasa(casaDestino).eCarne())) {
            jogadores.get(getIDJogadorAtual()).comer(mapa.getAlimentoCasa(casaDestino),numJogada);

            updateJogada();

            return new MovementResult(MovementResultCode.CAUGHT_FOOD,"Apanhou " +
                    mapa.getAlimentoCasa(casaDestino).getNome());
        }

        updateJogada();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);

    }

    public String[] getWinnerInfo() {

        boolean primeiroJogadorEncontrado = false;
        int posicaoDoJogador1 = 0;
        int posicaoDoJogador2 = 0;
        int iDdoJogador2 = 0;


        if (mapa.nrJogadoresInCasa(mapa.getNrCasas()) > 0){
            return jogadores.get(mapa.getJogadorIDMenorInCasa(mapa.getNrCasas())).getInfo();
            //return jogadores.get(mapa.getCasa(mapa.getNrCasas()).jogadorIDMenor()).getInfo();
        }

        for (int i = mapa.getNrCasas() - 1; i >= 1 ; i--){
            if (mapa.nrJogadoresInCasa(i) > 1) {
                if (primeiroJogadorEncontrado) {
                    posicaoDoJogador2 = i + 1;
                    iDdoJogador2 = jogadores.get(mapa.getJogadorIDMenorInCasa(i)).getID();
                    break;
                }
                else{
                    return null;
                }
            }

            if (mapa.nrJogadoresInCasa(i) == 1) {
                if (!primeiroJogadorEncontrado){
                    posicaoDoJogador1 = i + 1;
                    primeiroJogadorEncontrado = true;
                }
                else{
                    posicaoDoJogador2 = i + 1;
                    iDdoJogador2 = jogadores.get(mapa.getJogadorIDMenorInCasa(i)).getID();
                    break;
                }
            }
        }


        int distanciaDosJogadores = posicaoDoJogador1 - posicaoDoJogador2 ;

        if (distanciaDosJogadores > (mapa.getNrCasas()/ 2)){
            return jogadores.get(iDdoJogador2).getInfo();
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
                    + mapa.findNrCasaContaining(jogador.getID()) + ", "
                    +jogador.getDistanciaPercorrida() + ", "
                    +jogador.getQuantidadeComeu();

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
        /*
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            return false;
        }

        return true;
        */
    }

    public boolean loadGame(File file) {
        return true;
    }

    //funções auxiliares------------------------------------------------------------------

    private boolean validarPlayersInfo(String[][] playersInfo) {

        if(playersInfo == null || playersInfo.length < 2 || playersInfo.length > 4) {
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

    private boolean validarfoodsInfo(String[][] foodInfo, int jungleSize) {

        if (foodInfo == null){
            return true;
        }

        ArrayList<String> idAlimentos = foodToArrayList(getFoodTypes());

        for (String[] food : foodInfo) {
            if (!(idAlimentos.contains(food[0]))) {
                return false;
            }

            if(!isStringNumeric(food[1])){
                return false;
            }

            if (Integer.parseInt(food[1]) >= jungleSize || Integer.parseInt(food[1]) <= 1) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<String> foodToArrayList(String[][] foods) {

        ArrayList<String> foodArrayList = new ArrayList<>();

        if (foods == null || foods.length == 0) {
            return null;
        }

        for (int i = 0; i < foods.length; i++) {
            foodArrayList.add(foods[i][0]);
        }

        return foodArrayList;
    }

    private ArrayList<String> speciesToArrayList(String[][] species) {

        ArrayList<String> speciesArrayList = new ArrayList<>();

        if (species == null || species.length == 0) {
            return null;
        }

        for (int i = 0; i < species.length; i++) {
            speciesArrayList.add(species[i][0]);
        }

        return speciesArrayList;
    }

    private boolean isStringNumeric(String string) {

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

    private void saveIDsJogadores() {
        iDsJogadores = new int[jogadores.size()];

        int i = 0;
        for (Jogador jogador : jogadores.values()) {
            iDsJogadores[i] = jogador.getID();
            i++;
        }
        Arrays.sort(iDsJogadores);
    }

    private void updateJogada () {
        indiceJogadorAtual++;
        if (indiceJogadorAtual >= iDsJogadores.length) {
            indiceJogadorAtual = 0;
        }
        numJogada++;
    }

    private int getIDJogadorAtual() {
        return iDsJogadores[indiceJogadorAtual];
    }

    private boolean todosSemEnergia() {
        for (Jogador jogador : jogadores.values()) {
            if (jogador.getEnergia() >= 2){
                return false;
            }
        }
        return true;
    }

}