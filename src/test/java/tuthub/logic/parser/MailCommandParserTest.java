package tuthub.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.MailCommand;

public class MailCommandParserTest {

    private MailCommandParser parser = new MailCommandParser();

    @Test
    public void isIndex() {
        assertTrue(parser.isIndex("3"));
        assertFalse(parser.isIndex("all"));
        assertFalse(parser.isIndex(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsMailCommand() {
        // no leading and trailing whitespaces
        MailCommand expectedMailCommand1 =
                new MailCommand("all");
        Index index = Index.fromOneBased(3);
        MailCommand expectedMailCommand2 =
                new MailCommand(index);

        assertParseSuccess(parser, "all", expectedMailCommand1);
        assertParseSuccess(parser, "3", expectedMailCommand2);

        // multiple whitespaces between input
        assertParseSuccess(parser, " \n all \n \t \t", expectedMailCommand1);
        assertParseSuccess(parser, " \n 3 \n \t \t", expectedMailCommand2);
    }
}
