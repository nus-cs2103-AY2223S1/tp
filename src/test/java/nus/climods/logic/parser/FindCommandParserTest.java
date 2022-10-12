package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.FindCommand;

class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(Arrays.asList("software", "engineering"));

        assertParseSuccess(parser, "software engineering", expectedFindCommand);
        assertParseSuccess(parser, " \n software \n \t engineering  \t", expectedFindCommand);
    }
}
