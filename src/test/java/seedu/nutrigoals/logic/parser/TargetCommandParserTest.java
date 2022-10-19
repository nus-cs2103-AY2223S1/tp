package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.logic.commands.TargetCommand;
import seedu.nutrigoals.model.Calorie;

class TargetCommandParserTest {
    private TargetCommandParser parser = new TargetCommandParser();

    @Test
    void parse_success() {
        final String calorieValue = "1231";
        TargetCommand expected = new TargetCommand(new Calorie(calorieValue));
        assertParseSuccess(parser, calorieValue, expected);
    }

    @Test
    void parse_invalidStringException() {
        for (String calorieValue : new String[] {"", "1.0", "invalid", Long.MAX_VALUE + "", "-1"}) {
            assertParseFailure(parser, calorieValue,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TargetCommand.MESSAGE_USAGE));
        }
    }
}
