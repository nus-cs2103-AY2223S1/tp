package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static tracko.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javafx.util.Pair;
import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.commons.util.CollectionUtil;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.item.InventoryItem;
import tracko.model.item.Quantity;
import tracko.model.item.exceptions.ItemNotFoundException;
import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Name;
import tracko.model.order.Order;
import tracko.model.order.Phone;

/**
 * Edits the details of an existing Order in the address book.
 */
public class EditOrderCommand extends Command {

    public static final String COMMAND_WORD = "edito";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing name, phone, email, address values will "
            + "be overwritten by the input values. An item tag ("
            + CliSyntax.PREFIX_ITEM + ") needs to be accompanied by a "
            + "quantity tag (" + CliSyntax.PREFIX_QUANTITY + "). \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
            + "[" + CliSyntax.PREFIX_ADDRESS + "ADDRESS] "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PHONE + "91234567 "
            + CliSyntax.PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_NONEXISTENT_ITEM = "The item that is being added does "
            + "not exist in the inventory list.";
    public static final String MESSAGE_ONE_ORDERED_ITEM = "An order list cannot have 0 items. "
            + "Perhaps you want to delete the order instead?";

    public static final String MESSAGE_ORDER_ALREADY_MARKED =
            "An order marked as paid and/or delivered cannot be edited!";

    private final Index index;
    private final EditOrderDescriptor editOrderDescriptor;

    /**
     * @param index of the order in the filtered order list to edit
     * @param editOrderDescriptor details to edit the order with
     */
    public EditOrderCommand(Index index, EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new EditOrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getSortedOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());

        if (orderToEdit.isCompleted() || orderToEdit.getDeliveryStatus() || orderToEdit.getPaidStatus()) {
            throw new CommandException(MESSAGE_ORDER_ALREADY_MARKED);
        }

        Order editedOrder = createEditedOrder(orderToEdit, editOrderDescriptor, model);

        model.setOrder(orderToEdit, editedOrder);
        model.refreshData();
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder));
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code orderToEdit}
     * edited with {@code EditOrderDescriptor}.
     */
    private static Order createEditedOrder(Order orderToEdit, EditOrderDescriptor editOrderDescriptor, Model model)
            throws CommandException {
        assert orderToEdit != null;

        Name updatedName = editOrderDescriptor.getName().orElse(orderToEdit.getName());
        Phone updatedPhone = editOrderDescriptor.getPhone().orElse(orderToEdit.getPhone());
        Email updatedEmail = editOrderDescriptor.getEmail().orElse(orderToEdit.getEmail());
        Address updatedAddress = editOrderDescriptor.getAddress().orElse(orderToEdit.getAddress());

        // Run checks on whether the item is in the inventory list
        Optional<Pair<String, Integer>> unlinkedItem = editOrderDescriptor.getUnlinkedItemToEdit();
        if (!unlinkedItem.equals(Optional.empty())) {
            Pair<String, Integer> itemToCompare = unlinkedItem.get();
            InventoryItem inventoryItem;

            try {
                inventoryItem = model.getItem(itemToCompare.getKey());
            } catch (ItemNotFoundException e) {
                throw new CommandException(MESSAGE_NONEXISTENT_ITEM);
            }

            ItemQuantityPair itemToEdit = new ItemQuantityPair(inventoryItem, new Quantity(itemToCompare.getValue()));
            editOrderDescriptor.updateItemList(orderToEdit, itemToEdit);
        }

        List<ItemQuantityPair> updatedItemList = editOrderDescriptor.getItemList().orElse(orderToEdit.getItemList());
        LocalDateTime timeCreated = orderToEdit.getTimeCreated();
        boolean isPaid = orderToEdit.getPaidStatus();
        boolean isDelivered = orderToEdit.getDeliveryStatus();

        return new Order(updatedName, updatedPhone, updatedEmail, updatedAddress, timeCreated,
                updatedItemList, isPaid, isDelivered);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditOrderCommand)) {
            return false;
        }

        // state check
        EditOrderCommand e = (EditOrderCommand) other;
        return index.equals(e.index)
                && editOrderDescriptor.equals(e.editOrderDescriptor);
    }

    /**
     * Stores the details to edit the order with. Each non-empty field value will replace the
     * corresponding field value of the order.
     */
    public static class EditOrderDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private List<ItemQuantityPair> itemList;
        private Pair<String, Integer> itemUnlinkedToInventory;

        public EditOrderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditOrderDescriptor(EditOrderDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setItemList(toCopy.itemList);
            setUnlinkedItemToEdit(toCopy.itemUnlinkedToInventory);
        }



        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, itemUnlinkedToInventory);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public Optional<List<ItemQuantityPair>> getItemList() {
            return Optional.ofNullable(itemList);
        }

        public void setItemList(List<ItemQuantityPair> itemList) {
            this.itemList = itemList;
        }

        /**
         * Updates the item list. This method checks whether the item that is going to be updated is
         * in the customer's ordered items list; if it is, then it will check whether the values are different
         * from the original or 0. If the value is zero, the item will be removed from the order list, else
         * its quantity will be updated. If the item is not in the item list, then the item will be added to
         * the customer's ordered items list.
         *
         * @param orderToEdit The order whose ordered items list is going to be edited.
         * @param itemToEdit The item that is going to be added, removed or have its quantity edited.
         */
        public void updateItemList(Order orderToEdit, ItemQuantityPair itemToEdit) throws CommandException {
            List<ItemQuantityPair> orderedItems = orderToEdit.getItemList();
            boolean hasItemBeenUpdated = false;
            for (int i = 0; i < orderedItems.size(); i++) {
                ItemQuantityPair itemInList = orderedItems.get(i);

                if (!itemInList.getItem().isSameItem(itemToEdit.getItem())) {
                    continue;
                }

                if (itemToEdit.getQuantityValue() == 0) {
                    if (orderedItems.size() == 1) {
                        throw new CommandException(MESSAGE_ONE_ORDERED_ITEM);
                    }
                    orderedItems.remove(i);
                    hasItemBeenUpdated = true;
                    break;
                } else if (itemInList.getQuantityValue() != itemToEdit.getQuantityValue()) {
                    ItemQuantityPair updatedItem = new ItemQuantityPair(itemInList.getItem(), itemToEdit.getQuantity());
                    orderedItems.set(i, updatedItem);
                    hasItemBeenUpdated = true;
                    break;
                }
            }

            if (!hasItemBeenUpdated && itemToEdit.getQuantityValue() != 0) {
                orderedItems.add(itemToEdit);
            }

            this.itemList = orderedItems;
        }

        public void setUnlinkedItemToEdit(Pair<String, Integer> itemUnlinkedToInventory) {
            this.itemUnlinkedToInventory = itemUnlinkedToInventory;
        }

        public Optional<Pair<String, Integer>> getUnlinkedItemToEdit() {
            return Optional.ofNullable(itemUnlinkedToInventory);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditOrderDescriptor)) {
                return false;
            }

            // state check
            EditOrderDescriptor e = (EditOrderDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getItemList().equals(e.getItemList());
        }
    }
}
