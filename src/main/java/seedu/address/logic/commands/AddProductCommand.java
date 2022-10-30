package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.product.Product;

/**
 * Adds a product to MyInsuRec.
 */
public class AddProductCommand extends Command {

    public static final String COMMAND_WORD = "addProduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a product.\n"
            + "Parameters: "
            + PREFIX_PRODUCT + "PRODUCT NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRODUCT + "PrudenSure";

    public static final String MESSAGE_SUCCESS = "New product added: %1$s";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in MyInsuRec";

    private final Product toAdd;

    /**
     * Creates an AddProductCommand to add the specified {@code Product}.
     */
    public AddProductCommand(Product product) {
        requireNonNull(product);
        toAdd = product;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProduct(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.addProduct(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandSpecific.PRODUCT);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProductCommand // instanceof handles nulls
                && toAdd.equals(((AddProductCommand) other).toAdd));
    }
}
