package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import static org.junit.Assert.*;

public class TestCasa {
    @Test
    public void testNrJogadores() {
        Casa casa = new Casa();

        casa.addJogador(1);
        casa.addJogador(7);
        casa.addJogador(4);

        int[] expectedIDs = {1, 7, 4};
        assertArrayEquals(expectedIDs, casa.getIDsJogadores());

        int expectedNrPlayers = 3;
        assertEquals(expectedNrPlayers, casa.nrJogadores());
    }
}
