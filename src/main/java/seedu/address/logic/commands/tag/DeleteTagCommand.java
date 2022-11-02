package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.task.Task.PREDICATE_SHOW_NON_ARCHIVED_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing person in the address book.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deleteL";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "LABEL]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "CS2103T";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Label: %1$s";
    public static final String MESSAGE_TAG_NOT_DELETED = "At least 1 label to delete must be provided.";
    public static final String MESSAGE_TAGS_DO_NOT_EXIST = "The label(s) you want to remove are not found "
        + "on the selected contact/task.";
    public static final String MESSAGE_MISSING_INDEX = "At least 1 contact or task index must be provided.";

    private final Index contactIndex;
    private final Index taskIndex;
    private final EditPersonDescriptor editPersonDescriptor;
    private final EditTaskDescriptor editTaskDescriptor;
    private final boolean deleteTagFromContact;
    private final boolean deleteTagFromTask;
    private final List<String> tagStrings;

    /**
     * @param contactIndex of the person in the filtered person list to edit
     * @param taskIndex of the task in the filtered task list to edit
     * @param editPersonDescriptor details to edit the person with
     * @param editTaskDescriptor details to edit the task with
     * @param deleteTagFromContact true if contactIndex was provided
     * @param deleteTagFromTask true if taskIndex was provided
     */
    public DeleteTagCommand(Index contactIndex, Index taskIndex, EditPersonDescriptor editPersonDescriptor,
                            EditTaskDescriptor editTaskDescriptor, boolean deleteTagFromContact,
                            boolean deleteTagFromTask, List<String> tagStrings) {
        requireNonNull(contactIndex);
        requireNonNull(taskIndex);
        requireNonNull(editPersonDescriptor);
        requireNonNull(editTaskDescriptor);

        this.contactIndex = contactIndex;
        this.taskIndex = taskIndex;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.deleteTagFromContact = deleteTagFromContact;
        this.deleteTagFromTask = deleteTagFromTask;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
        this.tagStrings = tagStrings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!deleteTagFromContact && !deleteTagFromTask) {
            throw new CommandException(MESSAGE_MISSING_INDEX);
        }

        if (deleteTagFromContact) {
            List<Person> lastShownList = model.getFilteredPersonList();

            if (contactIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToEdit = lastShownList.get(contactIndex.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

            if (!personToEdit.getTags().containsAll(editPersonDescriptor.getTags().orElse(new HashSet<>()))) {
                throw new CommandException(MESSAGE_TAGS_DO_NOT_EXIST);
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            for (String string : tagStrings) {
                Tag toDelete = new Tag(string);
                model.decreaseTagCount(toDelete);
            }
        }
        if (deleteTagFromTask) {
            List<Task> lastShownTaskList = model.getFilteredTaskList();

            if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToEdit = lastShownTaskList.get(taskIndex.getZeroBased());
            Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

            if (!taskToEdit.getTags().containsAll(editTaskDescriptor.getTags().orElse(new HashSet<>()))) {
                throw new CommandException(MESSAGE_TAGS_DO_NOT_EXIST);
            }

            model.setTask(taskToEdit, editedTask);
            model.updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);
            for (String string : tagStrings) {
                Tag toDelete = new Tag(string);
                model.decreaseTagCount(toDelete);
            }
        }
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS,
                editPersonDescriptor.getTags().orElse(new HashSet<>())));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        UUID id = editPersonDescriptor.getId().orElse(personToEdit.getId());
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

        return new Person(id, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark, updatedTags);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    public static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());
        Boolean updatedIsDone = editTaskDescriptor.getCompletionStatus().orElse(taskToEdit.getCompletionStatus());
        Boolean updatedIsArchived = editTaskDescriptor.getArchivalStatus().orElse(taskToEdit.getArchivalStatus());

        Set<Tag> newTags = editTaskDescriptor.getTags().orElse(new HashSet<>());
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(taskToEdit.getTags());
        if (newTags.size() > 0) {
            updatedTags.removeAll(newTags);
        }
        // Id cannot be updated
        Id id = taskToEdit.getId();

        return new Task(updatedDescription, updatedDeadline, updatedIsDone, updatedIsArchived, updatedTags, id);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        // state check
        DeleteTagCommand e = (DeleteTagCommand) other;
        return contactIndex.equals(e.contactIndex)
                && taskIndex.equals(e.taskIndex)
                && editTaskDescriptor.equals(e.editTaskDescriptor)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
