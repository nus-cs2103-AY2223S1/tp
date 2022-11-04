package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
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
            Person editedPerson = CommandUtil.createEditedPerson(personToEdit, editPersonDescriptor);

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
            Task editedTask = CommandUtil.createEditedTask(taskToEdit, editTaskDescriptor);

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
