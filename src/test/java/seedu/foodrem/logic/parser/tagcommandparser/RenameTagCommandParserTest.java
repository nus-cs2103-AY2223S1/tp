package seedu.foodrem.logic.parser.tagcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodrem.model.tag.TagName.MESSAGE_CONSTRAINTS;
import static seedu.foodrem.testutil.TypicalTags.FRUITS;
import static seedu.foodrem.testutil.TypicalTags.FRUITS_WITH_WHITESPACE;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;

public class RenameTagCommandParserTest {
    private final RenameTagCommandParser parser = new RenameTagCommandParser();

    @Test
    public void parse_tags_success() {
        Tag fruitTag = new TagBuilder(FRUITS).build();
        Tag fruitWithWhitespaceTag = new TagBuilder(FRUITS_WITH_WHITESPACE).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES,
                new RenameTagCommand(fruitTag, fruitWithWhitespaceTag));
    }

    @Test
    public void parse_invalidValue_failure() {
        // same tag
        assertParseFailure(parser,
                           VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_TAG_NAME_FRUITS,
                           "The tag name is already \"fruits\".");

        // invalid renamed tag
        assertParseFailure(parser,
                VALID_DESC_TAG_NAME_FRUITS + INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION,
                MESSAGE_CONSTRAINTS);

        // invalid original tag
        assertParseFailure(parser,
                INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION + VALID_DESC_TAG_NAME_FRUITS,
                MESSAGE_CONSTRAINTS);

        // both tags invalid
        assertParseFailure(parser,
                INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION + INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION,
                           "The tag name is already \"Frui//ts\".");

        // invalid args
        assertParseFailure(parser,
                VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS + VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameTagCommand.getUsage()));
    }
}
