package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditAppointmentCommand.MESSAGE_INVALID_APPOINTMENT_INDEX_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.Person.MAXIMUM_NUM_OF_APPOINTMENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.MaximumSortedList;
import seedu.address.logic.util.exceptions.SortedListException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * Deletes all appointments of an existing person in the address book.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "da";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete all appointments of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: [INDEX] \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Person's Appointment: %1$s";

    public static final String MESSAGE_NO_APPOINTMENT_TO_DELETE = "This person does not have an appointment to delete";
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

        Appointment appointmentToDelete;
        Appointment deletedAppointment;
        try {
            appointmentToDelete = appointmentSet.get(appointmentIndex.getZeroBased());
            deletedAppointment = appointmentSet.remove(appointmentIndex.getZeroBased());
        } catch (SortedListException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_APPOINTMENT_INDEX_FORMAT, appointmentIndex));
        }


        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
