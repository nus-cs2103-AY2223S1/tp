package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTSTOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINIMUMSTOCK;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Adds a SupplyItem to the inventory .
 */
public class AddItemCommand extends Command {

    public static final String COMMAND_WORD = "addItem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a supplyItem which is identified by index "
            + "of supplier in supplier list to inventory. "
            + "\nParameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_CURRENTSTOCK + "CURRENTSTOCK "
            + PREFIX_MINIMUMSTOCK + "MINIMUMSTOCK "
            + "\nExample: " + COMMAND_WORD + " 4 c/5 m/2";

    public static final String MESSAGE_SUCCESS = "New supplyItem added: %1$s";

    public static final String MESSAGE_DUPLICATE_SUPPLYITEM = "This supplyItem from different"
            + " supplier already exists in inventory";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "This supplyItem from same"
            + " supplier already exists in inventory";

    public static final String MESSAGE_MISSING_CURRENT_STOCK = "Current Stock field must be provided";
    public static final String MESSAGE_MISSING_MINIMUM_STOCK = "Minimum Stock field must be provided";
    public static final String MESSAGE_INVALID_STOCK = "Stock fields provided must be positive";


    private final Index targetIndex;
    private final int currentStock;
    private final int minimumStock;

    /**
     * Creates an AddItemCommand to add the specified {@code SupplyItem}
     */
    public AddItemCommand(Index index, int currentStock, int minimumStock) {
        requireNonNull(index);
        this.targetIndex = index;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;

    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<SupplyItem> inventoryList = model.getFilteredSupplyItemList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SUPPLYITEM_DISPLAYED_INDEX);
        }


        Person supplier = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        SupplyItem toAdd = new SupplyItem(supplier.getItem().toString(),
                currentStock, minimumStock, supplier, getTagSet("Item"));

        if (model.hasSupplyItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
        }

        for (SupplyItem item: inventoryList) {
            String existingSupplier = item.getSupplier().getName().toString();
            if (existingSupplier.equals(supplier.getName().toString())) {
                throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
            }
        }

        for (SupplyItem item: inventoryList) {
            String existingItems = item.getName();
            if (existingItems.equals(toAdd.getName())) {
                throw new CommandException(MESSAGE_DUPLICATE_SUPPLYITEM);
            }
        }


        model.addSupplyItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand// instanceof handles nulls
                && targetIndex.equals(((AddItemCommand) other).targetIndex))
                && currentStock == (((AddItemCommand) other).currentStock)
                && minimumStock == (((AddItemCommand) other).minimumStock);
    }
}
