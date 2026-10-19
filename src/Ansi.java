/**
 * Contains ANSI codes for various console debug and text coloring utilities.
 * @author Isaac Tsung
 * @version 10/19/2026
 */
public class Ansi {
    private static final String CLEAR_CONSOLE = "\033[H\033[2J\033[3J";
    private static final String RESET = "\u001B[0m";

    /**
     * Console text colors. Each constant stores the ANSI code that selects it.
     */
    public enum COLORS {
        /** Red text. */
        RED("\u001B[31m"),
        /** Green text. */
        GREEN("\u001B[32m"),
        /** Yellow text. */
        YELLOW("\u001B[33m"),
        /** Blue text. */
        BLUE("\033[34m");

        private final String code;

        /**
         * Stores the ANSI code for this color.
         *
         * @param code
         *            the ANSI color code
         */
        COLORS(String code) {
            this.code = code;
        }
    }

    /**
     * Prevents instantiation.
     */
    private Ansi() {
    }

    /**
     * Wraps text in a color code and a reset so later output is uncolored.
     *
     * @param color
     *            the color to apply
     * @param text
     *            the text to color
     * @return {@code text} with {@code color} applied
     * @throws NullPointerException
     *             if {@code color} or {@code text} is null
     */
    public static String paintText(COLORS color, String text) {
        if (text == null || color == null) {
            throw new NullPointerException();
        }
        return color.code + text + RESET;
    }

    /**
     * Clears the console and its scrollback, then moves the cursor home.
     */
    public static void clearConsole() {
        System.out.print(CLEAR_CONSOLE);
        System.out.flush();
    }
}
