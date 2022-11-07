package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.team.Team;

/**
 * Edit the name of the task.
 */
public class TaskEditCommand extends Command {

    public static final String COMMAND_WORD = "taskedit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the name of the task of the specified team "
            + "by the index number used in the displayed team list.\n"
            + "Parameters: " + PREFIX_TEAM_INDEX + "TEAM-INDEX (must be a positive integer), "
            + PREFIX_TASK_INDEX + "TASK-INDEX (must be a positive integer) "
            + " [" + PREFIX_TASK_NAME + "NEW-TASK-NAME] \n"
            + " [" + PREFIX_TASK_DEADLINE + "DD-MM-YYYY]"
            + " (It's optional to include deadline for a task!) \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TEAM_INDEX + "1 "
            + PREFIX_TASK_INDEX + "3 " + PREFIX_TASK_NAME + "Design UI "
            + PREFIX_TASK_DEADLINE + "12-12-2023";
    public static final String MESSAGE_SUCCESS = "Task edited: %1$s %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the team's task list.";

    private final Index taskIndex;
    private final Index teamIndex;
    private final TaskName newTaskName;
    private final LocalDate newDeadline;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates a TaskEditCommand to edit the specified {@code Task}
     *
     * @param teamIndex of the team in the filtered team list to edit.
     * @param taskIndex index of the task to be edited.
     * @param newTaskName new name of the task.
     * @param newDeadline new deadline of the task
     */
    public TaskEditCommand(Index teamIndex, Index taskIndex, TaskName newTaskName, LocalDate newDeadline) {
        requireNonNull(taskIndex);
        requireNonNull(teamIndex);

        this.teamIndex = teamIndex;
        this.taskIndex = taskIndex;
        this.newTaskName = newTaskName;
        this.newDeadline = newDeadline;
        this.editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setName(newTaskName);
        editTaskDescriptor.setDeadline(Optional.ofNullable(newDeadline));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownTeamList = model.getFilteredTeamList();

        if (teamIndex.getZeroBased() >= lastShownTeamList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        if (taskIndex.getZeroBased() >= lastShownTeamList.get(teamIndex.getZeroBased()).getTasks().getSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownTeamList.get(teamIndex.getZeroBased()).getTask(taskIndex.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.teamHasTask(teamIndex, editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.editTask(teamIndex, taskIndex, newTaskName, newDeadline);
        String deadlineString = newDeadline == null ? "" : newDeadline.toString();
        String nameString = newTaskName == null
                ? lastShownTeamList.get(teamIndex.getZeroBased())
                .getTask(taskIndex.getZeroBased()).getName().toString()
                : newTaskName.toString();
        return new CommandResult(String.format(MESSAGE_SUCCESS, nameString, deadlineString));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        TaskName updatedTaskName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        LocalDate updatedDeadline = editTaskDescriptor.getDeadline()
                .orElse(taskToEdit.getDeadline().orElse(null));

        return new Task(updatedTaskName, updatedDeadline, taskToEdit.getIsDone());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskEditCommand // instanceof handles nulls
                && taskIndex.equals(((TaskEditCommand) other).taskIndex)
                && teamIndex.equals(((TaskEditCommand) other).teamIndex)
                && newTaskName.equals(((TaskEditCommand) other).newTaskName))
                && newDeadline.equals(((TaskEditCommand) other).newDeadline);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private TaskName taskName;
        private Optional<LocalDate> deadline;

        public EditTaskDescriptor() {}

        public void setName(TaskName taskName) {
            this.taskName = taskName;
        }

        public Optional<TaskName> getName() {
            return Optional.ofNullable(taskName);
        }

        public void setDeadline(Optional<LocalDate> deadline) {
            this.deadline = deadline;
        }

        public Optional<LocalDate> getDeadline() {
            return deadline;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getName().equals(e.getName())
                    && getDeadline().equals(e.getDeadline());
        }
    }
}
