package seedu.address.logic.parser.buyer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.BuyerCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESIRED_CHARACTERISTICS_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESIRED_CHARACTERISTICS_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PRICE_RANGE_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PRICE_RANGE_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PRICE_RANGE_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.TAG_DESC_PRIORITY_HIGH;
import static seedu.address.logic.commands.BuyerCommandTestUtil.TAG_DESC_PRIORITY_LOW;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalBuyers.AMY;
import static seedu.address.testutil.TypicalBuyers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.buyer.AddBuyerCommand;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.testutil.BuyerBuilder;

public class AddBuyerCommandParserTest {
    private AddBuyerCommandParser parser = new AddBuyerCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Buyer expectedBuyer = new BuyerBuilder(BOB).withPriority(VALID_PRIORITY_HIGH).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + PRICE_RANGE_DESC_BOB + DESIRED_CHARACTERISTICS_DESC_BOB
                        + TAG_DESC_PRIORITY_HIGH,
                new AddBuyerCommand(expectedBuyer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + PRICE_RANGE_DESC_BOB + DESIRED_CHARACTERISTICS_DESC_BOB
                        + TAG_DESC_PRIORITY_HIGH,
                new AddBuyerCommand(expectedBuyer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + PRICE_RANGE_DESC_BOB + DESIRED_CHARACTERISTICS_DESC_BOB
                        + TAG_DESC_PRIORITY_HIGH,
                new AddBuyerCommand(expectedBuyer));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + PRICE_RANGE_DESC_BOB + DESIRED_CHARACTERISTICS_DESC_BOB
                        + TAG_DESC_PRIORITY_HIGH,
                new AddBuyerCommand(expectedBuyer));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + PRICE_RANGE_DESC_BOB + DESIRED_CHARACTERISTICS_DESC_BOB
                        + TAG_DESC_PRIORITY_HIGH,
                new AddBuyerCommand(expectedBuyer));

        // multiple price ranges - last price range accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + PRICE_RANGE_DESC_AMY + PRICE_RANGE_DESC_BOB
                        + DESIRED_CHARACTERISTICS_DESC_BOB + TAG_DESC_PRIORITY_HIGH,
                new AddBuyerCommand(expectedBuyer));

        // multiple desired characteristics - last desired characteristics accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + PRICE_RANGE_DESC_BOB + DESIRED_CHARACTERISTICS_DESC_AMY
                        + DESIRED_CHARACTERISTICS_DESC_BOB + TAG_DESC_PRIORITY_HIGH,
                new AddBuyerCommand(expectedBuyer));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Buyer expectedBuyer = new BuyerBuilder(AMY)
                .withNoDesiredCharacteristics()
                .withNoPriceRange().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + TAG_DESC_PRIORITY_HIGH, new AddBuyerCommand(expectedBuyer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_PRIORITY_LOW + TAG_DESC_PRIORITY_HIGH, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_PRIORITY_LOW + TAG_DESC_PRIORITY_HIGH, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_PRIORITY_LOW + TAG_DESC_PRIORITY_HIGH, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_PRIORITY_LOW + TAG_DESC_PRIORITY_HIGH, Address.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);

        // invalid price range
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_PRICE_RANGE_DESC + DESIRED_CHARACTERISTICS_DESC_BOB
                + TAG_DESC_PRIORITY_LOW, PriceRange.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + PRICE_RANGE_DESC_BOB + DESIRED_CHARACTERISTICS_DESC_BOB
                        + TAG_DESC_PRIORITY_LOW,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE));
    }
}
