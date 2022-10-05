package seedu.condonery.model;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.model.property.Property;

/**
 * Represents the in-memory model of the Condonery data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PropertyDirectory propertyDirectory;
    private final UserPrefs userPrefs;
    private final FilteredList<Property> filteredProperties;

    /**
     * Initializes a ModelManager with the given propertyDirectory and userPrefs.
     */
    public ModelManager(ReadOnlyPropertyDirectory propertyDirectory, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(propertyDirectory, userPrefs);

        logger.fine("Initializing with address book: " + propertyDirectory + " and user prefs " + userPrefs);

        this.propertyDirectory = new PropertyDirectory(propertyDirectory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProperties = new FilteredList<>(this.propertyDirectory.getPropertyList());
    }

    public ModelManager() {
        this(new PropertyDirectory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPropertyDirectoryFilePath() {
        return userPrefs.getPropertyDirectoryFilePath();
    }

    @Override
    public void setPropertyDirectoryFilePath(Path propertyDirectoryFilePath) {
        requireNonNull(propertyDirectoryFilePath);
        userPrefs.setPropertyDirectoryFilePath(propertyDirectoryFilePath);
    }

    //=========== PropertyDirectory ================================================================================

    @Override
    public void setPropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory) {
        this.propertyDirectory.resetData(propertyDirectory);
    }

    @Override
    public ReadOnlyPropertyDirectory getPropertyDirectory() {
        return propertyDirectory;
    }

    //=========== ClientDirectory ================================================================================

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyDirectory.hasProperty(property);
    }

    @Override
    public void deleteProperty(Property target) {
        propertyDirectory.removeProperty(target);
    }

    @Override
    public void addProperty(Property property) {
        propertyDirectory.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);

        propertyDirectory.setProperty(target, editedProperty);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code versionedPropertyDirectory}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return propertyDirectory.equals(other.propertyDirectory)
                && userPrefs.equals(other.userPrefs)
                && filteredProperties.equals(other.filteredProperties);
    }

}
