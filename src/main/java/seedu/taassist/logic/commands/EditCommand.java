package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.taassist.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.commons.util.CollectionUtil;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.Student;

/**
 * Edits the details of an existing student in TA-Assist.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_MODULE_CLASS + "CLASS]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in TA-Assist.";

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

        if (!studentToEdit.isSame(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        if (!model.hasModuleClasses(editedStudent.getModuleClasses())) {
            throw new CommandException(String.format(MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Set<ModuleClass> updatedModuleClasses = editStudentDescriptor.getModuleClasses()
                .orElse(studentToEdit.getModuleClasses());
        // Module data cannot be edited through edit command.

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedModuleClasses);
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
        private Email email;
        private Address address;
        private Set<ModuleClass> moduleClasses;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code moduleClasses} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setModuleClasses(toCopy.moduleClasses);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, moduleClasses);
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
         * Sets {@code moduleClasses} to this object's {@code moduleClasses}.
         * A defensive copy of {@code moduleClasses} is used internally.
         */
        public void setModuleClasses(Set<ModuleClass> moduleClasses) {
            this.moduleClasses = (moduleClasses != null) ? new HashSet<>(moduleClasses) : null;
        }

        /**
         * Returns an unmodifiable moduleClass set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code moduleClasses} is null.
         */
        public Optional<Set<ModuleClass>> getModuleClasses() {
            return (moduleClasses != null) ? Optional.of(Collections.unmodifiableSet(moduleClasses)) : Optional.empty();
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
                    && getModuleClasses().equals(e.getModuleClasses());
        }
    }
}
