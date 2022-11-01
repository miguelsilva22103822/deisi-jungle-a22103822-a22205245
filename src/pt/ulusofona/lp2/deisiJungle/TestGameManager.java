package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestGameManager {

    @Test
    public void testCreateInitialJungle() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "asd", "A"};
        String[] jog2 = {"4", "joao", "E"};
        String[] jog3 = {"8", "asd", "A"};
        String[] jog4 = {"3", "joao", "E"};

        String[][] jogadores = {jog1, jog2, jog3, jog4};

        assertFalse(gameManager.createInitialJungle(5, 5, jogadores));
    }


}
