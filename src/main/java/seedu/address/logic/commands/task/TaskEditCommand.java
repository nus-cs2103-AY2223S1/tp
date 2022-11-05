package seedu.address.logic.commands.task;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.task.TaskAddCommand.MESSAGE_INVALID_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_STUDENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

/**
 * Edits the details of an existing person in the address book.
 */
public class TaskEditCommand extends Command {

    public static final String COMMAND_WORD = "task edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer and NOT TOO BIG) "
            + "[" + PREFIX_TASK_NAME + "TASK NAME] "
            + "[" + PREFIX_TASK_DESC + "TASK DESCRIPTION] "
            + "[" + PREFIX_TASK_DEADLINE + "TASK DEADLINE] "
            + "[" + PREFIX_TASK_STUDENT + "STUDENT]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_NAME + "Assignment 1 "
            + PREFIX_TASK_DESC + "Complete assignment 1 "
            + PREFIX_TASK_DEADLINE + "2021-10-10";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editTaskDescriptor details to edit the person with
     */
    public TaskEditCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        System.out.println("---");
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor, model);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createEditedTask(
            Task taskToEdit, EditTaskDescriptor editStudentDescriptor, Model model
    ) throws CommandException {
        assert taskToEdit != null;

        TaskName updatedTaskName = editStudentDescriptor.getTaskName().orElse(taskToEdit.getTaskName());
        TaskDescription updatedTaskDescription = editStudentDescriptor.getTaskDescription()
                .orElse(taskToEdit.getTaskDescription());
        TaskDeadline updatedTaskDeadline = editStudentDescriptor.getTaskDeadline().orElse(taskToEdit.getTaskDeadline());

        Set<Student> students = new HashSet<>();
        if (editStudentDescriptor.getStudentNames() != null) {
            for (String studentName : editStudentDescriptor.getStudentNames()) {
                if (isNull(model.findStudent(studentName))) {
                    throw new CommandException(MESSAGE_INVALID_STUDENT);
                }

                students.add(model.findStudent(studentName));
            }
        }

        return new Task(updatedTaskName, updatedTaskDescription, updatedTaskDeadline, students);
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

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTaskDescriptor {
        private TaskName taskName;
        private TaskDescription taskDescription;
        private TaskDeadline taskDeadline;
        private List<String> studentNames;


        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskName(toCopy.taskName);
            setTaskDescription(toCopy.taskDescription);
            setTaskDeadline(toCopy.taskDeadline);
            setStudentNames(toCopy.studentNames);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(taskName, taskDescription, taskDeadline, studentNames);
        }

        public void setTaskName(TaskName taskName) {
            this.taskName = taskName;
        }

        public Optional<TaskName> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setTaskDescription(TaskDescription taskDescription) {
            this.taskDescription = taskDescription;
        }

        public Optional<TaskDescription> getTaskDescription() {
            return Optional.ofNullable(taskDescription);
        }

        public void setTaskDeadline(TaskDeadline taskDeadline) {
            this.taskDeadline = taskDeadline;
        }

        public Optional<TaskDeadline> getTaskDeadline() {
            return Optional.ofNullable(taskDeadline);
        }

        /**
         * Sets {@code students} to this object's {@code students}.
         * A defensive copy of {@code students} is used internally.
         */
        public void setStudentNames(List<String> students) {
            this.studentNames = (students != null) ? new ArrayList<>(students) : null;
        }

        public List<String> getStudentNames() {
            return studentNames;
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

            return getTaskName().equals(e.getTaskName())
                    && getTaskDescription().equals(e.getTaskDescription())
                    && getTaskDeadline().equals(e.getTaskDeadline())
                    && getStudentNames().equals(e.getStudentNames());
        }
    }
}
