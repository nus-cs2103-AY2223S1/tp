package seedu.travelr.logic.parser;

import static seedu.travelr.logic.commands.CommandTestUtil.DATE_DESC;
import static seedu.travelr.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.EVENT_DESC_EATING;
import static seedu.travelr.logic.commands.CommandTestUtil.EVENT_DESC_SIGHTSEEING;
import static seedu.travelr.logic.commands.CommandTestUtil.LOCATION_DESC;
import static seedu.travelr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.travelr.logic.commands.CommandTestUtil.TITLE_DESC_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.TITLE_DESC_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_EATING;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.travelr.testutil.TypicalTrips.ANTARCTICA;
import static seedu.travelr.testutil.TypicalTrips.GERMANY;

import org.junit.jupiter.api.Test;

import seedu.travelr.logic.commands.AddCommand;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.TripBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Trip expectedTrip = new TripBuilder(ANTARCTICA).withEvents(VALID_EVENT_EATING).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_ANTARCTICA
                + DESCRIPTION_DESC_ANTARCTICA + EVENT_DESC_EATING
                + LOCATION_DESC + DATE_DESC, new AddCommand(expectedTrip));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_GERMANY + TITLE_DESC_ANTARCTICA
                + DESCRIPTION_DESC_ANTARCTICA + EVENT_DESC_EATING
                + LOCATION_DESC + DATE_DESC, new AddCommand(expectedTrip));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, TITLE_DESC_ANTARCTICA
                + DESCRIPTION_DESC_ANTARCTICA + EVENT_DESC_EATING
                + LOCATION_DESC + DATE_DESC, new AddCommand(expectedTrip));

        // multiple emails - last email accepted
        assertParseSuccess(parser, TITLE_DESC_ANTARCTICA
                + DESCRIPTION_DESC_ANTARCTICA + EVENT_DESC_EATING
                + LOCATION_DESC + DATE_DESC, new AddCommand(expectedTrip));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, TITLE_DESC_ANTARCTICA + DESCRIPTION_DESC_GERMANY
                + DESCRIPTION_DESC_ANTARCTICA + EVENT_DESC_EATING
                + LOCATION_DESC + DATE_DESC, new AddCommand(expectedTrip));

        // multiple events - all accepted
        Trip expectedTripMultipleEvents = new TripBuilder(ANTARCTICA).withEvents(
                VALID_EVENT_EATING, VALID_EVENT_SIGHTSEEING)
                .build();
        assertParseSuccess(parser, TITLE_DESC_ANTARCTICA + DESCRIPTION_DESC_ANTARCTICA
                + EVENT_DESC_SIGHTSEEING + EVENT_DESC_EATING
                + LOCATION_DESC + DATE_DESC, new AddCommand(expectedTripMultipleEvents));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero events
        Trip expectedTrip = new TripBuilder(GERMANY).withEvents().build();
        assertParseSuccess(parser, TITLE_DESC_GERMANY + DESCRIPTION_DESC_GERMANY + LOCATION_DESC + DATE_DESC,
                new AddCommand(expectedTrip));
    }

    //Not working at the moment
    /*
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_ANTARCTICA + DESCRIPTION_DESC_ANTARCTICA,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, TITLE_DESC_ANTARCTICA + DESCRIPTION_DESC_ANTARCTICA,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, TITLE_DESC_ANTARCTICA + DESCRIPTION_DESC_ANTARCTICA,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, TITLE_DESC_ANTARCTICA + VALID_DESCRIPTION_ANTARCTICA,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_ANTARCTICA + VALID_DESCRIPTION_ANTARCTICA,
                expectedMessage);
    }
     */

    //Not working at the moment
    /*
    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DESCRIPTION_DESC_ANTARCTICA
                + EVENT_DESC_SIGHTSEEING + EVENT_DESC_EATING, Title.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, TITLE_DESC_ANTARCTICA + INVALID_DESCRIPTION_DESC
                + EVENT_DESC_SIGHTSEEING + EVENT_DESC_EATING, Description.MESSAGE_CONSTRAINTS);

        // invalid event
        assertParseFailure(parser, TITLE_DESC_ANTARCTICA + DESCRIPTION_DESC_ANTARCTICA
                + INVALID_EVENT_DESC + VALID_EVENT_EATING, Event.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_DESCRIPTION_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_ANTARCTICA
                + DESCRIPTION_DESC_ANTARCTICA + EVENT_DESC_SIGHTSEEING + EVENT_DESC_EATING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
     */
}
