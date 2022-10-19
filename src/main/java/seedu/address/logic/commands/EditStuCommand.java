package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

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
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditStuCommand extends Command {

    public static final String COMMAND_WORD = "editstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]"
            + PREFIX_ID + "ID "
            + PREFIX_HANDLE + "HANDLE "
            + "[" + PREFIX_MODULE_CODE + "MODULE]..."
            + "[" + PREFIX_STUDENT_TA + "TA]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStuCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Student studentToEdit;
        if (personToEdit instanceof Student) {
            studentToEdit = (Student) personToEdit;
        } else {
            throw new CommandException(Messages.MESSAGE_NOT_A_STUDENT);
        }
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSamePerson(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setPerson(studentToEdit, editedStudent);
        if (editedStudent.isTeachingAssistant()) {
            if (studentToEdit.isTeachingAssistant()) {
                model.setTutor(studentToEdit, editedStudent);
            } else {
                model.addTutor(editedStudent);
            }
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, false,
                true, false, false);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    public static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());
        StudentId updatedId = editStudentDescriptor.getId().orElse(studentToEdit.getId());
        TelegramHandle updatedHandle = editStudentDescriptor.getTelegramHandle()
                .orElse(studentToEdit.getTelegramHandle());
        Set<ModuleCode> updatedStudentModuleInformation = editStudentDescriptor.getStudentModuleInfo()
                .orElse(studentToEdit.getStudentModuleInfo());
        Set<ModuleCode> updatedTeachingAssistantInfo = editStudentDescriptor.getTeachingAssistantInfo()
                .orElse(studentToEdit.getTeachingAssistantInfo());

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedId, updatedHandle, updatedStudentModuleInformation, updatedTeachingAssistantInfo);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStuCommand)) {
            return false;
        }

        // state check
        EditStuCommand e = (EditStuCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private StudentId id;
        private TelegramHandle telegramHandle;
        private Set<ModuleCode> studentModuleInfo;
        private Set<ModuleCode> teachingAssistantInfo;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setId(toCopy.id);
            setTelegramHandle(toCopy.telegramHandle);
            setStudentModuleInfo(toCopy.studentModuleInfo);
            setTeachingAssistantInfo(toCopy.teachingAssistantInfo);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, id,
                    telegramHandle, studentModuleInfo, teachingAssistantInfo);
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

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setId(StudentId id) {
            this.id = id;
        }

        public Optional<StudentId> getId() {
            return Optional.ofNullable(id);
        }

        public void setTelegramHandle(TelegramHandle handle) {
            this.telegramHandle = handle;
        }

        public Optional<TelegramHandle> getTelegramHandle() {
            return Optional.ofNullable(telegramHandle);
        }

        /**
         * Sets {@code moduleCodes} to this object's {@code studentModuleInfo}.
         * A defensive copy of {@code moduleCodes} is used internally.
         */
        public void setStudentModuleInfo(Set<ModuleCode> moduleCodes) {
            this.studentModuleInfo = (moduleCodes != null) ? new HashSet<>(moduleCodes) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code studentModuleInfo} is null.
         */
        public Optional<Set<ModuleCode>> getStudentModuleInfo() {
            return (studentModuleInfo != null)
                    ? Optional.of(Collections.unmodifiableSet(studentModuleInfo)) : Optional.empty();
        }

        /**
         * Sets {@code moduleCodes} to this object's {@code teachingAssistantInfo}.
         * A defensive copy of {@code moduleCodes} is used internally.
         */
        public void setTeachingAssistantInfo(Set<ModuleCode> moduleCodes) {
            this.teachingAssistantInfo = (moduleCodes != null) ? new HashSet<>(moduleCodes) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code studentModuleInfo} is null.
         */
        public Optional<Set<ModuleCode>> getTeachingAssistantInfo() {
            return (teachingAssistantInfo != null)
                    ? Optional.of(Collections.unmodifiableSet(teachingAssistantInfo)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getId().equals(e.getId())
                    && getTelegramHandle().equals(e.getTelegramHandle())
                    && getStudentModuleInfo().equals(e.getStudentModuleInfo())
                    && getTeachingAssistantInfo().equals(e.getTeachingAssistantInfo());
        }
    }
}

