package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.BUDGET_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.BUDGET_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_COUNTRY_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_PEOPLE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.ITINERARY_DESC_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.ITINERARY_DESC_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_BUDGET_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;
import seedu.waddle.testutil.ItineraryBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Itinerary expectedItinerary = new ItineraryBuilder(WINTER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, ITINERARY_DESC_DESC_SUMMER + ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple countries - last country accepted
        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_SUMMER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple people - last people accepted
        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + PEOPLE_DESC_SUMMER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_SUMMER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple budget - last budget accepted
        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_SUMMER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // country field missing
        Itinerary expectedItinerary = new ItineraryBuilder().withDescription(VALID_ITINERARY_DESC_WINTER)
                .withCountry("default").withStartDate(VALID_START_DATE_WINTER)
                .withDuration(VALID_DURATION_WINTER).withPeople(VALID_PEOPLE_WINTER)
                .withBudget(VALID_BUDGET_WINTER).build();

        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // people field missing
        expectedItinerary = new ItineraryBuilder().withDescription(VALID_ITINERARY_DESC_WINTER)
                .withCountry(VALID_COUNTRY_WINTER).withStartDate(VALID_START_DATE_WINTER)
                .withDuration(VALID_DURATION_WINTER).withPeople("1")
                .withBudget(VALID_BUDGET_WINTER).build();

        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_WINTER + COUNTRY_DESC_WINTER + BUDGET_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // budget field missing
        expectedItinerary = new ItineraryBuilder().withDescription(VALID_ITINERARY_DESC_WINTER)
                .withCountry(VALID_COUNTRY_WINTER).withStartDate(VALID_START_DATE_WINTER)
                .withDuration(VALID_DURATION_WINTER).withPeople(VALID_PEOPLE_WINTER)
                .withBudget("0").build();

        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_WINTER + COUNTRY_DESC_WINTER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // all optional fields missing
        expectedItinerary = new ItineraryBuilder().withDescription(VALID_ITINERARY_DESC_WINTER)
                .withCountry("default").withStartDate(VALID_START_DATE_WINTER)
                .withDuration(VALID_DURATION_WINTER).withPeople("1")
                .withBudget("0").build();

        assertParseSuccess(parser, ITINERARY_DESC_DESC_WINTER + START_DATE_DESC_WINTER
                        + DURATION_DESC_WINTER, new AddCommand(expectedItinerary));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_ITINERARY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER, expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + VALID_START_DATE_WINTER
                + DURATION_DESC_WINTER, expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + START_DATE_DESC_WINTER
                + VALID_DURATION_WINTER, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ITINERARY_DESC_WINTER + VALID_START_DATE_WINTER
                + VALID_DURATION_WINTER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, INVALID_DESC_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Description.MESSAGE_CONSTRAINTS);

        // invalid country
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + INVALID_COUNTRY_DESC + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Country.MESSAGE_CONSTRAINTS);

        // invalid start date
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + INVALID_START_DATE_DESC
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Date.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_DURATION_DESC + PEOPLE_DESC_WINTER, ItineraryDuration.MESSAGE_CONSTRAINTS);

        // invalid people
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + INVALID_PEOPLE_DESC, People.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_DESC_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_DURATION_DESC + PEOPLE_DESC_WINTER, Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER
                + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
