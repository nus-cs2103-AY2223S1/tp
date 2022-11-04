package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.commons.core.Messages.MESSAGE_NEGATIVE_NUMBER;
import static seedu.condonery.commons.core.Messages.MESSAGE_NUMBER_INVALID;
import static seedu.condonery.commons.core.Messages.MESSAGE_RANGE_INVALID;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.RangePropertyCommand;
import seedu.condonery.model.property.PropertyPriceWithinRangePredicate;

public class RangePropertyCommandParserTest {
    private final RangePropertyCommandParser parser = new RangePropertyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangePropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validRangeCommand() {
        PropertyPriceWithinRangePredicate predicate = new PropertyPriceWithinRangePredicate(90000, 180000);
        assertParseSuccess(parser, " l/90000 u/180000", new RangePropertyCommand(predicate));
    }

    @Test
    public void parse_lowerMoreThanUpper() {
        assertParseFailure(parser, " l/80000 u/70000", String.format(MESSAGE_RANGE_INVALID,
                RangePropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeInLower() {
        assertParseFailure(parser, " l/-80000 u/70000", String.format(MESSAGE_NEGATIVE_NUMBER,
                RangePropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeInLowerAndUpper() {
        assertParseFailure(parser, " l/-80000 u/-70000", String.format(MESSAGE_NEGATIVE_NUMBER,
                RangePropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_lowerGivenStringInsteadOfNumber() {
        assertParseFailure(parser, " l/-asdasd u/70000", String.format(MESSAGE_NUMBER_INVALID,
                RangePropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_upperGivenStringInsteadOfNumber() {
        assertParseFailure(parser, " l/90000 u/-asdasd", String.format(MESSAGE_NUMBER_INVALID,
                RangePropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_upperGivenSymbolsInsteadOfNumber() {
        assertParseFailure(parser, " l/90000 u/-,,,,,", String.format(MESSAGE_NUMBER_INVALID,
                RangePropertyCommand.MESSAGE_USAGE));
    }

}
