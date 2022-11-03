package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.logic.parser.EditAppointmentDescriptor.createEditedAppointment;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditAppointmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.util.MaximumSortedList;
import seedu.address.model.util.exceptions.SortedListException;

/**
 * Overwrites the appointment details of an existing person in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "ea";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a person's appointment "
            + "using the index numbers from the displayed person list and the identified person's appointment list.\n"
            + "Parameters: PERSON_INDEX.APPOINTMENT_INDEX "
            + "[" + PREFIX_APPOINTMENT_DATE + "DATE] "
            + "[" + PREFIX_APPOINTMENT_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 3.1 "
            + PREFIX_APPOINTMENT_DATE + "21-Jan-2023 12:30 PM "
            + PREFIX_APPOINTMENT_LOCATION + "Jurong Point, Starbucks";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Appointment was edited \nFrom: %1$s\nTo: %2$s";
    public static final String MESSAGE_NO_APPOINTMENT_TO_EDIT = "This client does not have an appointment to edit\n"
            + "Use command \"aa\" to add appointment instead";
    private final Index personIndex;
    private final Index appointmentIndex;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param personIndex of the person in the filtered person list
     * @param editAppointmentDescriptor new appointment details to overwrite with
     */
    public EditAppointmentCommand(Index personIndex, Index appointmentIndex,
                                  EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(personIndex);
        requireNonNull(appointmentIndex);
        requireNonNull(editAppointmentDescriptor);

        this.personIndex = personIndex;
        this.appointmentIndex = appointmentIndex;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
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

        Person personWithAppointmentToEdit = lastShownList.get(personIndex.getZeroBased());
        MaximumSortedList<Appointment> appointmentSet = personWithAppointmentToEdit.getAppointments();

        if (appointmentIndex.getZeroBased() >= appointmentSet.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        if (appointmentSet.isEmpty()) {
            throw new CommandException(MESSAGE_NO_APPOINTMENT_TO_EDIT);
        }

        Appointment appointmentToEdit;
        try {
            appointmentToEdit = appointmentSet.get(appointmentIndex.getZeroBased());
        } catch (SortedListException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);
        if (editAppointmentDescriptor.getDateTime().isPresent()
                && model.hasPersonWithSameAppointmentDateTime(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME);
        }

        try {
            appointmentSet.remove(appointmentIndex.getZeroBased());
        } catch (SortedListException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        appointmentSet.add(editedAppointment);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateCalendarEventList();
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, appointmentToEdit, editedAppointment));
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
               && appointmentIndex.equals(e.appointmentIndex)
               && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }
}
