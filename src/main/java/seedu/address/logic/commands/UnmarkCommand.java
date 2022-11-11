package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_INDEX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_INDEX;

import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Task;

/**
 * Marks a specified task as incomplete.
 */
@CommandLine.Command(name = UnmarkCommand.COMMAND_WORD,
        aliases = {UnmarkCommand.ALIAS}, mixinStandardHelpOptions = true)
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String ALIAS = "u";
    public static final String FULL_COMMAND = COMMAND_WORD;

    public static final String MESSAGE_MARK_SUCCESS = "Marked as incomplete: [ ] %1$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_ALREADY_UNMARKED = "This task has not been marked as done.";
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to mark a task as incomplete.\n";

    @CommandLine.Parameters(arity = "1", paramLabel = LABEL_TASK_INDEX, description = FLAG_TASK_INDEX_DESCRIPTION)
    private Index taskIndex;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

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
        if (!taskList.get(taskIndex.getZeroBased()).isComplete()) {
            throw new CommandException(MESSAGE_ALREADY_UNMARKED);
        }
        Task originalTask = taskList.get(taskIndex.getZeroBased());
        Task unmarkedTask = originalTask.mark(false);

        model.getTeam().setTask(originalTask, unmarkedTask);

        return new CommandResult(String.format(MESSAGE_MARK_SUCCESS,
                unmarkedTask.getName()));
    }

}
