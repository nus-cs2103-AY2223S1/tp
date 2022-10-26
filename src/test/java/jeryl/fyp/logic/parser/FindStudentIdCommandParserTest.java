package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOB;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.FindStudentIdCommand;
import jeryl.fyp.model.student.StudentIdContainsKeywordsPredicate;

public class FindStudentIdCommandParserTest {

    private FindStudentIdCommandParser parser = new FindStudentIdCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindStudentIdCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindStudentIdCommand expectedFindStudentIdCommand =
                new FindStudentIdCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList("neural network",
                        VALID_PROJECT_NAME_BOB)));
        assertParseSuccess(parser, "neural network/" + VALID_PROJECT_NAME_BOB, expectedFindStudentIdCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n neural network \n / \t " + VALID_PROJECT_NAME_BOB + "  \t",
                expectedFindStudentIdCommand);
    }

}
