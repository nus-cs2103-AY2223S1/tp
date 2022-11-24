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
    protected final Index indexOfAppointment;

    /**
     * Creates command that selects an appointment according to index of the appointment in the appointment list.
     *
     * @param indexOfAppointment Index of the appointment in the appointment list.
     */
    public SelectAppointmentCommand(Index indexOfAppointment) {
        requireNonNull(indexOfAppointment);

        this.indexOfAppointment = indexOfAppointment;
    }

    protected Appointment getTargetAppointment(Model model) throws CommandException {
        requireNonNull(model);

        List<Appointment> lastShownAppointmentList = model.getFilteredAppointmentList();

        if (indexOfAppointment.getZeroBased() >= lastShownAppointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment targetAppointment = lastShownAppointmentList.get(indexOfAppointment.getZeroBased());
        return targetAppointment;
    }

    protected Person getTargetPerson(Model model) throws CommandException {
        Person targetPerson = getTargetAppointment(model).getPatient();
        return targetPerson;
    }

    protected boolean hasSameIndexOfAppointment(SelectAppointmentCommand other) {
        return indexOfAppointment.equals(other.indexOfAppointment);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SelectAppointmentCommand)) {
            return false;
        }

        // state check
        SelectAppointmentCommand e = (SelectAppointmentCommand) other;
        return indexOfAppointment.equals(e.indexOfAppointment);
    }
}
