package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import modtrekt.commons.core.Messages;
import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.CliSyntax;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;


/**
 * Edits a task in the task book.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_IDENTIFIER = "-t";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the task. \n"
            + "Usage: " + COMMAND_WORD + " " + COMMAND_IDENTIFIER + " <INDEX>"
            + " [" + CliSyntax.PREFIX_MOD_CODE + "<MODULE_CODE> " + "] "
            + " [" + CliSyntax.PREFIX_DEADLINE + "<DEADLINE> " + "] "
            + " [" + CliSyntax.PREFIX_TASK_DESCRIPTION + "<TASK DESCRIPTION> " + "] ";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Task successfully edited: %1$s";

    private final Index targetIndex;

    private final ModCode targetModule;

    private final LocalDate targetDeadline;

    private final Description targetDesciption;

    /**
     * Creates an EditTaskCommand to edit the specified {@code Task}
     */
    public EditTaskCommand(Index targetIndex, ModCode targetModule, LocalDate targetDeadline,
                           Description targetDesciption) {
        this.targetIndex = targetIndex;
        this.targetModule = targetModule;
        this.targetDeadline = targetDeadline;
        this.targetDesciption = targetDesciption;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(targetIndex.getZeroBased());

        boolean archived = taskToEdit.isArchived();
        ModCode code = targetModule != null ? targetModule : taskToEdit.getModule();
        if (!model.hasModuleWithModCode(code)) {
            throw new CommandException(String.format("Module code %s does not exist.",
                    code.toString()));
        }
        Description description = targetDesciption != null ? targetDesciption : taskToEdit.getDescription();
        LocalDate deadline = targetDeadline != null
                ? targetDeadline
                : taskToEdit instanceof Deadline
                ? ((Deadline) taskToEdit).getDueDate()
                : null;

        Task newTask = deadline != null
                ? new Deadline(description, code, deadline, archived)
                : new Task(description, code, archived);

        model.deleteTask(taskToEdit);
        model.addTask(newTask);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, newTask));
    }

    public ModCode getTargetModule() {
        return targetModule;
    }

    public Description getTargetDesciption() {
        return targetDesciption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetIndex, targetModule, targetDeadline, targetDesciption);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTaskCommand // instanceof handles nulls
                && (targetIndex == null || targetIndex.equals(((EditTaskCommand) other).targetIndex))
                && (targetModule == null || targetModule.equals(((EditTaskCommand) other).targetModule))
                && (targetDesciption == null || targetDesciption.equals(((EditTaskCommand) other).targetDesciption))
                && (targetDeadline == null || targetDeadline.equals(((EditTaskCommand) other).targetDeadline)));
    }
}
