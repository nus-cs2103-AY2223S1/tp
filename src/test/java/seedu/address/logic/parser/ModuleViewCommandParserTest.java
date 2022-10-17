package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModuleCodes.CODE_THIRD_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.ModuleViewCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ModuleViewCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ModuleViewCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ModuleViewCommandParserTest {
    private ModuleViewCommandParser parser = new ModuleViewCommandParser();

    @Test
    public void parse_validArgs_returnsModuleViewCommand() {
        assertParseSuccess(parser, " c/CS2102", new ModuleViewCommand(CODE_THIRD_MODULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ModuleViewCommand.MESSAGE_USAGE));
    }
}
