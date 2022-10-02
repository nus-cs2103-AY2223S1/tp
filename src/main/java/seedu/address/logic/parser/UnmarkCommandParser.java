package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnmarkCommandParser extends SelectAppointmentCommandParser<UnmarkCommand>{
    @Override
    public UnmarkCommand parse(String args) throws ParseException {
        try {
            List<Index> personAndAppointmentIndexes = super.getPersonAndAppointmentIndexes(args);

            Index indexOfPerson = personAndAppointmentIndexes.get(0);
            Index indexOfAppointment = personAndAppointmentIndexes.get(1);
            return new UnmarkCommand(indexOfPerson, indexOfAppointment);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
