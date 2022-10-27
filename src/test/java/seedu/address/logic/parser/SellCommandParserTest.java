package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_NUMBERFORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GOODS_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS_SELL_PAPAYA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_SELL_PAPAYA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_SELL_PAPAYA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.transaction.Date.DEFAULT_PATTERN;
import static seedu.address.model.transaction.Date.NEW_PATTERN;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SellCommand;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.SellTransaction;
import seedu.address.model.transaction.Transaction;


public class SellCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SellCommand.MESSAGE_USAGE);
    private SellCommandParser parser = new SellCommandParser();

    @Test
    public void parse_invalidIndexPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // negative index
        assertParseFailure(parser, "-1" + VALID_QUANTITY + VALID_GOODS + VALID_PRICE,
                MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidSellFields_failure() {
        // invalid price
        assertParseFailure(parser, "1" + VALID_QUANTITY_SELL_PAPAYA + VALID_GOODS_SELL_PAPAYA + INVALID_PRICE,
                MESSAGE_INVALID_FORMAT);
        // invalid quantity
        assertParseFailure(parser, "1" + INVALID_QUANTITY + VALID_GOODS_SELL_PAPAYA + VALID_PRICE_SELL_PAPAYA,
                MESSAGE_INVALID_FORMAT);
        // invalid goods
        assertParseFailure(parser, "1" + VALID_QUANTITY + INVALID_GOODS_EMPTY + VALID_PRICE
                        + VALID_DATE_WITH_PREFIX, Goods.MESSAGE_CONSTRAINTS);
        // only price
        assertParseFailure(parser, "1" + VALID_PRICE_SELL_PAPAYA, MESSAGE_INVALID_FORMAT);
        // only quantity
        assertParseFailure(parser, "1" + VALID_QUANTITY_SELL_PAPAYA,
                MESSAGE_INVALID_FORMAT);
        // only goods
        assertParseFailure(parser, "1" + VALID_GOODS_SELL_PAPAYA, MESSAGE_INVALID_FORMAT);
        // goods and price but no quantity
        assertParseFailure(parser, "1" + VALID_GOODS_SELL_PAPAYA + VALID_PRICE_SELL_PAPAYA,
               MESSAGE_INVALID_FORMAT);
        // only date
        assertParseFailure(parser, "1" + VALID_DATE_WITH_PREFIX,
                MESSAGE_INVALID_FORMAT);
        // invalid date
        assertParseFailure(parser, "1" + VALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE_NEGATIVE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE_NUMBERFORMAT, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE_WITH_PREFIX, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + INVALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE_NUMBERFORMAT, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_GOODS_BUY_ORANGE + VALID_PRICE
                + INVALID_DATE_EMPTY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidFormat_failure() {
        assertParseFailure(parser, "1" + INVALID_QUANTITY + VALID_GOODS_SELL_PAPAYA + INVALID_PRICE,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_QUANTITY_SELL_PAPAYA + VALID_GOODS_SELL_PAPAYA + VALID_PRICE_SELL_PAPAYA,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_QUANTITY_BUY_ORANGE + VALID_GOODS_BUY_ORANGE
                + VALID_PRICE_SELL_PAPAYA, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_QUANTITY_BUY_ORANGE,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_QUANTITY_SELL_PAPAYA + VALID_QUANTITY_SELL_PAPAYA
                + VALID_QUANTITY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_QUANTITY + VALID_GOODS,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_GOODS + VALID_PRICE,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_QUANTITY_BUY_ORANGE + VALID_QUANTITY_BUY_ORANGE,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_GOODS_SELL_PAPAYA + VALID_GOODS_SELL_PAPAYA,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_GOODS + VALID_GOODS_SELL_PAPAYA + VALID_QUANTITY_SELL_PAPAYA,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_GOODS + VALID_GOODS_BUY_ORANGE + VALID_QUANTITY_BUY_ORANGE
                + INVALID_DATE, MESSAGE_INVALID_FORMAT);
        // date at wrong position
        assertParseFailure(parser, "1" + VALID_DATE + VALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_PRICE,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_QUANTITY + VALID_GOODS_BUY_ORANGE + VALID_DATE + VALID_PRICE,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_QUANTITY + VALID_DATE + VALID_GOODS_BUY_ORANGE + VALID_PRICE,
                MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_validField() {
        Goods goods = new Goods(VALID_GOODS_BUY_ORANGE);
        Quantity quantity = new Quantity(VALID_QUANTITY_BUY_ORANGE);
        Price price = new Price(VALID_PRICE_BUY_ORANGE);
        LocalDate now = LocalDate.now();
        LocalDate datetime = LocalDate.parse(now.toString(), DEFAULT_PATTERN);
        String output = datetime.format(NEW_PATTERN);
        Date date = new Date(output);
        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        assertParseSuccess(parser, "1" + VALID_QUANTITY + VALID_GOODS + VALID_PRICE,
                new SellCommand(Index.fromOneBased(1), transaction));

    }


}
