package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.person.Email;

/**
 * Edits the details of an existing appointment in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "editappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_MEDICAL_TEST + "ADDRESS] "
            + "[" + PREFIX_SLOT + "DATETIME<yyyy-MM-dd hh:mm>] "
            + "[" + PREFIX_DOCTOR + "DOCTOR] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_MEDICAL_TEST + "Computer Tomography"
            + PREFIX_SLOT + "2020-10-09 13:30"
            + PREFIX_DOCTOR + "Muhammad Wong";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilterAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.equals(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code AppointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Email updatedEmail = editAppointmentDescriptor.getEmail().orElse(appointmentToEdit.getEmail());
        MedicalTest updatedMedicalTest = editAppointmentDescriptor.getTest().orElse(appointmentToEdit.getMedicalTest());
        Slot updatedSlot = editAppointmentDescriptor.getSlot().orElse(appointmentToEdit.getSlot());
        Doctor updatedDoctor = editAppointmentDescriptor.getDoctor().orElse(appointmentToEdit.getDoctor());

        return new Appointment(updatedEmail, updatedMedicalTest, updatedSlot, updatedDoctor);
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
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Email email;
        private MedicalTest test;
        private Slot slot;
        private Doctor doctor;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setEmail(toCopy.email);
            setMedicalTest(toCopy.test);
            setSlot(toCopy.slot);
            setDoctor(toCopy.doctor);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(email, test, slot, doctor);
        }

        public void setMedicalTest(MedicalTest test) {
            this.test = test;
        }

        public Optional<MedicalTest> getTest() {
            return Optional.ofNullable(test);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setSlot(Slot slot) {
            this.slot = slot;
        }

        public Optional<Slot> getSlot() {
            return Optional.ofNullable(slot);
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }

        public Optional<Doctor> getDoctor() {
            return Optional.ofNullable(doctor);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getEmail().equals(e.getEmail())
                    && getTest().equals(e.getTest())
                    && getDoctor().equals(e.getDoctor())
                    && getSlot().equals(e.getSlot());
        }
    }
}
