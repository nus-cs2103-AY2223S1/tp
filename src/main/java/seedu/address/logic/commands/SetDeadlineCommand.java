package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_DEADLINE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_INDEX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_INDEX;

import java.time.LocalDateTime;
import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.LocalDateTimeConverter;
import seedu.address.model.Model;
import seedu.address.model.team.Task;

/**
 * Sets a deadline for a specified task.
 */
@CommandLine.Command(name = SetDeadlineCommand.COMMAND_WORD, aliases = {SetDeadlineCommand.ALIAS})
public class SetDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String ALIAS = "d";
    public static final String FULL_COMMAND = SetCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to set a deadline for a task.\n";

    public static final String MESSAGE_SET_DEADLINE_SUCCESS = "Set Deadline: %1$s %2$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";

    @CommandLine.Parameters(arity = "1", paramLabel = LABEL_TASK_INDEX, description = FLAG_TASK_INDEX_DESCRIPTION)
    private Index taskIndex;

    @CommandLine.Parameters(arity = "1..2", parameterConsumer = LocalDateTimeConverter.class,
            paramLabel = LABEL_TASK_DEADLINE,
            description = FLAG_TASK_DEADLINE_DESCRIPTION)
    private LocalDateTime deadline;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public SetDeadlineCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Task> taskList = model.getFilteredTaskList();
        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex.getOneBased()));
        }

        Task originalTask = taskList.get(taskIndex.getZeroBased());
        Task newTask = originalTask.setDeadline(deadline);

        model.getTeam().setTask(originalTask, newTask);

        return new CommandResult(String.format(MESSAGE_SET_DEADLINE_SUCCESS,
                taskList.get(taskIndex.getZeroBased()).getName(),
                taskList.get(taskIndex.getZeroBased()).getDeadlineAsString()));
    }

}
