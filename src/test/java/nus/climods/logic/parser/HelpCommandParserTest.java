package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.HelpCommand;

class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_emptyArg_returnsHelpCommand() {
        HelpCommand expectedHelpCommand = new HelpCommand();
        assertParseSuccess(parser, "     ", expectedHelpCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
            "i love testing",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

}
