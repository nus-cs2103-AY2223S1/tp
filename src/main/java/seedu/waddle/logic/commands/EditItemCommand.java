package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_ITEM_DURATION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalTime;
import java.util.Optional;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.commons.util.CollectionUtil;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Day;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Description;
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
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]"
            + "[" + PREFIX_PRIORITY + "PRIORITY]"
            + "[" + PREFIX_COST + "COST]"
            + "[" + PREFIX_ITEM_DURATION + "DURATION]"
            + "[" + PREFIX_START_TIME + "START TIME]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Visit the Eiffel Tower ";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists.";

    public static final String MESSAGE_EDIT_START_TIME = "Editing start time of unscheduled item is not allowed";

    private final MultiIndex multiIndex;
    private final EditItemDescriptor editItemDescriptor;

    /**
     * @param multiIndex of the item in the unique item list to edit
     * @param editItemDescriptor details to edit the item with
     */
    public EditItemCommand(MultiIndex multiIndex, EditItemDescriptor editItemDescriptor) {
        requireNonNull(multiIndex);
        requireNonNull(editItemDescriptor);

        this.multiIndex = multiIndex;
        this.editItemDescriptor = new EditItemDescriptor(editItemDescriptor);
    }

    /**
     * Creates and returns an {@code Item} with the details of {@code itemToEdit}
     * edited with {@code editItemDescriptor}.
     */
    private static Item createEditedItem(Item itemToEdit,
        EditItemDescriptor editItemDescriptor) {
        assert itemToEdit != null;

        Description updatedDescription = editItemDescriptor.getDescription().orElse(itemToEdit.getDescription());
        Priority updatedPriority = editItemDescriptor.getPriority().orElse(itemToEdit.getPriority());
        Cost updatedCost = editItemDescriptor.getCost().orElse(itemToEdit.getCost());
        Duration updatedDuration = editItemDescriptor.getDuration().orElse(itemToEdit.getDuration());

        Item editedItem = new Item(updatedDescription, updatedPriority, updatedCost, updatedDuration);
        editedItem.setStartTime(editItemDescriptor.getStartTime().orElse(itemToEdit.getStartTime()));

        return editedItem;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StageManager stageManager = StageManager.getInstance();
        Itinerary itinerary = stageManager.getSelectedItinerary();

        if (multiIndex.getDayIndex() == null) {
            if (multiIndex.getTaskIndex().getZeroBased() >= itinerary.getUnscheduledSize()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
            }
            Item itemToEdit = itinerary.getItem(multiIndex);
            Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);
            if (!itemToEdit.isSameItem(editedItem) && itinerary.hasItem(editedItem)) {
                throw new CommandException(MESSAGE_DUPLICATE_ITEM);
            }
            itinerary.setItem(itemToEdit, editedItem, multiIndex);

            return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedItem));
        } else {
            if (multiIndex.getDayIndex().getZeroBased() >= itinerary.getDuration().getValue()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
            }
            Day day = itinerary.getDays().get(multiIndex.getDayIndex().getZeroBased());
            if (multiIndex.getTaskIndex().getZeroBased() >= day.getItemSize()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
            }
            Item itemToEdit = itinerary.getItem(multiIndex);
            Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);
            //if new cos causes over budget throw command exception
            if (itinerary.getBudget().calculateLeftOverBudget()
                    + itemToEdit.getCost().getValue() - editedItem.getCost().getValue() < 0) {
                throw new CommandException(Messages.MESSAGE_OVER_BUDGET);
            }

            if (!itemToEdit.isSameItem(editedItem) && day.hasItem(editedItem)) {
                throw new CommandException(MESSAGE_DUPLICATE_ITEM);
            }
            itinerary.setItem(itemToEdit, editedItem, multiIndex);

            return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedItem));
        }
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
        return multiIndex.equals(e.multiIndex)
                && editItemDescriptor.equals(e.editItemDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditItemDescriptor {
        private Description description;
        private Priority priority;
        private Cost cost;
        private Duration duration;
        private LocalTime startTime;

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
            return CollectionUtil.isAnyNonNull(description, priority, cost, duration, startTime);
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
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

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setStartTime(LocalTime startTime) {
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
