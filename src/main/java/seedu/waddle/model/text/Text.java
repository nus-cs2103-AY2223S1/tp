package seedu.waddle.model.text;

import java.util.ArrayList;

public class Text {
    public static final int indentNone = 0;
    public static final int indentTwo = 2;
    public static final int indentFour = 4;

    public static String indent(String text, int indents) {
        String indentText = " ".repeat(indents);
        if (indents == 0 || text.equals("")) {
            return text;
        }
        return indentText + text.replaceAll(System.lineSeparator(), System.lineSeparator() + indentText);
    }
}
