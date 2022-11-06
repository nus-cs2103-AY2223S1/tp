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
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.ArrayList;
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
import seedu.address.model.module.Module;
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
 * Edits the details of an existing student in profNus.
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
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in profNus.";

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

        //checks for same module in student and ta
        Set<ModuleCode> student = editedStudent.getStudentModuleInfo();
        Set<ModuleCode> teacher = editedStudent.getTeachingAssistantInfo();
        for (ModuleCode moduleCode : student) {
            for (ModuleCode otherModuleCode: teacher) {
                if (moduleCode.equals(otherModuleCode)) {
                    throw new CommandException(Messages.MESSAGE_STUDENT_AND_TA);
                }
            }
        }

        if (!studentToEdit.isSamePerson(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        //checks if module exists
        if (editedStudent.getStudentModuleInfo().size() > 0 || editedStudent.isTeachingAssistant()) {
            List<Module> moduleList = model.getAllModuleList();
            List<ModuleCode> studentModules = new ArrayList<>();
            studentModules.addAll(editedStudent.getStudentModuleInfo());
            studentModules.addAll(editedStudent.getTeachingAssistantInfo());
            ModuleCode lastViewedCode = null;
            for (ModuleCode moduleCode : studentModules) {
                boolean isValid = false;
                for (Module module : moduleList) {
                    if (module.getCode().equals(moduleCode)) {
                        isValid = true;
                    } else {
                        lastViewedCode = moduleCode;
                    }
                }
                if (!isValid) {
                    throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST
                            + " Module not found: " + lastViewedCode + ".\nPlease create the module first using "
                            + "the madd command or specify an existing module!");
                }
            }
        }

        model.setPerson(studentToEdit, editedStudent);
        if (editedStudent.isTeachingAssistant()) {
            if (studentToEdit.isTeachingAssistant()) {
                model.setTutor(studentToEdit, editedStudent);
            } else {
                model.addTutor(editedStudent);
            }
        } else {
            if (studentToEdit.isTeachingAssistant()) {
                model.deleteTutor(studentToEdit);
            }
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, false,
                true, false, false, false, false, false);
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
        Set<String> updatedClassGroups = editStudentDescriptor.getClassGroups()
                .orElse(studentToEdit.getClassGroups());

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedId, updatedHandle, updatedStudentModuleInformation, updatedTeachingAssistantInfo,
                updatedClassGroups);
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
        private Set<String> classGroups;

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
            setClassGroups(toCopy.classGroups);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, id,
                    telegramHandle, studentModuleInfo, teachingAssistantInfo, classGroups);
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
         * Returns an unmodifiable ModuleCode set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code studentModuleInfo} is null.
         */
        public Optional<Set<ModuleCode>> getTeachingAssistantInfo() {
            return (teachingAssistantInfo != null)
                    ? Optional.of(Collections.unmodifiableSet(teachingAssistantInfo)) : Optional.empty();
        }

        /**
         * Sets {@code classGroups} to this object's {@code teachingAssistantInfo}.
         * A defensive copy of {@code moduleCodes} is used internally.
         */
        public void setClassGroups(Set<String> classGroups) {
            this.classGroups = (classGroups != null) ? new HashSet<>(classGroups) : null;
        }

        /**
         * Returns an unmodifiable String set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code classGroups} is null.
         */
        public Optional<Set<String>> getClassGroups() {
            return (classGroups != null)
                    ? Optional.of(Collections.unmodifiableSet(classGroups)) : Optional.empty();
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

