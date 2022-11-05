package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_ASSIGNEES_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_INDEX_DESCRIPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

/**
 * Assigns a task to a specific member in the team.
 */
@CommandLine.Command(name = AssignTaskCommand.COMMAND_WORD,
        aliases = {AssignTaskCommand.ALIAS}, mixinStandardHelpOptions = true)
public class AssignTaskCommand extends Command {
    public static final String COMMAND_WORD = "task";
    public static final String ALIAS = "ta";
    public static final String FULL_COMMAND = AssignCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Assigns a task to the specified member in the team.\n"
            + "Parameters: TASK_INDEX (must be a valid positive integer) "
            + "MEMBER_INDEX (must be a valid positive integer)\n"
            + "Example: " + FULL_COMMAND + " 1 2";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assigned task successfully. %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This task has already been assigned to %1$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist."
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "Invalid member index provided";

    @CommandLine.Parameters(arity = "1", description = FLAG_TASK_INDEX_DESCRIPTION)
    private Index taskIndex;

    @CommandLine.Option(names = {FLAG_ASSIGNEE_STR, FLAG_ASSIGNEE_STR_LONG}, required = true, arity = "*",
            description = FLAG_TASK_ASSIGNEES_DESCRIPTION)
    private List<Index> assignees = new ArrayList<>();

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public AssignTaskCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Task> taskList = model.getFilteredTaskList();
        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Team team = model.getTeam();
        Task task = taskList.get(taskIndex.getZeroBased());

        List<Person> memberList = team.getTeamMembers();
        for (Index index : assignees) {
            if (index.getZeroBased() >= memberList.size()) {
                throw new CommandException(MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS);
            }
        }

        // convert list of assignee index to list of persons
        List<Person> assigneePersonList = assignees.stream()
                .map(index -> memberList.get(index.getZeroBased()))
                .collect(Collectors.toList());

        // reset assignees
        Task taskWithNoAssignees = task.clearAssignees();
        team.setTask(task, taskWithNoAssignees);

        // add assignees
        for (Person assignee : assigneePersonList) {
            Task newTask = taskWithNoAssignees.addAssignee(assignee);

            team.setTask(taskWithNoAssignees, newTask);
        }

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                taskList.get(taskIndex.getZeroBased())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignTaskCommand // instanceof handles nulls
                && taskIndex.equals(((AssignTaskCommand) other).taskIndex) // state check
                && assignees.equals(((AssignTaskCommand) other).assignees));
    }
}
