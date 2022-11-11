package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameStartsWithKeywordPredicate;

/**
 * Finds and lists all persons in Plannit whose name starts with the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class FindPersonCommand extends Command {
    public static final String COMMAND_WORD = "find-person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose name starts with "
            + "the specified keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " Nathan";

    private final NameStartsWithKeywordPredicate predicate;

    public FindPersonCommand(NameStartsWithKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isNotAtHome = !model.getHomeStatusAsBoolean();

        if (isNotAtHome) {
            throw new CommandException(Messages.MESSAGE_NOT_AT_HOMEPAGE);
        }
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
