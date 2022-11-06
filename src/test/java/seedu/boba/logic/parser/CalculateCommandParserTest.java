package seedu.boba.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.boba.logic.commands.CalculateCommand;
import seedu.boba.logic.commands.FindCommand;
import seedu.boba.model.customer.MultiSearchPredicate;
import seedu.boba.model.customer.Phone;

import java.util.Arrays;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_ARITHMETIC_EXPRESSION;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseSuccess;
public class CalculateCommandParserTest {

    private CalculateCommandParser parser = new CalculateCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalculateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCalculateCommand() {
        // no leading and trailing whitespaces
        CalculateCommand expectedCalculateCommand =
                new CalculateCommand("1+1");
        assertParseSuccess(parser, "1+1", expectedCalculateCommand);

        // have white spaces but no parenthesis
        // assertParseSuccess(parser, "   1 +  1   ", expectedCalculateCommand);
    }

    @Test
    public void parse_validExpression_success() {
        String expression = "114+514";
        CalculateCommand expectedCalculateCommand = new CalculateCommand(" 114+514");

        String userInput = " " + VALID_ARITHMETIC_EXPRESSION;
        assertParseSuccess(parser, userInput, expectedCalculateCommand);
    }
}
