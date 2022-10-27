package seedu.waddle.model.text;

/**
 * This class contains methods for text related operations.
 */
public class Text {
    public static final int INDENT_NONE = 0;
    public static final int INDENT_TWO = 2;
    public static final int INDENT_FOUR = 4;

    /**
     * Indents the input text by specified amount of spaces.
     *
     * @param text    Text to indent.
     * @param indents Number of spaces to indent.
     * @return The indented text.
     */
    public static String indent(String text, int indents) {
        String indentText = " ".repeat(indents);
        if (indents == 0 || text.equals("")) {
            return text;
        }
        return indentText + text.replaceAll(System.lineSeparator(), System.lineSeparator() + indentText);
    }
}
