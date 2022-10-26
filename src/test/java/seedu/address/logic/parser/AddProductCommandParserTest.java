package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_PRODUCT1;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_PRODUCT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.model.product.Product;
import seedu.address.testutil.ProductBuilder;

public class AddProductCommandParserTest {
    private AddProductCommandParser parser = new AddProductCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Product expectedProduct = new ProductBuilder().withProductName(VALID_PRODUCT_1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PRODUCT_DESC_PRODUCT1,
                new AddProductCommand(expectedProduct));

        // multiple products - last product accepted
        assertParseSuccess(parser, PRODUCT_DESC_PRODUCT2 + PRODUCT_DESC_PRODUCT1,
                new AddProductCommand(expectedProduct));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE);

        // missing product prefix
        assertParseFailure(parser, VALID_PRODUCT_1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid product name
        assertParseFailure(parser, INVALID_PRODUCT_DESC,
                Product.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PRODUCT_DESC_PRODUCT1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE));
    }
}
