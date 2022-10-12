package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsTagPredicate;

/**
 * Lists batchmates that match all tags specified by the user.
 * Keyword matching is case-insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findTag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists batchmates that match all tags specified\n"
            + "Example: " + COMMAND_WORD + " team1";

    private final PersonContainsTagPredicate predicate;

    public FindTagCommand(PersonContainsTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                && predicate.equals(((FindTagCommand) other).predicate)); // state check
    }
}
