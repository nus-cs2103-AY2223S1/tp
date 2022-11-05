package seedu.address.logic.commands.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;

/**
 * Edits the grade of an existing student-task pair in the address book.
 */
public class GradeEditCommand extends Command {
    public static final String COMMAND_WORD = "grade edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the grade of the student's task "
            + "by the index number used in the displayed student list and task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: STUDENT_INDEX TASK_INDEX (must be positive integers and NOT TOO BIG)"
            + "[" + PREFIX_GRADE + "GRADE (T or F)]...\n"
            + "Example: " + COMMAND_WORD + " 1 2 "
            + PREFIX_GRADE + "T";
    public static final String MESSAGE_EDIT_GRADE_SUCCESS = "Grade %s of Task %s and Student %s";
    public static final String MESSAGE_NOT_EDITED = "T OR F must be provided";
    public static final String MESSAGE_STUDENT_TASK_PAIR_NOT_FOUND = "This student and task pair is not found.";
    private final Index studentIndex;
    private final Index taskIndex;
    private final EditGradeDescriptor editGradeDescriptor;
    /**
     * @param studentIndex of the student in the filtered student list to edit
     * @param taskIndex of the task in the filtered task list to edit
     * @param editGradeDescriptor details to edit the grade with
     */
    public GradeEditCommand(Index studentIndex, Index taskIndex, EditGradeDescriptor editGradeDescriptor) {
        requireNonNull(studentIndex);
        requireNonNull(taskIndex);
        requireNonNull(editGradeDescriptor);

        this.studentIndex = studentIndex;
        this.taskIndex = taskIndex;
        this.editGradeDescriptor = new EditGradeDescriptor(editGradeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (studentIndex.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        } else if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Student studentGradeToEdit = lastShownStudentList.get(studentIndex.getZeroBased());
        Task taskGradeToEdit = lastShownTaskList.get(taskIndex.getZeroBased());
        if (!taskGradeToEdit.hasStudent(studentGradeToEdit)) {
            throw new CommandException(MESSAGE_STUDENT_TASK_PAIR_NOT_FOUND);
        }
        Grade editedGrade = createEditedGrade(studentGradeToEdit, taskGradeToEdit, editGradeDescriptor);
        model.addGrade(new GradeKey(studentGradeToEdit, taskGradeToEdit), editedGrade);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_GRADE_SUCCESS, editedGrade.name(),
                taskGradeToEdit.getTaskName().taskName, studentGradeToEdit.getName()));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Grade createEditedGrade(Student student, Task task, EditGradeDescriptor editGradeDescriptor) {
        assert student != null;
        assert task != null;

        return editGradeDescriptor.getGrade();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeEditCommand)) {
            return false;
        }

        // state check
        GradeEditCommand e = (GradeEditCommand) other;
        return this.studentIndex.equals(e.studentIndex)
                && this.taskIndex.equals(e.taskIndex)
                && this.editGradeDescriptor.equals(e.editGradeDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditGradeDescriptor {
        private Grade grade;

        public EditGradeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGradeDescriptor(EditGradeDescriptor toCopy) {
            setGrade(toCopy.grade);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Grade getGrade() {
            return this.grade;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGradeDescriptor)) {
                return false;
            }

            // state check
            EditGradeDescriptor e = (EditGradeDescriptor) other;

            return getGrade().equals(e.getGrade());
        }
    }
}
