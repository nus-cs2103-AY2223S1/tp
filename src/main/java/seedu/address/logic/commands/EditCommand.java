package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_OWED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOK_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES_PER_CLASS;
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
import seedu.address.model.student.AdditionalNotes;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Email;
import seedu.address.model.student.Mark;
import seedu.address.model.student.Money;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.storage.ClassStorage;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_NOK_PHONE + "NOK_PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_CLASS_DATE_TIME + "CLASS_DATE_TIME] "
            + "[" + PREFIX_MONEY_OWED + "MONEY_OWED] "
            + "[" + PREFIX_MONEY_PAID + "MONEY_PAID] "
            + "[" + PREFIX_RATES_PER_CLASS + "RATES_PER_CLASS] "
            + "[" + PREFIX_ADDITIONAL_NOTES + "NOTES] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";
    public static final String MESSAGE_CLASS_CONFLICT = "There is a conflict between the class timings.";
    private static final String MESSAGE_MULTIPLE_CLASSES_PER_DAY = "A student cannot have multiple classes per day";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit.
     * @param editStudentDescriptor details to edit the student with.
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }


        if (!editStudentDescriptor.hasEmptyClass()) {
            if (editedStudent.hasMultipleClasses()) {
                throw new CommandException(MESSAGE_MULTIPLE_CLASSES_PER_DAY);
            }
            editedStudent.setDisplayClass(editedStudent.getAClass());
            ClassStorage.saveClass(editedStudent, index.getOneBased());
            ClassStorage.removeExistingClass(studentToEdit);
        } else if (!studentToEdit.hasEmptyClass()) {
            editedStudent.setClass(studentToEdit.getAClass());
            editedStudent.setDisplayClass(studentToEdit.getDisplayedClass());
            if (editedStudent.hasMultipleClasses()) {
                throw new CommandException(MESSAGE_MULTIPLE_CLASSES_PER_DAY);
            }
            ClassStorage.updateStudent(studentToEdit, editedStudent);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}.
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Phone updatedNokPhone = editStudentDescriptor.getNokPhone().orElse(studentToEdit.getNokPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Class updatedClassDateTime = editStudentDescriptor.getAClass().orElse(studentToEdit.getAClass());
        Money updatedMoneyOwed = editStudentDescriptor.getMoneyOwed().orElse(studentToEdit.getMoneyOwed());
        Money updatedMoneyPaid = editStudentDescriptor.getMoneyPaid().orElse(studentToEdit.getMoneyPaid());
        Money updatedRatesPerClass = editStudentDescriptor.getRatesPerClass().orElse(studentToEdit.getRatesPerClass());
        AdditionalNotes updatedNotes = editStudentDescriptor.getAdditionalNotes()
                .orElse(studentToEdit.getAdditionalNotes());
        Optional<AdditionalNotes> appendedAdditionalNotes = editStudentDescriptor.getAppendedAdditionalNotes();
        if (!appendedAdditionalNotes.isEmpty()) {
            updatedNotes.appendNotes(appendedAdditionalNotes.get());
        }
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());

        // Unmodifiable states by the user
        Mark markStatus = studentToEdit.getMarkStatus();
        Class displayedClass = studentToEdit.getDisplayedClass();;

        return new Student(updatedName, updatedPhone, updatedNokPhone, updatedEmail, updatedAddress,
                updatedClassDateTime, updatedMoneyOwed, updatedMoneyPaid, updatedRatesPerClass, updatedNotes,
                updatedTags, markStatus, displayedClass);
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
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Phone nokPhone;
        private Email email;
        private Address address;
        private Class aClass;
        private Money moneyOwed;
        private Money moneyPaid;
        private Money ratesPerClass;
        private AdditionalNotes additionalNotes;
        private AdditionalNotes appendedAdditionalNotes;
        private Set<Tag> tags;

        public EditStudentDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setNokPhone(toCopy.nokPhone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setClass(toCopy.aClass);
            setMoneyOwed(toCopy.moneyOwed);
            setMoneyPaid(toCopy.moneyPaid);
            setRatesPerClass(toCopy.ratesPerClass);
            setAdditionalNotes(toCopy.additionalNotes);
            setAppendedAdditionalNotes(toCopy.appendedAdditionalNotes);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, nokPhone, email, address, aClass, moneyOwed, moneyPaid,
                    ratesPerClass, additionalNotes, appendedAdditionalNotes, tags);
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

        public void setNokPhone(Phone nokPhone) {
            this.nokPhone = nokPhone;
        }

        public Optional<Phone> getNokPhone() {
            return Optional.ofNullable(nokPhone);
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

        public void setClass(Class aClass) {
            if (aClass == null) {
                this.aClass = new Class();
            } else {
                this.aClass = aClass;
            }
        }

        public Optional<Class> getAClass() {
            return Optional.ofNullable(aClass);
        }

        public void setMoneyOwed(Money moneyOwed) {
            this.moneyOwed = moneyOwed;
        }

        public Optional<Money> getMoneyOwed() {
            return Optional.ofNullable(moneyOwed);
        }

        public void setMoneyPaid(Money moneyPaid) {
            this.moneyPaid = moneyPaid;
        }

        public Optional<Money> getMoneyPaid() {
            return Optional.ofNullable(moneyPaid);
        }

        public void setRatesPerClass(Money ratesPerClass) {
            this.ratesPerClass = ratesPerClass;
        }

        public Optional<Money> getRatesPerClass() {
            return Optional.ofNullable(ratesPerClass);
        }

        public void setAdditionalNotes(AdditionalNotes additionalNotes) {
            this.additionalNotes = additionalNotes;
        }

        public Optional<AdditionalNotes> getAdditionalNotes() {
            return Optional.ofNullable(additionalNotes);
        }

        public void setAppendedAdditionalNotes(AdditionalNotes appendedAdditionalNotes) {
            this.appendedAdditionalNotes = appendedAdditionalNotes;
        }

        public Optional<AdditionalNotes> getAppendedAdditionalNotes() {
            return Optional.ofNullable(appendedAdditionalNotes);
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
         * @return {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Checks if class is empty.
         *
         * @return true if class is empty.
         */
        public boolean hasEmptyClass() {
            return getAClass().get().isEmpty();
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
                    && getNokPhone().equals(e.getNokPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getAClass().toString().equals(e.getAClass().toString())
                    && getMoneyOwed().equals(e.getMoneyOwed())
                    && getMoneyPaid().equals(e.getMoneyPaid())
                    && getRatesPerClass().equals(e.getRatesPerClass())
                    && getAdditionalNotes().equals(e.getAdditionalNotes())
                    && getAppendedAdditionalNotes().equals(e.getAppendedAdditionalNotes())
                    && getTags().equals(e.getTags());
        }
    }
}
