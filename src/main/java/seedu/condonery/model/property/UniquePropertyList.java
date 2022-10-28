package seedu.condonery.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.condonery.model.property.exceptions.DuplicatePropertyException;
import seedu.condonery.model.property.exceptions.PropertyNotFoundException;
import seedu.condonery.model.property.exceptions.UniquePropertyNotFoundException;

/**
 * A list of properties that enforces uniqueness between its elements and does not allow nulls.
 * A property is considered unique by comparing using {@code Property#isSameProperty(Property)}.
 * As such, adding and updating of properties uses Property#isSameProperty(Property) for equality
 * so as to ensure that the property being added or updated is unique in terms of identity in the
 * UniquePropertyList. However, the removal of a property uses Property#equals(Object) so
 * as to ensure that the property with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Property#isSameProperty(Property)
 */
public class UniquePropertyList implements Iterable<Property> {

    private final ObservableList<Property> internalList = FXCollections.observableArrayList();
    private final ObservableList<Property> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent property as the given argument.
     */
    public boolean contains(Property toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameProperty);
    }

    /**
     * Adds a property to the list.
     * The property must not already exist in the list.
     */
    public void add(Property toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePropertyException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the list.
     * The property identity of {@code editedProperty} must not be the same as another existing property in the list.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PropertyNotFoundException();
        }

        if (!target.isSameProperty(editedProperty) && contains(editedProperty)) {
            throw new DuplicatePropertyException();
        }

        internalList.set(index, editedProperty);
    }

    /**
     * Removes the equivalent property from the list.
     * The property must exist in the list.
     */
    public void remove(Property toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PropertyNotFoundException();
        }
    }

    public void setProperties(UniquePropertyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code properties}.
     * {@code properties} must not contain duplicate properties.
     */
    public void setProperties(List<Property> properties) {
        requireAllNonNull(properties);
        if (!propertiesAreUnique(properties)) {
            throw new DuplicatePropertyException();
        }

        internalList.setAll(properties);
    }

    /**
     * Returns true if a property whos name contains the given String exists in the property directory.
     */
    public boolean hasPropertyName(String substring) {
        for (Property property : internalList) {
            if (property.getName().toString().contains(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if only one unique property whos name contains the given String exists in the property directory.
     */
    public boolean hasUniquePropertyName(String substring) {
        int containCount = 0;
        for (Property property : internalList) {
            if (property.getName().toString().contains(substring)) {
                containCount += 1;
            }
        }
        if (containCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a unique property whos name contains the given string.
     *
     * @throws UniquePropertyNotFoundException if the substring does not match to a unique
     *                                 property.
     */
    public Property getUniquePropertyByName(String substring) throws UniquePropertyNotFoundException {
        Property uniqueProperty = null;
        for (Property property : internalList) {
            if (property.getName().containsSubstring(substring)) {
                if (uniqueProperty == null) {
                    uniqueProperty = property;
                } else {
                    throw new UniquePropertyNotFoundException();
                }
            }
        }
        if (uniqueProperty == null) {
            throw new UniquePropertyNotFoundException();
        } else {
            return uniqueProperty;
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Property> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Property> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePropertyList // instanceof handles nulls
                        && internalList.equals(((UniquePropertyList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code properties} contains only unique properties.
     */
    private boolean propertiesAreUnique(List<Property> properties) {
        for (int i = 0; i < properties.size() - 1; i++) {
            for (int j = i + 1; j < properties.size(); j++) {
                if (properties.get(i).isSameProperty(properties.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
