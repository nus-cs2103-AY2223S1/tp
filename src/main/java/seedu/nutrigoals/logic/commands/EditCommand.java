package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;

import seedu.nutrigoals.commons.core.Messages;
import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.commons.util.CollectionUtil;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.logic.parser.Prefix;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;

/**
 * Edits the details of an existing meal in the food list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final Prefix PREFIX_CALORIE = new Prefix("c/");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the food identified "
            + "by the index number used in the displayed food list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_CALORIE + "CALORIE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Bread "
            + PREFIX_CALORIE + "150";

    public static final String MESSAGE_DUPLICATE_FOOD = "This food already exists in the nutrition tracker.";
    public static final String MESSAGE_EDIT_FOOD_SUCCESS = "Edited Food: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditFoodDescriptor editFoodDescriptor;

    /**
     * @param index of the meal in the filtered meal list to edit
     * @param editFoodDescriptor details to edit the meal with
     */
    public EditCommand(Index index, EditFoodDescriptor editFoodDescriptor) {
        requireNonNull(index);
        requireNonNull(editFoodDescriptor);

        this.index = index;
        this.editFoodDescriptor = new EditFoodDescriptor(editFoodDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEAL_DISPLAYED_INDEX);
        }

        Food foodToEdit = lastShownList.get(index.getZeroBased());
        Food editedFood = createEditedFood(foodToEdit, editFoodDescriptor);

        if (!foodToEdit.isSameFood(editedFood) && model.hasFood(editedFood)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.setFood(foodToEdit, editedFood);
        model.updateFilteredFoodList(model.getDatePredicate());
        return new CommandResult(String.format(MESSAGE_EDIT_FOOD_SUCCESS, editedFood));
    }

    /**
     * Creates and returns a {@code Food} with the details of {@code foodToEdit}
     * edited with {@code editFoodDescriptor}.
     */
    private static Food createEditedFood(Food foodToEdit, EditFoodDescriptor editFoodDescriptor) {
        assert foodToEdit != null;

        Name updatedName = editFoodDescriptor.getName().orElse(foodToEdit.getName());
        Calorie updatedCalorie = editFoodDescriptor.getCalorie().orElse(foodToEdit.getCalorie());
        Tag updatedTag = editFoodDescriptor.getTag().orElse(foodToEdit.getTag());

        return new Food(updatedName, updatedCalorie, updatedTag, foodToEdit.getDateTime());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFoodDescriptor.equals(e.editFoodDescriptor);
    }

    /**
     * Stores the details to edit the food with. Each non-empty field value will replace the
     * corresponding field value of the food.
     */
    public static class EditFoodDescriptor {
        private Name name;
        private Calorie calorie;
        private Tag tag;
        private DateTime dateTime;

        public EditFoodDescriptor() {}

        /**
         * Constructor for unit tests
         *
         * @param name    Food name
         * @param calorie Calorie content
         * @param tagName     Food tags
         */
        public EditFoodDescriptor(String name, String calorie, String tagName) {
            this.name = new Name(name);
            this.calorie = new Calorie(calorie);
            this.tag = new Tag(tagName);
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFoodDescriptor(EditFoodDescriptor toCopy) {
            setName(toCopy.name);
            setCalorie(toCopy.calorie);
            setTag(toCopy.tag);
            setDateTime(toCopy.dateTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, calorie, tag);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setCalorie(Calorie calorie) {
            this.calorie = calorie;
        }

        public Optional<Calorie> getCalorie() {
            return Optional.ofNullable(calorie);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTag(Tag tag) {
            this.tag = tag;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Tag> getTag() {
            // return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
            return Optional.ofNullable(tag);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFoodDescriptor)) {
                return false;
            }

            // state check
            EditFoodDescriptor e = (EditFoodDescriptor) other;

            return getName().equals(e.getName())
                    && getTag().equals(e.getTag())
                    && getCalorie().equals(e.getCalorie());
        }
    }
}
