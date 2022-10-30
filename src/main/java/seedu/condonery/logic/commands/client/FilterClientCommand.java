package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.ClientTagContainsKeywordsPredicate;

/**
 * Finds and lists all properties in Condonery whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterClientCommand extends Command {

    public static final String COMMAND_WORD = "filter -c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " high-end";

    private final ClientTagContainsKeywordsPredicate predicate;

    /**
     * Creates a FilterClientCommand to filter the specified {@code Client}
     *
     * @param predicate
     */
    public FilterClientCommand(ClientTagContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterClientCommand // instanceof handles nulls
                && predicate.equals(((FilterClientCommand) other).predicate)); // state check
    }
}
