package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of mods that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Mod#isSameMod(Mod)}. As such, adding and updating of
 * persons uses Mod#isSameMod(Mod) for equality so as to ensure that the mod being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Mod#isSameMod(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Mod#isSameMod(Mod) (Mod)
 */
public class UniqueModList implements Iterable<Mod> {

    private final ObservableList<Mod> internalList = FXCollections.observableArrayList();
    private final ObservableList<Mod> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent mod as the given argument.
     */
    public boolean contains(Mod toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMod);
    }

    /**
     * Adds a mod to the list.
     * The mod must not already exist in the list.
     */
    public void add(Mod toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the mod {@code target} in the list with {@code editedMod}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedMod} must not be the same as another existing mod in the list.
     */
    public void setMod(Mod target, Mod editedMod) {
        requireAllNonNull(target, editedMod);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameMod(editedMod) && contains(editedMod)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedMod);
    }

    /**
     * Removes the equivalent mod from the list.
     * The person must exist in the list.
     */
    public void remove(Mod toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setMods(UniqueModList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code mods}.
     * {@code mods} must not contain duplicate mods.
     */
    public void setMods(List<Mod> mods) {
        requireAllNonNull(mods);
        if (!modsAreUnique(mods)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(mods);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Mod> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Mod> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModList // instanceof handles nulls
                && internalList.equals(((UniqueModList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code mods} contains only unique mods.
     */
    private boolean modsAreUnique(List<Mod> mods) {
        for (int i = 0; i < mods.size() - 1; i++) {
            for (int j = i + 1; j < mods.size(); j++) {
                if (mods.get(i).isSameMod(mods.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
