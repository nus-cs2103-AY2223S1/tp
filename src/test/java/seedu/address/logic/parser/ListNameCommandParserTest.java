package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.NameContainsKeywordsPredicate;

public class ListNameCommandParserTest {

    private ListNameCommandParser parser = new ListNameCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListNameCommand() {
        // no leading and trailing whitespaces
        ListNameCommand expectedListNameCommand =
                new ListNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedListNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedListNameCommand);
    }

    @Test
    public void getPredicate_emptyArg_throwsParseException() {
        try {
            parser.getPredicate("     ");
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListNameCommand.MESSAGE_USAGE), pe.getMessage());
        }
    }

    @Test
    public void getPredicate_validArgs_returnsPredicate() {
        // no leading and trailing whitespaces
        NameContainsKeywordsPredicate expectedNameContainsKeywordPredicate = new NameContainsKeywordsPredicate(
                Arrays.asList("Alice", "Bob"));
        try {
            NameContainsKeywordsPredicate predicate = parser.getPredicate("Alice Bob");
            assertEquals(expectedNameContainsKeywordPredicate, predicate);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }

        // multiple whitespaces between keywords
        try {
            NameContainsKeywordsPredicate predicate = parser.getPredicate(" \n Alice \n \t Bob  \t");
            assertEquals(expectedNameContainsKeywordPredicate, predicate);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}
