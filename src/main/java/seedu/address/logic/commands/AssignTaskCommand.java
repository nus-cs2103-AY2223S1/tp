package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * Assigns a task to a specific member in the team.
 */
public class AssignTaskCommand extends Command {
    public static final String COMMAND_WORD = "assign_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Assigns a task to the specified member in the team.\n"
        + "Parameters: INDEX of task (must be a positive integer) \n"
            + "Parameters: INDEX of person (must be a positive integer) \n"
        + "Example: " + COMMAND_WORD + " 1" + " 2";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assigned Task: %1$s assigned to %2$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This task has already been assigned to %1$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "This member does not exist. "
            + "There are less than %1$s members in your team.";
    private final int taskIndex;
    private final int memberIndex;

    /**
     * Returns a command that assigns a task to the specified member in the team.
     * @param taskIndex the index of the task to be added.
     * @param memberIndex the index of the member that the task is assigned to.
     */
    public AssignTaskCommand(int taskIndex, int memberIndex) {
        this.taskIndex = taskIndex;
        this.memberIndex = memberIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getTeam().getTaskList();
        List<Person> memberList = model.getTeam().getTeamMembers();
        if (taskIndex >= taskList.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex + 1));
        } else if (memberIndex >= memberList.size()) {
            throw new CommandException(String.format(MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS, memberIndex + 1));
        } else if (taskList.get(taskIndex).checkAssignee(memberList.get(memberIndex))) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ASSIGNMENT,
                    memberList.get(memberIndex).getName()));
        }
        model.getTeam().assignTask(taskIndex, memberIndex);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                taskList.get(taskIndex).getName(), memberList.get(memberIndex).getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AssignTaskCommand // instanceof handles nulls
            && taskIndex == (((AssignTaskCommand) other).taskIndex)) // state check
                && memberIndex == (((AssignTaskCommand) other).memberIndex); // state check
    }
}
