package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandResult.CommandType.EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUITIONCLASS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTOR;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the entry identified "
            + "by the index number used in the displayed list.\n "
            + "\nExisting values will be overwritten by the input values.\n"
            + "\nParameters:\n INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "\nExample:\n " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_EDIT_CLASS_SUCCESS = "Edited Class: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the database.";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists in the database.";

    private final Index index;
    private final EditDescriptor editDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditDescriptor editDescriptor) {
        requireNonNull(index);
        requireNonNull(editDescriptor);

        this.index = index;
        this.editDescriptor = editDescriptor;
    }

    public static String getMessageUsage(Model.ListType listType) {
        switch (listType) {
        case STUDENT_LIST:
            return "Valid edit command index:\n"
                    + "edit INDEX "
                    + "[" + PREFIX_NAME + "NAME] "
                    + "[" + PREFIX_PHONE + "PHONE] "
                    + "[" + PREFIX_EMAIL + "EMAIL] "
                    + "[" + PREFIX_ADDRESS + "ADDRESS] "
                    + "[" + PREFIX_SUBJECT_OR_SCHOOL + "SCHOOL] "
                    + "[" + PREFIX_LEVEL + "LEVEL] "
                    + "[" + PREFIX_TAG + "TAG]...\n";

        case TUTOR_LIST:
            return "Valid edit command index:\n"
                    + "edit INDEX "
                    + "[" + PREFIX_NAME + "NAME] "
                    + "[" + PREFIX_PHONE + "PHONE] "
                    + "[" + PREFIX_EMAIL + "EMAIL] "
                    + "[" + PREFIX_ADDRESS + "ADDRESS] "
                    + "[" + PREFIX_QUALIFICATION + "QUALIFICATION] "
                    + "[" + PREFIX_INSTITUTION + "INSTITUTION] "
                    + "[" + PREFIX_TAG + "TAG]...\n";

        case TUITIONCLASS_LIST:
            return "Valid edit command index:\n"
                    + "edit INDEX "
                    + "[" + PREFIX_NAME + "NAME] "
                    + "[" + PREFIX_SUBJECT_OR_SCHOOL + "SUBJECT] "
                    + "[" + PREFIX_LEVEL + "LEVEL] "
                    + "[" + PREFIX_DAY + "DAY] "
                    + "[" + PREFIX_TIME + "TIME] "
                    + "[" + PREFIX_TAG + "TAG]...\n";

        default:
            return MESSAGE_USAGE;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        FilteredList<?> lastShownList = model.getCurrentList();

        assert (model.getCurrentListType() == Model.ListType.STUDENT_LIST
                || model.getCurrentListType() == Model.ListType.TUTOR_LIST
                || model.getCurrentListType() == Model.ListType.TUITIONCLASS_LIST);

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (model.getCurrentListType() == Model.ListType.TUITIONCLASS_LIST) {
            TuitionClass classToEdit = (TuitionClass) lastShownList.get(index.getZeroBased());
            TuitionClass editedClass = createEditedClass(classToEdit, editDescriptor);

            if (!classToEdit.isSameTuitionClass(editedClass) && model.hasTuitionClass(editedClass)) {
                throw new CommandException(MESSAGE_DUPLICATE_CLASS);
            }

            model.setTuitionClass(classToEdit, editedClass);
            model.updateFilteredTuitionClassList(PREDICATE_SHOW_ALL_TUITIONCLASS);
            return new CommandResult(
                    String.format(MESSAGE_EDIT_CLASS_SUCCESS, editedClass), EDIT, index.getZeroBased());
        }

        Person personToEdit = (Person) lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editDescriptor);

        assert (!(personToEdit instanceof NextOfKin));

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        if (model.getCurrentListType() == Model.ListType.STUDENT_LIST) {
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENT);
        } else {
            model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTOR);
        }
        return new CommandResult(
                String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson), EDIT, index.getZeroBased());
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditDescriptor editDescriptor) {
        assert personToEdit != null;
        assert editDescriptor instanceof EditPersonDescriptor;

        EditPersonDescriptor editPersonDescriptor = (EditPersonDescriptor) editDescriptor;

        seedu.address.model.person.Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        Person editedPerson = null;

        if (editPersonDescriptor instanceof EditStudentDescriptor && personToEdit instanceof Student) {
            EditStudentDescriptor editStudentDescriptor = (EditStudentDescriptor) editDescriptor;
            Student studentToEdit = (Student) personToEdit;

            School updatedSchool = editStudentDescriptor.getSchool().orElse(studentToEdit.getSchool());
            Level updatedLevel = editStudentDescriptor.getLevel().orElse(studentToEdit.getLevel());
            NextOfKin updatedNextOfKin = studentToEdit.getNextOfKin();
            List<TuitionClass> tuitionClasses = studentToEdit.getTuitionClasses();
            editedPerson = new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                    updatedSchool, updatedLevel, updatedNextOfKin, tuitionClasses);

        } else if (editPersonDescriptor instanceof EditTutorDescriptor && personToEdit instanceof Tutor) {
            EditTutorDescriptor editTutorDescriptor = (EditTutorDescriptor) editDescriptor;
            Tutor tutorToEdit = (Tutor) personToEdit;

            Institution updatedInstitution = editTutorDescriptor.getInstitution().orElse(tutorToEdit.getInstitution());
            Qualification updatedQualification =
                    editTutorDescriptor.getQualification().orElse(tutorToEdit.getQualification());
            List<TuitionClass> tuitionClasses = tutorToEdit.getTuitionClasses();
            editedPerson = new Tutor(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                    updatedQualification, updatedInstitution, tuitionClasses);
        } else {
            assert false;
        }

        return editedPerson;
    }

    private static TuitionClass createEditedClass(TuitionClass classToEdit, EditDescriptor editDescriptor) {
        assert classToEdit != null;
        assert editDescriptor instanceof EditTuitionClassDescriptor;

        EditTuitionClassDescriptor editTuitionClassDescriptor = (EditTuitionClassDescriptor) editDescriptor;

        seedu.address.model.tuitionclass.Name updatedName =
                editTuitionClassDescriptor.getName().orElse(classToEdit.getName());
        Subject updatedSubject = editTuitionClassDescriptor.getSubject().orElse(classToEdit.getSubject());
        Level updatedLevel = editTuitionClassDescriptor.getLevel().orElse(classToEdit.getLevel());
        Day updatedDay = editTuitionClassDescriptor.getDay().orElse(classToEdit.getDay());
        Time updatedTime = editTuitionClassDescriptor.getTime().orElse(classToEdit.getTime());
        Set<Tag> updatedTags = editTuitionClassDescriptor.getTags().orElse(classToEdit.getTags());

        return new TuitionClass(updatedName, updatedSubject, updatedLevel, updatedDay, updatedTime, updatedTags);
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
                && editDescriptor.equals(e.editDescriptor);
    }

    /**
     * Abstract class that will store the details to edit.
     */
    public abstract static class EditDescriptor {
        public abstract boolean isAnyFieldEdited();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor extends EditDescriptor {
        private seedu.address.model.person.Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        @Override
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(seedu.address.model.person.Name name) {
            this.name = name;
        }

        public Optional<seedu.address.model.person.Name> getName() {
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

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor extends EditPersonDescriptor {
        private School school;
        private Level level;
        private NextOfKin nextOfKin;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            super(toCopy);
            setSchool(toCopy.school);
            setLevel(toCopy.level);
            setNextOfKin(toCopy.nextOfKin);
        }

        public EditStudentDescriptor(EditPersonDescriptor toCopy) {
            super(toCopy);
        }

        /**
         * Returns true if at least one field is edited.
         */
        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited()
                    || CollectionUtil.isAnyNonNull(school, level, nextOfKin);
        }

        //student specific methods
        public void setSchool(School school) {
            this.school = school;
        }

        public Optional<School> getSchool() {
            return Optional.ofNullable(school);
        }

        public void setLevel(Level level) {
            this.level = level;
        }

        public Optional<Level> getLevel() {
            return Optional.ofNullable(level);
        }

        public void setNextOfKin(NextOfKin nextOfKin) {
            this.nextOfKin = nextOfKin;
        }

        public Optional<NextOfKin> getNextOfKin() {
            return Optional.ofNullable(nextOfKin);
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

            return super.equals(e)
                    && getSchool().equals(e.getSchool())
                    && getLevel().equals(e.getLevel())
                    && getNextOfKin().equals(e.getNextOfKin());
        }
    }

    /**
     * Stores the details to edit the tutor with. Each non-empty field value will replace the
     * corresponding field value of the tutor.
     */
    public static class EditTutorDescriptor extends EditPersonDescriptor {
        private Qualification qualification;
        private Institution institution;

        public EditTutorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTutorDescriptor(EditTutorDescriptor toCopy) {
            super(toCopy);
            setQualification(toCopy.qualification);
            setInstitution(toCopy.institution);
        }

        public EditTutorDescriptor(EditPersonDescriptor toCopy) {
            super(toCopy);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited()
                    || CollectionUtil.isAnyNonNull(qualification, institution);
        }

        public void setQualification(Qualification qualification) {
            this.qualification = qualification;
        }

        public Optional<Qualification> getQualification() {
            return Optional.ofNullable(qualification);
        }

        public void setInstitution(Institution institution) {
            this.institution = institution;
        }

        public Optional<Institution> getInstitution() {
            return Optional.ofNullable(institution);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTutorDescriptor)) {
                return false;
            }

            // state check
            EditTutorDescriptor e = (EditTutorDescriptor) other;

            return super.equals(e)
                    && getQualification().equals(e.getQualification())
                    && getInstitution().equals(e.getInstitution());
        }
    }

    /**
     * Stores the details to edit the class with. Each non-empty field value will replace the
     * corresponding field value of the class.
     */
    public static class EditTuitionClassDescriptor extends EditDescriptor {
        private seedu.address.model.tuitionclass.Name name;
        private Subject subject;
        private Level level;
        private Day day;
        private Time time;
        private Set<Tag> tags;

        public EditTuitionClassDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTuitionClassDescriptor(EditTuitionClassDescriptor toCopy) {
            setName(toCopy.name);
            setSubject(toCopy.subject);
            setLevel(toCopy.level);
            setDay(toCopy.day);
            setTime(toCopy.time);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        @Override
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, subject, level, day, time, tags);
        }

        public void setName(seedu.address.model.tuitionclass.Name name) {
            this.name = name;
        }

        public Optional<seedu.address.model.tuitionclass.Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Optional<Subject> getSubject() {
            return Optional.ofNullable(subject);
        }

        public void setLevel(Level level) {
            this.level = level;
        }

        public Optional<Level> getLevel() {
            return Optional.ofNullable(level);
        }

        public void setDay(Day day) {
            this.day = day;
        }

        public Optional<Day> getDay() {
            return Optional.ofNullable(day);
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTuitionClassDescriptor)) {
                return false;
            }

            // state check
            EditTuitionClassDescriptor e = (EditTuitionClassDescriptor) other;

            return getName().equals(e.getName())
                    && getSubject().equals(e.getSubject())
                    && getLevel().equals(e.getLevel())
                    && getDay().equals(e.getDay())
                    && getTime().equals(e.getTime())
                    && getTags().equals(e.getTags());
        }
    }


}
