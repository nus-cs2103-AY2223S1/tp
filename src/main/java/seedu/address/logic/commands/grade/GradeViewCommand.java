package seedu.address.logic.commands.grade;

import static java.util.Objects.requireNonNull;

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
public class GradeViewCommand extends Command {
    public static final String COMMAND_WORD = "grade view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View the grade of the student's task "
            + "by the index number used in the displayed student list and task list.\n"
            + "Parameters: STUDENT_INDEX TASK_INDEX (must be positive integers and NOT TOO BIG)"
            + "\n"
            + "Example: " + COMMAND_WORD + " 1 2";
    public static final String MESSAGE_VIEW_GRADE_SUCCESS = "Grade: %s";
    public static final String MESSAGE_STUDENT_TASK_PAIR_NOT_FOUND = "This student and task pair is not found.";
    private final Index studentIndex;
    private final Index taskIndex;
    /**
     * @param studentIndex of the student in the filtered student list to edit
     * @param taskIndex of the task in the filtered task list to edit
     */
    public GradeViewCommand(Index studentIndex, Index taskIndex) {
        requireNonNull(studentIndex);
        requireNonNull(taskIndex);

        this.studentIndex = studentIndex;
        this.taskIndex = taskIndex;
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
        if (!taskGradeToEdit.getStudents().contains(studentGradeToEdit)) {
            throw new CommandException(MESSAGE_STUDENT_TASK_PAIR_NOT_FOUND);
        }
        Grade grade = model.getGradeMap().get(new GradeKey(studentGradeToEdit, taskGradeToEdit));
        if (grade == null) {
            grade = Grade.UNGRADED;
        }
        return new CommandResult(String.format(MESSAGE_VIEW_GRADE_SUCCESS, grade.name()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeViewCommand)) {
            return false;
        }

        // state check
        GradeViewCommand e = (GradeViewCommand) other;
        return this.studentIndex.equals(e.studentIndex)
                && this.taskIndex.equals(e.taskIndex);
    }

}
