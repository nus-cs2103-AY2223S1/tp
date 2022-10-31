package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.classify.commons.core.Messages;
import seedu.classify.commons.core.index.Index;
import seedu.classify.commons.util.CollectionUtil;
import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.model.Model;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.model.student.Student;

/**
 * Edits the details of an existing student in the student record.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_STUDENT_NAME + "STUDENT NAME] "
            + "[" + PREFIX_ID + "ID] "
            + "[" + PREFIX_CLASS + "CLASS] "
            + "[" + PREFIX_PARENT_NAME + "PARENT NAME] "
            + "[" + PREFIX_PHONE + "PARENT PHONE NUMBER] "
            + "[" + PREFIX_EMAIL + "PARENT EMAIL] "
            + "[" + PREFIX_EXAM + "NAME SCORE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 ";
    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the student record.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
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
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (model.excludesAndHasStudent(studentToEdit, editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.storePredicate(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedStudentName = editStudentDescriptor.getStudentName().orElse(studentToEdit.getStudentName());
        Id updatedId = editStudentDescriptor.getId().orElse(studentToEdit.getId());
        Class updatedClassName = editStudentDescriptor.getClassName().orElse(studentToEdit.getClassName());
        Name updatedParentName = editStudentDescriptor.getParentName().orElse(studentToEdit.getParentName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Set<Exam> updatedExams = editStudentDescriptor.getExams().orElse(new HashSet<>());
        updatedExams.addAll(studentToEdit.getExams());

        return new Student(updatedStudentName, updatedId, updatedClassName, updatedParentName, updatedPhone,
                updatedEmail, updatedExams);
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
        private Name studentName;
        private Id id;
        private Class className;
        private Name parentName;
        private Phone phone;
        private Email email;
        private Set<Exam> exams;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setStudentName(toCopy.studentName);
            setId(toCopy.id);
            setClassName(toCopy.className);
            setParentName(toCopy.parentName);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setExams(toCopy.exams);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(studentName, id, className, parentName, phone, email, exams);
        }

        public void setStudentName(Name studentName) {
            this.studentName = studentName;
        }

        public Optional<Name> getStudentName() {
            return Optional.ofNullable(studentName);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        public void setClassName(Class className) {
            this.className = className;
        }

        public Optional<Class> getClassName() {
            return Optional.ofNullable(className);
        }

        public void setParentName(Name parentName) {
            this.parentName = parentName;
        }

        public Optional<Name> getParentName() {
            return Optional.ofNullable(parentName);
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

        /**
         * Sets {@code exams} to this object's {@code exams}.
         * A defensive copy of {@code exams} is used internally.
         */
        public void setExams(Set<Exam> exams) {
            this.exams = (exams != null) ? new HashSet<>(exams) : null;
        }

        /**
         * Returns an exam set.
         * Returns {@code Optional#empty()} if {@code exams} is null.
         */
        public Optional<Set<Exam>> getExams() {
            return (exams != null) ? Optional.of(exams) : Optional.empty();
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

            return getStudentName().equals(e.getStudentName())
                    && getId().equals((e.getId()))
                    && getClassName().equals((e.getClassName()))
                    && getParentName().equals(e.getParentName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getExams().equals(e.getExams());
        }
    }
}
