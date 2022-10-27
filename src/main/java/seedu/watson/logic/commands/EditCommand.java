package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_INDEX_NUMBERS;
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
import seedu.watson.model.person.Address;
import seedu.watson.model.person.Attendance;
import seedu.watson.model.person.Email;
import seedu.watson.model.person.Name;
import seedu.watson.model.person.IndexNumber;
import seedu.watson.model.person.Person;
import seedu.watson.model.person.Phone;
import seedu.watson.model.person.Remark;
import seedu.watson.model.person.StudentClass;
import seedu.watson.model.person.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;

/**
 * Edits the details of an existing person in the watson book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
                                               + "by the index number used in the displayed person list. "
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

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the watson book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        IndexNumber updatedIndexNumber = editPersonDescriptor.getIndexNumber().orElse(personToEdit.getIndexNumber());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        StudentClass updatedStudentClass =
            editPersonDescriptor.getStudentClass().orElse(personToEdit.getStudentClass());
        Attendance updatedAttendance = editPersonDescriptor.getAttendance().orElse(personToEdit.getAttendance());
        SubjectHandler updatedSubjectHandler =
                editPersonDescriptor.getSubjectHandler().orElse(personToEdit.getSubjectHandler());
        Set<Remark> updatedRemarks = editPersonDescriptor.getRemarks().orElse(personToEdit.getRemarks());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedIndexNumber, updatedPhone, updatedEmail, updatedAddress,
                          updatedStudentClass, updatedAttendance, updatedRemarks, updatedSubjectHandler,
                          updatedTags);
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
        private IndexNumber indexNumber;
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
            setIndexNumber(toCopy.indexNumber);
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
            return CollectionUtil.isAnyNonNull(name, indexNumber, phone, email, address, studentClass,
                                               tags, remarksList, subjectHandler);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<IndexNumber> getIndexNumber() {
            return Optional.ofNullable(indexNumber);
        }

        public void setIndexNumber(IndexNumber indexNumber) {
            this.indexNumber = indexNumber;
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
         * When editing a Person, to edit the Person's subjects, a new SubjectHandler is created
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
                   && getIndexNumber().equals(e.getIndexNumber())
                   && getPhone().equals(e.getPhone())
                   && getEmail().equals(e.getEmail())
                   && getAddress().equals(e.getAddress())
                   && getTags().equals(e.getTags());
        }
    }
}
