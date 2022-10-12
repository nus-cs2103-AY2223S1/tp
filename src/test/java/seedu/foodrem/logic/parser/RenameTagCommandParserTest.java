package seedu.foodrem.logic.parser;

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
import seedu.foodrem.logic.parser.tagcommandparser.RenameTagCommandParser;
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

        assertParseSuccess(parser,
                VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_TAG_NAME_FRUITS,
                new RenameTagCommand(fruitTag, fruitTag));
    }

    @Test
    public void parse_invalidValue_failure() {
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
                MESSAGE_CONSTRAINTS);

        // invalid args
        assertParseFailure(parser,
                VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS + VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameTagCommand.MESSAGE_USAGE));
    }
}
