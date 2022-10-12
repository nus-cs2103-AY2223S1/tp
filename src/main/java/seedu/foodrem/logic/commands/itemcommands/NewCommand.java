package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;

/**
 * Creates a new an item in FoodRem.
 */
public class NewCommand extends Command {

    public static final String COMMAND_WORD = "new";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new item in FoodRem. "
            + "\n"
            + "Parameters: "
            + PREFIX_ITEM_NAME + "NAME "
            + "[" + PREFIX_ITEM_QUANTITY + "QUANTITY" + "] "
            + "[" + PREFIX_ITEM_UNIT + "UNIT" + "] "
            + "[" + PREFIX_ITEM_BOUGHT_DATE + "BOUGHT_DATE" + "] "
            + "[" + PREFIX_ITEM_EXPIRY_DATE + "EXPIRY_DATE" + "] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM_NAME + "Potatoes "
            + PREFIX_ITEM_QUANTITY + "10 "
            + PREFIX_ITEM_UNIT + "kg "
            + PREFIX_ITEM_BOUGHT_DATE + "11-11-2022 "
            + PREFIX_ITEM_EXPIRY_DATE + "21-11-2022 ";

    public static final String MESSAGE_SUCCESS = "New item added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in FoodRem";

    private final Item newItem;

    /**
     * Creates an AddCommand to add the specified {@code Item}
     */
    public NewCommand(Item item) {
        requireNonNull(item);
        newItem = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(newItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addItem(newItem);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newItem));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NewCommand // instanceof handles nulls
                && newItem.equals(((NewCommand) other).newItem));
    }
}
