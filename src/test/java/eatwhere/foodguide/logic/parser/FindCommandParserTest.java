package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.HELP_DESC;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.logic.commands.FindCommand;
import eatwhere.foodguide.model.eatery.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        CommandParserTestUtil.assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_displayHelp_success() {
        // only help prefix
        CommandParserTestUtil.assertParseDisplayCommandHelp(parser,
                HELP_DESC, FindCommand.MESSAGE_USAGE);

        // help prefix overrides keywords
        CommandParserTestUtil.assertParseDisplayCommandHelp(parser,
                "Alice Bob" + HELP_DESC, FindCommand.MESSAGE_USAGE);
    }
}
