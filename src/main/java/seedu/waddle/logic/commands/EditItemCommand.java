package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Optional;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.util.CollectionUtil;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Edits the details of an existing item.
 */
public class EditItemCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the item identified "
            + "by the index number used in the displayed item list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Visit the Eiffel Tower ";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists.";

    private final Index index;
    private final EditItemDescriptor editItemDescriptor;

    /**
     * @param index                   of the item in the unique item list to edit
     * @param editItemDescriptor details to edit the item with
     */
    public EditItemCommand(Index index, EditItemDescriptor editItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editItemDescriptor);

        this.index = index;
        this.editItemDescriptor = new EditItemDescriptor(editItemDescriptor);
    }

    /**
     * Creates and returns an {@code Item} with the details of {@code itemToEdit}
     * edited with {@code editItemDescriptor}.
     */
    private static Item createEditedItem(Item itemToEdit,
        EditItemDescriptor editItemDescriptor) {
        assert itemToEdit != null;

        String updatedDescription = editItemDescriptor.getDescription().orElse(itemToEdit.getDescription());

        return new Item(updatedDescription);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StageManager stageManager = StageManager.getInstance();
        Itinerary itinerary = stageManager.getSelectedItinerary();

        if (index.getZeroBased() >= itinerary.getItemSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToEdit = itinerary.getItemList().get(index.getZeroBased());
        Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);

        if (!itemToEdit.isSameItem(editedItem) && itinerary.hasItem(editedItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        itinerary.setItem(itemToEdit, editedItem);
        return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedItem));
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
        private String description;

        public EditItemDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description);
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(String description) {
            this.description = description;
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

            return getDescription().equals(e.getDescription());
        }
    }
}
