package nus.climods.logic.parser;

import nus.climods.logic.commands.ExitCommand;
import org.junit.jupiter.api.Test;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseFailure;

public class ExitCommandParserTest {
    private final ExitCommandParser parser = new ExitCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser,
                "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));
    }
}
