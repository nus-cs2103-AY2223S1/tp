package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.module.Module.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterPrevModCommand;
import seedu.address.model.module.PrevModContainsKeywordsPredicate;

public class FilterPrevModCommandParserTest {

    private FilterPrevModCommandParser parser = new FilterPrevModCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPrevModCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterPrevModCommand() {
        // no leading and trailing whitespaces
        FilterPrevModCommand expectedFilterPrevModCommand =
                new FilterPrevModCommand(new PrevModContainsKeywordsPredicate("CS2100"));
        assertParseSuccess(parser, "CS2100", expectedFilterPrevModCommand);

        assertParseSuccess(parser, " CS2100", expectedFilterPrevModCommand);
    }

    @Test
    public void parse_invalidArgs_returnsInvalidCommandErrorMessage() {
        assertParseFailure(parser, "Alice", MESSAGE_CONSTRAINTS);
    }
}
