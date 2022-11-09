package seedu.nutrigoals.model.meal;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.nutrigoals.model.meal.exceptions.MealNotFoundException;

/**
 * Represents the user's list of {@code Food}.
 * Supports a minimal set of list operations.
 */
public class FoodList implements Iterable<Food> {

    private final ObservableList<Food> internalList = FXCollections.observableArrayList();
    private final SortedList<Food> sortedList = new SortedList<>(internalList, new FoodComparator());
    private final ObservableList<Food> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(sortedList);

    /**
     * Returns true if the list contains an equivalent food as the given argument.
     */
    public boolean contains(Food toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFood);
    }

    /**
     * Adds a Food to the list.
     */
    public void add(Food toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent Food from the list.
     * The Food must exist in the list.
     */
    public void remove(Food toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MealNotFoundException();
        }
    }

    /**
     * Replaces the food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the list.
     */
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MealNotFoundException();
        }


        internalList.set(index, editedFood);
    }

    public void setFood(FoodList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }


    /**
     * Replaces the contents of this list with {@code Food}.
     */
    public void setFood(List<Food> foods) {
        requireAllNonNull(foods);

        internalList.setAll(foods);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Food> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Food> iterator() {
        return sortedList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FoodList // instanceof handles nulls
                        && sortedList.equals(((FoodList) other).sortedList));
    }

    @Override
    public int hashCode() {
        return sortedList.hashCode();
    }
}
