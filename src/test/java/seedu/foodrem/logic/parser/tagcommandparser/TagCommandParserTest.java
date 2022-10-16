package seedu.foodrem.logic.parser.tagcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ID_FIRST;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalTags.FRUITS;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.tagcommands.TagCommand;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.model.tag.TagName;
import seedu.foodrem.testutil.TagBuilder;

public class TagCommandParserTest {

    private final TagCommandParser parser = new TagCommandParser();
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    @Test
    public void parse_tagThenIndex_success() {
        Tag fruitTag = new TagBuilder(FRUITS).build();
        String fruitTagName = fruitTag.getName();
        Index targetIndex = INDEX_FIRST_ITEM;

        String userInput = VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_ID_FIRST;
        TagCommand expectedCommand = new TagCommand(fruitTagName, targetIndex);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexThenTag_success() {
        Tag fruitTag = new TagBuilder(FRUITS).build();
        String fruitTagName = fruitTag.getName();
        Index targetIndex = INDEX_FIRST_ITEM;

        String userInput = VALID_DESC_ID_FIRST + VALID_DESC_TAG_NAME_FRUITS;
        TagCommand expectedCommand = new TagCommand(fruitTagName, targetIndex);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidExtraFlagAfterTag_failure() {

        String userInput = VALID_DESC_ID_FIRST + VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_ITEM_QUANTITY_CUCUMBERS;

        assertParseFailure(parser, userInput, TagName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidExtraFlagAfterIndex_failure() {

        String userInput = VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_ID_FIRST + VALID_DESC_ITEM_QUANTITY_CUCUMBERS;

        assertParseFailure(parser, userInput, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingParts_failure() {
        // no tag specified
        assertParseFailure(parser, VALID_DESC_ID_FIRST, MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, VALID_DESC_TAG_NAME_FRUITS, MESSAGE_INVALID_FORMAT);

        // no tag and no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // Number preamble
        assertParseFailure(parser, "0 " +VALID_DESC_TAG_NAME_FRUITS
                + VALID_DESC_ID_FIRST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "some random string  "+ VALID_DESC_TAG_NAME_FRUITS
                + VALID_DESC_ID_FIRST, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "qty/1 " + VALID_DESC_TAG_NAME_FRUITS
                + VALID_DESC_ID_FIRST, MESSAGE_INVALID_FORMAT);
    }

}
