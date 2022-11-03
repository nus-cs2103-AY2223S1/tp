package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;
import static seedu.address.model.task.Task.PREDICATE_SHOW_NON_ARCHIVED_TASKS;

import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandUtil;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
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
    public static final String MESSAGE_DUPLICATE_TAG_ON_PERSON = "This person already has the "
        + "tag you are trying to add";
    public static final String MESSAGE_DUPLICATE_TAG_ON_TASK = "This task already has the tag you are trying to add";
    public static final String MESSAGE_MISSING_INDEX = "At least 1 contact or task index must be provided.";

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

        if (!addTagToContact && !addTagToTask) {
            throw new CommandException(MESSAGE_MISSING_INDEX);
        }

        if (addTagToContact) {
            List<Person> lastShownPersonList = model.getFilteredPersonList();

            if (contactIndex.getZeroBased() >= lastShownPersonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToEdit = lastShownPersonList.get(contactIndex.getZeroBased());
            Person editedPerson = CommandUtil.createEditedPerson(personToEdit, editPersonDescriptor);

            if (personToEdit.equals(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_TAG_ON_PERSON);
            }

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
            List<Task> lastShownTaskList = model.getFilteredTaskList();

            if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToEdit = lastShownTaskList.get(taskIndex.getZeroBased());
            Task editedTask = CommandUtil.createEditedTask(taskToEdit, editTaskDescriptor);

            if (taskToEdit.equals(editedTask)) {
                throw new CommandException(MESSAGE_DUPLICATE_TAG_ON_TASK);
            }

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

