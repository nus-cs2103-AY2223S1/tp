package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class StudentEnrollCommand extends Command {

    public static final String COMMAND_WORD = "student enroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enroll the student identified to the given tutorial "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer and NOT TOO BIG) "
            + PREFIX_TUTORIAL_GROUP + "TUTORIAL GROUP ";

    public static final String MESSAGE_ENROLL_PERSON_SUCCESS = "Enrolled Student to: %1$s ";
    public static final String MESSAGE_TUTORIAL_GROUP_NOT_FOUND = "This tutorial group is not found.";
    public static final String MESSAGE_TUTORIAL_GROUP_ALREADY_ENROLLED = "This student is already enrolled in this "
            + "tutorial group.";
    public static final String MESSAGE_NOT_EDITED = "Tutorial group not edited.";
    public static final String MESSAGE_STUDENT_ALREADY_ENROLLED = "Student already enrolled in a tutorial: %1$s."
            + "Expel him first then enroll.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editStudentDescriptor details to edit the person with
     */
    public StudentEnrollCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
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

        if (!model.hasTutorialGroup(editedStudent.getTutorialGroup())) {
            throw new CommandException(MESSAGE_TUTORIAL_GROUP_NOT_FOUND);
        }

        if (studentToEdit.belongsTo(editedStudent.getTutorialGroup())) {
            throw new CommandException(MESSAGE_TUTORIAL_GROUP_ALREADY_ENROLLED);
        }

        if (studentToEdit.isEnrolledInTutorial()) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ALREADY_ENROLLED,
                    studentToEdit.getTutorialGroup()));
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ENROLL_PERSON_SUCCESS, editedStudent.getTutorialGroup()));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = studentToEdit.getName();
        Phone updatedPhone = studentToEdit.getPhone();
        Email updatedEmail = studentToEdit.getEmail();
        Set<Tag> updatedTags = studentToEdit.getTags();
        TutorialGroup updatedTutorialGroup = editStudentDescriptor.getTutorialGroup()
                .orElse(studentToEdit.getTutorialGroup());
        return new Student(updatedName, updatedPhone, updatedEmail, updatedTags, updatedTutorialGroup);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentEnrollCommand)) {
            return false;
        }

        // state check
        StudentEnrollCommand e = (StudentEnrollCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditStudentDescriptor {
        private TutorialGroup tutorialGroup;
        private Name name;
        private Phone phone;
        private Email email;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setTutorialGroup(toCopy.tutorialGroup);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tutorialGroup);
        }


        public void setTutorialGroup(TutorialGroup tutorialGroup) {
            this.tutorialGroup = tutorialGroup;
        }

        public Optional<TutorialGroup> getTutorialGroup() {
            return Optional.ofNullable(tutorialGroup);
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

            return getTutorialGroup().equals(e.getTutorialGroup());
        }
    }
}
