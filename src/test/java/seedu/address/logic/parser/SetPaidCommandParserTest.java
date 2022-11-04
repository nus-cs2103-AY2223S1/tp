package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetPaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetPaidCommandParserTest {

    private SetPaidCommandParser parser = new SetPaidCommandParser();

    @Test
    public void checkCommandCreated() throws ParseException {
        String testInput = "1";
        SetPaidCommand expectedCommand = new SetPaidCommand(Index.fromOneBased(1));
        assertEquals(expectedCommand, parser.parse(testInput));
    }

    @Test
    public void checkParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetPaidCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "@/.!", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetPaidCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "@/xz", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetPaidCommand.MESSAGE_USAGE));
    }
}
