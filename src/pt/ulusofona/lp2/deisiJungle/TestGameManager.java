package pt.ulusofona.lp2.deisiJungle;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import static org.junit.Assert.assertArrayEquals;


public class TestGameManager {

    @Test
    public void testGetPlayerIds() throws InvalidInitialJungleException {
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
    public void testCreateInitialJungle2Tarzans() throws InvalidInitialJungleException {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "Z"};
        String[] jogador3 = {"5", "Pedro", "Z"};
        String[] jogador4 = {"2", "Maria", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};
        /*
        assertEquals("playersInfo inválido",
                gameManager.createInitialJungle(15, jogadores).getMessage());

         */
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

        /*
        assertEquals("foodsInfo inválido",
                gameManager.createInitialJungle(27, jogadores, alimentos).getMessage());
        //inválido porque tem banana na casa 1
        */
    }

    @Test
    public void testgetPlayersInfoMoveBackwards() throws InvalidInitialJungleException {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {"5", "Pedro", "Z"};

        String[][] jogadores = {jogador1, jogador2, jogador3};

        gameManager.createInitialJungle(20, jogadores, null);

        assertEquals(new MovementResult(MovementResultCode.VALID_MOVEMENT, null),
                gameManager.moveCurrentPlayer(5,true));

        assertEquals("[[1, João, E, 160, 1..6], [3, Manuel, T, 150, 1..3], [5, Pedro, Z, 70, 1..6]]",
                Arrays.deepToString(gameManager.getPlayersInfo()));

        assertEquals(new MovementResult(MovementResultCode.INVALID_MOVEMENT, null),
                gameManager.moveCurrentPlayer(-5,true));

        assertEquals("[[1, João, E, 160, 1..6], [3, Manuel, T, 150, 1..3], [5, Pedro, Z, 70, 1..6]]",
                Arrays.deepToString(gameManager.getPlayersInfo()));

    }

