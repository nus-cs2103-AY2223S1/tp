package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Optional;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.util.CollectionUtil;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.item.StartTime;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Edits the details of an existing item.
 */
public class EditItemCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the item identified "
            + "by the index number used in the displayed item list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]"
            + "[" + PREFIX_PRIORITY + "PRIORITY]"
            + "[" + PREFIX_COST + "COST]"
            + "[" + PREFIX_DURATION + "DURATION]"
            + "[" + PREFIX_START_TIME + "START TIME]...\n"
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
        Priority updatedPriority = editItemDescriptor.getPriority().orElse(itemToEdit.getPriority());

        return new Item(updatedDescription, updatedPriority, itemToEdit.getCost(), itemToEdit.getDuration());
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
        private Priority priority;
        private Cost cost;
        private Duration duration;
        private StartTime startTime;

        public EditItemDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            setDescription(toCopy.description);
            setPriority(toCopy.priority);
            setCost(toCopy.cost);
            setDuration(toCopy.duration);
            setStartTime(toCopy.startTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, priority, cost, duration);
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Cost> getCost() {
            return Optional.ofNullable(cost);
        }

        public void setCost(Cost cost) {
            this.cost = cost;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<StartTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setStartTime(StartTime startTime) {
            this.startTime = startTime;
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
