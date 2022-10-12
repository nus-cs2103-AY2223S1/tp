package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GOODS_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BUY_ORANGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BuyCommand;
import seedu.address.model.transaction.Goods;


public class BuyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BuyCommand.MESSAGE_TRANSACTION_INVALID);
    private BuyCommandParser parser = new BuyCommandParser();

    @Test
    public void parse_invalidIndexPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);


        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }


    //DONE
    @Test
    public void parse_invalidBuyFields_failure() {
        // invalid price
        assertParseFailure(parser, "1" + VALID_QUANTITY_BUY_ORANGE + VALID_GOODS_BUY_ORANGE + INVALID_PRICE,
                MESSAGE_INVALID_FORMAT);
        // invalid quantity
        assertParseFailure(parser, "1" + INVALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_PRICE_BUY_ORANGE,
                MESSAGE_INVALID_FORMAT);
        // invalid goods
        assertParseFailure(parser, "1" + VALID_QUANTITY + INVALID_GOODS_EMPTY + VALID_PRICE ,
                Goods.MESSAGE_CONSTRAINTS);
        // only price
        assertParseFailure(parser, "1" + VALID_PRICE_BUY_ORANGE, MESSAGE_INVALID_FORMAT);
        // only quantity
        assertParseFailure(parser, "1" + VALID_QUANTITY_BUY_ORANGE,
                MESSAGE_INVALID_FORMAT);
        // only goods
        assertParseFailure(parser, "1" + VALID_GOODS_BUY_ORANGE, MESSAGE_INVALID_FORMAT);
        // goods and price but no quantity
        assertParseFailure(parser, "1" + VALID_GOODS_BUY_ORANGE + VALID_PRICE_BUY_ORANGE,
                MESSAGE_INVALID_FORMAT);


    }


}
