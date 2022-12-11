package seedu.foodrem.logic.parser.tagcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.commons.core.Messages.MESSAGE_NON_POSITIVE_INDEX;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ID_FIRST;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalTags.FRUITS;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;

/**
 * A class to test the UntagCommandParser.
 */
public class UntagCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.getUsage());
    private final UntagCommandParser parser = new UntagCommandParser();

    @Test
    public void parse_indexThenTag_success() {
        Tag fruitTag = new TagBuilder(FRUITS).build();
        String fruitTagName = fruitTag.getName();

        String userInput = VALID_DESC_ID_FIRST + VALID_DESC_TAG_NAME_FRUITS;
        UntagCommand expectedCommand = new UntagCommand(fruitTagName, INDEX_FIRST_ITEM);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidExtraFlagAfterTag_failure() {
        String userInput = VALID_DESC_ID_FIRST + VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_ITEM_QUANTITY_CUCUMBERS;

        assertParseFailure(parser, userInput, StringUtil.getInvalidCharactersMessage("tag name"));
    }

    @Test
    public void parse_invalidExtraFlagAfterIndex_failure() {
        String userInput = VALID_DESC_TAG_NAME_FRUITS + VALID_DESC_ID_FIRST + VALID_DESC_ITEM_QUANTITY_CUCUMBERS;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
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
        assertParseFailure(parser, "-1 " + VALID_DESC_TAG_NAME_FRUITS
                + VALID_DESC_ID_FIRST, MESSAGE_NON_POSITIVE_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "some random string  " + VALID_DESC_TAG_NAME_FRUITS
                + VALID_DESC_ID_FIRST, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "qty/1 " + VALID_DESC_TAG_NAME_FRUITS
                + VALID_DESC_ID_FIRST, MESSAGE_INVALID_FORMAT);
    }
}
