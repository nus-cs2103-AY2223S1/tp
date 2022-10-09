package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
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
 * Adds a task to an existing module in Plannit.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task "
            + "to the module identified by the module code. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + PREFIX_TASK_DESCRIPTION + "TASK DESCRIPTION \n"
            + "Example: " + COMMAND_WORD
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_TASK_DESCRIPTION + "Complete programming assignment";

    public static final String MESSAGE_ADD_TASK_SUCCESS =
            "New task added to: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK =
            "This task already exists in this module.";

    private final Index index;
    private final AddTaskToPersonDescriptor addTaskToPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to add task to
     * @param addTaskToPersonDescriptor contains details of Task to be added to
     *                                  the person
     */
    public AddTaskCommand(Index index, AddTaskToPersonDescriptor addTaskToPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(addTaskToPersonDescriptor);

        this.index = index;
        this.addTaskToPersonDescriptor = new AddTaskToPersonDescriptor(addTaskToPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToAddTaskTo = lastShownList.get(index.getZeroBased());
        Person personWithNewTask = createPersonWithNewTask(personToAddTaskTo, addTaskToPersonDescriptor);

        Boolean taskWasAdded = !personToAddTaskTo.isSamePerson(personWithNewTask);
        Boolean isDuplicateTask = personWithNewTask.hasDuplicateTasks();
        if (taskWasAdded && isDuplicateTask) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setPerson(personToAddTaskTo, personWithNewTask);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(MESSAGE_ADD_TASK_SUCCESS, personWithNewTask));
    }

    /**
     * Creates and returns a {@code Person} with the new task added.
     * @param personToAddTaskTo Person to add the new task to
     * @param addTaskToPersonDescriptor contains details of Task to be added
     *                                  to the person
     */
    private static Person createPersonWithNewTask(Person personToAddTaskTo,
                                                  AddTaskToPersonDescriptor addTaskToPersonDescriptor) {
        assert personToAddTaskTo != null;

        Name personName = personToAddTaskTo.getName();
        Phone personPhone = personToAddTaskTo.getPhone();
        Email personEmail = personToAddTaskTo.getEmail();
        Address personAddress = personToAddTaskTo.getAddress();
        Set<Tag> personTags = personToAddTaskTo.getTags();
        ObservableList<Task> personTasks = personToAddTaskTo.getTasks();
        TaskList updatedTasks = new TaskList(personTasks);
        Task taskToAdd = addTaskToPersonDescriptor.getTask();
        // Add new task to the list.
        updatedTasks.add(taskToAdd);
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
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        // state check
        AddTaskCommand e = (AddTaskCommand) other;
        return index.equals(e.index)
                && addTaskToPersonDescriptor.equals(e.addTaskToPersonDescriptor);
    }

    /**
     * Stores the details of task to be added to the person.
     */
    public static class AddTaskToPersonDescriptor {
        private Task newTask;

        public AddTaskToPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code newTask} is used internally.
         */
        public AddTaskToPersonDescriptor(AddTaskToPersonDescriptor toCopy) {
            setNewTask(toCopy.newTask);
        }

        /**
         * Sets {@code newTask} to this object's {@code newTask}.
         * A defensive copy of {@code newTask} is used internally.
         */
        public void setNewTask(Task newTask) {
            this.newTask = newTask;
        }


        /**
         * Returns the new task to be added as a {@code Task} object.
         */
        public Task getTask() {
            return newTask;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddTaskToPersonDescriptor)) {
                return false;
            }

            // state check
            AddTaskToPersonDescriptor e = (AddTaskToPersonDescriptor) other;

            return getTask().equals(e.getTask());
        }
    }
}
