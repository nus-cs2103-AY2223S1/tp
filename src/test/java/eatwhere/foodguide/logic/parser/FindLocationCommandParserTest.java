package eatwhere.foodguide.logic.parser;


import static eatwhere.foodguide.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.HELP_DESC;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.logic.commands.FindLocationCommand;
import eatwhere.foodguide.model.eatery.LocationContainsKeywordsPredicate;

public class FindLocationCommandParserTest {
    private FindLocationCommandParser parser = new FindLocationCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLocationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindLocationCommand expectedFindLocationCommand =
                new FindLocationCommand(new LocationContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        CommandParserTestUtil.assertParseSuccess(parser, "Alice Bob", expectedFindLocationCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindLocationCommand);
    }

    @Test
    public void parse_displayHelp_success() {
        // only help prefix
        CommandParserTestUtil.assertParseDisplayCommandHelp(parser,
                HELP_DESC, FindLocationCommand.MESSAGE_USAGE);

        // help prefix overrides keywords
        CommandParserTestUtil.assertParseDisplayCommandHelp(parser,
                "Alice Bob" + HELP_DESC, FindLocationCommand.MESSAGE_USAGE);
    }
}
