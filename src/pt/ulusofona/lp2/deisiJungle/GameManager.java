package pt.ulusofona.lp2.deisiJungle;

import kotlin.reflect.jvm.internal.ReflectProperties;

import javax.swing.*;
import java.util.ArrayList;

class GameManager {
    String[][] especies;
    Mapa mapa;

    public GameManager() {
        String[] elefante = {"E", "Elefante", "elephant.png"};
        String[] leao = {"L", "Leão", "lion.png"};
        String[] tartaruga = {"T", "Tartaruga", "turtle.png"};
        String[] passaro = {"P", "Pássaro", "bird.png"};
        String[] tarzan = {"Z", "Tarzan", "tarzan.png"};

        String[][] especies = {elefante, leao, tartaruga, passaro, tarzan};
    }

    public String[][] getSpecies() {
        return especies;
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

        if(!Validacoes.validarPlayersInfo(playersInfo, getSpecies())) {
            return false;
        }

        if (jungleSize >= )







        //mapa tem de ter pelo menos 2* num jogadores casas






        mapa = new Mapa(jungleSize);

    }

    public int[] getPlayerIds(int squareNr) {
        return null;
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
}