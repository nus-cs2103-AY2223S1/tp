package seedu.address.logic.commands.client.find;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.client.ClientCommand;
import seedu.address.model.Model;
import seedu.address.model.client.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.ui.Ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_PHONE;

public class FindClientByPhoneCommand extends FindClientCommand {

    public static final String MESSAGE_FIND_CLIENT_USAGE = COMMAND_WORD + ": Finds and filters clients by keyword " +
            "from the " +
            "address book. "
            + "Parameters: "
            + PREFIX_CLIENT_PHONE + "PHONE "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_CLIENT_PHONE + "98765432 ";

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
