package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.pet.exceptions.DuplicatePetException;
import seedu.address.model.pet.exceptions.PetNotFoundException;

/**
 * A list of pets that enforces uniqueness between its elements and does not allow nulls.
 * A pet is considered unique by comparing using {@code Pet#isSamePet(Pet)}. As such, adding and updating of
 * pets uses Pet#isSamePet(Pet) for equality so as to ensure that the pet being added or updated is
 * unique in terms of identity in the UniquePetList. However, the removal of a pet uses Pet#equals(Object) so
 * as to ensure that the pet with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Pet#isSamePet(Pet)
 */
public class UniquePetList implements Iterable<Pet> {

    private final ObservableList<Pet> internalList = FXCollections.observableArrayList();
    private final ObservableList<Pet> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent pet as the given argument.
     */
    public boolean contains(Pet toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePet);
    }

    /**
     * Adds a pet to the list.
     * The pet must not already exist in the list.
     */
    public void add(Pet toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePetException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the pet {@code target} in the list with {@code editedPet}.
     * {@code target} must exist in the list.
     * The pet identity of {@code editedPet} must not be the same as another existing pet in the list.
     */
    public void setPet(Pet target, Pet editedPet) {
        requireAllNonNull(target, editedPet);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PetNotFoundException();
        }

        if (!target.isSamePet(editedPet) && contains(editedPet)) {
            throw new DuplicatePetException();
        }

        internalList.set(index, editedPet);
    }

    /**
     * Removes the equivalent pet from the list.
     * The pet must exist in the list.
     */
    public void remove(Pet toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PetNotFoundException();
        }
    }

    public void setPets(UniquePetList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code pets}.
     * {@code pets} must not contain duplicate pets.
     */
    public void setPets(List<Pet> pets) {
        requireAllNonNull(pets);
        if (!petsAreUnique(pets)) {
            throw new DuplicatePetException();
        }

        internalList.setAll(pets);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Pet> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Pet> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePetList // instanceof handles nulls
                && internalList.equals(((UniquePetList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true iff {@code pets} contains only unique pets.
     */
    private boolean petsAreUnique(List<Pet> pets) {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = i + 1; j < pets.size(); j++) {
                if (pets.get(i).isSamePet(pets.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
