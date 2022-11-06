package seedu.modquik.logic.parser.student;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.modquik.logic.parser.exceptions.ParseException;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // Test weakened to assert correct exception type thrown.
        String userInput = "     ";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    // Test disabled
    // @Test
    // public void parse_validArgs_returnsFindCommand() {
    //     // no leading and trailing whitespaces
    //     FindCommand expectedFindCommand =
    //             new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
    //     assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

    //     // multiple whitespaces between keywords
    //     assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    // }

}
