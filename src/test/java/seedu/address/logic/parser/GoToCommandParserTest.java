package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GoToCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;

public class GoToCommandParserTest {

    private GoToCommandParser parser = new GoToCommandParser();

    @Test
    public void equals() {
        GoToCommand firstGoToCommand =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE),
                        new ModuleCode(VALID_CS2106_MODULE_CODE));
        GoToCommand secondGoToCommand =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(VALID_MA2001_MODULE_CODE),
                        new ModuleCode(VALID_MA2001_MODULE_CODE));

        // same object -> returns true
        assertTrue(firstGoToCommand.equals(firstGoToCommand));

        // same values -> returns true
        GoToCommand firstGoToCommandCopy =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE),
                        new ModuleCode(VALID_CS2106_MODULE_CODE));

        assertTrue(firstGoToCommand.equals(firstGoToCommandCopy));

        // different types -> returns false
        assertFalse(firstGoToCommand.equals(1));

        // null -> returns false
        assertFalse(firstGoToCommand.equals(null));

        // different values -> returns false
        assertFalse(firstGoToCommand.equals(secondGoToCommand));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGoToCommand() {
        GoToCommand expectedGoToCommand =
                new GoToCommand(new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE),
                        new ModuleCode(VALID_CS2106_MODULE_CODE));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, VALID_CS2106_MODULE_CODE, expectedGoToCommand);

        // leading whitespaces
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_CS2106_MODULE_CODE, expectedGoToCommand);

        // trailing whitespaces
        assertParseSuccess(parser, VALID_CS2106_MODULE_CODE + PREAMBLE_WHITESPACE, expectedGoToCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_CS2106_MODULE_CODE + PREAMBLE_WHITESPACE,
                expectedGoToCommand);
    }
}
