package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.logic.commands.FindCommand;
import seedu.nutrigoals.model.meal.Name;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_blankArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFoodName_throwsParseException() {
        assertParseFailure(parser, "r!ce", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(new Name("rice"));
        assertParseSuccess(parser, "rice", expectedFindCommand);

        // whitespaces
        assertParseSuccess(parser, " \n rice \t", expectedFindCommand);
    }

}
