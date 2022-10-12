package nus.climods.model;

import javafx.collections.transformation.FilteredList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;

import java.util.Optional;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ReadOnlyModuleList getModuleList();

    void updateFilteredModuleList(Optional<String> facultyCode, Optional<Boolean> hasUser);

    FilteredList<Module> getFilteredModuleList();
}
