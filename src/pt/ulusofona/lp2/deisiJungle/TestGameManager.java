package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestGameManager {

    @Test
    public void testCreateInitialJungle() {

        GameManager gameManager = new GameManager();

        String[] jog1 = {"1", "manel", "T"};
        String[] jog2 = {"2", "joao", "E"};

        String[][] jogadores = {jog1, jog2};

        gameManager.createInitialJungle(5, 5, jogadores);

    }

}
