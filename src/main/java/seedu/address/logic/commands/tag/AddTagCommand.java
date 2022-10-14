package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing person in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addL";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "CS2103T";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag: %1$s";
    public static final String MESSAGE_TAG_NOT_ADDED = "At least 1 tag to add must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book.";
    public static final String MESSAGE_MISSING_INDEX = "At least 1 contact or task index must be provided.";

    private final Index contactIndex;
    private final Index taskIndex;
    private final EditPersonDescriptor editPersonDescriptor;
    private final EditTaskDescriptor editTaskDescriptor;
    private final boolean addTagToContact;
    private final boolean addTagToTask;

    /**
     * @param contactIndex of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public AddTagCommand(Index contactIndex, Index taskIndex, EditPersonDescriptor editPersonDescriptor,
        EditTaskDescriptor editTaskDescriptor, boolean addTagToContact, boolean addTagToTask) {
        requireNonNull(contactIndex);
        requireNonNull(taskIndex);
        requireNonNull(editPersonDescriptor);
        requireNonNull(editTaskDescriptor);

        this.contactIndex = contactIndex;
        this.taskIndex = taskIndex;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.addTagToContact = addTagToContact;
        this.addTagToTask = addTagToTask;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (addTagToContact) {
            List<Person> lastShownPersonList = model.getFilteredPersonList();

            if (contactIndex.getZeroBased() >= lastShownPersonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToEdit = lastShownPersonList.get(contactIndex.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS,
                    editPersonDescriptor.getTags().orElse(new HashSet<>())));
        }
        if (addTagToTask) {
            List<Task> lastShownTaskList = model.getFilteredTaskList();

            if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToEdit = lastShownTaskList.get(taskIndex.getZeroBased());
            Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

            if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }

            model.setTask(taskToEdit, editedTask);
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS,
                    editTaskDescriptor.getTags().orElse(new HashSet<>())));
        }
        throw new CommandException(MESSAGE_MISSING_INDEX);
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
            updatedTags.addAll(newTags);
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
            updatedTags.addAll(newTags);
        }

        return new Task(updatedDescription, updatedDeadline, updatedIsDone, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        return contactIndex.equals(e.contactIndex)
                && taskIndex.equals(e.taskIndex)
                && editTaskDescriptor.equals(e.editTaskDescriptor)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}

