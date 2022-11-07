package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetUnpaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetUnpaidCommandParserTest {

    private SetUnpaidCommandParser parser = new SetUnpaidCommandParser();

    @Test
    public void checkCommandCreated() throws ParseException {
        String testInput = "1";
        SetUnpaidCommand expectedCommand = new SetUnpaidCommand(Index.fromOneBased(1));
        assertEquals(expectedCommand, parser.parse(testInput));
    }

    @Test
    public void checkParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetUnpaidCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "@/.!", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetUnpaidCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "@/xz", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetUnpaidCommand.MESSAGE_USAGE));
    }
}

