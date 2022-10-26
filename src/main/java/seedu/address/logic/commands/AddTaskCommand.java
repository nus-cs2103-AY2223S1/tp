package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * Adds a task to the current team.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "add_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a task to the current team.\n"
            + "Parameters: TASK_NAME\n"
            + "Example: " + COMMAND_WORD + " merge PR#12";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Added Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the team";
    public static final String MESSAGE_TASK_NAME_FORMAT_ERROR = "Task name cannot be empty";
    public static final String MESSAGE_PERSON_NOT_EXISTS = "The person you are trying to assign "
            + "the task to does not exist";

    private final Task task;
    private String[] assignees;

    /**
     * Creates an AddTaskCommand to add a {@code Task} to the current {@code Team}.
     *
     * @param taskName the name of the task to be added.
     */
    public AddTaskCommand(String taskName, String[] assignees, LocalDateTime deadline) {
        this.task = new Task(taskName, List.of(), false, deadline);
        if (!assignees.equals(null)) {
            this.assignees = assignees;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getTeam().hasTask(task)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        List<Person> memberList = model.getTeam().getTeamMembers();
        List<Person> assigneePersonList = memberList.stream()
                .filter(member -> Arrays.asList(assignees)
                        .contains(member.getName().fullName))
                .collect(Collectors.toList());
        if (assigneePersonList.size() < memberList.size()) {
            throw new CommandException(MESSAGE_PERSON_NOT_EXISTS);
        }
        for (Person assignee : assigneePersonList) {
            task.assignTo(assignee);
        }
        model.getTeam().addTask(task);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, task));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && task.equals(((AddTaskCommand) other).task)); // state check
    }
}
