package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_NAME_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_NAME_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_ITEM_UNIT_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_UNIT_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_EXPIRY_DATE_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_UNIT_POTATOES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditItemDescriptorBuilder;

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
        assertParseFailure(parser, "1" + INVALID_DESC_ITEM_NAME_CUCUMBERS, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DESC_ITEM_QUANTITY_CUCUMBERS, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DESC_ITEM_UNIT_CUCUMBERS, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS, Name.MESSAGE_CONSTRAINTS);

        // Invalid Name followed by valid Quantity
        assertParseFailure(parser,
            "1"
                + INVALID_DESC_ITEM_NAME_CUCUMBERS
                + VALID_DESC_ITEM_QUANTITY_POTATOES,
            Phone.MESSAGE_CONSTRAINTS);

        // Valid name followed by valid name.
        // The test case for invalid name followed by valid name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1"
                + INVALID_DESC_ITEM_NAME_CUCUMBERS
                + VALID_DESC_ITEM_NAME_CUCUMBERS,
            Phone.MESSAGE_CONSTRAINTS);

        //// while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Item} being edited,
        //// parsing it together with a valid tag results in error
        //assertParseFailure(parser, "1"
        //    + TAG_DESC_FRIEND
        //    + TAG_DESC_HUSBAND
        //    + TAG_EMPTY,
        //    Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                + INVALID_DESC_ITEM_NAME_POTATOES
                + INVALID_DESC_ITEM_QUANTITY_POTATOES
                + VALID_DESC_ITEM_EXPIRY_DATE_POTATOES
                + VALID_DESC_ITEM_BOUGHT_DATE_POTATOES,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
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
            .withItemName(VALID_DESC_ITEM_NAME_POTATOES)
            .withItemQuantity(VALID_DESC_ITEM_QUANTITY_POTATOES)
            .withItemUnit(VALID_DESC_ITEM_UNIT_POTATOES)
            .withItemBoughtDate(VALID_DESC_ITEM_BOUGHT_DATE_POTATOES)
            .withItemExpiryDate(VALID_DESC_ITEM_EXPIRY_DATE_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased()
            + VALID_DESC_ITEM_NAME_CUCUMBERS
            + VALID_DESC_ITEM_QUANTITY_POTATOES;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
            .withItemName(VALID_DESC_ITEM_NAME_CUCUMBERS)
            .withItemQuantity(VALID_DESC_ITEM_QUANTITY_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
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
        Index targetIndex = INDEX_FIRST_PERSON;
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
            .withItemName(VALID_DESC_ITEM_NAME_POTATOES)
            .withItemQuantity(VALID_DESC_ITEM_QUANTITY_POTATOES)
            .withItemUnit(VALID_DESC_ITEM_UNIT_POTATOES)
            .withItemBoughtDate(VALID_DESC_ITEM_BOUGHT_DATE_POTATOES)
            .withItemExpiryDate(VALID_DESC_ITEM_EXPIRY_DATE_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased()
            + INVALID_DESC_ITEM_NAME_CUCUMBERS
            + VALID_DESC_ITEM_QUANTITY_POTATOES;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
            .withItemQuantity(VALID_DESC_ITEM_QUANTITY_POTATOES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
            + VALID_DESC_ITEM_NAME_POTATOES
            + INVALID_DESC_ITEM_NAME_CUCUMBERS
            + VALID_DESC_ITEM_QUANTITY_POTATOES
            + VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
            + VALID_DESC_ITEM_EXPIRY_DATE_POTATOES;
        descriptor = new EditItemDescriptorBuilder()
            .withItemUnit(VALID_DESC_ITEM_UNIT_POTATOES)
            .withItemBoughtDate(VALID_DESC_ITEM_BOUGHT_DATE_POTATOES)
            .withItemExpiryDate(VALID_DESC_ITEM_EXPIRY_DATE_POTATOES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    //@Test
    //public void parse_resetTags_success() {
    //    Index targetIndex = INDEX_THIRD_PERSON;
    //    String userInput = targetIndex.getOneBased() + TAG_EMPTY;
    //
    //    EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withTags().build();
    //    EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
    //
    //    assertParseSuccess(parser, userInput, expectedCommand);
    //}
}
