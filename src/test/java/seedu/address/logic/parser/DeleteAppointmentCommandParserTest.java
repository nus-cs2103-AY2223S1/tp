package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.Person.MAXIMUM_APPOINTMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.util.MaximumSortedList;
import seedu.address.model.person.Appointment;

public class DeleteAppointmentCommandParserTest {
    private DeleteAppointmentCommandParser parser = new DeleteAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteAppointmentCommand() {
        MaximumSortedList<Appointment> emptyAppointments = new MaximumSortedList<>(MAXIMUM_APPOINTMENTS);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setAppointments(emptyAppointments);
        assertParseSuccess(parser, "1", new DeleteAppointmentCommand(INDEX_FIRST_PERSON));
    }


    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1^_^", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE));
    }
}
