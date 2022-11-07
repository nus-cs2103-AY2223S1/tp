package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_INDEX_NUMBERS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.watson.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.watson.commons.core.Messages;
import seedu.watson.commons.core.index.Index;
import seedu.watson.commons.util.CollectionUtil;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.student.Address;
import seedu.watson.model.student.Attendance;
import seedu.watson.model.student.Email;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Phone;
import seedu.watson.model.student.Remark;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.StudentClass;
import seedu.watson.model.student.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;

/**
 * Edits the details of an existing student in the watson book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
                                               + "by the index number used in the displayed student list. "
                                               + "Existing values will be overwritten by the input values.\n"
                                               + "Parameters: INDEX (must be a positive integer) "
                                               + "[" + PREFIX_NAME + "NAME] "
                                               + "[" + PREFIX_INDEX_NUMBERS + "INDEX NUMBER] "
                                               + "[" + PREFIX_PHONE + "PHONE] "
                                               + "[" + PREFIX_EMAIL + "EMAIL] "
                                               + "[" + PREFIX_ADDRESS + "ADDRESS] "
                                               + "[" + PREFIX_STUDENTCLASS + "CLASS] "
                                               + "[" + PREFIX_REMARK + "REMARK] "
                                               + "[" + PREFIX_TAG + "TAG]...\n"
                                               + "Example: " + COMMAND_WORD + " 1 "
                                               + PREFIX_PHONE + "91234567 "
                                               + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the watson book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the student in the filtered student list to edit
     * @param editPersonDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedPerson(Student studentToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(studentToEdit.getAddress());
        StudentClass updatedStudentClass =
            editPersonDescriptor.getStudentClass().orElse(studentToEdit.getStudentClass());
        Attendance updatedAttendance = editPersonDescriptor.getAttendance().orElse(studentToEdit.getAttendance());
        SubjectHandler updatedSubjectHandler =
                editPersonDescriptor.getSubjectHandler().orElse(studentToEdit.getSubjectHandler());
        Set<Remark> updatedRemarks = editPersonDescriptor.getRemarks().orElse(studentToEdit.getRemarks());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(studentToEdit.getTags());

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress,
                          updatedStudentClass, updatedAttendance, updatedRemarks, updatedSubjectHandler,
                          updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedPerson(studentToEdit, editPersonDescriptor);

        if (!studentToEdit.isSamePerson(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStudent));
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
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private StudentClass studentClass;
        private Attendance attendance;
        private Set<Remark> remarksList;
        private SubjectHandler subjectHandler;
        private Set<Tag> tags;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setStudentClass(toCopy.studentClass);
            setAttendance(toCopy.attendance);
            setRemarks(toCopy.remarksList);
            setSubjectHandler(toCopy.subjectHandler);
            setTags(toCopy.tags);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, studentClass,
                                               tags, remarksList, subjectHandler);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<StudentClass> getStudentClass() {
            return Optional.ofNullable(studentClass);
        }

        public void setStudentClass(StudentClass studentClass) {
            this.studentClass = studentClass;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Set<Remark>> getRemarks() {
            return (remarksList != null) ? Optional.of(Collections.unmodifiableSet(remarksList)) : Optional.empty();
        }

        public void setRemarks(Set<Remark> remarksList) {
            this.remarksList = (remarksList != null) ? new HashSet<>(remarksList) : null;
        }

        public Optional<SubjectHandler> getSubjectHandler() {
            return Optional.ofNullable(subjectHandler);
        }

        /**
         * A SubjectHandler handles the addition of new subjects and the editing of current subjects.
         * When editing a Student, to edit the Student's subjects, a new SubjectHandler is created
         * with the old subjects and the new changes.
         *
         * @param subjectHandler the new SubjectHandler
         */
        public void setSubjectHandler(SubjectHandler subjectHandler) {
            this.subjectHandler = subjectHandler;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
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
                   && getAddress().equals(e.getAddress())
                   && getTags().equals(e.getTags());
        }
    }
}
