package seedu.waddle.model.text;

public class Text {
    public static final int indentNone = 0;
    public static final int indentTwo = 2;
    public static final int indentFour = 4;

    public static String indent(String text, int indents) {
        if (indents == 0) {
            return text;
        }
        return " ".repeat(indents) + text;
    }
}
