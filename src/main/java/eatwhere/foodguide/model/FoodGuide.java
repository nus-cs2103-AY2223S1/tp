package eatwhere.foodguide.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.UniqueEateryList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the food-guide level
 * Duplicates are not allowed (by .isSameEatery comparison)
 */
public class FoodGuide implements ReadOnlyFoodGuide {

    private final UniqueEateryList eateries;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        eateries = new UniqueEateryList();
    }

    public FoodGuide() {}

    /**
     * Creates an FoodGuide using the Persons in the {@code toBeCopied}
     */
    public FoodGuide(ReadOnlyFoodGuide toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the eatery list with {@code eateries}.
     * {@code eateries} must not contain duplicate eateries.
     */
    public void setEateries(List<Eatery> eateries) {
        this.eateries.setEateries(eateries);
    }

    /**
     * Resets the existing data of this {@code FoodGuide} with {@code newData}.
     */
    public void resetData(ReadOnlyFoodGuide newData) {
        requireNonNull(newData);

        setEateries(newData.getEateryList());
    }

    //// eatery-level operations

    /**
     * Returns true if an eatery with the same identity as {@code eatery} exists in the food guide.
     */
    public boolean hasEatery(Eatery eatery) {
        requireNonNull(eatery);
        return eateries.contains(eatery);
    }

    /**
     * Adds an eatery to the food guide.
     * The eatery must not already exist in the food guide.
     */
    public void addEatery(Eatery p) {
        eateries.add(p);
    }

    /**
     * Replaces the given eatery {@code target} in the list with {@code editedEatery}.
     * {@code target} must exist in the food guide.
     * The eatery identity of {@code editedEatery} must not be the same as another existing eatery in the food guide.
     */
    public void setEatery(Eatery target, Eatery editedEatery) {
        requireNonNull(editedEatery);

        eateries.setEatery(target, editedEatery);
    }

    /**
     * Removes {@code key} from this {@code FoodGuide}.
     * {@code key} must exist in the food guide.
     */
    public void removeEatery(Eatery key) {
        eateries.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return eateries.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Eatery> getEateryList() {
        return eateries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FoodGuide // instanceof handles nulls
                && eateries.equals(((FoodGuide) other).eateries));
    }

    @Override
    public int hashCode() {
        return eateries.hashCode();
    }
}
