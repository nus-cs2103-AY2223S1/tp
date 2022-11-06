package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NON_EXISTING_PRODUCT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DateKeyword;
import seedu.address.model.Model;
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.object == null) {
            model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        }
        if (this.object instanceof DateKeyword) {
            model.updateFilteredClientList(client -> client.isBirthdayInPeriod((DateKeyword) object));
        }
        if (this.object instanceof Product) {
            if (!model.hasProduct((Product) object)) {
                throw new CommandException(MESSAGE_NON_EXISTING_PRODUCT);
            }
            model.updateFilteredClientList(client -> client.hasProduct((Product) object));
        }
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.CLIENT);
    }
}
