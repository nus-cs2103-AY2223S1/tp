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
public class StudentExpelCommand extends Command {

    public static final String COMMAND_WORD = "student expel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Expel the student identified to the given tutorial "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TUTORIAL_GROUP + "TUTORIAL GROUP ";

    public static final String MESSAGE_EXPEL_PERSON_SUCCESS = "Expelled Student from: %1$s ";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book.";
    public static final String MESSAGE_TUTORIAL_GROUP_NOT_FOUND = "This tutorial group is not found.";
    public static final String MESSAGE_NOT_EDITED = "Tutorial group not edited.";
    public static final String MESSAGE_DOES_NOT_BELONG_TO_THIS_GROUP = "The student does not belong to this group";
    public static final String MESSAGE_TUTORIAL_NOT_INITIATED = "Tutorial group not initiated for this student. "
            + "Cannot expel the student.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editStudentDescriptor details to edit the person with
     */
    public StudentExpelCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
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
        TutorialGroup originalGroup = editedStudent.getTutorialGroup();

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!model.hasTutorialGroup(originalGroup)) {
            throw new CommandException(MESSAGE_TUTORIAL_GROUP_NOT_FOUND);
        }

        if (!studentToEdit.isEnrolledInTutorial()) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_INITIATED);
        }

        if (!studentToEdit.belongsTo(originalGroup)) {
            throw new CommandException(MESSAGE_DOES_NOT_BELONG_TO_THIS_GROUP);
        }

        editedStudent = createExpelledStudent(studentToEdit, editStudentDescriptor);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EXPEL_PERSON_SUCCESS, originalGroup));
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

    private static Student createExpelledStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = studentToEdit.getName();
        Phone updatedPhone = studentToEdit.getPhone();
        Email updatedEmail = studentToEdit.getEmail();
        Set<Tag> updatedTags = studentToEdit.getTags();
        TutorialGroup updatedTutorialGroup = null;
        return new Student(updatedName, updatedPhone, updatedEmail, updatedTags, updatedTutorialGroup);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentExpelCommand)) {
            return false;
        }

        // state check
        StudentExpelCommand e = (StudentExpelCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditStudentDescriptor {
        private TutorialGroup tutorialGroup;

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

        /**
         * Resets tutorial group.
         */
        public void resetTutorialGroup(TutorialGroup tutorialGroup) {
            this.tutorialGroup = tutorialGroup;
            //TODO: change later
        }

        public Optional<TutorialGroup> getTutorialGroup() {
            return Optional.ofNullable(tutorialGroup);
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
