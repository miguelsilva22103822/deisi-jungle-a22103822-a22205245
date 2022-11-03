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

        assertEquals(4, gameManager.getPlayerIds(0).length);
    }

}
