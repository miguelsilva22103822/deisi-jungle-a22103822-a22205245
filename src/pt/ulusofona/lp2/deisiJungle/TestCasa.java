package pt.ulusofona.lp2.deisiJungle;

import org.junit.*;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;

public class TestCasa {
    @Test
    public void testGetIDsJogadores() {
        Casa casa = new Casa();

        casa.addJogador(1);
        casa.addJogador(3);
        casa.addJogador(4);
        casa.addJogador(7);


        int[] ids = {1, 3, 4, 7};

        //System.out.println(Arrays.toString(ids));
        //System.out.println(Arrays.toString(casa.getIDsJogadores()));
        assertTrue(Arrays.equals(casa.getIDsJogadores(), ids));

    }

    @Test
    public void testGetIDsJogadores2() {
        Casa casa = new Casa();

        int[] ids = {};

        //System.out.println(Arrays.toString(ids));
        //System.out.println(Arrays.toString(casa.getIDsJogadores()));

        assertTrue(Arrays.equals(casa.getIDsJogadores(), ids));

    }

    @Test
    public void testGetInfo() {
        Casa casa = new Casa();

        //System.out.println(Arrays.toString(casa.getInfo()));
    }

    @Test
    public void testGetInfo2() {
        Casa casa = new Casa();

        casa.addJogador(1);
        casa.addJogador(3);
        casa.addJogador(4);
        casa.addJogador(7);

        //System.out.println(Arrays.toString(casa.getInfo()));
    }
}
