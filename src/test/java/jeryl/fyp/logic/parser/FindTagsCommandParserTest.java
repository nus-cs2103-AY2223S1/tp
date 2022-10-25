package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOB;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.FindTagsCommand;
import jeryl.fyp.model.student.TagsContainKeywordsPredicate;

public class FindTagsCommandParserTest {

    private FindTagsCommandParser parser = new FindTagsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindTagsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTagsCommand expectedFindTagsCommand =
                new FindTagsCommand(new TagsContainKeywordsPredicate(Arrays.asList("neural network",
                        VALID_PROJECT_NAME_BOB)));
        assertParseSuccess(parser, "neural network/" + VALID_PROJECT_NAME_BOB, expectedFindTagsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n neural network \n / \t " + VALID_PROJECT_NAME_BOB + "  \t",
                expectedFindTagsCommand);
    }

}
