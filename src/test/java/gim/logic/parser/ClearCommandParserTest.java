package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.commands.CommandTestUtil.DESC_PREFIX_CONFIRM;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import gim.logic.commands.ClearCommand;



public class ClearCommandParserTest {
    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_oneConfirmFieldPresentNoMessage_success() {
        assertParseSuccess(parser, DESC_PREFIX_CONFIRM, new ClearCommand());
    }

    @Test
    public void parse_oneConfirmFieldPresentWithMessage_success() {
        assertParseSuccess(parser, DESC_PREFIX_CONFIRM + VALID_NAME_BENCH_PRESS, new ClearCommand());
    }

    @Test
    public void parse_noConfirmField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
    }
}
