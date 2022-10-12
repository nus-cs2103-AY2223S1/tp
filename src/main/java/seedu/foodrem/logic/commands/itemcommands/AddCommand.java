package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;

/**
 * Adds an item to FoodRem.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to FoodRem. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ITEM_QUANTITY + "QUANTITY "
            + PREFIX_ITEM_UNIT + "UNIT "
            + PREFIX_ITEM_BOUGHT_DATE + "BOUGHT DATE "
            + PREFIX_ITEM_EXPIRY_DATE + "EXPIRY DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Potatoes "
            + PREFIX_ITEM_QUANTITY + "10 "
            + PREFIX_ITEM_UNIT + "kg "
            + PREFIX_ITEM_BOUGHT_DATE + "11-11-2022 "
            + PREFIX_ITEM_EXPIRY_DATE + "21-11-2022 ";

    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in FoodRem";

    private final Item toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Item}
     */
    public AddCommand(Item item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
