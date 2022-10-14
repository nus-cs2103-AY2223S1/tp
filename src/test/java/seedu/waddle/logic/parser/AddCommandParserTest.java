package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.END_DATE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.END_DATE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_COUNTRY_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_PEOPLE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.NAME_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.NAME_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_END_DATE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_NAME_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.waddle.testutil.TypicalItineraries.SUMMER;
import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;
import seedu.waddle.testutil.ItineraryBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Itinerary expectedItinerary = new ItineraryBuilder(WINTER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_WINTER + COUNTRY_DESC_WINTER
                + START_DATE_DESC_WINTER + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_SUMMER + NAME_DESC_WINTER + COUNTRY_DESC_WINTER
                + START_DATE_DESC_WINTER + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_WINTER + COUNTRY_DESC_SUMMER + COUNTRY_DESC_WINTER
                + START_DATE_DESC_WINTER + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_WINTER + PEOPLE_DESC_WINTER + START_DATE_DESC_SUMMER
                + START_DATE_DESC_WINTER + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + END_DATE_DESC_SUMMER + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedItinerary));

        // multiple tags - all accepted
        Itinerary expectedPersonMultipleTags = new ItineraryBuilder(WINTER).build();
        assertParseSuccess(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + END_DATE_DESC_WINTER + PEOPLE_DESC_SUMMER + PEOPLE_DESC_WINTER,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Itinerary expectedItinerary = new ItineraryBuilder(SUMMER).build();
        assertParseSuccess(parser, NAME_DESC_SUMMER + COUNTRY_DESC_SUMMER + START_DATE_DESC_SUMMER
                + END_DATE_DESC_SUMMER, new AddCommand(expectedItinerary));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + END_DATE_DESC_WINTER, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_WINTER + VALID_COUNTRY_WINTER + START_DATE_DESC_WINTER
                + END_DATE_DESC_WINTER, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + VALID_START_DATE_WINTER
                + END_DATE_DESC_WINTER, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + VALID_END_DATE_WINTER, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_WINTER + VALID_COUNTRY_WINTER + VALID_START_DATE_WINTER
                + VALID_END_DATE_WINTER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_WINTER + INVALID_COUNTRY_DESC + START_DATE_DESC_WINTER
                + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER, Country.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + INVALID_START_DATE_DESC
                + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER, Date.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_END_DATE_DESC + PEOPLE_DESC_WINTER, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + END_DATE_DESC_WINTER + INVALID_PEOPLE_DESC, People.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_END_DATE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_WINTER + COUNTRY_DESC_WINTER
                + START_DATE_DESC_WINTER + END_DATE_DESC_WINTER + PEOPLE_DESC_WINTER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
