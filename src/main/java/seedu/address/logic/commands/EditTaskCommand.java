package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_ADD_STUDENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Assignment;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.FormatDate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;
import seedu.address.model.task.ToDo;

/**
 * Edits the details of an existing task in the task book.
 */
public class EditTaskCommand extends Command {
    public static final String COMMAND_WORD = "edit-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TASK_TITLE + "TITLE] "
            + "[" + PREFIX_TASK_DESCRIPTION + "DESCRIPTION...] \n"
            + "One or none of : " + "[" + PREFIX_DEADLINE_DATE + "Date in YYYY-MM-DD], "
            + "[" + PREFIX_ASSIGNMENT_ADD_STUDENTS + "Student names separated by commas] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_TITLE + "Test Title "
            + PREFIX_TASK_DESCRIPTION + "Very descriptive description ";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_WRONG_TASK_TYPE = "You are adding an invalid field to the task. "
            + "Create a task of the correct type to add relevant field";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());

        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit,
                                         EditTaskDescriptor editTaskDescriptor) throws CommandException {
        assert taskToEdit != null;

        TaskTitle updatedTitle = editTaskDescriptor.getTitle().orElse(taskToEdit.getTitle());
        TaskDescription updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Optional<FormatDate> inputDate = editTaskDescriptor.getDate();
        Optional<List<String>> inputStudentsToAdd = editTaskDescriptor.getStudentsToAdd();
        Optional<List<String>> inputStudentsToDelete = editTaskDescriptor.getStudentsToDelete();

        if (taskToEdit instanceof ToDo && !inputDate.isPresent()
                && !inputStudentsToAdd.isPresent() && !inputStudentsToDelete.isPresent()) {
            return new ToDo(updatedTitle, updatedDescription);
        } else if (taskToEdit instanceof Deadline
                && !inputStudentsToAdd.isPresent() && !inputStudentsToDelete.isPresent()) {
            Deadline deadlineTaskToEdit = (Deadline) taskToEdit;
            FormatDate updatedDate = editTaskDescriptor.getDate().orElse(deadlineTaskToEdit.getDate());
            return new Deadline(updatedTitle, updatedDescription, updatedDate);
        } else if (taskToEdit instanceof Deadline && !inputDate.isPresent()) {
            Assignment assignmentTaskToEdit = (Assignment) taskToEdit;
            if (editTaskDescriptor.getStudentsToAdd().isPresent()) {
                assignmentTaskToEdit.addStudent(editTaskDescriptor.getStudentsToAdd().get());
            }
            if (editTaskDescriptor.getStudentsToDelete().isPresent()) {
                assignmentTaskToEdit.deleteStudent(editTaskDescriptor.getStudentsToDelete().get());
            }
            List<String> updatedStudents = assignmentTaskToEdit.getStudents();
            return new Assignment(updatedTitle, updatedDescription, updatedStudents);
        } else {
            throw new CommandException(MESSAGE_WRONG_TASK_TYPE);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private TaskTitle title;
        private TaskDescription description;
        private FormatDate date;
        private List<String> studentsToAdd;
        private List<String> studentsToDelete;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setDate(toCopy.date);
            setStudentsToAdd(toCopy.studentsToAdd);
            setStudentsToDelete(toCopy.studentsToDelete);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, date, studentsToAdd, studentsToDelete);
        }

        public void setTitle(TaskTitle title) {
            this.title = title;
        }

        public Optional<TaskTitle> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(TaskDescription description) {
            this.description = description;
        }

        public Optional<TaskDescription> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDate(FormatDate date) {
            this.date = date;
        }

        public Optional<FormatDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setStudentsToAdd(List<String> students) {
            this.studentsToAdd = students;
        }

        public void setStudentsToDelete(List<String> students) {
            this.studentsToDelete = students;
        }

        public Optional<List<String>> getStudentsToAdd() {
            return Optional.ofNullable(studentsToAdd);
        }

        public Optional<List<String>> getStudentsToDelete() {
            return Optional.ofNullable(studentsToDelete);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskCommand.EditTaskDescriptor)) {
                return false;
            }
            // might get errors here
            // state check
            EditTaskCommand.EditTaskDescriptor e = (EditTaskCommand.EditTaskDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getDate().equals(e.getDate())
                    && getStudentsToAdd().equals(e.getStudentsToAdd())
                    && getStudentsToDelete().equals(e.getStudentsToDelete());
        }
    }
}
