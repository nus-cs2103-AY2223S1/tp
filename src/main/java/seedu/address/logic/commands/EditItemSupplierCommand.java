package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SUPPLY_ITEMS;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing supply item in the inventory.
 * Can only be executed through {@code EditCommand}.
 */
public class EditItemSupplierCommand extends Command {

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited Item.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This supply item already exists in the inventory.";
    public static final String MESSAGE_ITEM_NOTFOUND = "The specified item could not be found";

    private final Person editedSupplier;

    /**
     * @param editedSupplier supplier details to edit the item with
     */
    public EditItemSupplierCommand(Person editedSupplier) {
        requireNonNull(editedSupplier);
        this.editedSupplier = editedSupplier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<SupplyItem> supplyItemOptional = model.supplyItemSuppliedBy(editedSupplier);

        SupplyItem supplyItemToEdit =
                supplyItemOptional.orElseThrow(() -> new CommandException(MESSAGE_ITEM_NOTFOUND));
        SupplyItem editedSupplyItem = new SupplyItem(
                editedSupplier.getItem().toString(),
                supplyItemToEdit.getCurrentStock(),
                supplyItemToEdit.getMinStock(),
                editedSupplier,
                supplyItemToEdit.getTags());
        Index supplyItemToEditIndex =
                Index.fromZeroBased(model.getFilteredSupplyItemList().indexOf(supplyItemToEdit));

        if (model.hasSupplyItemExcluding(editedSupplyItem, supplyItemToEdit)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.setSupplyItem(editedSupplyItem, supplyItemToEditIndex);
        model.updateFilteredSupplyItemList(PREDICATE_SHOW_ALL_SUPPLY_ITEMS);

        return new CommandResult(MESSAGE_EDIT_ITEM_SUCCESS);
    }

}
