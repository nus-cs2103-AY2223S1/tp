package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS2106;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GoToCommand;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;

public class GoToCommandParserTest {

    private static final String MODULECODESTR_CS2106 = CS2106.getModuleCodeAsUpperCaseString();
    private GoToCommandParser parser = new GoToCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        GoToCommand expectedGoToCommand =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_CS2106));
        assertParseSuccess(parser, MODULECODESTR_CS2106, expectedGoToCommand);
    }
}
