package seedu.address.logic.commands.client.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.logic.parser.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.ui.Ui;


/**
 * Represents a class to find and filter client list by phone.
 */
public class FindClientByPhoneCommand extends FindClientCommand {

    private final PhoneContainsKeywordsPredicate predicate;

    public FindClientByPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindClientByPhoneCommand // instanceof handles nulls
                && predicate.equals(((FindClientByPhoneCommand) other).predicate)); // state check
    }
}
