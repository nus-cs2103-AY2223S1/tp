package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.commands.CommandTestUtil.INVALID_ASC_ARGS;
import static seedu.watson.logic.commands.CommandTestUtil.SUBJECT_ARG;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_ASC_ARGS;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.SortCommand;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // missing subject prefix
        assertParseFailure(parser, VALID_ASC_ARGS, expectedMessage);

        // missing ascending or descending argument
        assertParseFailure(parser, SUBJECT_ARG, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        assertParseFailure(parser, INVALID_ASC_ARGS + SUBJECT_ARG, expectedMessage);
    }
}
