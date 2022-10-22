package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_AT_HOMEPAGE;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameStartsWithKeywordPredicate;

/**
 * Finds and lists all people in Plannit whose name starts with any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindPersonCommand extends Command {
    public static final String COMMAND_WORD = "find-person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all people whose name starts with "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " Nathan";

    private final NameStartsWithKeywordPredicate predicate;

    public FindPersonCommand(NameStartsWithKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isNotAtHome = !model.getHomeStatus();

        if (isNotAtHome) {
            throw new CommandException(MESSAGE_NOT_AT_HOMEPAGE);
        }
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
