package seedu.foodrem.logic.parser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_NAME_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_NAME_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_UNIT_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_ITEM_UNIT_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_EXPIRY_DATE_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_NAME_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_UNIT_POTATOES;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodrem.model.item.itemvalidator.ItemBoughtDateValidator.MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE;
import static seedu.foodrem.model.item.itemvalidator.ItemExpiryDateValidator.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE;
import static seedu.foodrem.model.item.itemvalidator.ItemNameValidator.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME;
import static seedu.foodrem.model.item.itemvalidator.ItemQuantityValidator.MESSAGE_FOR_NOT_A_NUMBER;
import static seedu.foodrem.model.item.itemvalidator.ItemUnitValidator.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESC_ITEM_NAME_POTATOES, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_DESC_ITEM_NAME_POTATOES, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DESC_ITEM_NAME_POTATOES, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid Fields
        assertParseFailure(parser,
                "1" + INVALID_DESC_ITEM_NAME_CUCUMBERS,
                MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertParseFailure(parser,
                "1" + INVALID_DESC_ITEM_QUANTITY_CUCUMBERS,
                MESSAGE_FOR_NOT_A_NUMBER);
        assertParseFailure(parser,
                "1" + INVALID_DESC_ITEM_UNIT_CUCUMBERS,
                MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertParseFailure(parser,
                "1" + INVALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS,
                MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE);
        assertParseFailure(parser,
                "1" + INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS,
                MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);

        // Invalid Name followed by valid Quantity
        assertParseFailure(parser,
                "1"
                        + INVALID_DESC_ITEM_NAME_CUCUMBERS
                        + VALID_DESC_ITEM_QUANTITY_POTATOES,
                MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);

        // Valid name followed by valid name.
        // The test case for invalid name followed by valid name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1"
                        + VALID_DESC_ITEM_NAME_CUCUMBERS
                        + INVALID_DESC_ITEM_NAME_CUCUMBERS,
                MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                        + INVALID_DESC_ITEM_NAME_POTATOES
                        + INVALID_DESC_ITEM_QUANTITY_POTATOES
                        + VALID_DESC_ITEM_EXPIRY_DATE_POTATOES
                        + VALID_DESC_ITEM_BOUGHT_DATE_POTATOES,
                MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput = targetIndex.getOneBased()
                + VALID_DESC_ITEM_NAME_CUCUMBERS
                + VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                + VALID_DESC_ITEM_UNIT_CUCUMBERS
                + VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                + VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                + VALID_DESC_ITEM_NAME_POTATOES
                + VALID_DESC_ITEM_QUANTITY_POTATOES
                + VALID_DESC_ITEM_UNIT_POTATOES
                + VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                + VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_POTATOES)
                .withItemQuantity(VALID_ITEM_QUANTITY_POTATOES)
                .withItemUnit(VALID_ITEM_UNIT_POTATOES)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + VALID_DESC_ITEM_NAME_CUCUMBERS
                + VALID_DESC_ITEM_QUANTITY_POTATOES;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(VALID_ITEM_QUANTITY_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + VALID_DESC_ITEM_NAME_POTATOES;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + VALID_DESC_ITEM_QUANTITY_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemQuantity(VALID_ITEM_QUANTITY_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit
        userInput = targetIndex.getOneBased() + VALID_DESC_ITEM_UNIT_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemUnit(VALID_ITEM_UNIT_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // bought date
        userInput = targetIndex.getOneBased() + VALID_DESC_ITEM_BOUGHT_DATE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expiry date
        userInput = targetIndex.getOneBased() + VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + VALID_DESC_ITEM_NAME_CUCUMBERS
                + VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                + VALID_DESC_ITEM_UNIT_CUCUMBERS
                + VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                + VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                + VALID_DESC_ITEM_NAME_POTATOES
                + VALID_DESC_ITEM_QUANTITY_POTATOES
                + VALID_DESC_ITEM_UNIT_POTATOES
                + VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                + VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_POTATOES)
                .withItemQuantity(VALID_ITEM_QUANTITY_POTATOES)
                .withItemUnit(VALID_ITEM_UNIT_POTATOES)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased()
                + INVALID_DESC_ITEM_NAME_POTATOES
                + VALID_DESC_ITEM_NAME_POTATOES;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + INVALID_DESC_ITEM_NAME_POTATOES
                + VALID_DESC_ITEM_NAME_POTATOES
                + VALID_DESC_ITEM_QUANTITY_POTATOES
                + VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                + VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_POTATOES)
                .withItemQuantity(VALID_ITEM_QUANTITY_POTATOES)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    //@Test
    //public void parse_resetTags_success() {
    //    Index targetIndex = INDEX_THIRD_ITEM;
    //    String userInput = targetIndex.getOneBased() + TAG_EMPTY;
    //
    //    EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withTags().build();
    //    EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
    //
    //    assertParseSuccess(parser, userInput, expectedCommand);
    //}
}
