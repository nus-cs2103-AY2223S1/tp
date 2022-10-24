package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_PHONE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.predicates.ClientContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.ui.Ui;


/**
 * Represents an abstract class to find and filter client list.
 */
public class FindClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of client(s) shown.";
    private static final String MESSAGE_CLIENT_NOT_FOUND = "A client matching requirements not found.";
    public static final String MESSAGE_FIND_CLIENT_USAGE = COMMAND_WORD + ": Finds and filters clients by keyword "
            + "address book. "
            + "Parameters: "
            + PREFIX_CLIENT_NAME + "NAME "
            + PREFIX_CLIENT_EMAIL + "EMAIL "
            + PREFIX_CLIENT_PHONE + "PHONE "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_CLIENT_NAME + "John "
            + PREFIX_CLIENT_EMAIL + "john@gmail.com "
            + PREFIX_CLIENT_PHONE + "12345678 ";

    public final ClientContainsKeywordsPredicate predicate;
    public FindClientCommand(ClientContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindClientCommand // instanceof handles nulls
                && predicate.equals(((FindClientCommand) other).predicate)); // state check
    }

}