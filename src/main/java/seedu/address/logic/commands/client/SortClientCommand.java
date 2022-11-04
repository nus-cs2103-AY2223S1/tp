package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.SortCategory;
import seedu.address.model.client.Client;
import seedu.address.ui.Ui;

/**
 * Sort clients in project book.
 */
public class SortClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_SUCCESS = "Sorted clients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort clients in project book. \n"
            + "Sort by client id: "
            + "[" + PREFIX_CLIENT_ID + "0] (ascending) or "
            + "[" + PREFIX_CLIENT_ID + "1] (descending)."
            + "Sort by name: "
            + "[" + PREFIX_NAME + "0] (alphabetical) or "
            + "[" + PREFIX_NAME + "1] (reverse alphabetical). \n"
            + "Example: "
            + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "0";

    private final Prefix sortKey;
    private final int sortOrder;

    /**
     * Specifies a sorting client command which has a key and an order to sort by.
     *
     * @param order is the element to sort by
     * @param key is the variant of the element to sort by
     */
    public SortClientCommand(Prefix key, int order) {
        this.sortKey = key;
        this.sortOrder = order;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        String sortKeyString = "";
        Client.setSortOrder(this.sortOrder);

        if (sortKey.equals(PREFIX_CLIENT_ID)) {
            model.sortClientsById(sortOrder);
            Client.setSortCategory(SortCategory.ID);
            sortKeyString = "client id";
        }

        if (sortKey.equals(PREFIX_NAME)) {
            model.sortClientsByName(sortOrder);
            Client.setSortCategory(SortCategory.NAME);
            sortKeyString = "name.";
        }
        model.sortClientsByPin();

        ui.showClients();
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS + " according to " + sortKeyString);
    }
}
