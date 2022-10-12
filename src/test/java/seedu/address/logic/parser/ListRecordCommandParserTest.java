package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListRecordCommand;

public class ListRecordCommandParserTest {

    private ListRecordCommandParser parser = new ListRecordCommandParser();

    @Test
    public void parse_validArgs_returnsListRecordCommand() {
        assertParseSuccess(parser, "1", new ListRecordCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRecordCommand.MESSAGE_USAGE));
    }
}

