package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindLocationCommand;
import seedu.address.model.person.LocationContainsKeywordsPredicate;

public class FindLocationCommandParserTest {

    private FindLocationCommandParser parser = new FindLocationCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLocationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindLocationCommand expectedFindLocationCommand =
                new FindLocationCommand(new LocationContainsKeywordsPredicate(Arrays.asList("jurong", "clementi")));
        assertParseSuccess(parser, "jurong clementi", expectedFindLocationCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n jurong \n \t clementi  \t", expectedFindLocationCommand);
    }
}
