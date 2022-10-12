package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.property.Property;
import seedu.address.model.property.UniquePropertyList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameProperty comparison)
 */
public class ProportyBook implements ReadOnlyPropertyBook {

    private final UniquePropertyList properties;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        properties = new UniquePropertyList();
    }

    public ProportyBook() {}

    /**
     * Creates a ProportyBook using the Properties in the {@code propertyModel}
     */
    public ProportyBook(ReadOnlyPropertyBook propertyModel) {
        this();
        resetData(propertyModel);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the property list with {@code properties}.
     * {@code properties} must not contain duplicate properties.
     */
    public void setProperties(List<Property> properties) {
        this.properties.setProperties(properties);
    }

    /**
     * Resets the existing data of this {@code ProportyBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPropertyBook newData) {
        requireNonNull(newData);

        setProperties(newData.getPropertyList());
    }

    //// property-level operations

    /**
     * Returns true if a property with the same identity as {@code property} exists in the property model.
     */
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return properties.contains(property);
    }

    /**
     * Adds a property to the property model.
     * The property must not already exist in the property model.
     */
    public void addProperty(Property p) {
        properties.add(p);
    }

    /**
     * Replaces the given property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the property model.
     * The property identity of {@code editedProperty} must not be the same as another existing property in the
     * property model.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireNonNull(editedProperty);

        properties.setProperty(target, editedProperty);
    }

    /**
     * Removes {@code key} from this {@code ProportyBook}.
     * {@code key} must exist in the property model.
     */
    public void removeProperty(Property key) {
        properties.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return properties.asUnmodifiableObservableList().size() + " properties";
        // TODO: refine later
    }

    @Override
    public ObservableList<Property> getPropertyList() {
        return properties.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProportyBook // instanceof handles nulls
                && properties.equals(((ProportyBook) other).properties));
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}