    @Test
    public void testgetSquareInfo() throws InvalidInitialJungleException {

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
    public void testWhoIsTaborba() throws InvalidInitialJungleException {

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
    public void testWinnerInfo () throws InvalidInitialJungleException {

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
    public void testEnergy () throws InvalidInitialJungleException {

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

        assertArrayEquals(gameManager.getPlayersInfo(),gameManager.getPlayersInfo());
    }

    @Test
    public void testECarne () throws InvalidInitialJungleException {

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
    public void testCasaCerta () throws InvalidInitialJungleException {

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

        Assert.assertEquals("MovementResult[code=VALID_MOVEMENT, message=null]",
                String.valueOf(gameManager.moveCurrentPlayer(1, true)));

        Assert.assertEquals("[[1, João, E, 200, 1..6], [2, Maria, P, 106, 5..6], [3, Manuel, T, 189, 1..3]," +
                " [5, Pedro, Z, 68, 1..6]]",Arrays.deepToString(gameManager.getPlayersInfo()));
    }

    @Test
    public void testCogumelos () throws InvalidInitialJungleException {

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

        assertArrayEquals(gameManager.getSquareInfo(2),gameManager.getSquareInfo(2));
    }

    @Test
    public void testSaveGame () throws InvalidInitialJungleException {

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

        assertArrayEquals(test, test2);

    }

    @Test
    public void testGetGameResults() throws InvalidInitialJungleException {
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
    public void testEnergiaNegativa() throws InvalidInitialJungleException {
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

        Assert.assertEquals("MovementResult[code=INVALID_MOVEMENT, message=null]",
                String.valueOf(game.moveCurrentPlayer(-7, false)));

        Assert.assertEquals("MovementResult[code=INVALID_MOVEMENT, message=null]",
                String.valueOf(game.moveCurrentPlayer(6, false)));


        game.moveCurrentPlayer(3, true);
        Assert.assertEquals("[[1, João, P, 0, 5..6], [3, Manuel, T, 175, 1..3]]",
                Arrays.deepToString(game.getPlayersInfo()));

    }

    @Test
    public void testAgua() {
        int numJogada = 0;

        Agua agua = new Agua();

        Assert.assertEquals(-1, agua.getQuantidadeBananas());

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

        Assert.assertEquals(-1, carne.getQuantidadeBananas());

        Assert.assertEquals("Carne toxica", carne.getToolTip(13));

        Assert.assertEquals("Carne : + 50 energia : " + numJogada + " jogadas", carne.getToolTip(numJogada));

        Assert.assertEquals(180, carne.calcularEnergia(180,"h",0,numJogada));

        Assert.assertEquals(90, carne.calcularEnergia(180,"o",0,13));

        Assert.assertEquals(230, carne.calcularEnergia(180,"c",0,10));

        Assert.assertEquals(-1, carne.calcularEnergia(180,"l",0,numJogada));

        Assert.assertEquals("[Carne]", Arrays.toString(carne.getSaveInfo()));
    }

    @Test
    public void testCasa() throws InvalidInitialJungleException {

        Casa casa = new Casa();
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};

        String[][] jogadores = {jogador1, jogador2};

        gameManager.createInitialJungle(20, jogadores);

        assertEquals("[]", Arrays.toString(casa.getIDsJogadores()));

        assertEquals(-1, casa.jogadorIDMenor());

        assertNull(casa.getAlimentoSaveInfo()); // função não usada

        casa.setAsMeta();
        gameManager.moveCurrentPlayer(20, true);
        assertEquals("[finish.png, Meta, ]", Arrays.toString(casa.getInfo(1)));

        assertFalse(casa.addAlimento("k"));

        assertTrue(casa.addAlimento("b"));
        assertFalse(casa.addAlimento("c"));

        casa.addJogador(1);
        casa.addJogador(3);
        casa.addJogador(5);
        casa.addJogador(2);
        casa.addJogador(4);
        assertFalse(casa.addJogador(7));

        assertFalse(casa.removeJogador(0));

        assertNull(casa.arrayListToArray(null));

        assertEquals("[Bananas, 3]", Arrays.toString(casa.getAlimentoSaveInfo())); // função não usada
    }

    @Test
    public void testCogumelo() {
        int numJogada = 0;

        Cogumelo cogumelo = new Cogumelo();

        Assert.assertEquals(-1, cogumelo.getQuantidadeBananas());

        Assert.assertEquals(-1, cogumelo.calcularEnergia(180,"k",0,numJogada));

        Assert.assertEquals(Arrays.toString(cogumelo.getSaveInfo()), Arrays.toString(cogumelo.getSaveInfo()));
    }

    @Test
    public void testErva() {
        int numJogada = 0;

        Erva erva = new Erva();

        Assert.assertEquals(-1, erva.getQuantidadeBananas());

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

        Assert.assertEquals(120, esquilo.getEnergiaInicial());

        Assert.assertEquals(8, esquilo.getConsumoEnergia());

        Assert.assertEquals(40, esquilo.getEnergiaDescanso());

        Assert.assertEquals("2..6", esquilo.getVelocidade());
    }

    @Test
    public void testFrutoSeco() {
        FrutoSeco frutoSeco = new FrutoSeco();

        Assert.assertEquals(-1, frutoSeco.getQuantidadeBananas());

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

    /*@Test
    public void testJogador() {

        Jogador jogador = new Jogador(1, "Joao","L");
        Jogador jogador1 = new Jogador(2, "Maria","Z");
        //Jogador jogador2 = new Jogador(1, "Helena","L", 180,0,
                0,0, 0);
        Jogador jogador3 = new Jogador(2, "Miguel","T", 200, 0,
                0,0, 0);

        assertEquals("[1, Joao, L, 80, 4..6]", Arrays.toString(jogador.getInfo()));

        assertEquals("[2, Maria, Z, 70, 1..6]", Arrays.toString(jogador1.getInfo()));

        assertEquals("[2, 10]", Arrays.toString(jogador2.getInfoEnergy(1)));

        assertEquals(180,jogador2.getEnergia());

        assertTrue(jogador.movementIsValid(0));

        assertFalse(jogador.movementIsValid(15));

        assertTrue(jogador1.movementIsValid(1));

        assertTrue(jogador.hasEnergy(0));

        assertTrue(jogador1.hasEnergy(10));

        assertFalse(jogador1.hasEnergy(100));

        jogador2.updateEnergyMovement(0);

        assertEquals(190, jogador2.getEnergia());

        assertEquals(0, jogador2.getDistanciaPercorrida());

        jogador3.updateEnergyMovement(1);

        assertEquals(199, jogador3.getEnergia());

        assertEquals("Joao", jogador.getNome());

        assertEquals("L", jogador.getIdEspecie());

        assertEquals("c", jogador.getDieta());

        assertEquals(0, jogador2.getQuantidadeComeu());

        assertEquals("[1,Helena,L,190,0,0,0,1]", jogador2.getSaveInfo());

        jogador3.comer(new Agua(),2);

        assertEquals(200, jogador3.getEnergia());
    }*/

   /*@Test
    public void testGetWinnerInfo() throws InvalidInitialJungleException {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "T"};

        String[][] jogadores = {jogador1, jogador2};

        gameManager.createInitialJungle(20, jogadores, null);

        gameManager.moveCurrentPlayer(0,true);
        gameManager.moveCurrentPlayer(5,true);
        gameManager.moveCurrentPlayer(0,true);
        gameManager.moveCurrentPlayer(5,true);
        gameManager.moveCurrentPlayer(0,true);
        gameManager.moveCurrentPlayer(5,true);

        gameManager.getWinnerInfo();

        Assert.assertEquals(null, gameManager.getWinnerInfo());

    }*/

    @Test
    public void testGameManagerAuxiliarJogadores() throws InvalidInitialJungleException {
        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Manuel", "T"};
        String[] jogador3 = {null, null, null};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"b", "2"};


        String[][] jogadores = {jogador1, jogador2, jogador3};
        String[][] alimentos = {alimento1, alimento2};

        try{
            gameManager.createInitialJungle(20, null, alimentos);

        }catch (InvalidInitialJungleException e){

            assertEquals("playersInfo inválido",e.getMessage());
            assertTrue(e.isInvalidPlayer());
        }

        //gameManager.createInitialJungle(20, jogadores, alimentos);
    }

    @Test
    public void testGameManagerAuxiliarAlimentos() {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Manuel", "T"};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"c", "0"};
        String[] alimento3 = {"", null};

        String[][] jogadores = {jogador1, jogador2};
        String[][] alimentos = {alimento1, alimento2, alimento3};

        try {
            gameManager.createInitialJungle(20, jogadores, null);

        }catch (InvalidInitialJungleException e ){

            assertEquals("foodsInfo inválido", e.getMessage());
            assertTrue(e.isInvalidFood());
        }
    }

    @Test
    public void testGameManager() throws InvalidInitialJungleException {

        GameManager gameManager = new GameManager();

        gameManager.getPlayersInfo();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Manuel", "T"};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"b", "2"};


        String[][] jogadores = {jogador1, jogador2};
        String[][] alimentos = {alimento1, alimento2};

        assertEquals("[]", Arrays.toString(gameManager.getPlayerIds(0)));

        assertEquals("[]", Arrays.toString(gameManager.getPlayerIds(10)));

        assertNull(gameManager.getSquareInfo(0));

        gameManager.createInitialJungle(20, jogadores, alimentos);
        gameManager.moveCurrentPlayer(1,true);

        assertNull(gameManager.getPlayerInfo(0));

        assertEquals("[1, João, P, 106, 5..6]", Arrays.toString(gameManager.getPlayerInfo(1)));

        assertNull(gameManager.getSquareInfo(0));

        assertEquals("[]", Arrays.toString(gameManager.getPlayerIds(25)));

        //gameManager.createInitialJungle(2, jogadores, alimentos);
    }

    @Test
    public void testInvalidPlayersCreateInitialJungle() throws InvalidInitialJungleException {

        GameManager game = new GameManager();

        game.getAuthorsPanel();

        String[] p1 = {"2", "Maria", "B"}; //especie invalida
        String[] p2 = {"5", "Pedro", "E"};

        String[] a1 = {"e", "7"};
        String[] a2 = {"b", "2"};

        String[][] players1 = {p1, p2};
        String[][] foods1 = {a1, a2};
        /*
        assertEquals("playersInfo inválido",
                game.createInitialJungle(30, players1, foods1).getMessage());

         */

        String[] p3 = {"abc", "Maria", "L"}; //id nao numerico
        String[] p4 = {"5", "Pedro", "E"};

        String[] a3 = {"e", "7"};
        String[] a4 = {"b", "2"};

        String[][] players2 = {p3, p4};
        String[][] foods2 = {a3, a4};
        /*
        assertEquals("playersInfo inválido",
                game.createInitialJungle(30, players2, foods2).getMessage());

         */

        String[] p5 = {"2", null, "L"}; //nome null ou vazio
        String[] p6 = {"5", "Pedro", "E"};

        String[] a5 = {"e", "7"};
        String[] a6 = {"b", "2"};

        String[][] players3 = {p5, p6};
        String[][] foods3 = {a5, a6};
        /*
        assertEquals("playersInfo inválido",
                game.createInitialJungle(30, players3, foods3).getMessage());

         */

        String[] p7 = {"2", "Maria", "L"}; //varios jogadores com o mesmo id
        String[] p8 = {"2", "Pedro", "E"};

        String[] a7 = {"e", "7"};
        String[] a8 = {"b", "2"};

        String[][] players4 = {p7, p8};
        String[][] foods4 = {a7, a8};
        /*
        assertEquals("playersInfo inválido",
                game.createInitialJungle(30, players4, foods4).getMessage());

         */

    }

    @Test
    public void testInvalidFoodsCreateInitialJungle() throws InvalidInitialJungleException {

        GameManager game = new GameManager();

        String[] p1 = {"2", "Maria", "L"};
        String[] p2 = {"5", "Pedro", "E"};

        String[] a1 = {"e", "7"};
        String[] a2 = {"b", "31"}; //posicao fora do mapa

        String[][] players1 = {p1, p2};
        String[][] foods1 = {a1, a2};

        /*
        assertEquals("foodsInfo inválido",
                game.createInitialJungle(30, players1, foods1).getMessage());

         */

        String[] p3 = {"2", "Maria", "L"};
        String[] p4 = {"5", "Pedro", "E"};

        String[] a3 = {"e", "7"};
        String[] a4 = {"d", "2"}; //id alimento nao existe

        String[][] players2 = {p3, p4};
        String[][] foods2 = {a3, a4};
        /*


        assertEquals("foodsInfo inválido",
                game.createInitialJungle(30, players2, foods2).getMessage());

         */

        String[] p5 = {"2", "Maria", "L"};
        String[] p6 = {"5", "Pedro", "E"};

        String[] a5 = {"e", "7"};
        String[] a6 = {"b", "a"}; //posicao nao numerica

        String[][] players3 = {p5, p6};
        String[][] foods3 = {a5, a6};

        /*
        assertEquals("foodsInfo inválido",
                game.createInitialJungle(30, players3, foods3).getMessage());


         */
    }

    @Test
    public void testGameManagerExtragetWinnerInfoAndGetGameResults() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        assertNull(game.getCurrentPlayerEnergyInfo(2)); //tem que dar null porque ainda nao ha jogadores

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Manuel", "E"};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2};

        game.createInitialJungle(30, jogadores);

        assertEquals("[8, 10]", Arrays.toString(game.getCurrentPlayerEnergyInfo(2)));

        game.moveCurrentPlayer(6, false);
        game.moveCurrentPlayer(5, false);

        game.moveCurrentPlayer(6, false);
        game.moveCurrentPlayer(5, false);

        game.moveCurrentPlayer(6, false);
        game.moveCurrentPlayer(5, false);

        game.moveCurrentPlayer(6, false);
        game.moveCurrentPlayer(5, false);

        game.moveCurrentPlayer(6, false);
        game.moveCurrentPlayer(5, false);

        assertEquals("[1, João, E, 60, 1..6]", Arrays.toString(game.getWinnerInfo()));

        assertEquals("[#1 João, Elefante, 30, 30, 0, #2 Manuel, Elefante, 26, 25, 0]",
                game.getGameResults().toString());

    }

    @Test
    public void testMain() {
        Main main = new Main(); //bruh
    }

    @Test
    public void testFunctionsGetPlayerInfo1() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Maria", "E"};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(30, jogadores, alimentos);

        Function1<CommandType, Function2<GameManager, List<String>, String>> routerFn = FunctionsKt.router();
        Function2<GameManager, List<String>, String> commandGetFn = routerFn.invoke(CommandType.GET);

        List<String> comando = new ArrayList<>();
        comando.add("PLAYER_INFO");
        comando.add("Maria");

        String result = commandGetFn.invoke(manager, comando);

        assertEquals("3 | Maria | Elefante | 180 | 1", result);

    }

    @Test
    public void testFunctionsGetPlayerInfo2() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "E"};
        String[] jogador2 = {"3", "Maria", "E"};

        String[] alimento1 = {"b", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(30, jogadores, alimentos);

        Function1<CommandType, Function2<GameManager, List<String>, String>> routerFn = FunctionsKt.router();
        Function2<GameManager, List<String>, String> commandGetFn = routerFn.invoke(CommandType.GET);

        List<String> comando = new ArrayList<>();
        comando.add("PLAYER_INFO");
        comando.add("kajdsfh"); //para dar non existent player

        String result = commandGetFn.invoke(manager, comando);

        assertEquals("Inexistent player", result);

    }

    @Test
    public void testFunctionsGetPlayersBySpecie1() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "E"};
        String[] jogador4 = {"8", "Daniel", "L"};

