package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECTNAME_BOB;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.FindCommand;
import jeryl.fyp.model.student.ProjectNameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ProjectNameContainsKeywordsPredicate(Arrays.asList("neural network",
                        VALID_PROJECTNAME_BOB)));
        assertParseSuccess(parser, "neural network \\" + VALID_PROJECTNAME_BOB, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n neural network \n \\ \t " + VALID_PROJECTNAME_BOB + "  \t",
                expectedFindCommand);
    }

}
