package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOB;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.FindStudentNameCommand;
import jeryl.fyp.model.student.StudentNameContainsKeywordsPredicate;

public class FindStudentNameCommandParserTest {

    private FindStudentNameCommandParser parser = new FindStudentNameCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindStudentNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindStudentNameCommand expectedFindStudentNameCommand =
                new FindStudentNameCommand(new StudentNameContainsKeywordsPredicate(Arrays.asList("neural network",
                        VALID_PROJECT_NAME_BOB)));
        assertParseSuccess(parser, "neural network/" + VALID_PROJECT_NAME_BOB, expectedFindStudentNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n neural network \n / \t " + VALID_PROJECT_NAME_BOB + "  \t",
                expectedFindStudentNameCommand);
    }

}
