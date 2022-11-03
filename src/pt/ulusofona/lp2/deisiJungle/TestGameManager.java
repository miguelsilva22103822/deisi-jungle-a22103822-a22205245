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
        System.out.println(Arrays.toString(gameManager.mapa.getCasa(1).getInfo()));

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

}
