package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Deletes all tasks and contacts whose tags contain any of the argument keywords.
 * Any tags that contain any of the argument keywords are also deleted.
 * Keyword matching is case-insensitive.
 */
public class DeleteAllCommand extends Command {

    public static final String COMMAND_WORD = "deleteA";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all tasks and contacts whose tags contain any of "
            + "the specified keyword (case-insensitive).\n"
            + "Any tags that contains any of the specified keyword are also deleted.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s\n";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s\n";

    private final PersonContainsKeywordsPredicate personPredicate;
    private final TaskContainsKeywordsPredicate taskPredicate;
    private final Set<Tag> tagsToDelete;

    /**
     * Creates a DeleteAllCommand object.
     *
     * @param tagsToDelete The tags of the tasks and contacts to delete.
     */
    public DeleteAllCommand(Set<Tag> tagsToDelete) {
        this.tagsToDelete = tagsToDelete;
        this.personPredicate = new PersonContainsKeywordsPredicate(tagsToDelete);
        this.taskPredicate = new TaskContainsKeywordsPredicate(tagsToDelete);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemark = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Set<Tag> newTags = editPersonDescriptor.getTags().orElse(new HashSet<>());
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(personToEdit.getTags());
        if (newTags.size() > 0) {
            updatedTags.removeAll(newTags);
        }

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark, updatedTags);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());
        Boolean updatedIsDone = editTaskDescriptor.getIsDone().orElse(taskToEdit.getStatus());
        Set<Tag> newTags = editTaskDescriptor.getTags().orElse(new HashSet<>());
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(taskToEdit.getTags());
        if (newTags.size() > 0) {
            updatedTags.removeAll(newTags);
        }
        // Id cannot be updated
        Id id = taskToEdit.getId();

        return new Task(updatedDescription, updatedDeadline, updatedIsDone, updatedTags, id);
    }

    private String deleteAndUpdateTasks(Model model) {
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        List<Task> tasksToBeDeleted = lastShownTaskList.stream()
                .filter(taskPredicate)
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Task task : tasksToBeDeleted) {
            boolean isTagSizeOne = task.getTags().size() == 1;
            if (isTagSizeOne) {
                model.deleteTask(task);
                sb.append(String.format(MESSAGE_DELETE_TASK_SUCCESS, task));
            } else {
                EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
                editTaskDescriptor.setTags(tagsToDelete);
                Task editedTask = createEditedTask(task, editTaskDescriptor);
                model.setTask(task, editedTask);
            }
        }
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return sb.toString();
    }

    private String deleteAndUpdatePersons(Model model) {
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Person> personsWithTag = lastShownPersonList.stream()
                .filter(personPredicate)
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Person person : personsWithTag) {
            boolean isTagSizeOne = person.getTags().size() == 1;
            if (isTagSizeOne) {
                model.deletePerson(person);
                sb.append(String.format(MESSAGE_DELETE_PERSON_SUCCESS, person));
            } else {
                EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
                editPersonDescriptor.setTags(tagsToDelete);
                Person editedPerson = createEditedPerson(person, editPersonDescriptor);
                model.setPerson(person, editedPerson);
            }
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return sb.toString();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        String deletedTasksMessage = deleteAndUpdateTasks(model);
        String deletedPersonsMessage = deleteAndUpdatePersons(model);

        return new CommandResult(deletedPersonsMessage + deletedTasksMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAllCommand // instanceof handles nulls
                && tagsToDelete.equals(((DeleteAllCommand) other).tagsToDelete));
    }
}
