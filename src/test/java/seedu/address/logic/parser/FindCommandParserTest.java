package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.ItemContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    public static final String COMMAND_WORD = "find";

    public static final String VALID_KEYWORD_NO_SPACE = " n/Alice Bob";
    public static final String VALID_KEYWORD_WITH_SPACE = " n/ \n Alice \n \t Bob  \t";

    public static final String VALID_ITEM_KEYWORD_NO_SPACE = " i/Chicken Egg";
    public static final String VALID_ITEM_KEYWORD_WITH_SPACE = " i/ \n Chicken \n \t Egg  \t";



    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // missing tag
        assertParseFailure(parser, "find ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // invalid tags
        assertParseFailure(parser, "find p/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "find pr/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "find a/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "find t/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyNameArg_throwsParseException() {
        assertParseFailure(parser, "find n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_NAME_EMPTY_COMMAND));
        assertParseFailure(parser, "find n/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_NAME_EMPTY_COMMAND));
    }

    @Test
    public void parse_emptyItemArg_throwsParseException() {
        assertParseFailure(parser, "find i/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_ITEM_EMPTY_COMMAND));
        assertParseFailure(parser, "find i/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_ITEM_EMPTY_COMMAND));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        assertParseSuccess(parser, COMMAND_WORD + VALID_KEYWORD_NO_SPACE, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, COMMAND_WORD + VALID_KEYWORD_WITH_SPACE, expectedFindCommand);
    }

    @Test
    public void parse_validItemArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ItemContainsKeywordsPredicate(Arrays.asList("Chicken", "Egg")));

        assertParseSuccess(parser, COMMAND_WORD + VALID_ITEM_KEYWORD_NO_SPACE, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, COMMAND_WORD + VALID_ITEM_KEYWORD_WITH_SPACE, expectedFindCommand);
    }

}
