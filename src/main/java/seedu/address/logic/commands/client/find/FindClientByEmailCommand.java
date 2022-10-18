package seedu.address.logic.commands.client.find;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.client.ClientCommand;
import seedu.address.model.Model;
import seedu.address.model.client.predicates.EmailContainsKeywordsPredicate;
import seedu.address.ui.Ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_EMAIL;

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
