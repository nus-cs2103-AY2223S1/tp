package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the FinBook.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final int DEFAULT_INDEX = -1;

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the client identified by the index number used in the displayed person list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MULTIPLE_DELETE_USAGE = COMMAND_WORD
        + ": Deletes the multiple client identified by the index number used in the displayed person list.\n"
        + "Parameters: INDEX (must be a positive integer) separated by commas and cannot have repeated indexes\n"
        + "Example: " + COMMAND_WORD + " 1, 2, 4";

    public static final String MESSAGE_RANGE_DELETE_USAGE = COMMAND_WORD
        + ": Deletes multiple clients identified by the index number used in the displayed client list.\n"
        + "Parameters: startIndex - endIndex (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 2 - 4";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_DELETE_ALL_SUCCESS = "All clients in FinBook has been deleted!";

    private final Index targetIndex;
    private final List<Index> indexList;
    private boolean deleteAll = false;

    /**
     * DeleteCommand constructor with Index parameter to indicate delete single client command
     * Sets targetIndex to targetIndex and indexList to null
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.indexList = null;
    }

    /**
     * DeleteCommand constructor with no parameters to indicate a deleteAll command
     * Sets both targetIndex and indexList to null and deleteAll boolean to be true
     * to indicate that user input a deleteAll command
     */
    public DeleteCommand() {
        this.targetIndex = null;
        this.indexList = null;
        this.deleteAll = true;
    }

    /**
     * DeleteCommand constructor with Index list parameter to indicate a deleteMultiple command
     * Sets targetIndex to null and indexList to indexList
     */
    public DeleteCommand(List<Index> indexList) {
        this.targetIndex = null;
        this.indexList = indexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (deleteAll) {
            return deleteAll(model);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexList != null) {
            return deleteMultiple(model, lastShownList);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), DEFAULT_INDEX);
    }

    /**
     * Handles delete all clients in execute method
     *
     * @param model of FinBook
     * @return new CommandResult
     */
    public CommandResult deleteAll(Model model) {
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_DELETE_ALL_SUCCESS, DEFAULT_INDEX);
    }

    /**
     * Handles delete multiple clients in execute method
     *
     * @param model         of Finook
     * @param lastShownList list of clients displayed
     * @return new CommandResult
     */
    public CommandResult deleteMultiple(Model model, List<Person> lastShownList) throws CommandException {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < indexList.size(); i++) {
            Index targetIndex = indexList.get(i);
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            if (i != indexList.size() - 1) {
                output.append(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete)).append("\n");
            } else {
                output.append(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
            }
        }
        return new CommandResult(output.toString(), DEFAULT_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || ((other instanceof DeleteCommand // instanceof handles nulls
            && ((targetIndex != null && targetIndex.equals(((DeleteCommand) other).targetIndex)))) //index check
            || (indexList != null && indexList.equals(((DeleteCommand) other).indexList))) // List index check
            || (targetIndex == null && ((DeleteCommand) other).targetIndex == null //deleteAll check
            && indexList == null && ((DeleteCommand) other).indexList == null //deleteAll check
            && deleteAll == (((DeleteCommand) other).deleteAll)); //deleteAll check
    }
}
