package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.EDIT_COMMAND;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.CollectionUtil;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemPrice;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemRemark;
import seedu.foodrem.model.item.ItemUnit;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Edits the details of an existing item in FoodRem.
 */
public class EditCommand extends Command {
    private static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in FoodRem.";
    private final EditItemDescriptor editItemDescriptor;
    private final Index index;

    /**
     * Constructs an EditCommand object.
     *
     * @param index              of the item in the filtered item list to edit
     * @param editItemDescriptor details to edit the item with
     */
    public EditCommand(Index index, EditItemDescriptor editItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editItemDescriptor);

        this.index = index;
        this.editItemDescriptor = new EditItemDescriptor(editItemDescriptor);
    }

    /**
     * Creates and returns a {@code Item} with the details of {@code itemToEdit}
     * edited with {@code editItemDescriptor}.
     *
     * @param itemToEdit the item to be edited.
     * @param editItemDescriptor the descriptor of how the item should be edited.
     * @return an item which is edited.
     */
    private static Item createEditedItem(Item itemToEdit, EditItemDescriptor editItemDescriptor) {
        assert itemToEdit != null;

        ItemName updatedName = editItemDescriptor.getItemName().orElse(itemToEdit.getName());
        ItemQuantity updatedQuantity = editItemDescriptor.getItemQuantity().orElse(itemToEdit.getQuantity());
        ItemUnit updatedUnit = editItemDescriptor.getItemUnit().orElse(itemToEdit.getUnit());
        ItemBoughtDate updatedBoughtDate = editItemDescriptor.getItemBoughtDate().orElse(itemToEdit.getBoughtDate());
        ItemExpiryDate updatedExpiryDate = editItemDescriptor.getItemExpiryDate().orElse(itemToEdit.getExpiryDate());
        ItemPrice updatedPrice = editItemDescriptor.getItemPrice().orElse(itemToEdit.getPrice());
        ItemRemark updatedRemarks = editItemDescriptor.getItemRemarks().orElse(itemToEdit.getRemarks());
        Set<Tag> tagSet = itemToEdit.getTagSet();

        return new Item(updatedName,
                updatedQuantity,
                updatedUnit,
                updatedBoughtDate,
                updatedExpiryDate,
                updatedPrice,
                updatedRemarks,
                tagSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult<ItemWithMessage> execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getCurrentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
        }

        Item itemToEdit = lastShownList.get(index.getZeroBased());
        Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);

        if (!itemToEdit.isSameItem(editedItem) && model.hasItem(editedItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.setItem(itemToEdit, editedItem);
        return CommandResult.from(new ItemWithMessage(editedItem,
                "Item successfully edited with the following values:"));
    }

    /**
     * Returns a string representing how to use the command.
     *
     * @return a string representing how to use the command.
     */
    public static String getUsage() {
        return EDIT_COMMAND.getUsage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editItemDescriptor.equals(e.editItemDescriptor);
    }

    /**
     * Stores the details to edit the item with. Each non-empty field value will replace the
     * corresponding field value of the item.
     */
    public static class EditItemDescriptor {
        private ItemName name;
        private ItemQuantity quantity;
        private ItemUnit unit;
        private ItemBoughtDate boughtDate;
        private ItemExpiryDate expiryDate;
        private ItemPrice price;
        private ItemRemark remarks;

        /**
         * Constructs an EditItemDescriptor.
         */
        public EditItemDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param toCopy the descriptor of the item to copy.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            setItemName(toCopy.name);
            setItemQuantity(toCopy.quantity);
            setItemUnit(toCopy.unit);
            setItemBoughtDate(toCopy.boughtDate);
            setItemExpiryDate(toCopy.expiryDate);
            setItemPrice(toCopy.price);
            setItemRemarks(toCopy.remarks);
        }

        /**
         * Returns {@code true} if at least one field is edited.
         *
         * @return true if at least one field is edited, false otherwise.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, quantity, unit, boughtDate, expiryDate, price, remarks);
        }

        /**
         * Returns an optional item name.
         *
         * @return an optional item name.
         */
        public Optional<ItemName> getItemName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets the item name of the descriptor.
         *
         * @param name the item name.
         */
        public void setItemName(ItemName name) {
            this.name = name;
        }

        /**
         * Returns an optional item quantity.
         *
         * @return an optional item quantity.
         */
        public Optional<ItemQuantity> getItemQuantity() {
            return Optional.ofNullable(quantity);
        }

        /**
         * Sets the item quantity of the descriptor.
         *
         * @param quantity the item quantity.
         */
        public void setItemQuantity(ItemQuantity quantity) {
            this.quantity = quantity;
        }

        /**
         * Returns an optional item unit.
         *
         * @return an optional item unit.
         */
        public Optional<ItemUnit> getItemUnit() {
            return Optional.ofNullable(unit);
        }

        /**
         * Sets the item unit of the descriptor.
         *
         * @param unit the item unit.
         */
        public void setItemUnit(ItemUnit unit) {
            this.unit = unit;
        }

        /**
         * Returns an optional item bought date.
         *
         * @return an optional item bought date.
         */
        public Optional<ItemBoughtDate> getItemBoughtDate() {
            return Optional.ofNullable(boughtDate);
        }

        /**
         * Sets the item bought date of the descriptor.
         *
         * @param boughtDate the item bought date.
         */
        public void setItemBoughtDate(ItemBoughtDate boughtDate) {
            this.boughtDate = boughtDate;
        }

        /**
         * Returns an optional item expiry date.
         *
         * @return an optional item expiry date.
         */
        public Optional<ItemExpiryDate> getItemExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        /**
         * Sets the item expiry date of the descriptor.
         *
         * @param expiryDate the item expiry date.
         */
        public void setItemExpiryDate(ItemExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        /**
         * Returns an optional item price.
         *
         * @return an optional item price.
         */
        public Optional<ItemPrice> getItemPrice() {
            return Optional.ofNullable(price);
        }

        /**
         * Sets the item price of the descriptor.
         *
         * @param price the item price.
         */
        public void setItemPrice(ItemPrice price) {
            this.price = price;
        }

        /**
         * Returns an optional item remark.
         *
         * @return an optional item remark.
         */
        public Optional<ItemRemark> getItemRemarks() {
            return Optional.ofNullable(remarks);
        }

        /**
         * Sets the item remark of the descriptor.
         *
         * @param remarks the item remarks.
         */
        public void setItemRemarks(ItemRemark remarks) {
            this.remarks = remarks;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof EditItemDescriptor)) {
                return false;
            }
            EditItemDescriptor e = (EditItemDescriptor) other;

            return getItemName().equals(e.getItemName())
                    && getItemQuantity().equals(e.getItemQuantity())
                    && getItemUnit().equals(e.getItemUnit())
                    && getItemBoughtDate().equals(e.getItemBoughtDate())
                    && getItemExpiryDate().equals(e.getItemExpiryDate())
                    && getItemPrice().equals(e.getItemPrice())
                    && getItemRemarks().equals(e.getItemRemarks());
        }
    }
}
