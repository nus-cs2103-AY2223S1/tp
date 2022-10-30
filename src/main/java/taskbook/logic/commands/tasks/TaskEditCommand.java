package taskbook.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static taskbook.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static taskbook.commons.util.CollectionUtil.requireAllNonNull;
import static taskbook.logic.parser.CliSyntax.PREFIX_ASSIGN_FROM;
import static taskbook.logic.parser.CliSyntax.PREFIX_ASSIGN_TO;
import static taskbook.logic.parser.CliSyntax.PREFIX_DATE;
import static taskbook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static taskbook.logic.parser.CliSyntax.PREFIX_INDEX;
import static taskbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import taskbook.commons.core.index.Index;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;
import taskbook.model.person.Person;
import taskbook.model.task.EditTaskDescriptor;
import taskbook.model.task.Task;

/**
 * Edits the details of an existing task in the task book.
 */
public class TaskEditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
        + "by the index number used in the displayed task list. \n"
        + "Existing values will be overwritten by the input values.\n"
        + "\n"
        + "Parameters: \n" + PREFIX_INDEX + "INDEX (must be a positive integer) "
        + "[" + PREFIX_ASSIGN_FROM + "NAME] "
        + "[" + PREFIX_ASSIGN_TO + "NAME] "
        + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
        + "[" + PREFIX_DATE + "DATE] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 "
        + PREFIX_ASSIGN_FROM + "Jackie Chan "
        + PREFIX_DESCRIPTION + "Practice kick 10000 times";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task book.";
    public static final String MESSAGE_ASSIGNOR_ASSIGNEE = "A task cannot have both an assignor and an assignee.";
    public static final String MESSAGE_INVALID_PARAMETER = "Invalid parameter: %s.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found in task book!";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public TaskEditCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireAllNonNull(index, editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getSortedTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = taskToEdit.createEditedCopy(editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (!editedTask.isSelfAssigned()) {
            Person personToFind = model.findPerson(editedTask.getName());
            if (personToFind == null) {
                throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
            }
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskListPredicate(Model.PREDICATE_SHOW_ALL_TASKS);
        model.commitTaskBook();
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskEditCommand)) {
            return false;
        }

        // state check
        TaskEditCommand e = (TaskEditCommand) other;
        return index.equals(e.index)
            && editTaskDescriptor.equals(e.editTaskDescriptor);
    }
}
