package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.NEW_LINE_CHARACTER;
import static seedu.address.logic.commands.contact.DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS;
import static seedu.address.logic.commands.tag.DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS;
import static seedu.address.logic.commands.task.DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.task.Task.PREDICATE_SHOW_NON_ARCHIVED_TASKS;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandUtil;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
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
            + ": Deletes all tasks and contacts whose labels contain any of "
            + "the specified keyword (case-sensitive).\n"
            + "Any labels that contains any of the specified keyword are also deleted.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    public static final String MESSAGE_TAGS_DO_NOT_EXIST = "The label(s) you want to remove cannot be found";

    private final PersonContainsKeywordsPredicate personPredicate;
    private final TaskContainsKeywordsPredicate taskPredicate;
    private final Set<Tag> tagsToDelete;

    /**
     * Creates a DeleteAllCommand object.
     *
     * @param tagsToDelete The tags of the tasks and contacts to delete.
     */
    public DeleteAllCommand(Set<Tag> tagsToDelete) {
        requireNonNull(tagsToDelete);
        assert !tagsToDelete.isEmpty();

        this.tagsToDelete = tagsToDelete;
        this.personPredicate = new PersonContainsKeywordsPredicate(tagsToDelete);
        this.taskPredicate = new TaskContainsKeywordsPredicate(tagsToDelete);
    }

    private void checkIfTagsExist(Model model) throws CommandException {
        for (Tag tag : tagsToDelete) {
            if (!model.hasTag(tag)) {
                throw new CommandException(MESSAGE_TAGS_DO_NOT_EXIST);
            }
        }
    }

    private Task removeTags(Model model, Task task) {
        for (Tag tag : tagsToDelete) {
            if (task.containTag(tag)) {
                model.decreaseTagCount(tag);
            }
        }
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setTags(tagsToDelete);
        return CommandUtil.createEditedTask(task, editTaskDescriptor);
    }

    private Person removeTags(Model model, Person person) {
        for (Tag tag : tagsToDelete) {
            if (person.containTag(tag)) {
                model.decreaseTagCount(tag);
            }
        }
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setTags(tagsToDelete);
        return CommandUtil.createEditedPerson(person, editPersonDescriptor);
    }

    private String deleteAndUpdateTasks(Model model) {
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        List<Task> tasksToBeDeleted = lastShownTaskList.stream()
                .filter(taskPredicate)
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Task task : tasksToBeDeleted) {
            boolean isTagSizeGreaterThanOne = task.getTagSize() > 1;
            if (isTagSizeGreaterThanOne) {
                Task editedTask = removeTags(model, task);
                boolean isTagSizeNonEmpty = !(editedTask.getTagSize() == 0);
                if (isTagSizeNonEmpty) {
                    model.setTask(task, editedTask);
                    continue;
                }
            }
            model.deleteTask(task);
            sb.append(String.format(MESSAGE_DELETE_TASK_SUCCESS, task));
            sb.append(NEW_LINE_CHARACTER);
        }
        model.updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);
        return sb.toString();
    }

    private String deleteAndUpdatePersons(Model model) {
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Person> personsWithTag = lastShownPersonList.stream()
                .filter(personPredicate)
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Person person : personsWithTag) {
            boolean isTagSizeGreaterThanOne = person.getTagsSize() > 1;
            Person editedPerson = removeTags(model, person);
            if (isTagSizeGreaterThanOne) {
                boolean isTagSizeNonEmpty = !(editedPerson.getTagsSize() == 0);
                if (isTagSizeNonEmpty) {
                    model.setPerson(person, editedPerson);
                    continue;
                }
            }
            model.deletePerson(person);
            sb.append(String.format(MESSAGE_DELETE_PERSON_SUCCESS, person));
            sb.append(NEW_LINE_CHARACTER);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return sb.toString();
    }

    private String updateTagList(Model model) {
        StringBuilder sb = new StringBuilder();
        for (Tag toDelete: tagsToDelete) {
            sb.append(String.format(MESSAGE_DELETE_TAG_SUCCESS, toDelete));
            sb.append(NEW_LINE_CHARACTER);
            while (model.hasTag(toDelete)) {
                model.decreaseTagCount(toDelete);
            }
        }
        return sb.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        checkIfTagsExist(model);
        String deletedPersonsMessage = deleteAndUpdatePersons(model);
        String deletedTasksMessage = deleteAndUpdateTasks(model);
        String deletedTagMessage = updateTagList(model);
        model.commitAddressBook();

        return new CommandResult(deletedTagMessage
                + deletedPersonsMessage
                + deletedTasksMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAllCommand // instanceof handles nulls
                && tagsToDelete.equals(((DeleteAllCommand) other).tagsToDelete));
    }
}
