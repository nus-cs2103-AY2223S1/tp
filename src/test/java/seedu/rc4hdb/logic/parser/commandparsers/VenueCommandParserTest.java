package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TIME_PERIOD;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_VENUE_NAME;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;
import static seedu.rc4hdb.testutil.TypicalBookings.HP_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.HP_5_TO_6PM_STRING;
import static seedu.rc4hdb.testutil.TypicalBookings.MONDAY;
import static seedu.rc4hdb.testutil.TypicalBookings.MONDAY_STRING;
import static seedu.rc4hdb.testutil.TypicalVenues.DISCUSSION_ROOM_NAME;
import static seedu.rc4hdb.testutil.TypicalVenues.DISCUSSION_ROOM_STRING;
import static seedu.rc4hdb.testutil.TypicalVenues.HALL_STRING;
import static seedu.rc4hdb.testutil.TypicalVenues.HALL_VENUE_NAME;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_STRING;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;
import seedu.rc4hdb.logic.commands.venuecommands.BookCommand;
import seedu.rc4hdb.logic.commands.venuecommands.UnbookCommand;
import seedu.rc4hdb.logic.commands.venuecommands.VenueAddCommand;
import seedu.rc4hdb.logic.commands.venuecommands.VenueDeleteCommand;
import seedu.rc4hdb.logic.commands.venuecommands.VenueViewCommand;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;

/**
 * Unit tests for {@link VenueCommandParser}.
 */
public class VenueCommandParserTest {

    private static final String INVALID_VENUE_SECOND_COMMAND = "remove";

    private final VenueCommandParser parser = new VenueCommandParser();

    @Test
    public void parse_validBookCommand() {
        String bookCommand = new StringBuilder()
                .append(BookCommand.COMMAND_WORD).append(" ")
                .append(1).append(" ")
                .append(PREFIX_VENUE_NAME).append(MEETING_ROOM_STRING).append(" ")
                .append(PREFIX_TIME_PERIOD).append(HP_5_TO_6PM_STRING).append(" ")
                .append(PREFIX_DAY).append(MONDAY_STRING).toString();
        BookingDescriptor expectedBookingDescriptor = new BookingDescriptor();
        expectedBookingDescriptor.setVenueName(MEETING_ROOM_VENUE_NAME);
        expectedBookingDescriptor.setHourPeriod(HP_5_TO_6PM);
        expectedBookingDescriptor.setDayOfWeek(MONDAY);
        assertParseSuccess(parser, bookCommand, new BookCommand(Index.fromOneBased(1), expectedBookingDescriptor));
    }

    @Test
    public void parse_validUnbookCommand() {
        String unbookCommand = new StringBuilder()
                .append(UnbookCommand.COMMAND_WORD).append(" ")
                .append(PREFIX_VENUE_NAME).append(MEETING_ROOM_STRING).append(" ")
                .append(PREFIX_TIME_PERIOD).append(HP_5_TO_6PM_STRING).append(" ")
                .append(PREFIX_DAY).append(MONDAY_STRING).toString();
        BookingDescriptor expectedBookingDescriptor = new BookingDescriptor();
        expectedBookingDescriptor.setVenueName(MEETING_ROOM_VENUE_NAME);
        expectedBookingDescriptor.setHourPeriod(HP_5_TO_6PM);
        expectedBookingDescriptor.setDayOfWeek(MONDAY);
        assertParseSuccess(parser, unbookCommand, new UnbookCommand(expectedBookingDescriptor));
    }

    @Test
    public void parse_validVenueAddCommand() {
        String venueAddCommand = VenueAddCommand.COMMAND_WORD + " " + HALL_STRING;
        assertParseSuccess(parser, venueAddCommand, new VenueAddCommand(HALL_VENUE_NAME));
    }

    @Test
    public void parse_validVenueDeleteCommand() {
        String venueDeleteCommand = VenueDeleteCommand.COMMAND_WORD + " " + HALL_STRING;
        assertParseSuccess(parser, venueDeleteCommand, new VenueDeleteCommand(HALL_VENUE_NAME));
    }

    @Test
    public void parse_validVenueViewCommand() {
        String venueViewCommand = VenueViewCommand.COMMAND_WORD + " " + DISCUSSION_ROOM_STRING;
        assertParseSuccess(parser, venueViewCommand, new VenueViewCommand(DISCUSSION_ROOM_NAME));
    }

    @Test
    public void parse_invalidSecondCommand() {
        assertParseFailure(parser, INVALID_VENUE_SECOND_COMMAND + " " + DISCUSSION_ROOM_STRING,
                MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void parse_noWhiteSpace() {
        assertParseFailure(parser, VenueViewCommand.COMMAND_WORD + HALL_STRING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

}
