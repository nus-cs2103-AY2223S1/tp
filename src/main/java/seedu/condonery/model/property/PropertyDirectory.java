package seedu.condonery.model.property;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.condonery.model.property.exceptions.UniquePropertyNotFoundException;

/**
 * Wraps all data at the Condonery level
 * Duplicates are not allowed (by .isSameProperty comparison)
 */
public class PropertyDirectory implements ReadOnlyPropertyDirectory {

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

    public PropertyDirectory() {}

    /**
     * Creates an PropertyDirectory using the Properties in the {@code toBeCopied}
     */
    public PropertyDirectory(ReadOnlyPropertyDirectory toBeCopied, Path imageDirectoryPath) {
        this();
        resetData(toBeCopied);
        for (Property property : this.properties) {
            property.setImageDirectoryPath(imageDirectoryPath);
        }
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
     * Resets the existing data of this {@code PropertyDirectory} with {@code newData}.
     */
    public void resetData(ReadOnlyPropertyDirectory newData) {
        requireNonNull(newData);

        setProperties(newData.getPropertyList());
    }

    //// property-level operations

    /**
     * Returns true if a property with the same identity as {@code property} exists in the address book.
     */
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return properties.contains(property);
    }

    /**
     * Adds a property to the address book.
     * The property must not already exist in the address book.
     */
    public void addProperty(Property p) {
        properties.add(p);
    }

    /**
     * Replaces the given property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the address book.
     * The property identity of {@code editedProperty} must not be the same
     * as another existing property in the address book.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireNonNull(editedProperty);

        properties.setProperty(target, editedProperty);
    }

    /**
     * Removes {@code key} from this {@code PropertyDirectory}.
     * {@code key} must exist in the address book.
     */
    public void removeProperty(Property key) {
        properties.remove(key);
    }

    //// util methods
    /**
     * Returns true if a property whos name contains the given String exists in the property directory.
     */
    public boolean hasPropertyName(String substring) {
        return properties.hasPropertyName(substring);
    }

    /**
     * Returns true if only one unique property whos name contains the given String exists in the property directory.
     */
    public boolean hasUniquePropertyName(String substring) {
        return properties.hasUniquePropertyName(substring);
    }

    /**
     * Returns a unique property whos name contains the given string.
     *
     * @throws UniquePropertyNotFoundException if the substring does not match to a unique
     *                                 property.
     */
    public Property getUniquePropertyByName(String substring) throws UniquePropertyNotFoundException {
        return properties.getUniquePropertyByName(substring);
    }

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
                || (other instanceof PropertyDirectory // instanceof handles nulls
                && properties.equals(((PropertyDirectory) other).properties));
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}
