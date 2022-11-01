package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BIRTHDAY_1_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BIRTHDAY_2_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BIRTHDAY_3_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_PRODUCT1;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_PRODUCT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.product.Product;
import seedu.address.testutil.ClientBuilder;

public class AddClientCommandParserTest {
    private AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).withProducts(VALID_PRODUCT_1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT1, new AddClientCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT1, new AddClientCommand(expectedClient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT1, new AddClientCommand(expectedClient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT1, new AddClientCommand(expectedClient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT1, new AddClientCommand(expectedClient));

        // multiple Products - all accepted
        Client expectedClientMultipleProducts = new ClientBuilder(BOB).withProducts(VALID_PRODUCT_1, VALID_PRODUCT_2)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PRODUCT_DESC_PRODUCT2 + PRODUCT_DESC_PRODUCT1, new AddClientCommand(expectedClientMultipleProducts));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero products
        Client expectedClient = new ClientBuilder(AMY).withProducts().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddClientCommand(expectedClient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PRODUCT_DESC_PRODUCT2 + PRODUCT_DESC_PRODUCT1, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PRODUCT_DESC_PRODUCT2 + PRODUCT_DESC_PRODUCT1, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + PRODUCT_DESC_PRODUCT2 + PRODUCT_DESC_PRODUCT1, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + PRODUCT_DESC_PRODUCT2 + PRODUCT_DESC_PRODUCT1, Address.MESSAGE_CONSTRAINTS);

        // invalid product
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_PRODUCT_DESC + PRODUCT_DESC_PRODUCT1, Product.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT2 + PRODUCT_DESC_PRODUCT1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidBirthday_failure() {
        // boundary value analysis
        // ep: [1jan, 31jan, 1feb, 28feb, ... (basically all valid dates)][invalid dates]
        // 30 feb, not exist
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_BIRTHDAY_1_DESC, Birthday.MESSAGE_CONSTRAINTS);
        // 29 feb 2001, non leap year
        assertParseFailure(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + INVALID_BIRTHDAY_2_DESC, Birthday.MESSAGE_CONSTRAINTS);
        // 31 nov, not exist
        assertParseFailure(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + INVALID_BIRTHDAY_3_DESC, Birthday.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validBirthday_success() {
        // boundary value analysis, using start and end values from valid dates
        Client expectedClient = new ClientBuilder(BOB)
                .withProducts(VALID_PRODUCT_1).withBirthday(VALID_BIRTHDAY_AMY).build();

        // 1 jan
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT1 + BIRTHDAY_DESC_AMY, new AddClientCommand(expectedClient));

        Client expectedClient2 = new ClientBuilder(BOB)
                .withProducts(VALID_PRODUCT_1).withBirthday(VALID_BIRTHDAY_BOB).build();

        // 31 dec
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PRODUCT_DESC_PRODUCT1 + BIRTHDAY_DESC_BOB, new AddClientCommand(expectedClient2));
    }
}
