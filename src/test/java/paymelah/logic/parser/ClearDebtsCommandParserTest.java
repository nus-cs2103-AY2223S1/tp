package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseFailure;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import paymelah.logic.commands.ClearDebtsCommand;

public class ClearDebtsCommandParserTest {

    private ClearDebtsCommandParser parser = new ClearDebtsCommandParser();

    @Test
    public void parse_validArgs_returnsClearDebtsCommand() {
        assertParseSuccess(parser, "1", new ClearDebtsCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearDebtsCommand.MESSAGE_USAGE));
    }
}
