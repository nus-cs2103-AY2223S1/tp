package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.*;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.model.itinerary.*;
import seedu.waddle.testutil.ItineraryBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Itinerary expectedItinerary = new ItineraryBuilder(WINTER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_WINTER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_SUMMER + NAME_DESC_WINTER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple country - last country accepted
        assertParseSuccess(parser, NAME_DESC_WINTER + COUNTRY_DESC_SUMMER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple people - last people accepted
        assertParseSuccess(parser, NAME_DESC_WINTER + PEOPLE_DESC_WINTER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_SUMMER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        /* TODO: multiple budget
        Itinerary expectedItineraryMultipleTags = new ItineraryBuilder(WINTER).build();
        assertParseSuccess(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_WINTER + PEOPLE_DESC_SUMMER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedItineraryMultipleTags));

         */
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER, expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + VALID_START_DATE_WINTER
                + DURATION_DESC_WINTER, expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + VALID_DURATION_WINTER, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_WINTER + VALID_COUNTRY_WINTER + VALID_START_DATE_WINTER
                + VALID_DURATION_WINTER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_WINTER + INVALID_COUNTRY_DESC + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Country.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + INVALID_START_DATE_DESC
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Date.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_DURATION_DESC + PEOPLE_DESC_WINTER, ItineraryDuration.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + INVALID_PEOPLE_DESC, People.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_DURATION_DESC + PEOPLE_DESC_WINTER, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_WINTER + COUNTRY_DESC_WINTER
                + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
