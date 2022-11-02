package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;

/**
 * Adds a property to the property book.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "addprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to Cobb.\n"
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_PRICE + " PRICE "
            + PREFIX_ADDRESS + " ADDRESS "
            + PREFIX_DESCRIPTION + " DESCRIPTION "
            + PREFIX_OWNER_NAME + " OWNER NAME "
            + PREFIX_PHONE + " OWNER PHONE "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " Clementi Flat "
            + PREFIX_PRICE + " 500000 "
            + PREFIX_ADDRESS + " 311 Clementi Ave 2, #02-25 "
            + PREFIX_DESCRIPTION + " Ready to be bought "
            + PREFIX_OWNER_NAME + " John "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_CHARACTERISTICS + " 3-Room; Near School";

    public static final String MESSAGE_SUCCESS = "New property added!\n%1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in Cobb";

    private final Property property;

    /**
     * Creates an AddPropertyCommand to add the specified {@code Property}
     */
    public AddPropertyCommand(Property property) {
        requireNonNull(property);
        this.property = property;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProperty(property)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.addProperty(property);
        return new CommandResult(String.format(MESSAGE_SUCCESS, property));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyCommand // instanceof handles nulls
                && property.equals(((AddPropertyCommand) other).property));
    }
}
