package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
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
import seedu.address.model.patient.Name;

/**
 * Edits the details of an existing appointment in the HealthContact.
 */
public class EditAppointmentCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("editappointment", "ea");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values. At least one field must be provided.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_MEDICAL_TEST + "MEDICAL_TEST] "
            + "[" + PREFIX_SLOT + "SLOT<yyyy-MM-dd HH:mm>] "
            + "[" + PREFIX_DOCTOR + "DOCTOR]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John Doe"
            + PREFIX_MEDICAL_TEST + "Computed Tomography"
            + PREFIX_SLOT + "2020-10-09 13:30"
            + PREFIX_DOCTOR + "Muhammad Wong";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the HealthContact.";
    public static final String MESSAGE_PATIENT_NOT_EXIST = "This patient does not exist in the HealthContact";
    public static final String MESSAGE_PATIENT_NAME_CASE_UNMATCHED = "The case of name of the patient is not matched";

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
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!model.hasPatient(editedAppointment.getName())) {
            throw new CommandException(MESSAGE_PATIENT_NOT_EXIST);
        }

        if (!model.hasPatientWithExactlySameName(editedAppointment.getName())) {
            throw new CommandException(MESSAGE_PATIENT_NAME_CASE_UNMATCHED);
        }

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

        Name updatedName = editAppointmentDescriptor.getName().orElse(appointmentToEdit.getName());
        MedicalTest updatedMedicalTest = editAppointmentDescriptor.getTest().orElse(appointmentToEdit.getMedicalTest());
        Slot updatedSlot = editAppointmentDescriptor.getSlot().orElse(appointmentToEdit.getSlot());
        Doctor updatedDoctor = editAppointmentDescriptor.getDoctor().orElse(appointmentToEdit.getDoctor());

        return new Appointment(updatedName, updatedMedicalTest, updatedSlot, updatedDoctor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Name name;
        private MedicalTest test;
        private Slot slot;
        private Doctor doctor;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setName(toCopy.name);
            setMedicalTest(toCopy.test);
            setSlot(toCopy.slot);
            setDoctor(toCopy.doctor);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, test, slot, doctor);
        }

        public void setMedicalTest(MedicalTest test) {
            this.test = test;
        }

        public Optional<MedicalTest> getTest() {
            return Optional.ofNullable(test);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
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
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getName().equals(e.getName())
                    && getTest().equals(e.getTest())
                    && getDoctor().equals(e.getDoctor())
                    && getSlot().equals(e.getSlot());
        }
    }
}
