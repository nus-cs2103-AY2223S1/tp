package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.Property;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Buyer> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Property> PREDICATE_SHOW_ALL_PROPERTIES = unused -> true;

    //=========== UserPrefs ==================================================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' buyer model file path.
     */
    Path getPersonBookFilePath();

    /**
     * Sets the user prefs' buyer model file path.
     */
    void setPersonBookFilePath(Path personBookFilePath);

    /**
     * Returns the user prefs' property book file path.
     */
    Path getPropertyBookFilePath();

    /**
     * Sets the user prefs' property book file path.
     */
    void setPropertyBookFilePath(Path propertyBookFilePath);

    //=========== PersonBook ================================================================================

    /**
     * Replaces buyer model data with the data in {@code personModel}.
     */
    void setPersonBook(ReadOnlyPersonBook personBook);

    /**
     * Returns the PersonBook
     */
    ReadOnlyPersonBook getPersonBook();

    /**
     * Returns true if a buyer with the same identity as {@code buyer} exists in the address book.
     */
    boolean hasPerson(Buyer buyer);

    /**
     * Deletes the given buyer.
     * The buyer must exist in the address book.
     */
    void deletePerson(Buyer target);

    /**
     * Adds the given buyer.
     * {@code buyer} must not already exist in the address book.
     */
    void addPerson(Buyer buyer);

    /**
     * Replaces the given buyer {@code target} with {@code editedBuyer}.
     * {@code target} must exist in the address book.
     * The buyer identity of {@code editedBuyer} must not be the same as another existing buyer in the address book.
     */
    void setPerson(Buyer target, Buyer editedBuyer);

    //=========== Filtered Buyer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered buyer list
     */
    ObservableList<Buyer> getFilteredPersonList();

    /**
     * Updates the filter of the filtered buyer list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Buyer> predicate);

    //=========== PropertyBook ================================================================================

    /**
     * Replaces property book data with the data in {@code propertyBook}.
     */
    void setPropertyBook(ReadOnlyPropertyBook propertyBook);

    /**
     * Returns the PropertyBook
     */
    ReadOnlyPropertyBook getPropertyBook();

    /**
     * Returns true if a property with the same identity as {@code property} exists in the property book.
     */
    boolean hasProperty(Property property);

    /**
     * Deletes the given property.
     * The property must exist in the property book.
     */
    void deleteProperty(Property target);

    /**
     * Adds the given property.
     * {@code property} must not already exist in the property book.
     */
    void addProperty(Property property);

    /**
     * Replaces the given property {@code target} with {@code editedProperty}.
     * {@code target} must exist in the property book.
     * The property identity of {@code editedProperty} must not be the same as another existing property in the address
     * book.
     */
    void setProperty(Property target, Property editedProperty);

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered property list
     */
    ObservableList<Property> getFilteredPropertyList();

    /**
     * Updates the filter of the filtered property list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPropertyList(Predicate<Property> predicate);

}
