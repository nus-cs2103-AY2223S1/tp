package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsInterestPredicate;

/**
 * Lists batchmates that match all tags specified by the user.
 * Keyword matching is case-insensitive.
 */
public class FindInterestCommand extends Command {

    public static final String COMMAND_WORD = "findInt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists batchmates that match all interests specified\n"
            + "Example: " + COMMAND_WORD + " tennis";

    private final PersonContainsInterestPredicate predicate;

    public FindInterestCommand(PersonContainsInterestPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size()), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInterestCommand // instanceof handles nulls
                && predicate.equals(((FindInterestCommand) other).predicate)); // state check
    }
}
