import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for {@link Ansi}.
 *
 * @author Isaac Tsung
 * @version 10/19/2026
 */
public class AnsiTest {

    /**
     * Both arguments present takes the return branch.
     */
    @Test
    public void testPaintText() {
        assertEquals("\u001B[31mHello\u001B[0m", Ansi.paintText(Ansi.COLORS.RED, "Hello"));
    }

    /**
     * Null text takes the first condition of the guard.
     */
    @Test
    public void testPaintTextNullText() {
        try {
            Ansi.paintText(Ansi.COLORS.RED, null);
            fail("Expected NullPointerException for null text");
        }
        catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }
    }

    /**
     * Null color takes the second condition of the guard.
     */
    @Test
    public void testPaintTextNullColor() {
        try {
            Ansi.paintText(null, "Hello");
            fail("Expected NullPointerException for null color");
        }
        catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }
    }

    /**
     * Red text is written to the console.
     */
    @Test
    public void testRedTextDrawnInConsole() {
        System.out.println(Ansi.paintText(Ansi.COLORS.RED, "I am red."));
    }

    /**
     * Blue text is written to the console.
     */
    @Test
    public void testBlueTextDrawnInConsole() {
        System.out.println(Ansi.paintText(Ansi.COLORS.BLUE, "I am blue."));
    }

    /**
     * Colored lines can be cleared from the console.
     */
    @Test
    public void testConsoleCanClear() {
        System.out.println(Ansi.paintText(Ansi.COLORS.RED, "I am red."));
        System.out.println(Ansi.paintText(Ansi.COLORS.BLUE, "I am blue."));
        Ansi.clearConsole();
    }
}
