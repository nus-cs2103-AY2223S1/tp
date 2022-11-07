package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.SelectPropertyCommand;

public class SelectPropertyCommandParserTest {

    private final SelectPropertyCommandParser parser = new SelectPropertyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SelectPropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SelectPropertyCommand expectedSelectCommand =
                new SelectPropertyCommand(INDEX_FIRST);
        assertParseSuccess(parser, "1", expectedSelectCommand);
    }
}
