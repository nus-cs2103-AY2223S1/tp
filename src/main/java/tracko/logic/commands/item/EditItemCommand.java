package tracko.logic.commands.item;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.commons.util.CollectionUtil;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.item.exceptions.ItemUnmodifiableException;
import tracko.model.tag.Tag;

/**
 * Edits the details of an existing item in the inventory list.
 */
public class EditItemCommand extends Command {

    public static final String COMMAND_WORD = "editi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the item identified "
            + "by the index number used in the displayed inventory list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + CliSyntax.PREFIX_ITEM + "ITEM NAME "
            + CliSyntax.PREFIX_QUANTITY + "QUANTITY "
            + CliSyntax.PREFIX_DESCRIPTION + "DESCRIPTION "
            + CliSyntax.PREFIX_TAG + "TAG... "
            + CliSyntax.PREFIX_SELL_PRICE + "SELL PRICE "
            + CliSyntax.PREFIX_COST_PRICE + "COST PRICE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_ITEM + "Paper "
            + CliSyntax.PREFIX_QUANTITY + "1000 "
            + CliSyntax.PREFIX_DESCRIPTION + "White printing paper "
            + CliSyntax.PREFIX_TAG + "Limited "
            + CliSyntax.PREFIX_TAG + "New "
            + CliSyntax.PREFIX_SELL_PRICE + "2.00 "
            + CliSyntax.PREFIX_COST_PRICE + "1.98 ";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited Item:\n%1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the inventory list.";
    public static final String MESSAGE_UNCOMPLETED_ORDER_ITEM = "Item cannot be edited, there exists uncompleted "
            + "orders for item:\n%1$s";

    private final Index index;
    private final EditItemDescriptor editItemDescriptor;

    /**
     * @param index of the item in the filtered inventory list to edit
     * @param editItemDescriptor details to edit the item with
     */
    public EditItemCommand(Index index, EditItemDescriptor editItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editItemDescriptor);

        this.index = index;
        this.editItemDescriptor = new EditItemDescriptor(editItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<InventoryItem> lastShownList = model.getFilteredItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        InventoryItem inventoryItemToEdit = lastShownList.get(index.getZeroBased());
        InventoryItem editedInventoryItem = createEditedItem(inventoryItemToEdit, editItemDescriptor);

        if (!inventoryItemToEdit.isSameItem(editedInventoryItem) && model.hasItem(editedInventoryItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        try {
            model.setItem(inventoryItemToEdit, editedInventoryItem);
            model.refreshData();
            model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);
            return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedInventoryItem));
        } catch (ItemUnmodifiableException e) {
            return new CommandResult(String.format(MESSAGE_UNCOMPLETED_ORDER_ITEM, editedInventoryItem));
        }
    }

    /**
     * Creates and returns a {@code Item} with the details of {@code itemToEdit}
     * edited with {@code editItemDescriptor}.
     */
    private static InventoryItem createEditedItem(InventoryItem inventoryItemToEdit,
                                                  EditItemDescriptor editItemDescriptor) {
        assert inventoryItemToEdit != null;

        ItemName updatedItemName = editItemDescriptor.getItemName().orElse(inventoryItemToEdit.getItemName());
        Quantity updatedQuantity = editItemDescriptor.getQuantity().orElse(inventoryItemToEdit.getTotalQuantity());
        Description updatedDescription = editItemDescriptor.getDescription()
            .orElse(inventoryItemToEdit.getDescription());
        Set<Tag> updatedTags = editItemDescriptor.getTags().orElse(inventoryItemToEdit.getTags());
        Price sellPrice = editItemDescriptor.getSellPrice().orElse(inventoryItemToEdit.getSellPrice());
        Price costPrice = editItemDescriptor.getCostPrice().orElse(inventoryItemToEdit.getCostPrice());

        return new InventoryItem(updatedItemName, updatedDescription, updatedQuantity,
            updatedTags, sellPrice, costPrice);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditItemCommand)) {
            return false;
        }

        // state check
        EditItemCommand e = (EditItemCommand) other;
        return index.equals(e.index)
                && editItemDescriptor.equals(e.editItemDescriptor);
    }

    /**
     * Stores the details to edit the item with. Each non-empty field value will replace the
     * corresponding field value of the item.
     */
    public static class EditItemDescriptor {
        private ItemName itemName;
        private Quantity quantity;
        private Description description;
        private Set<Tag> tags;
        private Price sellPrice;
        private Price costPrice;

        public EditItemDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            setItemName(toCopy.itemName);
            setQuantity(toCopy.quantity);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
            setSellPrice(toCopy.sellPrice);
            setCostPrice(toCopy.costPrice);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(itemName, quantity, description, tags, sellPrice, costPrice);
        }

        public void setItemName(ItemName itemName) {
            this.itemName = itemName;
        }

        public Optional<ItemName> getItemName() {
            return Optional.ofNullable(itemName);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setSellPrice(Price sellPrice) {
            this.sellPrice = sellPrice;
        }

        public Optional<Price> getSellPrice() {
            return Optional.ofNullable(sellPrice);
        }

        public void setCostPrice(Price costPrice) {
            this.costPrice = costPrice;
        }

        public Optional<Price> getCostPrice() {
            return Optional.ofNullable(costPrice);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditItemDescriptor)) {
                return false;
            }

            // state check
            EditItemDescriptor e = (EditItemDescriptor) other;

            return getItemName().equals(e.getItemName())
                    && getQuantity().equals(e.getQuantity())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags())
                    && getSellPrice().equals(e.getSellPrice())
                    && getCostPrice().equals(e.getCostPrice());
        }
    }
}
