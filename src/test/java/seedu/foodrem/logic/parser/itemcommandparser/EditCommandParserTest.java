package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_NON_POSITIVE_INDEX;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;
import seedu.foodrem.testutil.MessageToUser;
import seedu.foodrem.testutil.TypicalIndexes;

/**
 * A class to test the EditCommandParser.
 */
public class EditCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getUsage());
    private static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES,
                           MESSAGE_NON_POSITIVE_INDEX);

        // zero index
        assertParseFailure(parser, "0" + CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES,
                           MESSAGE_NON_POSITIVE_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid Fields
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_DESC_ITEM_NAME_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_DESC_ITEM_UNIT_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE);
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_DESC_ITEM_PRICE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_PRICE);

        // Invalid Name followed by valid Quantity
        assertParseFailure(parser,
                "1"
                        + CommandTestUtil.INVALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);

        // Valid name followed by valid name.
        // The test case for invalid name followed by valid name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1"
                        + CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_NAME_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                        + CommandTestUtil.INVALID_DESC_ITEM_NAME_POTATOES
                        + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_ITEM;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)
                .withItemUnit(CommandTestUtil.VALID_ITEM_UNIT_CUCUMBERS)
                .withItemBoughtDate(CommandTestUtil.VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .withItemExpiryDate(CommandTestUtil.VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .withItemPrice(CommandTestUtil.VALID_ITEM_PRICE_CUCUMBERS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit
        userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_DESC_ITEM_UNIT_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemUnit(CommandTestUtil.VALID_ITEM_UNIT_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // bought date
        userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemBoughtDate(CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expiry date
        userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemExpiryDate(CommandTestUtil.VALID_ITEM_EXPIRY_DATE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price
        userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_DESC_ITEM_PRICE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemPrice(CommandTestUtil.VALID_ITEM_PRICE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS
                + CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_UNIT_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_PRICE_POTATOES;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_POTATOES)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_POTATOES)
                .withItemUnit(CommandTestUtil.VALID_ITEM_UNIT_POTATOES)
                .withItemBoughtDate(CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES)
                .withItemExpiryDate(CommandTestUtil.VALID_ITEM_EXPIRY_DATE_POTATOES)
                .withItemPrice(CommandTestUtil.VALID_ITEM_PRICE_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.INVALID_DESC_ITEM_NAME_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + CommandTestUtil.INVALID_DESC_ITEM_NAME_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_UNIT_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_PRICE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_POTATOES)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_POTATOES)
                .withItemUnit(CommandTestUtil.VALID_ITEM_UNIT_POTATOES)
                .withItemBoughtDate(CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES)
                .withItemExpiryDate(CommandTestUtil.VALID_ITEM_EXPIRY_DATE_POTATOES)
                .withItemPrice(CommandTestUtil.VALID_ITEM_PRICE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
