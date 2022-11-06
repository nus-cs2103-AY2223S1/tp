package seedu.address.logic.commands.task;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_STUDENT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

/**
 * Adds a person to the address book.
 */
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "task add";

    // TODO: Update help once student is properly implemented
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book. "
            + "Parameters: "
            + PREFIX_TASK_NAME + "TASK NAME "
            + PREFIX_TASK_DESC + "TASK DESCRIPTION "
            + PREFIX_TASK_DEADLINE + "TASK DEADLINE "
            // + "[" + PREFIX_TASK_STUDENT + "STUDENT]...\n"
            + PREFIX_TASK_STUDENT + "STUDENT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_NAME + "Assignment 1 "
            + PREFIX_TASK_DESC + "Complete assignment 1 "
            + PREFIX_TASK_DEADLINE + "10/10/2022 "
            + PREFIX_TASK_STUDENT + "John Doe";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";
    public static final String MESSAGE_INVALID_STUDENT = "This student does not exist in the address book";

    private final TaskName taskName;
    private final TaskDescription taskDescription;
    private final TaskDeadline taskDeadline;
    private final List<String> studentNames;

    /**
     * Creates an TaskAddCommand to add the specified {@code Person}
     */
    public TaskAddCommand(TaskName taskName, TaskDescription taskDesc, TaskDeadline taskDeadline,
                          List<String> studentNames) {
        requireNonNull(taskName);
        requireNonNull(taskDesc);
        requireNonNull(taskDeadline);
        requireNonNull(studentNames);
        this.taskName = taskName;
        this.taskDescription = taskDesc;
        this.taskDeadline = taskDeadline;
        this.studentNames = studentNames;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(studentNames);

        Set<Student> students = new HashSet<>();
        for (String studentName : studentNames) {
            if (isNull(model.findStudent(studentName))) {
                throw new CommandException(MESSAGE_INVALID_STUDENT);
            }

            students.add(model.findStudent(studentName));
        }

        Task toAdd = new Task(taskName, taskDescription, taskDeadline, students);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskAddCommand // instanceof handles nulls
                && taskName.equals(((TaskAddCommand) other).taskName)
                && taskDescription.equals(((TaskAddCommand) other).taskDescription)
                && taskDeadline.equals(((TaskAddCommand) other).taskDeadline)
                && studentNames.equals(((TaskAddCommand) other).studentNames));
    }
}
