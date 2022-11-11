package jarvis.logic.commands;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static jarvis.logic.parser.CliSyntax.PREFIX_MATRIC_NUM;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static jarvis.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.commons.util.CollectionUtil;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.GradeProfile;
import jarvis.model.MatricNum;
import jarvis.model.Model;
import jarvis.model.Student;
import jarvis.model.StudentName;

/**
 * Edits the details of an existing student in the student book.
 */
public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "editstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] [" + PREFIX_MATRIC_NUM + "MATRIC_NUM]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John Do";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the student book.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireAllNonNull(index, editStudentDescriptor);

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

        if (!studentToEdit.equals(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        StudentName updatedStudentName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        MatricNum updatedMatricNum = editStudentDescriptor.getMatricNum().orElse(studentToEdit.getMatricNum());
        GradeProfile gradeProfile = studentToEdit.getGradeProfile();
        return new Student(updatedStudentName, updatedMatricNum, gradeProfile);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private StudentName studentName;
        private MatricNum matricNum;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.studentName);
            setMatricNum(toCopy.matricNum);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(studentName, matricNum);
        }

        public void setName(StudentName studentName) {
            this.studentName = studentName;
        }

        public void setMatricNum(MatricNum matricNum) {
            this.matricNum = matricNum;
        }

        public Optional<StudentName> getName() {
            return Optional.ofNullable(studentName);
        }

        public Optional<MatricNum> getMatricNum() {
            return Optional.ofNullable(matricNum);
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
                    && getMatricNum().equals(e.getMatricNum());
        }
    }
}
