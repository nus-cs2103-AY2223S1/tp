package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.task.AssignedToContactsPredicate;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s"
            + "\nThe following tasks' assigned contacts have been modified:";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Teammate> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Teammate teammateToDelete = lastShownList.get(targetIndex.getZeroBased());
        updateTasksAssignedContacts(model, teammateToDelete);
        model.deletePerson(teammateToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, teammateToDelete));
    }

    private void updateTasksAssignedContacts(Model model, Teammate teammateToDelete) throws CommandException {
        Contact contactToDelete = new Contact(teammateToDelete.getName().fullName);
        model.updateFilteredTaskList(new AssignedToContactsPredicate(contactToDelete));
        List<Task> lastShownTaskList = new ArrayList<>(model.getFilteredTaskList());
        List<Task> modifiedTaskList = new ArrayList<>();
        for (Task task : lastShownTaskList) {
            Set<Contact> newAssignedContactList = new HashSet<>(task.getAssignedContacts());
            newAssignedContactList.remove(contactToDelete);
            Task newTask = new Task(task.getTitle(), task.getCompleted(), task.getDeadline(),
                    task.getProject(), newAssignedContactList);
            model.setTask(task, newTask);
            modifiedTaskList.add(newTask);
        }
        model.updateFilteredTaskList(modifiedTaskList::contains);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
