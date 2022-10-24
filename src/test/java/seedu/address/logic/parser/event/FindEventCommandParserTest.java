package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.event.FindEventCommandParser.MESSAGE_MISSING_KEYWORDS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.FindEventCommand;
import seedu.address.model.event.TitleContainsKeywordsPredicate;

public class FindEventCommandParserTest {
    private FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ",
                String.format(MESSAGE_MISSING_KEYWORDS + FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindEventCommand expectedFindCommand =
                new FindEventCommand(new TitleContainsKeywordsPredicate(Arrays.asList("Presentation", "Practice")));
        assertParseSuccess(parser, "Presentation Practice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "Presentation Practice", expectedFindCommand);
    }
}
