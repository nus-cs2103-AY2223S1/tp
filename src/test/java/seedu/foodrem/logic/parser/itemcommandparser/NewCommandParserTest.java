package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.itemcommands.NewCommand;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.MessageToUser;
import seedu.foodrem.testutil.TypicalItems;

/**
 * A class to test the NewCommandParser.
 */
public class NewCommandParserTest {
    private final NewCommandParser parser = new NewCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder(TypicalItems.CUCUMBERS_WITHOUT_TAG).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS, new NewCommand(expectedItem));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS, new NewCommand(expectedItem));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS, new NewCommand(expectedItem));

        // multiple units - last unit accepted
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS, new NewCommand(expectedItem));

        // multiple bought dates - last bought date accepted
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS, new NewCommand(expectedItem));

        // multiple expiry dates - last expiry date accepted
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS, new NewCommand(expectedItem));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Item expectedItem = new ItemBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_POTATOES)
                .withItemBoughtDate(CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES)
                .withItemExpiryDate(CommandTestUtil.VALID_ITEM_EXPIRY_DATE_POTATOES)
                .build();
        assertParseSuccess(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES,
                new NewCommand(expectedItem));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NewCommand.getUsage());

        // missing name prefix
        assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.VALID_ITEM_NAME_POTATOES,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                CommandTestUtil.INVALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);

        // invalid quantity
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_POTATOES,
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);

        // invalid unit
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);

        // invalid bought date
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE);

        // invalid expiry date
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);

        // invalid price
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_PRICE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_PRICE);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.INVALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS,
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);

        // non-empty preamble
        assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.VALID_DESC_ITEM_NAME_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_UNIT_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_CUCUMBERS
                        + CommandTestUtil.VALID_DESC_ITEM_PRICE_CUCUMBERS,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NewCommand.getUsage()));
    }
}
