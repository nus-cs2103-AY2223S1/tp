package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.model.Model;
import seedu.address.model.product.Product;

/**
 * Lists all persons in MyInsuRec to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "listClient";
    public static final String MESSAGE_SUCCESS = "Listed all clients";
    private final Object object;

    public ListClientCommand() {
        this.object = null;
    }

    public ListClientCommand(Product product) {
        this.object = product;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (this.object == null) {
            model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        } else if (this.object instanceof Product) {
            model.updateFilteredClientList(client -> client.hasProduct((Product) object));
        }
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.CLIENT);
    }
}
