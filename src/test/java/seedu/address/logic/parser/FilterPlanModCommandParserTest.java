package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterPlanModCommand;
import seedu.address.model.module.PlanModContainsKeywordsPredicate;

public class FilterPlanModCommandParserTest {

    private FilterPlanModCommandParser parser = new FilterPlanModCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPlanModCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterTagCommand() {
        // no leading and trailing whitespaces
        FilterPlanModCommand expectedFilterPlanModCommand =
                new FilterPlanModCommand(new PlanModContainsKeywordsPredicate("Alice"));
        assertParseSuccess(parser, "Alice", expectedFilterPlanModCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t", expectedFilterPlanModCommand);
    }

}
