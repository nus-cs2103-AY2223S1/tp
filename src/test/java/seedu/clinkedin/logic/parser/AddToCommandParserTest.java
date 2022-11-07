package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.AddToCommand;

public class AddToCommandParserTest {

    private final AddToCommandParser parser = new AddToCommandParser();
    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "addTo",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "addTo 1 n/name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noAttribute_throwsParserException() {
        assertParseFailure(parser, "addTo",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }
}
