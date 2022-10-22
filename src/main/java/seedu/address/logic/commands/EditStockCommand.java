package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTSTOCK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SUPPLY_ITEMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Edits the stock level of a supply item.
 */
public class EditStockCommand extends Command {
    public static final String COMMAND_WORD = "editStock";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the stock of an item which is identified by"
            + "index of inventory. "
            + "Parameters: "
            + "INDEX (must be a positive integer)"
            + PREFIX_CURRENTSTOCK + "CurrentStock: "
            + "Example: " + COMMAND_WORD + " 1 c/5";

    public static final String MESSAGE_SUCCESS = "Stock updated: %1$s";
    public static final String MESSAGE_NOT_EDITED = "Item's stock is not updated.";
    private final EditStockDescriptor editStockDescriptor;
    private final Index index;

    /**
     * @param index of the supply item in the filtered inventory to edit.
     * @param editStockDescriptor details to edit the supply item with.
     */
    public EditStockCommand(Index index, EditStockDescriptor editStockDescriptor) {
        requireNonNull(editStockDescriptor);
        requireNonNull(index);

        this.index = index;
        this.editStockDescriptor = editStockDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<SupplyItem> lastShownSupplyItemList = model.getFilteredSupplyItemList();

        if (index.getZeroBased() >= lastShownSupplyItemList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SUPPLYITEM_DISPLAYED_INDEX);
        }

        SupplyItem supplyItemToUpdate = lastShownSupplyItemList.get(index.getZeroBased());
        SupplyItem updatedSupplyItem = createEditedSupplyItem(supplyItemToUpdate, editStockDescriptor);

        model.setSupplyItem(updatedSupplyItem, index);
        model.updateFilteredSupplyItemList(PREDICATE_SHOW_ALL_SUPPLY_ITEMS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedSupplyItem));
    }

    /**
     * Creates and returns a {@code SupplyItem}
     * with the details of only the edited {@code currentStock} of {@code supplyItemToEdit}
     * edited with {@code editStockDescriptor}.
     */
    private static SupplyItem createEditedSupplyItem(SupplyItem supplyItemToEdit,
            EditStockDescriptor editStockDescriptor) {
        assert supplyItemToEdit != null;

        String name = supplyItemToEdit.getName();
        int currentStock = editStockDescriptor.getCurrentStock().orElse(supplyItemToEdit.getCurrentStock());
        int minStock = supplyItemToEdit.getMinStock();
        Person supplier = supplyItemToEdit.getSupplier();
        Set<Tag> tags = supplyItemToEdit.getTags();

        return new SupplyItem(name, currentStock, minStock, supplier, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStockCommand)) {
            return false;
        }

        EditStockCommand e = (EditStockCommand) other;

        return index.equals(e.index)
                && editStockDescriptor.equals(e.editStockDescriptor);
    }

    /**
     * Stores the details to edit the supply item with. Each non-empty field value will replace the
     * corresponding field value of the supply item.
     */
    public static class EditStockDescriptor {
        // Identity fields
        private String name;

        // Data fields
        private int currentStock;
        private int minStock;
        private Person supplier;
        private Set<Tag> tags;

        public EditStockDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStockDescriptor(EditStockDescriptor toCopy) {
            setItemName(toCopy.name);
            setMinStock(toCopy.minStock);
            setCurrentStock(toCopy.currentStock);
            setPerson(toCopy.supplier);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, currentStock, minStock, supplier, tags);
        }

        private void setItemName(String name) {
            this.name = name;
        }

        public Optional<String> getItemName() {
            return Optional.ofNullable(name);
        }

        private void setPerson(Person person) {
            this.supplier = person;
        }

        public Optional<Person> getSupplierPerson() {
            return Optional.ofNullable(supplier);
        }

        public void setCurrentStock(int currentStock) {
            this.currentStock = currentStock;
        }

        public Optional<Integer> getCurrentStock() {
            return Optional.ofNullable(currentStock);
        }

        private void setMinStock(int minStock) {
            this.minStock = minStock;
        }

        public Optional<Integer> getMinStock() {
            return Optional.ofNullable(minStock);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        private void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStockCommand.EditStockDescriptor)) {
                return false;
            }

            // state check
            EditStockDescriptor e = (EditStockDescriptor) other;

            return getItemName().equals(e.getItemName())
                    && getMinStock().equals(e.getMinStock())
                    && getCurrentStock().equals(e.getCurrentStock())
                    && getSupplierPerson().equals(e.getSupplierPerson())
                    && getTags().equals(e.getTags());
        }
    }
}
