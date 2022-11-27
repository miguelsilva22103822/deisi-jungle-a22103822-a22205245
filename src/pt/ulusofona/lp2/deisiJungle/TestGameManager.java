package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestGameManager {
    @Test
    public void testGetPlayerIds() {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"4", "Manuel", "T"};
        String[] jogador3 = {"6", "Pedro", "Z"};
        String[] jogador4 = {"8", "Maria", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        gameManager.createInitialJungle(20, jogadores);

        int[] expectedPlayerIds = {1, 4, 6, 8};

        assertArrayEquals(expectedPlayerIds, gameManager.getPlayerIds(1));
    }

    @Test
    public void testMoveCurrentPlayer() {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"4", "Manuel", "T"};
        String[] jogador3 = {"6", "Pedro", "Z"};

        String[][] jogadores = {jogador1, jogador2, jogador3};

        //assertTrue(gameManager.createInitialJungle(22, 8, jogadores));

        //assertTrue(gameManager.moveCurrentPlayer(5, false));

        int[] expectedPlayerIds = {1};
        assertArrayEquals(expectedPlayerIds, gameManager.getPlayerIds(6));

        int expectedCurrentPlayerID = 4;
    }

    @Test
    public void testGetSquareInfo() {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"3", "João", "E"};
        String[] jogador2 = {"2", "Manuel", "T"};
        String[] jogador3 = {"1", "Pedro", "Z"};
        String[] jogador4 = {"5", "Maria", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        //assertTrue(gameManager.createInitialJungle(20, 4, jogadores));

        String[] expectedSquareInfo = {"blank.png", "Vazio", "3,2,1,5"};
        assertArrayEquals(expectedSquareInfo, gameManager.getSquareInfo(1));
    }

    @Test
    public void testCreateInitialJungle2Tarzans() {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "Z"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        //assertFalse(gameManager.createInitialJungle(15, 4, jogadores));
    }

    @Test
    public void testCreateInitialJungleFood() {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "1" };
        String[] alimento2 = {"a", "4" };
        String[] alimento3 = {"c", "2" };
        String[] alimento4 = {"e", "6" };
        String[] alimento5 = {"m", "3" };

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(27, jogadores, alimentos);
    }

    @Test
    public void testGetSpecies() {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "1" };
        String[] alimento2 = {"a", "4" };
        String[] alimento3 = {"c", "2" };
        String[] alimento4 = {"e", "6" };
        String[] alimento5 = {"m", "3" };

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = null;

        System.out.println(Arrays.deepToString(gameManager.getSpecies()));

        gameManager.createInitialJungle(27, jogadores, alimentos);

        gameManager.getCurrentPlayerInfo();


    }
}
