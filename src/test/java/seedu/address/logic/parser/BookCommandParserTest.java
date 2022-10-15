package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BookCommand;
import seedu.address.model.person.Appointment;

public class BookCommandParserTest {
    private final BookCommandParser parser = new BookCommandParser();
    private final Appointment appointment =
            new Appointment("Sore Throat", "2022-12-10 16:30", "", false);

    private final Appointment recurringAppointment =
            new Appointment("Sore Throat", "2022-12-10 16:30", "2M", false);
    @Test
    public void parse_validArgs_returnsBookCommand() {
        appointment.setPatient(ALICE);
        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30",
                new BookCommand(INDEX_FIRST_PERSON, appointment));
    }

    @Test
    public void parse_validArgs_returnsBookCommand1() {
        appointment.setPatient(ALICE);
        assertParseSuccess(parser, "1 r/Sore Throat d/16:30 2022-12-10",
                new BookCommand(INDEX_FIRST_PERSON, appointment));
    }

    @Test
    public void parse_validArgs_returnsBookCommand2() {
        recurringAppointment.setPatient(ALICE);
        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/2M",
                new BookCommand(INDEX_FIRST_PERSON, recurringAppointment));
    }

    @Test
    public void parse_validArgs_returnsBookCommand3() {
        recurringAppointment.setPatient(ALICE);
        assertParseSuccess(parser, "1 r/Sore Throat d/2022-12-10 16:30 pe/ 0Y  2M  0D",
                new BookCommand(INDEX_FIRST_PERSON, recurringAppointment));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a r/Sore Throat d/2022-12-10 16:30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookCommand.MESSAGE_USAGE));
    }
}
