package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;

public class TestGameManager {

    @Test
    public void testCreateInitialJungle() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "asd", "E"};
        String[] jog2 = {"4", "joao", "E"};
        String[] jog3 = {"6", "asd", "E"};
        String[] jog4 = {"8", "abd", "E"};

        String[][] jogadores = {jog1, jog2, jog3, jog4};

        assertTrue(gameManager.createInitialJungle(9, 5, jogadores));
    }

    @Test
    public void testGetPlayerIds() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "asd", "E"};
        String[] jog2 = {"4", "joao", "E"};
        String[] jog3 = {"6", "dfg", "E"};
        String[] jog4 = {"8", "dfgh", "E"};


        String[][] jogadores = {jog1, jog2, jog3, jog4};

        assertTrue(gameManager.createInitialJungle(9, 5, jogadores));

        assertEquals(4, gameManager.getPlayerIds(1).length);
    }

    @Test
    public void testGetSquareInfo() {
        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "asd", "E"};
        String[] jog2 = {"4", "joao", "T"};
        String[] jog3 = {"6", "dfg", "Z"};
        String[] jog4 = {"8", "dfgh", "P"};


        String[][] jogadores = {jog1, jog2, jog3, jog4};

        gameManager.createInitialJungle(9, 5, jogadores);

        String[] correctInfo = {"blank.png", "Vazio", "1, 4, 6, 8"};
        //System.out.println(Arrays.toString(gameManager.mapa.getCasa(1).getInfo()));

    }

    @Test
    public void testGetPlayerInfo() {
        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "asd", "E"};
        String[] jog2 = {"4", "joao", "T"};
        String[] jog3 = {"6", "dfg", "Z"};
        String[] jog4 = {"8", "dfgh", "P"};


        String[][] jogadores = {jog1, jog2, jog3, jog4};

        gameManager.createInitialJungle(9, 5, jogadores);

        String[] correctInfo = {"4", "joao", "T", "5"};

        assertArrayEquals(gameManager.getPlayerInfo(4), correctInfo);

    }

    @Test
    public void testManyThings() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "Manel", "E"};
        String[] jog2 = {"3", "Joao", "T"};
        String[] jog3 = {"4", "Maria", "Z"};
        String[] jog4 = {"7", "Antonio", "P"};

        String[][] jogadores = {jog1, jog2, jog3, jog4};

        gameManager.createInitialJungle(15, 5, jogadores);

        System.out.println(Arrays.toString(gameManager.getSquareInfo(1)));
        System.out.println(Arrays.toString(gameManager.getSquareInfo(2)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(1)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(2)));

        System.out.println(gameManager.moveCurrentPlayer(1, true));

        System.out.println(Arrays.toString(gameManager.getSquareInfo(1)));
        System.out.println(Arrays.toString(gameManager.getSquareInfo(2)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(1)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(2)));

        System.out.println(gameManager.moveCurrentPlayer(1, true));

        System.out.println(Arrays.toString(gameManager.getSquareInfo(1)));
        System.out.println(Arrays.toString(gameManager.getSquareInfo(2)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(1)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(2)));

        System.out.println(gameManager.moveCurrentPlayer(1, true));

        System.out.println(Arrays.toString(gameManager.getSquareInfo(1)));
        System.out.println(Arrays.toString(gameManager.getSquareInfo(2)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(1)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(2)));

        System.out.println(gameManager.moveCurrentPlayer(1, true));

        System.out.println(Arrays.toString(gameManager.getSquareInfo(1)));
        System.out.println(Arrays.toString(gameManager.getSquareInfo(2)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(1)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(2)));

        System.out.println(gameManager.moveCurrentPlayer(1, true));

        System.out.println(Arrays.toString(gameManager.getSquareInfo(1)));
        System.out.println(Arrays.toString(gameManager.getSquareInfo(2)));
        System.out.println(Arrays.toString(gameManager.getSquareInfo(3)));

        System.out.println();
    }

    @Test
    public void testLinkedList() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"3", "Manel", "E"};
        String[] jog2 = {"2", "Joao", "T"};
        String[] jog3 = {"9", "Maria", "Z"};
        String[] jog4 = {"4", "Antonio", "P"};

        String[][] jogadores = {jog1, jog2, jog3, jog4};

        gameManager.createInitialJungle(9, 5, jogadores);


        System.out.println(Arrays.toString(gameManager.getSquareInfo(1)));

        System.out.println(Arrays.toString(gameManager.getSquareInfo(2)));

        System.out.println(Arrays.toString(gameManager.getSquareInfo(9)));

        System.out.println(Arrays.toString(gameManager.iDsJogadores));

    }

    @Test
    public void testGetPlayersInfo() {
        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "asd", "E"};
        String[] jog2 = {"4", "joao", "T"};
        String[] jog3 = {"6", "dfg", "Z"};
        String[] jog4 = {"8", "dfgh", "P"};


        String[][] jogadores = {jog1, jog2, jog3, jog4};

        gameManager.createInitialJungle(9, 5, jogadores);

        System.out.println(Arrays.deepToString(gameManager.getPlayersInfo()));

    }

    @Test
    public void test123() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"4", "Manel", "E"};
        String[] jog2 = {"3", "Joao", "T"};
        String[] jog3 = {"5", "Maria", "Z"};
        String[] jog4 = {"7", "Antonio", "P"};

        String[][] jogadores = {jog1, jog2, jog3};

        gameManager.createInitialJungle(15, 5, jogadores);
        System.out.println(gameManager.getIDJogadorAtual());

        System.out.println(gameManager.moveCurrentPlayer(7, false));
        System.out.println(gameManager.getIDJogadorAtual());

        System.out.println(gameManager.moveCurrentPlayer(2, false));
        System.out.println(gameManager.getIDJogadorAtual());


        System.out.println(gameManager.moveCurrentPlayer(1, false));
        System.out.println(gameManager.getIDJogadorAtual());



        System.out.println();
    }

    @Test
    public void testTheWinner() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "Manel", "E"};
        String[] jog2 = {"3", "Joao", "T"};
        String[] jog3 = {"4", "Maria", "Z"};
        String[] jog4 = {"7", "Antonio", "P"};

        String[][] jogadores = {jog1, jog2};

        gameManager.createInitialJungle(15, 5, jogadores);
        gameManager.moveCurrentPlayer(1,true);
        gameManager.moveCurrentPlayer(15,true);
        gameManager.moveCurrentPlayer(20,true);

        System.out.println(Arrays.toString(gameManager.getWinnerInfo()));
    }

    @Test
    public void testMove() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "Manel", "E"};
        String[] jog2 = {"3", "Joao", "T"};
        String[] jog3 = {"4", "Maria", "Z"};
        String[] jog4 = {"7", "Antonio", "P"};

        String[][] jogadores = {jog1, jog2};

        gameManager.createInitialJungle(15, 5, jogadores);
        gameManager.moveCurrentPlayer(1,false);
        gameManager.moveCurrentPlayer(15,true);
        gameManager.moveCurrentPlayer(20,true);


        System.out.println(Arrays.toString(gameManager.getCurrentPlayerInfo()));



    }
}
