package taskbook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import taskbook.logic.parser.exceptions.ParseException;

public class DateParserTest {

    private final LocalDate expectedOutput = LocalDate.parse("2022-10-31");
    private final LocalDate expectedLeap = LocalDate.parse("2024-02-29");

    @Test
    public void parse_validArgument1_returnsCorrectOutput() throws ParseException {
        LocalDate actual = DateParser.parse("2022-10-31");
        assertEquals(actual, expectedOutput);
    }

    @Test
    public void parse_validArgument2_returnsCorrectOutput() throws ParseException {
        LocalDate actual = DateParser.parse("Oct 31 2022");
        assertEquals(actual, expectedOutput);
    }

    @Test
    public void parse_validArgument3_returnsCorrectOutput() throws ParseException {
        LocalDate actual = DateParser.parse("10 31 2022");
        assertEquals(actual, expectedOutput);
    }

    @Test
    public void parse_validArgument4_returnsCorrectOutput() throws ParseException {
        LocalDate actual = DateParser.parse("31 Oct 2022");
        assertEquals(actual, expectedOutput);
    }

    @Test
    public void parse_validArgument5_returnsCorrectOutput() throws ParseException {
        LocalDate actual = DateParser.parse("29 Feb 2024");
        assertEquals(actual, expectedLeap);
    }

    @Test
    public void parse_emptyArgument_throwsException() {
        assertThrowsException("");
    }

    @Test
    public void parse_invalidInput_throwsException() {
        assertThrowsException("Halloween");
    }

    @Test
    public void parse_invalidFormat_throwsException() {
        assertThrowsException("31st October 2022");
    }

    @Test
    public void parse_invalidDate_throwsException() {
        assertThrowsException("2022-02-29");
        assertThrowsException("2022-02-30");
        assertThrowsException("2022-02-31");
        assertThrowsException("2022-12-32");
        assertThrowsException("2022-00-01");
        assertThrowsException("2022-12-00");
    }

    public void assertThrowsException(String userInput) {
        try {
            DateParser.parse(userInput);
            throw new AssertionError("The expected ParseException is not thrown.");
        } catch (ParseException e) {
            // intentionally empty catch block
        }
    }
}
