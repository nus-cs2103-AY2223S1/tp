package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.position.TeachingAssistant;

/**
 * Changes the availability of an existing teaching assistant in the address book.
 */
public class AvailabilityCommand extends Command {
    public static final String COMMAND_WORD = "avail";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the availability of the Teaching Assistant identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_AVAILABILITY
            + "[AVAILABILITY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AVAILABILITY + "available.";
    public static final String MESSAGE_PERSON_NOT_TA = "The person to edit is not a teaching assistant, there is no"
            + "availability to be edited.";
    public static final String MESSAGE_EDIT_AVAILABILITY_SUCCESS = "Edited availability to Teaching Assistant: %1$s";

    private final Index index;
    private final String availability;

    /**
     * @param index of the teaching assistant in the filtered person list to edit the availability
     * @param availability of the teaching assistant to be updated to
     */
    public AvailabilityCommand(Index index, String availability) {
        requireAllNonNull(index, availability);

        this.index = index;
        this.availability = availability;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!(personToEdit.getPosition() instanceof TeachingAssistant)) {
            throw new CommandException(MESSAGE_PERSON_NOT_TA);
        }
        TeachingAssistant currPosition = (TeachingAssistant) personToEdit.getPosition();
        currPosition.setDetails(availability);
        Person editedPerson = personToEdit;

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the availability is edited for
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_EDIT_AVAILABILITY_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailabilityCommand)) {
            return false;
        }

        // state check
        AvailabilityCommand e = (AvailabilityCommand) other;
        return index.equals(e.index)
                && availability.equals(e.availability);
    }
}
