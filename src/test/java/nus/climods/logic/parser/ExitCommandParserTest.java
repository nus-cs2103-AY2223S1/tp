package nus.climods.logic.parser;

import static nus.climods.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.ExitCommand;

public class ExitCommandParserTest {
    private final ExitCommandParser parser = new ExitCommandParser();
    private final ExitCommand expectedExitCommand = new ExitCommand();

    @Test
    public void parse_randomArg_returnsExitCommand() {
        assertParseSuccess(parser, "I love to write test cases!", expectedExitCommand);
    }
}
