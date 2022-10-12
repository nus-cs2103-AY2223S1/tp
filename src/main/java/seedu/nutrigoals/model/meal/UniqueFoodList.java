package seedu.nutrigoals.model.meal;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.nutrigoals.model.meal.exceptions.DuplicateMealException;
import seedu.nutrigoals.model.meal.exceptions.MealNotFoundException;

/**
 * A list of food that enforces uniqueness between its elements and does not allow nulls.
 * A Food is considered unique by comparing using {@code Food#isSameFood(Food)}. As such, adding and updating of
 * Foods uses Food#isSameFood(Food) for equality so as to ensure that the Food being added or updated is
 * unique in terms of identity in the UniqueFoodList. However, the removal of a Food uses Food#equals(Object) so
 * as to ensure that the Food with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Food#isSameFood(Food)
 */
public class UniqueFoodList implements Iterable<Food> {

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
        if (contains(toAdd)) {
            throw new DuplicateMealException();
        }
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

        if (!target.isSameFood(editedFood) && contains(editedFood)) {
            throw new DuplicateMealException();
        }

        internalList.set(index, editedFood);
    }

    public void setFood(UniqueFoodList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }


    /**
     * Replaces the contents of this list with {@code Food}.
     */
    public void setFood(List<Food> foods) {
        requireAllNonNull(foods);
        if (!foodsAreUnique(foods)) {
            throw new DuplicateMealException();
        }

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
                || (other instanceof UniqueFoodList // instanceof handles nulls
                        && sortedList.equals(((UniqueFoodList) other).sortedList));
    }

    @Override
    public int hashCode() {
        return sortedList.hashCode();
    }

    /**
     * Returns true if {@code food} contains only unique food.
     */
    private boolean foodsAreUnique(List<Food> foods) {
        for (int i = 0; i < foods.size() - 1; i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).isSameFood(foods.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
