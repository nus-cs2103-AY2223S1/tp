package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.logic.util.MaximumSortedList;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * Overwrites the appointment details of an existing person in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "ea";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Overwrites all "
            + "appointment details of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: [INDEX] "
            + "[" + PREFIX_APPOINTMENT_DATE + "DATE] "
            + "[" + PREFIX_APPOINTMENT_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT_DATE + "21-Jan-2023 12:30 PM "
            + PREFIX_APPOINTMENT_LOCATION + "Jurong Point, Starbucks";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Person's Appointment: %1$s";
    public static final String MESSAGE_NO_APPOINTMENT_TO_EDIT = "This client does not have an appointment to edit\n"
            + "Use command \"aa\" to add appointment instead";
    public static final String MESSAGE_INVALID_APPOINTMENT_INDEX_FORMAT = "This client does not have appointment at index %d";
    private final Index personIndex;
    private final Index appointmentIndex;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param personIndex of the person in the filtered person list
     * @param editPersonDescriptor new appointment details to overwrite with
     */
    public EditAppointmentCommand(Index personIndex, Index appointmentIndex, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personIndex);
        requireNonNull(appointmentIndex);
        requireNonNull(editPersonDescriptor);

        this.personIndex = personIndex;
        this.appointmentIndex = appointmentIndex;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }


    public Index getIndex() {
        return personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        MaximumSortedList<Appointment> appointmentSet = personToEdit.getAppointments();

        if (appointmentSet.isEmpty()) {
            throw new CommandException(MESSAGE_NO_APPOINTMENT_TO_EDIT);
        }

        if (appointmentIndex.getZeroBased() >= appointmentSet.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_APPOINTMENT_INDEX_FORMAT, appointmentIndex));
        }

        appointmentSet.replace(appointmentIndex.getZeroBased(), )
        Person editedPerson = createEditedPersonByOverwritingAppointment(personToEdit, editPersonDescriptor);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedPerson));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;

        return personIndex.equals(e.personIndex)
               && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
