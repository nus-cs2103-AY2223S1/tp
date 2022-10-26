package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interest.Interest;
import seedu.address.model.person.Person;

/**
 * Deletes interests from the specified batchmate.
 */
public class DeleteInterestCommand extends Command {

    public static final String COMMAND_WORD = "deleteInt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes interest(s) specified from the batchmate as identified by the index number of the"
            + " displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) INTEREST [MORE_INTERESTS]...\n"
            + "Example: " + COMMAND_WORD + " 1 tennis netflix";
    public static final String MESSAGE_SUCCESS = "Interest(s) deleted successfully!";
    public static final String MESSAGE_INVALID_INTEREST = "This batchmate does not have all of the interests specified."
            + "\nPlease check the entered interests and try again.";

    private final Index targetIndex;
    private final Set<Interest> interests;

    /**
     * Constructs a command that deletes all interests specified from the target batchmate.
     *
     * @param index The index of the batchmate.
     * @param interests The set of interests to be deleted.
     */
    public DeleteInterestCommand(Index index, Set<Interest> interests) {
        requireNonNull(index);
        requireNonNull(interests);

        this.targetIndex = index;
        this.interests = interests;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());

        if (personToEdit.canDeleteInterests(this.interests)) {
            personToEdit.deleteInterests(this.interests);
        } else {
            throw new CommandException(MESSAGE_INVALID_INTEREST);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToEdit), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInterestCommand)) {
            return false;
        }

        // state check
        DeleteInterestCommand e = (DeleteInterestCommand) other;
        return targetIndex.equals(e.targetIndex)
                && interests.equals(e.interests);
    }
}
