package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_UNIQUE_COMPARATOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_REVENUE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCustomerCommand;
import seedu.address.model.util.SortDirection;

public class SortCustomerCommandParserTest {
    private final SortCustomerCommandParser parser = new SortCustomerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCustomerByNameIncreasingCommand() {
        // no leading and trailing whitespaces
        SortCustomerCommand expectedSortCustomerCommand = SortCustomerCommandParser.convertPrefixDescriptionEntry(
                SortCustomerCommandParser.PREFIX_DESCRIPTION_MAP.get(PREFIX_SORT_CUSTOMER_NAME),
                SortDirection.INCREASING);
        assertParseSuccess(parser, " " + PREFIX_SORT_CUSTOMER_NAME + "+", expectedSortCustomerCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_SORT_CUSTOMER_NAME + "   + ", expectedSortCustomerCommand);
    }

    @Test
    public void parse_validArgs_returnsSortCustomerByRevenueDecreasingCommand() {
        // no leading and trailing whitespaces
        SortCustomerCommand expectedSortCustomerCommand = SortCustomerCommandParser.convertPrefixDescriptionEntry(
            SortCustomerCommandParser.PREFIX_DESCRIPTION_MAP.get(PREFIX_SORT_CUSTOMER_REVENUE),
            SortDirection.DECREASING);
        assertParseSuccess(parser, " " + PREFIX_SORT_CUSTOMER_REVENUE + "-", expectedSortCustomerCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_SORT_CUSTOMER_REVENUE + "   - ", expectedSortCustomerCommand);
    }

    @Test
    public void parse_multipleComparators_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " " + PREFIX_SORT_CUSTOMER_NAME + "-"
                + " " + PREFIX_SORT_CUSTOMER_REVENUE + "-", MESSAGE_INVALID_UNIQUE_COMPARATOR);
    }

}
