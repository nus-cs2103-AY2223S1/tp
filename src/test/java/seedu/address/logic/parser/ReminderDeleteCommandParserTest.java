package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReminderDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class ReminderDeleteCommandParserTest {
    private ReminderDeleteCommandParser parser = new ReminderDeleteCommandParser();

    @Test
    public void parse_missingIndex_parseException() throws ParseException {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        assertParseSuccess(parser, "1", new ReminderDeleteCommand(Index.fromOneBased(1)));
    }
}
