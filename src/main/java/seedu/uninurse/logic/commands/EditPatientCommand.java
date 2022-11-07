package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.commons.util.CollectionUtil;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.commands.exceptions.DuplicateEntryException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.TaskList;

/**
 * Edits the details of an existing patient in the patient list.
 */
public class EditPatientCommand extends EditGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a patient's contact details.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";
    public static final String MESSAGE_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_FAILURE = "At least one field to edit must be provided.";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index of the patient in the filtered patient list to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditPatientCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireAllNonNull(index, editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit;

        try {
            patientToEdit = model.getPatient(lastShownList.get(index.getZeroBased()));
        } catch (PatientNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT);
        }

        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        if (!patientToEdit.isSamePerson(editedPatient) && model.hasPerson(editedPatient)) {
            model.setPatientOfInterest(patientToEdit);
            throw new DuplicateEntryException(Messages.MESSAGE_DUPLICATE_PATIENT);
        }

        PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPatient),
                COMMAND_TYPE, personListTracker);
    }

    /**
     * Creates a patient using the details of an existing patient,
     * edited with editPatientDescriptor.
     *
     * @param patientToEdit The existing patient whose details are to be used.
     * @param editPatientDescriptor The given editPatientDescriptor.
     * @return a Patient with the details of patientToEdit edited with editPatientDescriptor
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Address updatedAddress = editPatientDescriptor.getAddress().orElse(patientToEdit.getAddress());

        // editPatient command does not allow editing of tags
        TagList updatedTags = patientToEdit.getTags();

        // editing of conditions is not supported
        ConditionList updatedConditions = patientToEdit.getConditions();

        // editPatient command does not allow editing medications
        MedicationList updatedMedications = patientToEdit.getMedications();

        // editPatient command does not allow editing tasks
        TaskList updatedTasks = patientToEdit.getTasks();

        // editPatient command does not allow editing remarks
        RemarkList updatedRemarks = patientToEdit.getRemarks();


        return new Patient(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedConditions,
                updatedMedications, updatedTasks, updatedRemarks);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientCommand)) {
            return false;
        }

        // state check
        EditPatientCommand o = (EditPatientCommand) other;
        return index.equals(o.index)
                && editPatientDescriptor.equals(o.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;

        public EditPatientDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            // state check
            EditPatientDescriptor o = (EditPatientDescriptor) other;

            return getName().equals(o.getName())
                    && getPhone().equals(o.getPhone())
                    && getEmail().equals(o.getEmail())
                    && getAddress().equals(o.getAddress());
        }
    }
}
