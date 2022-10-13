package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOSPITAL_WING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD_NUMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.HospitalWing;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WardNumber;
import seedu.address.model.tag.Medication;

/**
 * Edits the details of an existing patient in the database.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_NEXT_OF_KIN + "NEXT OF KIN] "
            + "[" + PREFIX_PATIENT_TYPE + "PATIENT TYPE] "
            + "[" + PREFIX_HOSPITAL_WING + "HOSPITAL WING] "
            + "[" + PREFIX_FLOOR_NUMBER + "FLOOR NUMBER] "
            + "[" + PREFIX_WARD_NUMBER + "WARD NUMBER] "
            + "[" + PREFIX_MEDICATION + "MEDICATION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_FLOOR_NUMBER + "12";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This patient already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the patient in the filtered patient list to edit
     * @param editPersonDescriptor details to edit the patient with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        NextOfKin updatedNextOfKin = editPersonDescriptor.getNextOfKin().orElse(personToEdit.getNextOfKin());
        PatientType updatedPatientType = editPersonDescriptor.getPatientType().orElse(personToEdit.getPatientType());

        HospitalWing updatedHospitalWing = null;
        FloorNumber updatedFloorNumber = null;
        WardNumber updatedWardNumber = null;
        if (updatedPatientType.isInpatient()) {
            updatedHospitalWing = editPersonDescriptor.getHospitalWing()
                    .orElse(personToEdit.getHospitalWing().orElse(null));
            updatedFloorNumber = editPersonDescriptor.getFloorNumber()
                    .orElse(personToEdit.getFloorNumber().orElse(null));
            updatedWardNumber = editPersonDescriptor.getWardNumber()
                    .orElse(personToEdit.getWardNumber().orElse(null));
            if (updatedHospitalWing == null || updatedFloorNumber == null || updatedWardNumber == null) {
                throw new CommandException(String.format(PatientType.DEPENDENCY_CONSTRAINTS, MESSAGE_USAGE));
            }
        }
        Set<Medication> updatedMedications = editPersonDescriptor.getMedications()
                .orElse(personToEdit.getMedications());
        List<PastAppointment> pastAppointments = personToEdit.getPastAppointments();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedNextOfKin, updatedPatientType,
                updatedHospitalWing, updatedFloorNumber, updatedWardNumber, updatedMedications, pastAppointments);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private NextOfKin nextOfKin;
        private PatientType patientType;
        private HospitalWing hospitalWing;
        private FloorNumber floorNumber;
        private WardNumber wardNumber;
        private Set<Medication> medications;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setNextOfKin(toCopy.nextOfKin);
            setPatientType(toCopy.patientType);
            setHospitalWing(toCopy.hospitalWing);
            setFloorNumber(toCopy.floorNumber);
            setWardNumber(toCopy.wardNumber);
            setMedications(toCopy.medications);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, nextOfKin, patientType,
                    hospitalWing, floorNumber, wardNumber, medications);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setNextOfKin(NextOfKin nextOfKin) {
            this.nextOfKin = nextOfKin;
        }

        public Optional<NextOfKin> getNextOfKin() {
            return Optional.ofNullable(nextOfKin);
        }

        public void setPatientType(PatientType patientType) {
            this.patientType = patientType;
        }

        public Optional<PatientType> getPatientType() {
            return Optional.ofNullable(patientType);
        }

        public void setHospitalWing(HospitalWing hospitalWing) {
            this.hospitalWing = hospitalWing;
        }

        public Optional<HospitalWing> getHospitalWing() {
            return Optional.ofNullable(hospitalWing);
        }

        public void setFloorNumber(FloorNumber floorNumber) {
            this.floorNumber = floorNumber;
        }

        public Optional<FloorNumber> getFloorNumber() {
            return Optional.ofNullable(floorNumber);
        }

        public void setWardNumber(WardNumber wardNumber) {
            this.wardNumber = wardNumber;
        }

        public Optional<WardNumber> getWardNumber() {
            return Optional.ofNullable(wardNumber);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setMedications(Set<Medication> medications) {
            this.medications = (medications != null) ? new HashSet<>(medications) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Medication>> getMedications() {
            return (medications != null) ? Optional.of(Collections.unmodifiableSet(medications)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getNextOfKin().equals(e.getNextOfKin())
                    && getPatientType().equals(e.getPatientType())
                    && getHospitalWing().equals(e.getHospitalWing())
                    && getFloorNumber().equals(e.getFloorNumber())
                    && getWardNumber().equals(e.getWardNumber())
                    && getMedications().equals(e.getMedications());
        }
    }
}
