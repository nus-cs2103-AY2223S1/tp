package seedu.foodrem.logic.parser.tagcommandparser;

import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodrem.testutil.TypicalTags.FRUITS;
import static seedu.foodrem.testutil.TypicalTags.FRUITS_WITH_WHITESPACE;
import static seedu.foodrem.testutil.TypicalTags.NUMBERS;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;

public class NewTagCommandParserTest {
    private static final int MAX_LENGTH = 20;
    private static final String EXCEED_MAX_CHARS_MESSAGE_CONSTRAINTS =
            String.format("The tag name should not exceed %d characters", MAX_LENGTH);
    private final NewTagCommandParser parser = new NewTagCommandParser();
    @Test
    public void parse_tags_success() {
        Tag fruitTag = new TagBuilder(FRUITS).build();
        Tag fruitWithWhitespaceTag = new TagBuilder(FRUITS_WITH_WHITESPACE).build();
        Tag numbersTag = new TagBuilder(NUMBERS).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS,
                new NewTagCommand(fruitTag));

        // whitespace only preamble
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES,
                new NewTagCommand(fruitWithWhitespaceTag));

        // whitespace only preamble
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_TAG_NAME_NUMBERS,
                new NewTagCommand(numbersTag));
    }

    @Test
    public void parse_invalidValue_failure() {
        // blank
        assertParseFailure(parser,
                "",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NewTagCommand.getUsage()));

        // invalid tag name
        assertParseFailure(parser,
                           CommandTestUtil.INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION,
                           StringUtil.getInvalidCharactersMessage("tag name"));

        // invalid length
        assertParseFailure(parser,
                CommandTestUtil.INVALID_DESC_TAG_NAME_EXCEED_CHAR_LIMIT,
                EXCEED_MAX_CHARS_MESSAGE_CONSTRAINTS);
    }
}
