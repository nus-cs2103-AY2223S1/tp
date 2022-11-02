package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddResponseCommand;
import seedu.address.model.student.Response;



public class AddResponseCommandParserTest {

    private AddResponseCommandParser parser = new AddResponseCommandParser();

    @Test
    public void parse_validArgs_returnsAddResponseCommand() {
        assertParseSuccess(parser, "1 m/7", new AddResponseCommand(INDEX_FIRST_STUDENT, new Response("7")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddResponseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefix_throwsParseException() {
        assertParseFailure(parser, "1", AddResponseCommand.MESSAGE_MISSING_PREFIX);
    }

    @Test
    public void parse_invalidIndexAndMissingPrefix_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddResponseCommand.MESSAGE_USAGE));
    }
}
