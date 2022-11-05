package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.commands.CommandTestUtil.COST_PRICE_DESC_DEFAULT;
import static tracko.logic.commands.CommandTestUtil.INVALID_COST_PRICE_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_ITEM_NAME_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static tracko.logic.commands.CommandTestUtil.INVALID_SELL_PRICE_DESC;
import static tracko.logic.commands.CommandTestUtil.ITEM_DESCRIPTION_DESC_DEFAULT;
import static tracko.logic.commands.CommandTestUtil.ITEM_NAME_DESC_DEFAULT;
import static tracko.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static tracko.logic.commands.CommandTestUtil.QUANTITY_DESC_DEFAULT;
import static tracko.logic.commands.CommandTestUtil.SELL_PRICE_DESC_DEFAULT;
import static tracko.logic.commands.CommandTestUtil.VALID_DEFAULT_ITEM_NAME;
import static tracko.logic.commands.CommandTestUtil.VALID_DEFAULT_QUANTITY;
import static tracko.logic.commands.item.EditItemCommand.MESSAGE_NOT_EDITED;
import static tracko.logic.commands.item.EditItemCommand.MESSAGE_USAGE;
import static tracko.logic.parser.CliSyntax.PREFIX_TAG;
import static tracko.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.item.EditItemCommand;
import tracko.logic.commands.item.EditItemCommand.EditItemDescriptor;
import tracko.logic.parser.item.EditItemCommandParser;
import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.testutil.EditItemDescriptorBuilder;
import tracko.testutil.InventoryItemBuilder;

public class EditItemCommandParserTest {
    private static final String INDEX_STRING = "1 ";
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private EditItemCommandParser parser = new EditItemCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        InventoryItem editedItem = new InventoryItemBuilder().build();
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(editedItem).build();

        // edit all fields
        assertParseSuccess(parser, INDEX_STRING + ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT
                        + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT + COST_PRICE_DESC_DEFAULT + TAG_EMPTY,
                new EditItemCommand(INDEX_FIRST, descriptor));

        //edit item name field
        assertParseSuccess(parser, INDEX_STRING + ITEM_NAME_DESC_DEFAULT,
                new EditItemCommand(INDEX_FIRST,
                        new EditItemDescriptorBuilder().withItemName(VALID_DEFAULT_ITEM_NAME).build()));

        //edit quantity field
        assertParseSuccess(parser, INDEX_STRING + QUANTITY_DESC_DEFAULT,
                new EditItemCommand(INDEX_FIRST,
                        new EditItemDescriptorBuilder().withQuantity(VALID_DEFAULT_QUANTITY).build()));

        //edit item name and quantity field
        assertParseSuccess(parser, INDEX_STRING + ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT,
                new EditItemCommand(INDEX_FIRST,
                        new EditItemDescriptorBuilder().withItemName(VALID_DEFAULT_ITEM_NAME)
                                .withQuantity(VALID_DEFAULT_QUANTITY).build()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        // missing INDEX
        assertParseFailure(parser, ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT
                        + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT + COST_PRICE_DESC_DEFAULT + TAG_EMPTY,
                expectedMessage);

        // empty preamble
        assertParseFailure(parser, " " + ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT
                        + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT + COST_PRICE_DESC_DEFAULT + TAG_EMPTY,
                expectedMessage);

        // no fields edited
        assertParseFailure(parser, INDEX_STRING, MESSAGE_NOT_EDITED);
    }

    @Test
    public void parseInitial_invalidValue_failure() {

        // invalid item name
        assertParseFailure(parser, INDEX_STRING + INVALID_ITEM_NAME_DESC + QUANTITY_DESC_DEFAULT
                + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT
                + COST_PRICE_DESC_DEFAULT, ItemName.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, INDEX_STRING + ITEM_NAME_DESC_DEFAULT + INVALID_QUANTITY_DESC
                + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT
                + COST_PRICE_DESC_DEFAULT, Quantity.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, INDEX_STRING + ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT
                + INVALID_DESCRIPTION_DESC + SELL_PRICE_DESC_DEFAULT
                + COST_PRICE_DESC_DEFAULT, Description.MESSAGE_CONSTRAINTS);

        // invalid sell price
        assertParseFailure(parser, INDEX_STRING + ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT
                + ITEM_DESCRIPTION_DESC_DEFAULT + INVALID_SELL_PRICE_DESC
                + COST_PRICE_DESC_DEFAULT, Price.MESSAGE_CONSTRAINTS);

        // invalid cost price
        assertParseFailure(parser, INDEX_STRING + ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT
                + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT
                + INVALID_COST_PRICE_DESC, Price.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INDEX_STRING + INVALID_ITEM_NAME_DESC + INVALID_QUANTITY_DESC
                + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT
                + COST_PRICE_DESC_DEFAULT, ItemName.MESSAGE_CONSTRAINTS);

        // non-integer preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ITEM_NAME_DESC_DEFAULT + QUANTITY_DESC_DEFAULT
                        + ITEM_DESCRIPTION_DESC_DEFAULT + SELL_PRICE_DESC_DEFAULT + COST_PRICE_DESC_DEFAULT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
