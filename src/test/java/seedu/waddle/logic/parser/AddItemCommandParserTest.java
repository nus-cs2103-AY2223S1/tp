package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.BUDGET_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_COUNTRY_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_PEOPLE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.ITINERARY_DESC_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.ITINERARY_DESC_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.waddle.testutil.ItemBuilder.DEFAULT_COST;
import static seedu.waddle.testutil.ItemBuilder.DEFAULT_DURATION;
import static seedu.waddle.testutil.ItemBuilder.DEFAULT_ITEM_DESC;
import static seedu.waddle.testutil.ItemBuilder.DEFAULT_PRIORITY;
import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.AddItemCommand;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;
import seedu.waddle.testutil.ItemBuilder;
import seedu.waddle.testutil.ItineraryBuilder;

public class AddItemCommandParserTest {
    private AddItemCommandParser parser = new AddItemCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DEFAULT_ITEM_DESC + DEFAULT_DURATION
                        + DEFAULT_COST + DEFAULT_PRIORITY, new AddItemCommand(expectedItem));

        // multiple desc - last desc accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_ITEM_DESC_ART + DEFAULT_ITEM_DESC
                + DEFAULT_DURATION + DEFAULT_COST + DEFAULT_PRIORITY, new AddItemCommand(expectedItem));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DEFAULT_ITEM_DESC + VALID_DURATION_WINTER + DEFAULT_DURATION
                + DEFAULT_COST + DEFAULT_PRIORITY, new AddItemCommand(expectedItem));

        // multiple cost - last cost accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DEFAULT_ITEM_DESC + DEFAULT_DURATION
                + DEFAULT_COST + DEFAULT_PRIORITY, new AddItemCommand(expectedItem));

        // multiple priority - last priority accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DEFAULT_ITEM_DESC + DEFAULT_DURATION
                + DEFAULT_COST + DEFAULT_PRIORITY, new AddItemCommand(expectedItem));

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
        assertParseFailure(parser, VALID_ITINERARY_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER, expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + VALID_START_DATE_WINTER
                + DURATION_DESC_WINTER, expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + VALID_DURATION_WINTER, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ITINERARY_DESC_WINTER + VALID_COUNTRY_WINTER + VALID_START_DATE_WINTER
                + VALID_DURATION_WINTER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Description.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + INVALID_COUNTRY_DESC + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Country.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + INVALID_START_DATE_DESC
                + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER, Date.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_DURATION_DESC + PEOPLE_DESC_WINTER, ItineraryDuration.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + DURATION_DESC_WINTER + INVALID_PEOPLE_DESC, People.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + COUNTRY_DESC_WINTER + START_DATE_DESC_WINTER
                + INVALID_DURATION_DESC + PEOPLE_DESC_WINTER, Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ITINERARY_DESC_DESC_WINTER + COUNTRY_DESC_WINTER
                        + START_DATE_DESC_WINTER + DURATION_DESC_WINTER + PEOPLE_DESC_WINTER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
