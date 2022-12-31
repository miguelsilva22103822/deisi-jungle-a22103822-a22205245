package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

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

        Assert.assertEquals("MovementResult[code=CAUGHT_FOOD, message=Apanhou Bananas]",
                String.valueOf(game.moveCurrentPlayer(6, true)));
        game.moveCurrentPlayer(0, true);

        Assert.assertEquals("MovementResult[code=VALID_MOVEMENT, message=null]",
                String.valueOf(game.moveCurrentPlayer(6, true)));
        game.moveCurrentPlayer(0, true);

        Assert.assertEquals("MovementResult[code=VALID_MOVEMENT, message=null]",
                String.valueOf(game.moveCurrentPlayer(6, true)));
        game.moveCurrentPlayer(0, true);

        Assert.assertEquals("MovementResult[code=VALID_MOVEMENT, message=null]",
                String.valueOf(game.moveCurrentPlayer(6, true)));
        game.moveCurrentPlayer(0, true);

        Assert.assertEquals("MovementResult[code=NO_ENERGY, message=null]",
                String.valueOf(game.moveCurrentPlayer(6, true)));
        game.moveCurrentPlayer(0, true);

        game.moveCurrentPlayer(3, true);
        Assert.assertEquals("[[1, João, P, 0, 5..6], [3, Manuel, T, 175, 1..3]]",
                Arrays.deepToString(game.getPlayersInfo()));

    }

    @Test
    public void testAgua() {
        int numJogada = 0;

        Agua agua = new Agua();

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

        CachoBananas cachoBananas = new CachoBananas();

        Assert.assertEquals(3, cachoBananas.getQuantidadeBananas());

        Assert.assertEquals("Bananas : " + cachoBananas.getQuantidadeBananas() + " : + 40 energia",
                cachoBananas.getToolTip(numJogada));

        assertEquals(-1,cachoBananas.calcularEnergia(180,"l", 0, 0));

        assertEquals(220, cachoBananas.calcularEnergia(180,"h", 0, 0));

        assertEquals(220, cachoBananas.calcularEnergia(180,"o", 0, 0));

        assertEquals(220, cachoBananas.calcularEnergia(180,"c", 0, 0));

        assertEquals(180, cachoBananas.calcularEnergia(180,"h", -1, 0));

        assertEquals(180, cachoBananas.calcularEnergia(180,"h", 2, 0));

        Assert.assertEquals("[Bananas, 0]", Arrays.toString(cachoBananas.getSaveInfo()));
    }

    @Test
    public void testCarne() {
        int numJogada = 0;

        Carne carne = new Carne();

        Assert.assertEquals(0,carne.getQuantidadeBananas());

        Assert.assertEquals("Carne toxica", carne.getToolTip(13));

        Assert.assertEquals("Carne : + 50 energia : " + numJogada + " jogadas", carne.getToolTip(numJogada));

        Assert.assertEquals(180, carne.calcularEnergia(180,"h",0,numJogada));

        Assert.assertEquals(90, carne.calcularEnergia(180,"o",0,13));

        Assert.assertEquals(230, carne.calcularEnergia(180,"c",0,10));

        Assert.assertEquals(-1, carne.calcularEnergia(180,"l",0,numJogada));

        Assert.assertEquals("[Carne]", Arrays.toString(carne.getSaveInfo()));
    }

    @Test
    public void testCasa() {
        String idAlimento = " ";
        int tamanhoMax = 4;

        GameManager game = new GameManager();
        Casa casa = new Casa();


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

        assertEquals("[]", Arrays.toString(casa.getIDsJogadores()));

        assertFalse(casa.addAlimento("k"));

        assertTrue(casa.addAlimento("b"));
        assertFalse(casa.addAlimento("c"));

        casa.addJogador(1);
        casa.addJogador(3);
        casa.addJogador(5);
        casa.addJogador(2);
        casa.addJogador(4);
        assertFalse(casa.addJogador(7));

    }

    @Test
    public void testCogumelo() {
        int numJogada = 0;

        Cogumelo cogumelo = new Cogumelo();

        Assert.assertEquals(0,cogumelo.getQuantidadeBananas());

        Assert.assertEquals(-1, cogumelo.calcularEnergia(180,"k",0,numJogada));

        Assert.assertEquals(Arrays.toString(cogumelo.getSaveInfo()), Arrays.toString(cogumelo.getSaveInfo()));
    }

    @Test
    public void testErva() {
        int numJogada = 0;

        Erva erva = new Erva();

        Assert.assertEquals(0,erva.getQuantidadeBananas());

        Assert.assertEquals("Erva : +- 20 energia",erva.getToolTip(numJogada));

        Assert.assertEquals(200,erva.calcularEnergia(180,"h",0,0));

        Assert.assertEquals(90,erva.calcularEnergia(70,"o",0,0));

        Assert.assertEquals(160,erva.calcularEnergia(180,"c",0,0));

        Assert.assertEquals(-1,erva.calcularEnergia(180,"k",0,0));

        Assert.assertEquals("[Erva]", Arrays.toString(erva.getSaveInfo()));
    }

    @Test
    public void testEsquilo() {

        Esquilo esquilo = new Esquilo();

        Assert.assertEquals("h", esquilo.getDieta());

        Assert.assertEquals(120,esquilo.getEnergiaInicial());

        Assert.assertEquals(8, esquilo.getConsumoEnergia());

        Assert.assertEquals(15,esquilo.getEnergiaDescanso());

        Assert.assertEquals("2..6", esquilo.getVelocidade());
    }

    @Test
    public void testFrutoSeco() {
        FrutoSeco frutoSeco = new FrutoSeco();

        Assert.assertEquals(0,frutoSeco.getQuantidadeBananas());

        Assert.assertEquals("Fruto Seco : + 5 energia", frutoSeco.getToolTip(0));

        Assert.assertEquals(105, frutoSeco.calcularEnergia(70,"h",0,0));

        Assert.assertEquals(77, frutoSeco.calcularEnergia(70,"o",0,0));

        Assert.assertEquals(55, frutoSeco.calcularEnergia(70,"c",0,0));

        Assert.assertEquals(-1, frutoSeco.calcularEnergia(70,"k",0,0));

        Assert.assertEquals("[Fruto Seco]", Arrays.toString(frutoSeco.getSaveInfo()));
    }

    @Test
    public void testLeao() {
        Leao leao = new Leao();

        Assert.assertEquals("c", leao.getDieta());
    }

    @Test
    public void testMapa() {
        Mapa mapa = new Mapa(1);

        Assert.assertEquals(-1, mapa.findNrCasaContaining(0));
    }

    @Test
    public void testJogador() {

    }

}
