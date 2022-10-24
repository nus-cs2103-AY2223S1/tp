package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ReminderCommandGroupParser.MESSAGE_USAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReminderClearCommand;
import seedu.address.logic.commands.ReminderDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class ReminderCommandGroupParserTest {
    private ReminderCommandGroupParser parser = new ReminderCommandGroupParser();

    @Test
    public void parse_emptyArguments_parseException() throws ParseException {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_clearCommand_success() {
        assertParseSuccess(parser, "c", new ReminderClearCommand());
        assertParseSuccess(parser, "clear", new ReminderClearCommand());
    }

    @Test
    public void parse_deleteCommand_success() throws ParseException {
        assertParseFailure(parser, "d", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ReminderDeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "delete", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ReminderDeleteCommand.MESSAGE_USAGE));

        assertParseSuccess(parser, "d 2", new ReminderDeleteCommandParser().parse("2"));
        assertParseSuccess(parser, "delete 1", new ReminderDeleteCommandParser().parse("1"));
    }
}
