package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BookCommand;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;


public class BookCommandParserTest {
    private final BookCommandParser parser = new BookCommandParser();
    private HashSet<Tag> tags = new HashSet<>(Arrays.asList(Tag.convertToTag("Ear"), Tag.convertToTag("Nose")));
    private final Appointment appointment =
            new Appointment("Sore Throat", "2022-12-10 16:30", "", false);

    private final Appointment recurringAppointment =
            new Appointment("Sore Throat", "2022-12-10 16:30", "2M", false);

    private final Appointment taggedAppointment =
            new Appointment("Sore Throat", "2022-12-10 16:30", "", tags, false);
    @Test
    public void parse_validArgs_returnsBookCommand() {
        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30",
                new BookCommand(INDEX_FIRST_PERSON, appointment));

        assertParseSuccess(parser, "1 r/Sore Throat d/16:30 2022-12-10",
                new BookCommand(INDEX_FIRST_PERSON, appointment));

        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/",
                new BookCommand(INDEX_FIRST_PERSON, appointment));
    }

    @Test
    public void parse_validRecurringArgs_returnsBookCommand() {
        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/2M",
                new BookCommand(INDEX_FIRST_PERSON, recurringAppointment));

        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/ 0Y  2M  0D",
                new BookCommand(INDEX_FIRST_PERSON, recurringAppointment));

        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/ 2M  0D",
                new BookCommand(INDEX_FIRST_PERSON, recurringAppointment));
    }

    @Test
    public void parse_validTagArgs_returnsBookCommand() {
        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 t/Ear t/Throat",
                new BookCommand(INDEX_FIRST_PERSON, taggedAppointment));

        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 t/EAR t/THROAT",
                new BookCommand(INDEX_FIRST_PERSON, taggedAppointment));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a r/Sore Throat d/2022-12-10 16:30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1 r/ d/2022-12-10 16:30", Appointment.REASON_MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1 r/Sore Throat d/2022-25-10 16:30", Appointment.DATE_MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/S",
                Appointment.TIME_PERIOD_MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/1M t/Sick", Tag.MESSAGE_CONSTRAINTS);
    }
}
