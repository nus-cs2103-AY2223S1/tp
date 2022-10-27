package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.FindTagCommand;
import seedu.intrack.model.internship.TagsContainKeywordsPredicate;

public class FindTagCommandParserTest {

    private FindTagCommandParser parser = new FindTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        FindTagCommand expectedFindTagsCommand =
                new FindTagCommand(new TagsContainKeywordsPredicate(Arrays.asList("Urgent", "Remote")));
        assertParseSuccess(parser, "Urgent Remote", expectedFindTagsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Urgent \n \t Remote  \t", expectedFindTagsCommand);
    }

}
