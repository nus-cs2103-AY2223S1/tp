package seedu.waddle.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TextTest {

    @Test
    public void indent_empty_string() {
        String emptyString = "";
        assertEquals(emptyString, Text.indent(emptyString, Text.INDENT_FOUR));
    }

    @Test
    public void indent_single_line() {
        String singleLine = "This is a test string.";
        String expected = "    This is a test string.";
        assertEquals(expected, Text.indent(singleLine, Text.INDENT_FOUR));
    }

    @Test
    public void indent_multi_line() {
        String multiLine = "This is a test string."
                + System.lineSeparator() + "This is a test string.";
        String expected = "    This is a test string."
                + System.lineSeparator() + "    This is a test string.";
        assertEquals(expected, Text.indent(multiLine, Text.INDENT_FOUR));
    }

    @Test
    public void indent_indented_multiLine() {
        String multiLine = "This is a test string."
                + System.lineSeparator() + "    This is a test string.";
        String expected = "    This is a test string."
                + System.lineSeparator() + "        This is a test string.";
        assertEquals(expected, Text.indent(multiLine, Text.INDENT_FOUR));
    }

}
