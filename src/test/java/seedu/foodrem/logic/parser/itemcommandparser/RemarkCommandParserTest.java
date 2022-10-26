package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.itemcommands.RemarkCommand;
import seedu.foodrem.model.item.ItemRemark;
import seedu.foodrem.testutil.MessageToUser;

class RemarkCommandParserTest {
    private final RemarkCommandParser parser = new RemarkCommandParser();

    private final String invalidFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.getUsage());

    @Test
    public void parse_remarksFieldPresent_success() {
        ItemRemark expectedRemarkItem = new ItemRemark(CommandTestUtil.VALID_ITEM_REMARKS_POTATOES);

        // Index present remarks present
        assertParseSuccess(parser,
                "1"
                        + CommandTestUtil.VALID_DESC_ITEM_REMARKS_POTATOES,
                new RemarkCommand(INDEX_FIRST_ITEM, expectedRemarkItem));

        // Index present remarks not present
        assertParseSuccess(parser,
                "1",
                new RemarkCommand(INDEX_FIRST_ITEM, new ItemRemark("")));

        // Multiple remarks - last unit accepted
        assertParseSuccess(parser,
                "1"
                        + CommandTestUtil.VALID_DESC_ITEM_REMARKS_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_REMARKS_POTATOES,
                new RemarkCommand(INDEX_FIRST_ITEM, expectedRemarkItem));

        // invalid remarks followed by valid remarks
        assertParseSuccess(parser,
                "1"
                        + CommandTestUtil.INVALID_DESC_ITEM_REMARKS_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_REMARKS_POTATOES,
                new RemarkCommand(INDEX_FIRST_ITEM, expectedRemarkItem));
    }

    @Test
    public void parse_notProperFormat_failure() {
        // blank
        assertParseFailure(parser, "", invalidFormatMessage);

        // index missing
        assertParseFailure(parser, ""
                + CommandTestUtil.VALID_DESC_ITEM_REMARKS_POTATOES, invalidFormatMessage);

        // invalid index
        assertParseFailure(parser, "a"
                + CommandTestUtil.VALID_DESC_ITEM_REMARKS_POTATOES, invalidFormatMessage);

        // multiple index
        assertParseFailure(parser, "1 2"
                + CommandTestUtil.VALID_DESC_ITEM_REMARKS_POTATOES, invalidFormatMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid remarks
        assertParseFailure(parser,
                "1"
                        + CommandTestUtil.INVALID_DESC_ITEM_REMARKS_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS);

        // invalid remarks
        assertParseFailure(parser,
                "1"
                        + CommandTestUtil.INVALID_DESC_ITEM_REMARKS_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS);

        // valid remarks followed by invalid remarks
        assertParseFailure(parser,
                "1"
                        + CommandTestUtil.VALID_DESC_ITEM_REMARKS_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_REMARKS_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS);
    }
}
