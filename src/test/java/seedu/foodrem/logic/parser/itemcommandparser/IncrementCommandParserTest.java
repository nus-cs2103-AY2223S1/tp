package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.itemcommands.IncrementCommand;
import seedu.foodrem.model.item.ItemQuantity;

class IncrementCommandParserTest {
    private final IncrementCommandParser parser = new IncrementCommandParser();

    @Test
    void parse_success() {
        // Index and quantity provided
        assertParseSuccess(parser,
                "1" + " " + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                new IncrementCommand(Index.fromOneBased(1),
                        new ItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)));

        // Index provided quantity not provided
        assertParseSuccess(parser,
                "1",
                new IncrementCommand(Index.fromOneBased(1),
                        new ItemQuantity("1")));

        // Multiple quantity take last
        assertParseSuccess(parser,
                "1"
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                new IncrementCommand(Index.fromOneBased(1),
                        new ItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)));

        // Multiple quantity invalid but last quantity valid
        assertParseSuccess(parser,
                "1"
                        + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_POTATOES
                        + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                new IncrementCommand(Index.fromOneBased(1),
                        new ItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)));

        // Another index but take first index
        assertParseSuccess(parser,
                "1"
                        + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_POTATOES
                        + "3"
                        + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                new IncrementCommand(Index.fromOneBased(1),
                        new ItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)));
    }

    @Test
    void parse_invalidFormat_failure() {
        // Nothing provided
        assertParseFailure(parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncrementCommand.getUsage()));

        // Index not provided
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncrementCommand.getUsage()));

        // Index negative
        assertParseFailure(parser,
                "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncrementCommand.getUsage()));
    }
}
