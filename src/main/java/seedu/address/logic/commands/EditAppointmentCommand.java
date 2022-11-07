package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditAppointmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Location;
import seedu.address.model.person.Person;
import seedu.address.model.util.MaximumSortedList;
import seedu.address.model.util.exceptions.SortedListException;

/**
 * Overwrites the appointment details of an existing person in the address book.
 *
 * @author Gerald Teo Jin Wei
 * @version 1.4
 * @since 2022-11-07
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "ea";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a person's appointment "
            + "using the index numbers from the displayed person list and the identified person's appointment list.\n"
            + "Parameters: PERSON_INDEX.APPOINTMENT_INDEX "
            + "[" + PREFIX_APPOINTMENT_DATE + "DATE] "
            + "[" + PREFIX_APPOINTMENT_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 3.1 "
            + PREFIX_APPOINTMENT_DATE + "09-01-2023 12:30 "
            + PREFIX_APPOINTMENT_LOCATION + "Jurong Point, Starbucks";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Appointment was edited \nFrom: %1$s\nTo: %2$s";
    public static final String MESSAGE_NO_APPOINTMENT_TO_EDIT = "This client does not have an appointment to edit\n"
            + "Use command \"aa\" to add appointment instead";
    public static final String MESSAGE_UNCHANGED_LOCATION = "The edited location is unchanged\n";
    private final Index personIndex;
    private final Index appointmentIndex;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * Creates an EditAppointmentCommand to be executed to edit an appointment
     * @param personIndex of the person in the filtered person list
     * @param appointmentIndex of the appointment in the specified person's appointment list
     * @param editAppointmentDescriptor new appointment details to overwrite with
     */
    public EditAppointmentCommand(Index personIndex, Index appointmentIndex,
                                  EditAppointmentDescriptor editAppointmentDescriptor) {
        requireAllNonNull(personIndex, appointmentIndex, editAppointmentDescriptor);

        this.personIndex = personIndex;
        this.appointmentIndex = appointmentIndex;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        MaximumSortedList<Appointment> appointmentSet = getAppointmentSet(model);
        Appointment appointmentToEdit = getAppointmentToEdit(appointmentSet);

        Appointment editedAppointment = editAppointmentDescriptor.createEditedAppointment(appointmentToEdit);
        if (editAppointmentDescriptor.getDateTime().isPresent()
                && model.hasPersonWithSameAppointmentDateTime(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME);
        }

        if (editAppointmentDescriptor.getLocation().isPresent()) {
            Location location = editAppointmentDescriptor.getLocation().get();
            if (location.equals(appointmentToEdit.getLocation())) {
                throw new CommandException(MESSAGE_UNCHANGED_LOCATION);
            }
        }

        try {
            appointmentSet.remove(appointmentIndex.getZeroBased());
        } catch (SortedListException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        appointmentSet.add(editedAppointment);

        model.updateCalendarEventList();
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, appointmentToEdit, editedAppointment));
    }

    private MaximumSortedList<Appointment> getAppointmentSet(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personWithAppointmentToEdit = lastShownList.get(personIndex.getZeroBased());
        MaximumSortedList<Appointment> appointmentSet = personWithAppointmentToEdit.getAppointments();
        return appointmentSet;
    }

    private Appointment getAppointmentToEdit(MaximumSortedList<Appointment> appointmentSet) throws CommandException {
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

        return appointmentToEdit;
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
