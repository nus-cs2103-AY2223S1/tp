package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * Assigns a task to a random member in the team.
 */
@CommandLine.Command(name = "random")
public class AssignTaskRandomlyCommand extends Command {
    public static final String COMMAND_WORD = "assign_task_rand";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a task to the random member in the team.\n"
            + "Parameters: INDEX of task (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Randomly Assigned Task: %1$s randomly assigned to %2$s";
    public static final String MESSAGE_ALL_MEMBERS_ASSIGNED = "There are no team members left to assign this task to";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist."
            + "There are less than %1$s tasks in your list.";

    @CommandLine.Parameters(arity = "1")
    private Index taskIndex;

    public AssignTaskRandomlyCommand() {
    }

    /**
     * Returns a command that assigns a task to the specified member in the team.
     *
     * @param taskIndex  the index of the task to be added.
     */
    public AssignTaskRandomlyCommand(int taskIndex) {
        this.taskIndex = Index.fromZeroBased(taskIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> tasks = model.getTeam().getTaskList();
        List<Person> members = model.getTeam().getTeamMembers();

        if (taskIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex.getOneBased()));
        }
        if (tasks.get(taskIndex.getZeroBased()).getAssigneesList().size() == members.size()) {
            throw new CommandException(MESSAGE_ALL_MEMBERS_ASSIGNED);
        }

        List<Person> unassignedMembers = new ArrayList<>();
        for (Person person : members) {
            if (!tasks.get(taskIndex.getZeroBased()).checkAssignee(person)) {
                unassignedMembers.add(person);
            }
        }

        Random random = new Random();
        Person assignee = unassignedMembers.get(random.nextInt(unassignedMembers.size()));

        model.getTeam().assignTask(tasks.get(taskIndex.getZeroBased()), assignee);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                tasks.get(taskIndex.getZeroBased()).getName(), assignee.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignTaskRandomlyCommand // instanceof handles nulls
                && taskIndex == (((AssignTaskRandomlyCommand) other).taskIndex)); // state check
    }
}
