package seedu.address.logic.commands.client.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.logic.parser.predicates.EmailContainsKeywordsPredicate;
import seedu.address.ui.Ui;


/**
 * Represents a class to find and filter client list by email.
 */
public class FindClientByEmailCommand extends FindClientCommand {
    private final EmailContainsKeywordsPredicate predicate;

    public FindClientByEmailCommand(EmailContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientByEmailCommand // instanceof handles nulls
                && predicate.equals(((FindClientByEmailCommand) other).predicate)); // state check
    }
}
