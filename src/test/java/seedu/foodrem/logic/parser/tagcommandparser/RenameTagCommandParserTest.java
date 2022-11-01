package seedu.foodrem.logic.parser.tagcommandparser;

import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.testutil.TypicalTags;

public class RenameTagCommandParserTest {
    private final RenameTagCommandParser parser = new RenameTagCommandParser();

    @Test
    public void parse_tags_success() {
        Tag fruitTag = new TagBuilder(TypicalTags.FRUITS).build();
        Tag fruitWithWhitespaceTag = new TagBuilder(TypicalTags.FRUITS_WITH_WHITESPACE).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS
                        + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES,
                new RenameTagCommand(fruitTag, fruitWithWhitespaceTag));

        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS,
                new RenameTagCommand(fruitTag, fruitTag));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid renamed tag
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS
                        + CommandTestUtil.INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION,
                 StringUtil.getInvalidCharactersMessage("tag name"));

        // invalid original tag
        assertParseFailure(parser,
                CommandTestUtil.INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION
                        + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS,
                 StringUtil.getInvalidCharactersMessage("tag name"));

        // both tags invalid
        assertParseFailure(parser,
                       CommandTestUtil.INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION
                        + CommandTestUtil.INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION,
                           StringUtil.getInvalidCharactersMessage("tag name"));

        // invalid args
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RenameTagCommand.getUsage()));
    }

    @Test
    public void parse_invalidCommandFailure() {
        // invalid args
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES
                        + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES
                        + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RenameTagCommand.getUsage()));
    }
}
