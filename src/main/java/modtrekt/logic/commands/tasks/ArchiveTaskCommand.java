package modtrekt.logic.commands.tasks;

import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static modtrekt.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.List;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.CommandResult;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Task;

/**
 * Archives a task in the task book belonging to a module.
 */
public class ArchiveTaskCommand extends Command {
    public static final String COMMAND_WORD = "archive";
    public static final String MESSAGE_COMMAND_USAGE =
            String.format("Format: %s %s <module code> %s <task index>", COMMAND_WORD, PREFIX_MOD_CODE, PREFIX_TASK);
    public static final String MESSAGE_COMMAND_HELP = String.format(
            "%s: Archives a task in the task book belonging to a module.\n%s",
            COMMAND_WORD,
            MESSAGE_COMMAND_USAGE
    );

    private final ModCode modCode;
    private final Index index;

    /**
     * Returns a new ArchiveTaskCommand object.
     *
     * @param modCode the module code of the module whose task is to be archived
     * @param index   the index of the task to be archived
     */
    public ArchiveTaskCommand(ModCode modCode, Index index) {
        this.modCode = modCode;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> tasks = model.getFilteredTaskList();

        // Check that the task index is not out of bounds.
        // The 0-based index is guaranteed by the Index class invariant to be >= 0.
        if (index.getZeroBased() >= tasks.size()) {
            throw new CommandException(String.format("Task index must be lower than %d.", tasks.size()));
        }
        Task target = model.getFilteredTaskList()
                .get(index.getZeroBased());

        // Check that the task has the same module code as the one specified.
        if (!target.getModule().equals(modCode)) {
            throw new CommandException(String.format("Task #%d does not belong to %s.",
                    index.getOneBased(),
                    modCode
            ));
        }

        // Check that the task is not already unarchived.
        if (target.isArchived()) {
            throw new CommandException(String.format("Task #%d is already archived.", index.getOneBased()));
        }

        // Archive the task.
        model.setTask(target, target.archive());
        return new CommandResult("Archived task.");
    }
}
