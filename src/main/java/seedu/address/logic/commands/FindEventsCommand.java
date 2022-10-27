package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.EventTitleContainsKeywordsPredicate;

/**
 * Finds and lists all events in address book whose event title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEventsCommand extends Command {

    public static final String COMMAND_WORD = "findEvents";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose event titles contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " marketing sale";

    private final EventTitleContainsKeywordsPredicate predicate;

    public FindEventsCommand(EventTitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventsCommand // instanceof handles nulls
                && predicate.equals(((FindEventsCommand) other).predicate)); // state check
    }
}
