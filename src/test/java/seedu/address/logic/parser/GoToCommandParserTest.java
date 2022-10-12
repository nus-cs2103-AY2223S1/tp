package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GoToCommand;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;

public class GoToCommandParserTest {

    private static final String MODULE_CODE_STR_CS2106 = CS2106.getModuleCodeAsUpperCaseString();
    private static final String MODULE_CODE_STR_MA2001 = MA2001.getModuleCodeAsUpperCaseString();

    private GoToCommandParser parser = new GoToCommandParser();

    @Test
    public void equals() {
        GoToCommand firstGoToCommand =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_STR_CS2106));
        GoToCommand secondGoToCommand =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_STR_MA2001));

        // same object -> returns true
        assertTrue(firstGoToCommand.equals(firstGoToCommand));

        // same values -> returns true
        GoToCommand firstGoToCommandCopy =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_STR_CS2106));
        assertTrue(firstGoToCommand.equals(firstGoToCommandCopy));

        // different types -> returns false
        assertFalse(firstGoToCommand.equals(1));

        // null -> returns false
        assertFalse(firstGoToCommand.equals(null));

        // different module -> returns false
        assertFalse(firstGoToCommand.equals(secondGoToCommand));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        GoToCommand expectedGoToCommand =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_STR_CS2106));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, MODULE_CODE_STR_CS2106, expectedGoToCommand);

        // leading whitespaces
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_STR_CS2106, expectedGoToCommand);

        // trailing whitespaces
        assertParseSuccess(parser, MODULE_CODE_STR_CS2106 + PREAMBLE_WHITESPACE, expectedGoToCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_STR_CS2106 + PREAMBLE_WHITESPACE,
                expectedGoToCommand);

        //
    }
}
