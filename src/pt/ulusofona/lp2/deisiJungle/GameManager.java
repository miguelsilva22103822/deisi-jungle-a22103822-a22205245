package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class GameManager {
    private Mapa mapa;
    private HashMap<Integer, Jogador> jogadores;
    private int[] iDsJogadores;
    private int indiceJogadorAtual;
    private int numJogada;
    private ArrayList<Alimento> alimentosIngeridos;

    public GameManager() {
        this.jogadores = new HashMap<>();
        this.alimentosIngeridos = new ArrayList<>();

        indiceJogadorAtual = 0;
    }

    //funções obrigatórias--------------------------------------------------------------

    public String[][] getSpecies() {

        String[] elefante = new Elefante().getInfo();
        String[] leao = new Leao().getInfo();
        String[] tartaruga = new Tartaruga().getInfo();
        String[] passaro = new Passaro().getInfo();
        String[] tarzan = new Tarzan().getInfo();
        String[] esquilo = new Esquilo().getInfo();

        return new String[][]{elefante, leao, tartaruga, passaro, tarzan, esquilo};
    }

    public String[][] getFoodTypes() {

        String[] cachoBanana = new CachoBananas().getFoodInfo();
        String[] agua = new Agua().getFoodInfo();
        String[] cogumelo = new Cogumelo().getFoodInfo();
        String[] erva = new Erva().getFoodInfo();
        String[] carne = new Carne().getFoodInfo();

        return new String[][]{cachoBanana, agua, cogumelo, erva, carne};
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo)
            throws InvalidInitialJungleException {

        if(!validarPlayersInfo(playersInfo)) {
             throw new InvalidInitialJungleException("playersInfo inválido", true, false);
        }

        if (jungleSize < (2 * playersInfo.length)) {
            throw new InvalidInitialJungleException("O mapa tem menos de duas posições por jogador", true, false);
        }

        if (!validarfoodsInfo(foodsInfo, jungleSize)) {
            throw new InvalidInitialJungleException("foodsInfo inválido", false, true);
        }

        mapa = new Mapa(jungleSize);
        mapa.initializeMap(playersInfo);
        mapa.initialzeMapFood(foodsInfo);

        for (String[] player : playersInfo) {
            Jogador tempJogador = new Jogador(Integer.parseInt(player[0]), player[1], player[2]);
            jogadores.put(tempJogador.getID(), tempJogador);
        }

        saveIDsJogadores();
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException {
        createInitialJungle(jungleSize, playersInfo, null);
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
        if (iDsJogadores == null) {
            return null;
        }
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

        Jogador jogadorAtual = jogadores.get(getIDJogadorAtual());

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
        // tenho que escrever alguma coisa para poder dar commit e submeter
        if (!jogadorAtual.hasEnergy(Math.abs(nrSquares))) {
            updateJogada();
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }

        int nrCasaAtual = mapa.findNrCasaContaining(getIDJogadorAtual());

        int casaDestino = nrCasaAtual + nrSquares;

        if (casaDestino < 1) {
            updateJogada();
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
        }

        mapa.removeJogadorFromCasa(getIDJogadorAtual(), nrCasaAtual);

        if (casaDestino > mapa.getNrCasas()) {
            casaDestino = mapa.getNrCasas();
        }

        jogadorAtual.updateEnergyMovement(nrSquares);

        mapa.addPlayerToCasa(getIDJogadorAtual(), casaDestino);
        jogadorAtual.setCasaAtual(casaDestino);

        if (mapa.getIdAlimentoCasa(casaDestino) != null
                && !(jogadorAtual.getDieta().equals("h") && mapa.getAlimentoCasa(casaDestino).eCarne())
                && mapa.getAlimentoCasa(casaDestino).getQuantidadeBananas() != 0) {

            jogadorAtual.comer(mapa.getAlimentoCasa(casaDestino),numJogada);

            alimentosIngeridos.add(mapa.getAlimentoCasa(casaDestino));

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

        int idWinner = -1;
        if (getWinnerInfo() != null) {
            idWinner = Integer.parseInt(getWinnerInfo()[0]);
            iDsOrdenados.add(idWinner);
        }

        for (int i = mapa.getNrCasas(); i >= 1 ; i--){
            if (mapa.nrJogadoresInCasa(i) > 0) {

                mapa.sortIDsCasa(i);

                for (int id : mapa.getPlayerIds(i)) {
                    if (id != idWinner) {
                        iDsOrdenados.add(id);
                    }
                }

            }
        }

        for (int i = 0; i < iDsOrdenados.size(); i++) {
            Jogador jogador = jogadores.get(iDsOrdenados.get(i));
            String playerResult = "#" + (i+1) + " " + jogador.getNome() + ", "
                    + jogador.getEspecie().getNome() + ", "
                    + mapa.findNrCasaContaining(jogador.getID()) + ", "
                    + jogador.getDistanciaPercorrida() + ", "
                    + jogador.getQuantidadeComeu();

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
        try
        {
            FileWriter fw = new FileWriter(file, false);

            PrintWriter pw = new PrintWriter(fw);

            StringBuilder text = new StringBuilder();

            text.append("Mapa:\n");
            text.append(mapa.getSaveInfo());

            text.append("Jogadores:\n");
            for (Jogador jogador : jogadores.values()) {
                text.append(jogador.getSaveInfo());
                text.append("\n");
            }

            text.append("iDJogadorAtual:\n");
            text.append(indiceJogadorAtual).append("\n");

            text.append("numJogada:\n");
            text.append(numJogada).append("\n");

            pw.println(text);

            pw.close();
        }
        catch(IOException e)
        {
            return false;
        }

        return true;
    }

    public boolean loadGame(File file) {
        try
        {
            Scanner scanner = new Scanner(file);

            StringBuilder fileContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }

            String[] lines = fileContent.toString().split("\n");

            ArrayList<String> casas = readCasas(lines);
            ArrayList<String> jogadores = readJogadores(lines);
            int idJogadorAtual = readIdJogadorAtual(lines);
            int numJogada = readNumJogada(lines);

            return loadInfoGame(casas, jogadores, idJogadorAtual, numJogada);
        }
        catch(IOException e)
        {
            return false;
        }

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

        //‘id’'s têm que ser todos diferentes
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

        //foods nunca é null porque é chamado com getFoodsTypes() como parametro

        for (int i = 0; i < foods.length; i++) {
            foodArrayList.add(foods[i][0]);
        }

        return foodArrayList;
    }

    private ArrayList<String> speciesToArrayList(String[][] species) {

        ArrayList<String> speciesArrayList = new ArrayList<>();

        //species nunca é null porque é chamado com getSpecies() como parametro

        for (int i = 0; i < species.length; i++) {
            speciesArrayList.add(species[i][0]);
        }

        return speciesArrayList;
    }

    private boolean isStringNumeric(String string) {//Ver se o String só tem numeros

        if (string == null) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) { // se String tiver letras retorna falso

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

    public void updateJogada () {
        indiceJogadorAtual++;
        if (indiceJogadorAtual >= iDsJogadores.length) {
            indiceJogadorAtual = 0;
        }
        numJogada++;
    }

    private int getIDJogadorAtual() {
        return iDsJogadores[indiceJogadorAtual];
    }

    private boolean loadInfoGame(ArrayList<String> casas, ArrayList<String> jogadores,
                                 int indiceJogadorAtual, int numJogada) {

        mapa = new Mapa(casas.size());
        mapa.loadGame(casas); //penso que já está bom

        this.jogadores = new HashMap<>();
        for (String jogadorInfo : jogadores) {
            String[] jogador = StringUtil.separarString(jogadorInfo);

            this.jogadores.put(Integer.parseInt(jogador[0]),
                    new Jogador(
                            Integer.parseInt(jogador[0]),
                            jogador[1],
                            jogador[2],
                            Integer.parseInt(jogador[3]),
                            Integer.parseInt(jogador[4]),
                            Integer.parseInt(jogador[5]),
                            Integer.parseInt(jogador[6]),
                            Integer.parseInt(jogador[7])
                    )
            );

        }

        saveIDsJogadores();

        this.indiceJogadorAtual = indiceJogadorAtual;
        this.numJogada = numJogada;

        return true;
    }

    private ArrayList<String> readCasas(String[] lines) {
        ArrayList<String> casas = new ArrayList<>();

        boolean aLerMapa = false;

        for (String line : lines) {
            if (line.equals("Mapa:")) {
                aLerMapa = true;
                continue;
            }

            if (line.equals("Jogadores:")) {
                break;
            }

            if (aLerMapa) {
                casas.add(line);
            }
        }

        return casas;
    }

    private ArrayList<String> readJogadores(String[] lines) {
        ArrayList<String> jogadores = new ArrayList<>();

        boolean aLerJogadores = false;

        for (String line : lines) {

            if (line.equals("Jogadores:")) {
                aLerJogadores = true;
                continue;
            }

            if (line.equals("iDJogadorAtual:")) {
                break;
            }

            if (aLerJogadores) {
                jogadores.add(line);
            }
        }

        return jogadores;
    }

    private int readIdJogadorAtual(String[] lines) {
        int idJogadorAtual = -1;

        boolean aLerIdJogadorAtual = false;

        for (String line : lines) {

            if (line.equals("iDJogadorAtual:")) {
                aLerIdJogadorAtual = true;
                continue;
            }

            if (line.equals("numJogada:")) {
                break;
            }

            if (aLerIdJogadorAtual) {
                idJogadorAtual = Integer.parseInt(line);
            }
        }

        return idJogadorAtual;
    }

    private int readNumJogada(String[] lines) {
        int numJogada = -1;

        boolean aLerNumJogada = false;

        for (String line : lines) {

            if (line.equals("numJogada:")) {
                aLerNumJogada = true;
                continue;
            }

            if (aLerNumJogada) {
                numJogada = Integer.parseInt(line);
                break;
            }
        }

        return numJogada;
    }

    //funções auxiliares para parte 3--------------------------------------------------------------

    public List<Jogador> getJogadores() {
        return new ArrayList<>(jogadores.values());
    }

    public ArrayList<Alimento> getAlimentosIngeridos() {
        return alimentosIngeridos;
    }

    public int getCasaDoJogadorAtual() {
        int iDJogadorAtual = iDsJogadores[indiceJogadorAtual];

        return jogadores.get(iDJogadorAtual).getCasaAtual();
    }

    public int getNrCasas() {
        return mapa.getNrCasas();
    }

}