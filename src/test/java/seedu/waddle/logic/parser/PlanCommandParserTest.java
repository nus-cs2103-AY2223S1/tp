package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.BUDGET_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.DAY_NUMBER_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_BUDGET_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_COUNTRY_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_DAY_NUMBER_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_PEOPLE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.ITINERARY_DESC_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.START_TIME_DESC_1200;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_BUDGET_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DAY_NUMBER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_TIME_1200;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PEOPLE;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_SECOND_ITINERARY;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_THIRD_ITINERARY;

import java.time.LocalTime;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.EditCommand;
import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.logic.commands.PlanCommand;
import seedu.waddle.model.item.StartTime;
import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.DayNumber;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;
import seedu.waddle.testutil.EditItineraryDescriptorBuilder;

public class PlanCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlanCommand.MESSAGE_USAGE);

    private PlanCommandParser parser = new PlanCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "12:00", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DAY_NUMBER_DESC + START_TIME_DESC_1200, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DAY_NUMBER_DESC + START_TIME_DESC_1200, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "invalid" + DAY_NUMBER_DESC + START_TIME_DESC_1200, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DAY_NUMBER_DESC + START_TIME_DESC_1200,
                DayNumber.MESSAGE_CONSTRAINTS); // invalid day number
        assertParseFailure(parser, "1" + DAY_NUMBER_DESC + INVALID_START_TIME_DESC,
                StartTime.MESSAGE_CONSTRAINTS); // invalid start time

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DAY_NUMBER_DESC + INVALID_START_TIME_DESC,
                DayNumber.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = Index.fromZeroBased(0).getOneBased() + DAY_NUMBER_DESC + START_TIME_DESC_1200;
        PlanCommand expectedCommand = new PlanCommand(Index.fromZeroBased(0),
                new DayNumber(VALID_DAY_NUMBER), VALID_START_TIME_1200);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
