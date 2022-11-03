package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.util.MaximumSortedList;
import seedu.address.model.util.exceptions.SortedListException;

/**
 * Deletes all appointments of an existing person in the address book.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "da";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a person's appointment "
            + "using the index numbers from the displayed person list and the identified person's appointment list.\n"
            + "Parameters: PERSON_INDEX.APPOINTMENT_INDEX \n"
            + "Example: " + COMMAND_WORD + " 3.1 ";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Person's Appointment: %1$s";

    private final Index personIndex;
    private final Index appointmentIndex;

    /**
     * @param personIndex of the person in the filtered person list
     * @param appointmentIndex of the person in the filtered person list
     */
    public DeleteAppointmentCommand(Index personIndex, Index appointmentIndex) {
        requireNonNull(personIndex);
        requireNonNull(appointmentIndex);

        this.personIndex = personIndex;
        this.appointmentIndex = appointmentIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personWithAppointmentToDelete = lastShownList.get(personIndex.getZeroBased());
        MaximumSortedList<Appointment> appointmentSet = personWithAppointmentToDelete.getAppointments();

        if (appointmentIndex.getZeroBased() >= appointmentSet.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment deletedAppointment;
        try {
            deletedAppointment = appointmentSet.remove(appointmentIndex.getZeroBased());
        } catch (SortedListException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateCalendarEventList();
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, deletedAppointment));
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAppointmentCommand)) {
            return false;
        }

        // state check
        DeleteAppointmentCommand e = (DeleteAppointmentCommand) other;
        return personIndex.equals(e.personIndex)
                && appointmentIndex.equals(e.appointmentIndex);
    }
}
