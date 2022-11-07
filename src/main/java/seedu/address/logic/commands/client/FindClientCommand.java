package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.predicates.ClientContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.ui.Ui;


/**
 * Represent a class to find and filter client list.
 */
public class FindClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of client(s) shown.";
    private static final String MESSAGE_CLIENT_NOT_FOUND = "A client matching requirements not found.";
    public static final String MESSAGE_FIND_CLIENT_USAGE = COMMAND_WORD + " " + COMMAND_FLAG
            + ": Finds clients by keyword.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_CLIENT_ID + "CLIENT ID] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_MOBILE + "MOBILE] \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "John "
            + PREFIX_CLIENT_ID + "1 "
            + PREFIX_EMAIL + "john@gmail.com "
            + PREFIX_MOBILE + "12345678 ";

    private final ClientContainsKeywordsPredicate predicate;
    public FindClientCommand(ClientContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showClients();
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
