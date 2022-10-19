package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.*;

import tracko.commons.core.index.Index;
import tracko.commons.util.CollectionUtil;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.order.*;
import tracko.model.tag.Tag;

/**
 * Edits the details of an existing Order in the address book.
 */
public class EditOrderCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing name, phone, email, address values will "
            + "be overwritten by the input values. An item tag ("
            + CliSyntax.PREFIX_ITEM + ") needs to be accompanied by a "
            + "quantity tag (" + CliSyntax.PREFIX_QUANTITY + ")."
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
            + "[" + CliSyntax.PREFIX_ADDRESS + "ADDRESS] "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PHONE + "91234567 "
            + CliSyntax.PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditOrderDescriptor editOrderDescriptor;

    /**
     * @param index of the Order in the filtered Order list to edit
     * @param editOrderDescriptor details to edit the Order with
     */
    public EditOrderCommand(Index index, EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new EditOrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // requireNonNull(model);
        // List<Order> lastShownList = model.getFilteredOrderList();
        //
        // if (index.getZeroBased() >= lastShownList.size()) {
        //    throw new CommandException(Messages.MESSAGE_INVALID_Order_DISPLAYED_INDEX);
        // }
        //
        // Order OrderToEdit = lastShownList.get(index.getZeroBased());
        // Order editedOrder = createEditedOrder(OrderToEdit, EditOrderDescriptor);
        //
        // if (!OrderToEdit.isSameOrder(editedOrder) && model.hasOrder(editedOrder)) {
        //    throw new CommandException(MESSAGE_DUPLICATE_Order);
        // }
        //
        // model.setOrder(OrderToEdit, editedOrder);
        // model.updateFilteredOrderList(PREDICATE_SHOW_ALL_OrderS);
        // return new CommandResult(String.format(MESSAGE_EDIT_Order_SUCCESS, editedOrder));
        // TODO: implement edit order functionality
        return null;
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code OrderToEdit}
     * edited with {@code EditOrderDescriptor}.
     */
    private static Order createEditedOrder(Order orderToEdit, EditOrderDescriptor editOrderDescriptor) {
        assert orderToEdit != null;

        Name updatedName = editOrderDescriptor.getName().orElse(orderToEdit.getName());
        Phone updatedPhone = editOrderDescriptor.getPhone().orElse(orderToEdit.getPhone());
        Email updatedEmail = editOrderDescriptor.getEmail().orElse(orderToEdit.getEmail());
        Address updatedAddress = editOrderDescriptor.getAddress().orElse(orderToEdit.getAddress());
        List<ItemQuantityPair> updatedItemList = editOrderDescriptor.getItemList().orElse(orderToEdit.getItemList());
        boolean isPaid = orderToEdit.getPaidStatus();
        boolean isDelivered = orderToEdit.getDeliveryStatus();

        return new Order(updatedName, updatedPhone, updatedEmail, updatedAddress, 
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
     * Stores the details to edit the Order with. Each non-empty field value will replace the
     * corresponding field value of the Order.
     */
    public static class EditOrderDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private List<ItemQuantityPair> itemList;

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
        }



        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, itemList);
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
