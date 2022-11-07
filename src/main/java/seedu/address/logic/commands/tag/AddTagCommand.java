package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandUtil.createEditedPerson;
import static seedu.address.logic.commands.CommandUtil.createEditedTask;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;
import static seedu.address.model.task.Task.PREDICATE_SHOW_NON_ARCHIVED_TASKS;

import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Adds tag(s) to an existing person/task in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addL";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the labels of the person/task identified "
            + "by the index number used in the displayed person/task list. "
            + "New tags will be added on to existing list of tags.\n"
            + "Parameters: " + PREFIX_CONTACT + "INDEX (must be a positive integer) "
            + PREFIX_TASK + "INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CONTACT + "1 " + PREFIX_TASK + "2 "
            + PREFIX_TAG + "CS2103T";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag: %1$s";
    public static final String MESSAGE_TAG_NOT_ADDED = "At least 1 tag to add must be provided.";
    public static final String MESSAGE_DUPLICATE_TAG_ON_PERSON_OR_TASK = "This person/task already has the "
        + "tag you are trying to add";

    private final Index contactIndex;
    private final Index taskIndex;
    private final EditPersonDescriptor editPersonDescriptor;
    private final EditTaskDescriptor editTaskDescriptor;
    private final boolean addTagToContact;
    private final boolean addTagToTask;
    private final List<String> tagStrings;

    /**
     * Constructor for AddTagCommand.
     * @param contactIndex index of chosen contact
     * @param taskIndex index of chosen task
     * @param editPersonDescriptor object used to add tags to contact
     * @param editTaskDescriptor object used to add tags to task
     * @param addTagToContact boolean checking if tags should be added to a contact
     * @param addTagToTask boolean checking if tags should be added to a task
     * @param tagStrings list of tag names in string format
     */
    public AddTagCommand(Index contactIndex, Index taskIndex, EditPersonDescriptor editPersonDescriptor,
                         EditTaskDescriptor editTaskDescriptor, boolean addTagToContact, boolean addTagToTask,
                         List<String> tagStrings) {
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
        this.tagStrings = tagStrings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownPersonList = model.getFilteredPersonList();
        Person personToEdit = lastShownPersonList.get(contactIndex.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        List<Task> lastShownTaskList = model.getFilteredTaskList();
        Task taskToEdit = lastShownTaskList.get(taskIndex.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);


        if (contactIndex.getZeroBased() >= lastShownPersonList.size()
            || taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_OR_TASK_DISPLAYED_INDEX);
        }

        if (personToEdit.equals(editedPerson) || taskToEdit.equals(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG_ON_PERSON_OR_TASK);
        }

        if (addTagToContact) {
            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            for (String string : tagStrings) {
                Tag toAdd = new Tag(string);
                if (model.hasTag(toAdd)) {
                    model.addTagCount(toAdd);
                } else {
                    model.addTag(toAdd);
                    model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
                }
            }
        }
        if (addTagToTask) {
            model.setTask(taskToEdit, editedTask);
            model.updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);

            for (String string : tagStrings) {
                Tag toAdd = new Tag(string);
                if (model.hasTag(toAdd)) {
                    model.addTagCount(toAdd);
                } else {
                    model.addTag(toAdd);
                    model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
                }
            }
        }

        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS,
                editPersonDescriptor.getTags().orElse(new HashSet<>())));
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

