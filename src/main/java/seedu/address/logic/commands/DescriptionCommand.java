package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;

/**
 * Changes the description of an existing person in the address book.
 */
public class DescriptionCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "description";

    public static final String COMMAND_SHORTCUT = "desc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the description of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing description will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DESCRIPTION + "[DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Likes to swim.";

    public static final String MESSAGE_ADD_DESCRIPTION_SUCCESS = "Updated description to: %1$s for Person: %2$s";
    public static final String MESSAGE_DELETE_DESCRIPTION_SUCCESS = "Removed description from Person: %2$s";
    public static final String MESSAGE_UNDO_RESET = "Re-set description to: %1$s for Person: %2$s";
    public static final String MESSAGE_REDO = "Re-update description to: %1$s for Person: %2$s";
    public static final String MESSAGE_REDO_DELETE = "Re-removed description from Person: %2$s";

    private final Index index;
    private final Description description;

    private Person personToEdit;
    private Person editedPerson;

    /**
     * @param index of the person in the filtered person list to edit the description
     * @param description of the person to be updated to
     */
    public DescriptionCommand(Index index, Description description) {
        requireAllNonNull(index, description);

        this.index = index;
        this.description = description;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        personToEdit = lastShownList.get(index.getZeroBased());
        editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), description, personToEdit.getNetWorth(), personToEdit.getMeetingTimes(),
                personToEdit.getFilePath(), personToEdit.getTags());


        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the description is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !description.value.isEmpty() ? MESSAGE_ADD_DESCRIPTION_SUCCESS
                : MESSAGE_DELETE_DESCRIPTION_SUCCESS;
        return String.format(message, description, personToEdit);
    }

    @Override
    public CommandResult undo(Model model) {

        model.setPerson(editedPerson, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return personToEdit.getDescription().value.isEmpty()
                ? new CommandResult(String.format(MESSAGE_DELETE_DESCRIPTION_SUCCESS, description, personToEdit))
                : new CommandResult(String.format(MESSAGE_UNDO_RESET, personToEdit.getDescription(), personToEdit));
    }

    @Override
    public CommandResult redo(Model model) {
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return description.value.isEmpty()
                ? new CommandResult(String.format(MESSAGE_REDO_DELETE, description, editedPerson))
                : new CommandResult(String.format(MESSAGE_REDO, description, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DescriptionCommand)) {
            return false;
        }

        // state check
        DescriptionCommand e = (DescriptionCommand) other;
        return index.equals(e.index)
                && description.equals(e.description);
    }
}
