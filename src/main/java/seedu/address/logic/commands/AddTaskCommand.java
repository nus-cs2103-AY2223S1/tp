package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_ASSIGNEES_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_DEADLINE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_NAME_DESCRIPTION;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.LocalDateTimeConverter;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;
import seedu.address.model.team.Team;

/**
 * Adds a task to the current team.
 */
@CommandLine.Command(name = AddTaskCommand.COMMAND_WORD,
        aliases = {AddTaskCommand.ALIAS}, mixinStandardHelpOptions = true)
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "task";
    public static final String ALIAS = "ta";
    public static final String FULL_COMMAND = AddCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Adds a task to the current team.\n"
            + "Parameters:"
            + " NAME "
            + FLAG_DEADLINE_STR + " DEADLINE "
            + FLAG_ASSIGNEE_STR + " ASSIGNEE "
            + "Example: " + FULL_COMMAND + " "
            + " \"Review PR\" "
            + FLAG_DEADLINE_STR + " \"02-Dec-2022 23:59\" "
            + FLAG_ASSIGNEE_STR + " 1 "
            + FLAG_ASSIGNEE_STR + " 2 ";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Added Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the team";
    public static final String MESSAGE_TASK_NAME_FORMAT_ERROR = "Task name cannot be empty";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "Invalid member index provided";


    @CommandLine.Parameters(arity = "1", description = FLAG_TASK_NAME_DESCRIPTION)
    private TaskName taskName;

    @CommandLine.Option(names = {FLAG_ASSIGNEE_STR, FLAG_ASSIGNEE_STR_LONG},
        description = FLAG_TASK_ASSIGNEES_DESCRIPTION, arity = "*")
    private List<Index> assignees = new ArrayList<>();

    @CommandLine.Option(names = {FLAG_DEADLINE_STR, FLAG_DEADLINE_STR_LONG},
            parameterConsumer = LocalDateTimeConverter.class, description = FLAG_TASK_DEADLINE_DESCRIPTION)
    private LocalDateTime deadline;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    /**
     * Creates an AddTaskCommand to add a {@code Task} to the current {@code Team}.
     */
    public AddTaskCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);

        Team team = model.getTeam();
        Task task = new Task(taskName, List.of(), false, deadline);
        if (team.hasTask(task)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        List<Person> memberList = team.getTeamMembers();
        for (Index index : assignees) {
            if (index.getZeroBased() >= memberList.size()) {
                throw new CommandException(MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS);
            }
        }

        // convert list of assignee index to list of persons
        List<Person> assigneePersonsList = assignees.stream()
                .map(index -> memberList.get(index.getZeroBased()))
                .collect(Collectors.toList());

        for (Person assignee : assigneePersonsList) {
            task.addAssignee(assignee);
        }
        model.getTeam().addTask(task);

        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, task));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && taskName.equals(((AddTaskCommand) other).taskName)) // state check
                && assignees.equals(((AddTaskCommand) other).assignees)
                && deadline != null && deadline.equals(((AddTaskCommand) other).deadline);
    }
}
