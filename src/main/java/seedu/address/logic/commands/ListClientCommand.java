package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.function.Predicate;

import seedu.address.logic.parser.DateKeyword;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;


/**
 * Lists all persons in MyInsuRec to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "listClient";
    public static final String MESSAGE_SUCCESS = "Listed all clients";
    public static final String MESSAGE_USAGE = "Usage: listClient OR listClient pd/PRODUCT OR b/PERIOD";

    private final Object object;

    public ListClientCommand() {
        this.object = null;
    }

    public ListClientCommand(Product product) {
        this.object = product;
    }

    public ListClientCommand(DateKeyword dateKeyword) {
        this.object = dateKeyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Client> pred = PREDICATE_SHOW_ALL_CLIENTS;

        if (this.object instanceof DateKeyword) {
            pred = client -> client.isBirthdayInPeriod((DateKeyword) object);
        }
        if (this.object instanceof Product) {
            pred = client -> client.hasProduct((Product) object);
        }
        model.updateFilteredClientList(pred);
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.CLIENT);
    }
}
