package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.itemcommands.SortCommand;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.model.item.itemcomparators.ItemBoughtDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemExpiryDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemNameComparator;
import seedu.foodrem.model.item.itemcomparators.ItemPriceComparator;
import seedu.foodrem.model.item.itemcomparators.ItemQuantityComparator;
import seedu.foodrem.model.item.itemcomparators.ItemRemarkComparator;
import seedu.foodrem.model.item.itemcomparators.ItemUnitComparator;

class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    void parse_success() {
        SortCommand sortCommandName = new SortCommand(new ItemNameComparator());
        SortCommand sortCommandQuantity = new SortCommand(new ItemQuantityComparator());
        SortCommand sortCommandUnit = new SortCommand(new ItemUnitComparator());
        SortCommand sortCommandBoughtDate = new SortCommand(new ItemBoughtDateComparator());
        SortCommand sortCommandExpiryDate = new SortCommand(new ItemExpiryDateComparator());
        SortCommand sortCommandPrice = new SortCommand(new ItemPriceComparator());
        SortCommand sortCommandRemark = new SortCommand(new ItemRemarkComparator());

        assertParseSuccess(parser, String.valueOf(CliSyntax.PREFIX_NAME), sortCommandName);
        assertParseSuccess(parser, String.valueOf(CliSyntax.PREFIX_ITEM_QUANTITY), sortCommandQuantity);
        assertParseSuccess(parser, String.valueOf(CliSyntax.PREFIX_ITEM_UNIT), sortCommandUnit);
        assertParseSuccess(parser, String.valueOf(CliSyntax.PREFIX_ITEM_BOUGHT_DATE), sortCommandBoughtDate);
        assertParseSuccess(parser, String.valueOf(CliSyntax.PREFIX_ITEM_EXPIRY_DATE), sortCommandExpiryDate);
        assertParseSuccess(parser, String.valueOf(CliSyntax.PREFIX_ITEM_PRICE), sortCommandPrice);
        assertParseSuccess(parser, String.valueOf(CliSyntax.PREFIX_ITEM_REMARKS), sortCommandRemark);
    }

    @Test
    void parse_blank_failure() {
        String invalidFormat = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.getUsage());
        assertParseFailure(parser, "", invalidFormat);
    }

    @Test
    void parse_preambleNonEmpty_failure() {
        String invalidFormat = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.getUsage());
        assertParseFailure(parser, "1 " + CliSyntax.PREFIX_NAME, invalidFormat);
        assertParseFailure(parser, "1 " + CliSyntax.PREFIX_ITEM_QUANTITY, invalidFormat);
        assertParseFailure(parser, "1 " + CliSyntax.PREFIX_ITEM_UNIT, invalidFormat);
        assertParseFailure(parser, "1 " + CliSyntax.PREFIX_ITEM_BOUGHT_DATE, invalidFormat);
        assertParseFailure(parser, "1 " + CliSyntax.PREFIX_ITEM_EXPIRY_DATE, invalidFormat);
        assertParseFailure(parser, "1 " + CliSyntax.PREFIX_ITEM_PRICE, invalidFormat);
        assertParseFailure(parser, "1 " + CliSyntax.PREFIX_ITEM_REMARKS, invalidFormat);
    }

    @Test
    void parse_preambleMultiplePrefix_failure() {
        String invalidFormat = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.getUsage());
        assertParseFailure(parser, "" + CliSyntax.PREFIX_NAME + CliSyntax.PREFIX_NAME, invalidFormat);
        assertParseFailure(parser, "" + CliSyntax.PREFIX_ITEM_QUANTITY + CliSyntax.PREFIX_NAME, invalidFormat);
    }
}
