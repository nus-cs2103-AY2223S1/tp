package seedu.foodrem.logic.parser;

import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_TAG_NAME_EXCEED_CHAR_LIMIT;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_TAG_NAME_ILLEGAL_FIRST_CHAR;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_NUMBERS;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodrem.model.tag.Tag.EXCEED_MAX_CHARS_MESSAGE_CONSTRAINTS;
import static seedu.foodrem.model.tag.TagName.MESSAGE_CONSTRAINTS;
import static seedu.foodrem.testutil.TypicalTags.FRUITS;
import static seedu.foodrem.testutil.TypicalTags.FRUITS_WITH_WHITESPACE;
import static seedu.foodrem.testutil.TypicalTags.NUMBERS;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.tagcommands.AddTagCommand;
import seedu.foodrem.logic.parser.tagcommandparser.AddTagCommandParser;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;


public class AddTagCommandParserTest {
    private final AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_tags_success() {
        Tag fruitTag = new TagBuilder(FRUITS).build();
        Tag fruitWithWhitespaceTag = new TagBuilder(FRUITS_WITH_WHITESPACE).build();
        Tag numbersTag = new TagBuilder(NUMBERS).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                VALID_DESC_TAG_NAME_FRUITS,
                new AddTagCommand(fruitTag));

        // whitespace only preamble
        assertParseSuccess(parser,
                VALID_DESC_TAG_NAME_FRUITS_WITH_WHITESPACES,
                new AddTagCommand(fruitWithWhitespaceTag));

        // whitespace only preamble
        assertParseSuccess(parser,
                VALID_DESC_TAG_NAME_NUMBERS,
                new AddTagCommand(numbersTag));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                INVALID_DESC_TAG_NAME_DISALLOWED_PUNCTUATION,
                MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser,
                INVALID_DESC_TAG_NAME_ILLEGAL_FIRST_CHAR,
                MESSAGE_CONSTRAINTS);

        // invalid unit
        assertParseFailure(parser,
                INVALID_DESC_TAG_NAME_EXCEED_CHAR_LIMIT,
                EXCEED_MAX_CHARS_MESSAGE_CONSTRAINTS);

    }
}
