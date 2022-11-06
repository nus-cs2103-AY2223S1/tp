package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static gim.logic.commands.CommandTestUtil.VALID_END_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_END_DATE_DESC;
import static gim.logic.commands.CommandTestUtil.VALID_START_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_START_DATE_DESC;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import gim.logic.commands.RangeCommand;
import gim.model.date.Date;
import gim.model.exercise.DateWithinRangePredicate;

class RangeCommandParserTest {

    private RangeCommandParser parser = new RangeCommandParser();

    @Test
    public void parse_variationOneAllFieldsPresent_success() {
        assertParseSuccess(parser, VALID_START_DATE_DESC + VALID_END_DATE_DESC,
                new RangeCommand(new DateWithinRangePredicate(new Date(VALID_START_DATE), new Date(VALID_END_DATE))));
    }

    @Test
    public void parse_variationTwoAllFieldsPresent_success() {
        assertParseSuccess(parser, " last/5",
                new RangeCommand(new DateWithinRangePredicate(new Date(LocalDate.now().minusDays(5).toString()),
                        new Date())));
    }

    @Test
    public void parse_emptyFields_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespaces_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyStartDate_throwsParseException() {
        assertParseFailure(parser, VALID_END_DATE_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyEndDate_throwsParseException() {
        assertParseFailure(parser, VALID_START_DATE_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allKeywords_throwsParseException() {
        String numberOfDaysDescription = " last/5";
        assertParseFailure(parser, VALID_START_DATE_DESC + VALID_END_DATE_DESC
                + numberOfDaysDescription, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_incorrectKeywordsCombinationOne_throwsParseException() {
        String numberOfDaysDescription = " last/5";
        assertParseFailure(parser, VALID_START_DATE_DESC
                + numberOfDaysDescription, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_incorrectKeywordsCombinationTwo_throwsParseException() {
        String numberOfDaysDescription = " last/5";
        assertParseFailure(parser, VALID_END_DATE_DESC
                + numberOfDaysDescription, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStartDate_failure() {
        assertParseFailure(parser, INVALID_START_DATE_DESC + VALID_END_DATE_DESC,
                String.format(Date.MESSAGE_CONSTRAINTS_FORMAT));
    }

    @Test
    public void parse_invalidEndDate_failure() {
        assertParseFailure(parser, VALID_START_DATE_DESC + INVALID_END_DATE_DESC,
                String.format(Date.MESSAGE_CONSTRAINTS_FORMAT));
    }

    @Test
    public void parse_negativeDays_failure() {
        assertParseFailure(parser, " last/-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RangeCommand.MESSAGE_USAGE_TWO));
    }

    @Test
    public void parse_exceedLimit_failure() {
        assertParseFailure(parser, " last/999999",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RangeCommand.MESSAGE_USAGE_TWO));
    }

    @Test
    public void parse_invalidDays_failure() {
        assertParseFailure(parser, " last/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RangeCommand.MESSAGE_USAGE_TWO));
    }
}
