package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * Assigns a task to a specific member in the team.
 */
public class RandomlyAssignTaskCommand extends Command {
    public static final String COMMAND_WORD = "assign_task_rand";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a task to the random member in the team.\n"
            + "Parameters: INDEX of task (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Randomly Assigned Task: %1$s randomly assigned to %2$s";
    public static final String MESSAGE_ALL_MEMBERS_ASSIGNED = "There are no team members left to assign this task to";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist."
            + "There are less than %1$s tasks in your list.";
    private final int taskIndex;

    /**
     * Returns a command that assigns a task to the specified member in the team.
     *
     * @param taskIndex  the index of the task to be added.
     */
    public RandomlyAssignTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> tasks = model.getTeam().getTaskList();
        List<Person> members = model.getTeam().getTeamMembers();

        if (taskIndex >= tasks.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex + 1));
        }
        if (tasks.get(taskIndex).getAssignees().size() == members.size()) {
            throw new CommandException(MESSAGE_ALL_MEMBERS_ASSIGNED);
        }

        List<Person> unassignedMembers = new ArrayList<>();
        for (Person person : members) {
            if (!tasks.get(taskIndex).checkAssignee(person)) {
                unassignedMembers.add(person);
            }
        }

        Random random = new Random();
        Person assignee = unassignedMembers.get(random.nextInt(unassignedMembers.size()));

        model.getTeam().assignTask(tasks.get(taskIndex), assignee);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                tasks.get(taskIndex).getName(), assignee.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RandomlyAssignTaskCommand // instanceof handles nulls
                && taskIndex == (((RandomlyAssignTaskCommand) other).taskIndex)); // state check
    }
}
