package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteReminderCommand;

public class DeleteReminderCommandParserTest {
    private DeleteReminderCommandParser parser = new DeleteReminderCommandParser();

    @Test
    public void parse_parameters_failure() {
        // string specified instead of an index
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteReminderCommand.MESSAGE_USAGE));

        // index < 1
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteReminderCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteReminderCommand.MESSAGE_USAGE));

        // index not an integer
        assertParseFailure(parser, "1.0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteReminderCommand.MESSAGE_USAGE));

        // index not an integer
        assertParseFailure(parser, "1.923", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteReminderCommand.MESSAGE_USAGE));
    }

}