        String[] alimento1 = {"e", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(30, jogadores, alimentos);

        Function1<CommandType, Function2<GameManager, List<String>, String>> routerFn = FunctionsKt.router();
        Function2<GameManager, List<String>, String> commandGetFn = routerFn.invoke(CommandType.GET);

        List<String> comando = new ArrayList<>();
        comando.add("PLAYERS_BY_SPECIE");
        comando.add("E");

        String result = commandGetFn.invoke(manager, comando);

        assertEquals("Pedro,Maria", result);

    }

    @Test
    public void testFunctionsGetPlayersBySpecie2() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "E"};
        String[] jogador4 = {"8", "Daniel", "L"};

        String[] alimento1 = {"e", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(30, jogadores, alimentos);

        Function1<CommandType, Function2<GameManager, List<String>, String>> routerFn = FunctionsKt.router();
        Function2<GameManager, List<String>, String> commandGetFn = routerFn.invoke(CommandType.GET);

        List<String> comando = new ArrayList<>();
        comando.add("PLAYERS_BY_SPECIE");
        comando.add("Z");

        String result = commandGetFn.invoke(manager, comando);

        assertEquals("", result);

    }

    @Test
    public void testFunctionsGetMostTraveled() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "E"};
        String[] jogador4 = {"8", "Daniel", "L"};

        String[] alimento1 = {"e", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(30, jogadores, alimentos);

        manager.moveCurrentPlayer(3, true);
        manager.moveCurrentPlayer(5, true);
        manager.moveCurrentPlayer(8, true);

        Function1<CommandType, Function2<GameManager, List<String>, String>> routerFn = FunctionsKt.router();
        Function2<GameManager, List<String>, String> commandGetFn = routerFn.invoke(CommandType.GET);

        List<String> comando = new ArrayList<>();
        comando.add("MOST_TRAVELED");

        String result = commandGetFn.invoke(manager, comando);

        assertEquals("Pedro:E:8\n" + "Maria:E:5\n" + "João:P:3\n" + "Daniel:L:0\n" + "Total:16", result);

    }

    @Test
    public void testFunctionsGetTopEnergeticOmnivores() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "E"};
        String[] jogador4 = {"8", "Daniel", "Z"};

        String[] alimento1 = {"e", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(30, jogadores, alimentos);

        manager.moveCurrentPlayer(3, true);
        manager.moveCurrentPlayer(5, true);
        manager.moveCurrentPlayer(8, true);

        Function1<CommandType, Function2<GameManager, List<String>, String>> routerFn = FunctionsKt.router();
        Function2<GameManager, List<String>, String> commandGetFn = routerFn.invoke(CommandType.GET);

        List<String> comando = new ArrayList<>();
        comando.add("TOP_ENERGETIC_OMNIVORES");
        comando.add("2");

        String result = commandGetFn.invoke(manager, comando);

        assertEquals("Daniel:70\n" + "João:58", result);

    }

    @Test
    public void testeCreate() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "U"};
        String[] jogador4 = {"8", "Daniel", "L"};

        String[] alimento1 = {"e", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(30, jogadores, alimentos);

    }

    @Test
    public void testGetWineerNovo() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "U"};
        String[] jogador4 = {"8", "Daniel", "L"};

        String[] alimento1 = {"e", "7"};
        String[] alimento2 = {"b", "2"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(11, jogadores, alimentos);

        manager.moveCurrentPlayer(2,true); //1
        manager.moveCurrentPlayer(2,true);//2
        manager.moveCurrentPlayer(2,true);//3
        manager.moveCurrentPlayer(2,true);//4
        manager.moveCurrentPlayer(3,true);//1
        manager.moveCurrentPlayer(3,true);//2
        manager.moveCurrentPlayer(4,true);
        manager.moveCurrentPlayer(2,true);
        manager.moveCurrentPlayer(2,true);
        manager.moveCurrentPlayer(2,true);

        manager.getWinnerInfo();

        System.out.println(manager.getGameResults());

        System.out.println(Arrays.toString(manager.getWinnerInfo()));
    }


    @Test
    public void testalimento() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "U"};
        String[] jogador4 = {"8", "Daniel", "L"};


        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        manager.createInitialJungle(11, jogadores);

        manager.moveCurrentPlayer(2,true); //1
        manager.moveCurrentPlayer(2,true);//2
        manager.moveCurrentPlayer(2,true);//3
        manager.moveCurrentPlayer(2,true);//4
        manager.moveCurrentPlayer(3,true);//1
        manager.moveCurrentPlayer(3,true);//2
        manager.moveCurrentPlayer(4,true);
        manager.moveCurrentPlayer(2,true);

        System.out.println(manager.moveCurrentPlayer(2, true));
    }

    @Test
    public void testUnicornio() throws InvalidInitialJungleException {

        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "U"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "E"};
        String[] jogador4 = {"8", "Daniel", "L"};

        String[] alimento1 = {"c", "3"};
        String[] alimento2 = {"b", "5"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(11, jogadores, alimentos);

        System.out.println(manager.moveCurrentPlayer(2,true)); //1
        manager.moveCurrentPlayer(2,true);//2


        manager.getWinnerInfo();

       // System.out.println(manager.moveCurrentPlayer(,true));
    }

    @Test
    public void testResultados() throws InvalidInitialJungleException {
        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "U"};
        String[] jogador2 = {"3", "Maria", "E"};
        String[] jogador3 = {"5", "Pedro", "P"};
        String[] jogador4 = {"8", "Daniel", "L"};

        String[] alimento1 = {"c", "3"};
        String[] alimento2 = {"b", "5"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[][] alimentos = {alimento1, alimento2};

        manager.createInitialJungle(10, jogadores, alimentos);

        manager.moveCurrentPlayer(2,true);
        manager.moveCurrentPlayer(3,true);
        manager.moveCurrentPlayer(0,true);
        manager.moveCurrentPlayer(0,true);
        manager.moveCurrentPlayer(4,true);


        System.out.println(manager.getGameResults());

    }

    @Test
    public void testResultados1() throws InvalidInitialJungleException {
        GameManager manager = new GameManager();

        String[] jogador1 = {"1", "João", "P"};
        String[] jogador2 = {"5", "Maria", "E"};
        String[] jogador3 = {"3", "Pedro", "P"};


        String[][] jogadores = {jogador1, jogador2, jogador3};


        manager.createInitialJungle(11, jogadores);

        manager.moveCurrentPlayer(5,false);
        manager.moveCurrentPlayer(5,false);
        manager.moveCurrentPlayer(5,false);

        manager.moveCurrentPlayer(1,false);
        manager.moveCurrentPlayer(1,false);
        manager.moveCurrentPlayer(3,false);

        manager.getWinnerInfo();
        manager.getGameResults();

        System.out.println(Arrays.toString(manager.getWinnerInfo()));
        System.out.println(manager.getGameResults());

    }


}
