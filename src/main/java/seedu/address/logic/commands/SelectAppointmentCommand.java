package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * A class of commands that involve selecting an appointment only with no additional arguments.
 */
public abstract class SelectAppointmentCommand extends Command {
    protected final Index indexOfPerson;
    protected final Index indexOfAppointment;

    public SelectAppointmentCommand(Index indexOfPerson, Index indexOfAppointment) {
        requireNonNull(indexOfPerson);
        requireNonNull(indexOfAppointment);

        this.indexOfPerson = indexOfPerson;
        this.indexOfAppointment = indexOfAppointment;
    }

    protected Appointment getTargetAppointment(Model model) throws CommandException {
        Person targetPerson = getTargetPerson(model);
        List<Appointment> targetAppointmentList = targetPerson.getAppointments();

        if (indexOfAppointment.getZeroBased() >= targetAppointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment targetAppointment = targetAppointmentList.get(indexOfAppointment.getZeroBased());
        return targetAppointment;
    }

    protected Person getTargetPerson(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexOfPerson.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(indexOfPerson.getZeroBased());
        return targetPerson;
    }
}
