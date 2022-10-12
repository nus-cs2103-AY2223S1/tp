package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BuyCommand;
import seedu.address.model.transaction.*;

public class BuyCommandParserTest {
    private BuyCommandParser parser = new BuyCommandParser();
    private final String nonEmptyQuantity = "100 ";
    private final String nonEmptyPrice = "1.5 ";
    private final String nonEmptyGood = "Non empty good ";

    private final Goods goods = new Goods(nonEmptyGood);
    private final Price price = new Price(nonEmptyPrice);
    private final Quantity quantity = new Quantity(nonEmptyQuantity);
    private final Transaction nonEmptyTransaction = new BuyTransaction(goods, price, quantity);



    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_QUANTITY + nonEmptyQuantity + PREFIX_GOODS + nonEmptyGood + PREFIX_PRICE +
                nonEmptyPrice;
        BuyCommand expectedCommand = new BuyCommand(INDEX_FIRST_COMPANY, nonEmptyTransaction);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, BuyCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, BuyCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, BuyCommand.COMMAND_WORD + " " + nonEmptyTransaction, expectedMessage);
    }
}