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
import tracko.model.items.Description;
import tracko.model.items.Item;
import tracko.model.items.ItemName;
import tracko.model.items.Quantity;
import tracko.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
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
            + CliSyntax.PREFIX_TAG + "TAG...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_ITEM + "Paper "
            + CliSyntax.PREFIX_QUANTITY + "1000 "
            + CliSyntax.PREFIX_DESCRIPTION + "White printing paper "
            + CliSyntax.PREFIX_TAG + "Limited "
            + CliSyntax.PREFIX_TAG + "New";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited Item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the inventory list.";

    private final Index index;
    private final EditItemDescriptor editItemDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editItemDescriptor details to edit the person with
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
        ObservableList<Item> lastShownList = model.getInventoryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToEdit = lastShownList.get(index.getZeroBased());
        Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);

        if (!itemToEdit.isSameItem(editedItem) && model.hasItem(editedItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.setItem(itemToEdit, editedItem);
        model.updateFilteredItemList(Model.PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedItem));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Item createEditedItem(Item itemToEdit, EditItemDescriptor editItemDescriptor) {
        assert itemToEdit != null;

        ItemName updatedItemName = editItemDescriptor.getItemName().orElse(itemToEdit.getItemName());
        Quantity updatedQuantity = editItemDescriptor.getQuantity().orElse(itemToEdit.getQuantity());
        Description updatedDescription = editItemDescriptor.getDescription().orElse(itemToEdit.getDescription());
        Set<Tag> updatedTags = editItemDescriptor.getTags().orElse(itemToEdit.getTags());

        return new Item(updatedItemName, updatedDescription, updatedQuantity, updatedTags);
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
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditItemDescriptor {
        private ItemName itemName;
        private Quantity quantity;
        private Description description;
        private Set<Tag> tags;

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
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(itemName, quantity, description, tags);
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
                    && getTags().equals(e.getTags());
        }
    }
}
