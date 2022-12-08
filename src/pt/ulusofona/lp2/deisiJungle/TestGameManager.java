package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import java.awt.image.AreaAveragingScaleFilter;
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
    public void testgetPlayersInfo() {

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

        String[][] jogadores = {jogador1, jogador2, jogador3};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(20, jogadores, null);

        System.out.println(gameManager.moveCurrentPlayer(5,true));
        System.out.println(Arrays.deepToString(gameManager.getPlayersInfo()));

        System.out.println(gameManager.moveCurrentPlayer(-5,true));
        System.out.println(Arrays.deepToString(gameManager.getPlayersInfo()));

    }

    @Test
    public void testgetSquareInfo() {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3};

        gameManager.createInitialJungle(20, jogadores, null);
        gameManager.moveCurrentPlayer(21,true);

        assertArrayEquals(new String[]{"blank.png","Vazio",""},gameManager.getSquareInfo(2));
    }

    @Test
    public void testWhoIsTaborba() {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3};

        gameManager.createInitialJungle(20, jogadores, null);

        Assert.assertEquals("wrestling",gameManager.whoIsTaborda());

    }

    @Test
    public void testWinnerInfo () {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        gameManager.createInitialJungle(20, jogadores, null);

        gameManager.moveCurrentPlayer(5,true);
        gameManager.getWinnerInfo();

        assertNull(gameManager.getWinnerInfo());
    }

}
