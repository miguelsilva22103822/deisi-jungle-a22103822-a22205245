package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import java.io.File;
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

        assertEquals("playersInfo inválido",
                gameManager.createInitialJungle(15, jogadores).getMessage());
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

        assertEquals("foodsInfo inválido",
                gameManager.createInitialJungle(27, jogadores, alimentos).getMessage());
        //inválido porque tem banana na casa 1
    }

    @Test
    public void testgetPlayersInfoMoveBackwards() {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};

        String[] alimento1 = {"b", "7" };
        String[] alimento2 = {"a", "4" };
        String[] alimento3 = {"c", "2" };
        String[] alimento4 = {"e", "6" };
        String[] alimento5 = {"m", "3" };

        String[][] jogadores = {jogador1, jogador2, jogador3};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(20, jogadores, null);

        assertEquals(new MovementResult(MovementResultCode.VALID_MOVEMENT, null),
                gameManager.moveCurrentPlayer(5,true));

        assertEquals("[[1, João, E, 160, 1..6], [3, Manuel, T, 150, 1..3], [5, Pedro, Z, 70, 1..6]]",
                Arrays.deepToString(gameManager.getPlayersInfo()));

        assertEquals(new MovementResult(MovementResultCode.VALID_MOVEMENT, null),
                gameManager.moveCurrentPlayer(-5,true));

        assertEquals("[[1, João, E, 160, 1..6], [3, Manuel, T, 145, 1..3], [5, Pedro, Z, 70, 1..6]]",
                Arrays.deepToString(gameManager.getPlayersInfo()));

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

    @Test
    public void testEnergy () {

        GameManager gameManager = new GameManager();


        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "7" };
        String[] alimento2 = {"a", "4" };
        String[] alimento3 = {"c", "2" };
        String[] alimento4 = {"e", "6" };
        String[] alimento5 = {"m", "5" };

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(20, jogadores, alimentos);

        gameManager.moveCurrentPlayer(4,true);

        Assert.assertEquals(gameManager.getPlayersInfo(),gameManager.getPlayersInfo());
    }

    @Test
    public void testECarne () {

        GameManager gameManager = new GameManager();


        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"a", "4"};
        String[] alimento3 = {"c", "2"};
        String[] alimento4 = {"e", "6"};
        String[] alimento5 = {"m", "5"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};


        gameManager.createInitialJungle(20, jogadores, alimentos);

        assertEquals("MovementResult[code=VALID_MOVEMENT, message=null]"
                ,String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Carne]"
                ,String.valueOf(gameManager.moveCurrentPlayer(1, true)));

    }

    @Test
    public void testCasaCerta () {

        GameManager gameManager = new GameManager();


        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "2"};
        String[] alimento2 = {"a", "4"};
        String[] alimento3 = {"c", "7"};
        String[] alimento4 = {"e", "6"};
        String[] alimento5 = {"m", "5"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(20, jogadores, alimentos);

        Assert.assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Bananas]",
                String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        Assert.assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Bananas]",
                String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        Assert.assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Bananas]",
                String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        Assert.assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Bananas]",
                String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        Assert.assertEquals("[[1, João, E, 200, 1..6], [2, Maria, P, 106, 5..6], [3, Manuel, T, 189, 1..3]," +
                " [5, Pedro, Z, 68, 1..6]]",Arrays.deepToString(gameManager.getPlayersInfo()));
    }

    @Test
    public void testCogumelos () {

        GameManager gameManager = new GameManager();


        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "5"};
        String[] alimento2 = {"a", "4"};
        String[] alimento3 = {"c", "7"};
        String[] alimento4 = {"e", "6"};
        String[] alimento5 = {"m", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(20, jogadores, alimentos);

        Assert.assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Cogumelo Magico]"
                , String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        Assert.assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Cogumelo Magico]"
                , String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        Assert.assertEquals(gameManager.getSquareInfo(2),gameManager.getSquareInfo(2));
    }

    @Test
    public void testSaveGame () {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"6", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"1", "Maria", "P"};

        String[] alimento1 = {"b", "5"};
        String[] alimento2 = {"a", "10"};
        String[] alimento3 = {"c", "15"};
        String[] alimento4 = {"e", "20"};
        String[] alimento5 = {"m", "25"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(30, jogadores, alimentos);

        gameManager.moveCurrentPlayer(2, true);

        String[] test = gameManager.getCurrentPlayerInfo();

        File ficheiro = new File("Save");

        gameManager.saveGame(ficheiro);

        gameManager = new GameManager();

        gameManager.loadGame(ficheiro);

        String[] test2 = gameManager.getCurrentPlayerInfo();

        assertEquals(test, test2);
    }

    @Test
    public void testGetGameResults() {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "5"};
        String[] alimento2 = {"a", "4"};
        String[] alimento3 = {"c", "7"};
        String[] alimento4 = {"e", "6"};
        String[] alimento5 = {"m", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(50, jogadores, alimentos);
        gameManager.moveCurrentPlayer(1, true);

        Assert.assertEquals("MovementResult[code=VALID_MOVEMENT, message=null]",
                String.valueOf(gameManager.moveCurrentPlayer(10, true)));

        gameManager.moveCurrentPlayer(1, true);

        Assert.assertEquals("MovementResult[code=VALID_MOVEMENT, message=null]",
                String.valueOf(gameManager.moveCurrentPlayer(10, true)));

        gameManager.moveCurrentPlayer(1, true);

        Assert.assertEquals("MovementResult[code=NO_ENERGY, message=null]",
                String.valueOf(gameManager.moveCurrentPlayer(20, true)));

        assertNull(gameManager.getWinnerInfo());

        Assert.assertEquals("[#1 Maria, Passaro, 11, 10, 0, #2 Pedro, Tarzan, 11, 10, 0, " +
                        "#3 João, Elefante, 3, 2, 1, #4 Manuel, Tartaruga, 2, 1, 1]",
                String.valueOf(gameManager.getGameResults()));
    }

    @Test
    public void testEnergiaNegativa() {
        GameManager game = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Manuel", "T"};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"b", "28"};


        String[][] jogadores = {jogador1, jogador2};
        String[][] alimentos = {alimento1, alimento2};

        game.createInitialJungle(45, jogadores, alimentos);

        System.out.println(game.moveCurrentPlayer(6, true));
        game.moveCurrentPlayer(0, true);


        System.out.println(game.moveCurrentPlayer(6, true));
        game.moveCurrentPlayer(0, true);

        System.out.println(game.moveCurrentPlayer(6, true));
        game.moveCurrentPlayer(0, true);

        System.out.println(game.moveCurrentPlayer(6, true));
        game.moveCurrentPlayer(0, true);

        System.out.println(game.moveCurrentPlayer(3, true));
        game.moveCurrentPlayer(0, true);

        System.out.println(Arrays.deepToString(game.getPlayersInfo()));

    }

    @Test
    public void testAgua() {
        int numJogada = 0;

        GameManager gameManager = new GameManager();
        Agua agua = new Agua();


        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "5"};
        String[] alimento2 = {"a", "4"};
        String[] alimento3 = {"c", "7"};
        String[] alimento4 = {"e", "6"};
        String[] alimento5 = {"m", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        gameManager.createInitialJungle(20, jogadores, alimentos);

        Assert.assertEquals(0,agua.getQuantidadeBananas());

        Assert.assertEquals("Agua : + 15U|20% energia", agua.getToolTip(numJogada));

        Assert.assertEquals(195, agua.calcularEnergia(180,"h", 0, 0));

        Assert.assertEquals(216, agua.calcularEnergia(180,"o", 0, 0));

        Assert.assertEquals(195, agua.calcularEnergia(180,"c", 0, 0));

        Assert.assertEquals(-1, agua.calcularEnergia(180,"l", 0, 0));

        Assert.assertEquals("[Agua]", Arrays.toString(agua.getSaveInfo()));
    }

    @Test
    public void testCachoBananas() {
        int numJogada = 0;

        GameManager gameManager = new GameManager();
        CachoBananas cachoBananas = new CachoBananas();


        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[] alimento1 = {"b", "5"};
        String[] alimento2 = {"a", "4"};
        String[] alimento3 = {"c", "7"};
        String[] alimento4 = {"e", "6"};
        String[] alimento5 = {"m", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        String[][] alimentos = {alimento1, alimento2, alimento3, alimento4, alimento5};

        Assert.assertEquals(3, cachoBananas.getQuantidadeBananas());

        Assert.assertEquals("Bananas : " + cachoBananas.getQuantidadeBananas() + " : + 40 energia",
                cachoBananas.getToolTip(numJogada));

        assertEquals(-1,cachoBananas.calcularEnergia(180,"l", 0, 0));

        assertEquals(220, cachoBananas.calcularEnergia(180,"h", 0, 0));

        assertEquals(220, cachoBananas.calcularEnergia(180,"o", 0, 0));

        assertEquals(220, cachoBananas.calcularEnergia(180,"c", 0, 0));

        assertEquals(180, cachoBananas.calcularEnergia(180,"h", -1, 0));

        assertEquals(180, cachoBananas.calcularEnergia(180,"h", 2, 0));

        assertEquals(180, cachoBananas.calcularEnergia(180,"o", 3, 0));

        Assert.assertEquals("[Bananas, 0]", Arrays.toString(cachoBananas.getSaveInfo()));

    }

}
