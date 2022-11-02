package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_INDEX_DESCRIPTION;

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
@CommandLine.Command(name = AssignTaskRandomlyCommand.COMMAND_WORD, aliases = {"r"}, mixinStandardHelpOptions = true)
public class AssignTaskRandomlyCommand extends Command {
    public static final String COMMAND_WORD = "random";
    public static final String FULL_COMMAND = AssignCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Assigns a task to the random member in the team.\n"
            + "Parameters: TASK_INDEX (must be a valid positive integer) \n"
            + "Example: " + FULL_COMMAND + " 1";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Randomly Assigned Task: %1$s randomly assigned to %2$s";
    public static final String MESSAGE_ALL_MEMBERS_ASSIGNED = "There are no team members left to assign this task to";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist."
            + "There are less than %1$s tasks in your list.";

    @CommandLine.Parameters(arity = "1", description = FLAG_TASK_INDEX_DESCRIPTION)
    private Index taskIndex;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public AssignTaskRandomlyCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Task> tasks = model.getFilteredTaskList();
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

        Task originalTask = tasks.get(taskIndex.getZeroBased());
        Task newTask = originalTask.assignTo(assignee);

        model.getTeam().setTask(originalTask, newTask);

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                tasks.get(taskIndex.getZeroBased()).getName(), assignee.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignTaskRandomlyCommand // instanceof handles nulls
                && taskIndex.equals(((AssignTaskRandomlyCommand) other).taskIndex)); // state check
    }
}
