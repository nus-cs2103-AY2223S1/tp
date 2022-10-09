package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.task.Task;
import seedu.address.model.person.task.TaskList;
import seedu.address.model.tag.Tag;

/**
 * Deletes a task from an existing module in Plannit.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task "
            + "belonging to the module identified by the module code. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + PREFIX_TASK_NUMBER + "TASK NUMBER \n"
            + "Example: " + COMMAND_WORD
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_TASK_NUMBER + "1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS =
            "Deleted task from: %1$s";
    public static final String MESSAGE_TASK_NUMBER_DOES_NOT_EXIST =
            "Task number given does not exist.";

    private final Index index;
    private final DeleteTaskFromPersonDescriptor deleteTaskFromPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param deleteTaskFromPersonDescriptor details of task to be deleted
     */
    public DeleteTaskCommand(Index index,
                             DeleteTaskFromPersonDescriptor deleteTaskFromPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(deleteTaskFromPersonDescriptor);

        this.index = index;
        this.deleteTaskFromPersonDescriptor =
                new DeleteTaskFromPersonDescriptor(deleteTaskFromPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDeleteTaskFrom = lastShownList.get(index.getZeroBased());
        int indexOfTaskToDelete = deleteTaskFromPersonDescriptor
                .getTaskIndexToDelete().getZeroBased();
        int numberOfTasksInTaskList = personToDeleteTaskFrom.getTasks().size();
        if (indexOfTaskToDelete >= numberOfTasksInTaskList) {
            throw new CommandException(MESSAGE_TASK_NUMBER_DOES_NOT_EXIST);
        }
        Person personWithTaskDeleted = createPersonWithDeletedTask(
                personToDeleteTaskFrom, deleteTaskFromPersonDescriptor);

        model.setPerson(personToDeleteTaskFrom, personWithTaskDeleted);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS,
                personWithTaskDeleted));
    }

    /**
     * Creates and returns a {@code Person} with the details of
     * {@code personToDeleteTaskFrom} and the task indicated in
     * {@code deleteTaskToPersonDescriptor} deleted.
     */
    private static Person createPersonWithDeletedTask(
            Person personToDeleteTaskFrom,
            DeleteTaskFromPersonDescriptor deleteTaskToPersonDescriptor) {
        assert personToDeleteTaskFrom != null;

        Name personName = personToDeleteTaskFrom.getName();
        Phone personPhone = personToDeleteTaskFrom.getPhone();
        Email personEmail = personToDeleteTaskFrom.getEmail();
        Address personAddress = personToDeleteTaskFrom.getAddress();
        Set<Tag> personTags = personToDeleteTaskFrom.getTags();
        ObservableList<Task> personTasks = personToDeleteTaskFrom.getTasks();
        TaskList updatedTasks = new TaskList(personTasks);
        // Delete new task to the list.
        Index indexOfTaskToDelete =
                deleteTaskToPersonDescriptor.getTaskIndexToDelete();
        updatedTasks.remove(indexOfTaskToDelete);
        List<Task> updatedTasksAsList =
                updatedTasks.asUnmodifiableObservableList();
        return new Person(personName, personPhone, personEmail,
                personAddress, personTags, updatedTasksAsList);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        // state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return index.equals(e.index)
                && deleteTaskFromPersonDescriptor.equals(e.deleteTaskFromPersonDescriptor);
    }

    /**
     * Stores the task number of the {@code Task} to be deleted.
     */
    public static class DeleteTaskFromPersonDescriptor {
        private Index indexOfTaskToDelete;

        public DeleteTaskFromPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code indexOfTaskToDelete} is used internally.
         */
        public DeleteTaskFromPersonDescriptor(DeleteTaskFromPersonDescriptor toCopy) {
            setIndexOfTaskToDelete(toCopy.indexOfTaskToDelete);
        }

        /**
         * Sets {@code indexOfTaskToDelete} to this object's {@code
         * indexOfTaskToDelete}.
         * A defensive copy of {@code indexOfTaskToDelete} is used internally.
         */
        public void setIndexOfTaskToDelete(Index indexOfTaskToDelete) {
            this.indexOfTaskToDelete = indexOfTaskToDelete;
        }


        /**
         * Returns the {@code Index} of the task to be deleted.
         */
        public Index getTaskIndexToDelete() {
            return indexOfTaskToDelete;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeleteTaskFromPersonDescriptor)) {
                return false;
            }

            // state check
            DeleteTaskFromPersonDescriptor e = (DeleteTaskFromPersonDescriptor) other;

            return getTaskIndexToDelete().equals(e.getTaskIndexToDelete());
        }
    }
}
