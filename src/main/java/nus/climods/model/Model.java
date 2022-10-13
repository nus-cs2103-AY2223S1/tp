package nus.climods.model;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UserModule;


/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<UserModule> PREDICATE_SHOW_ALL_MODULES = unused -> true;

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

    /* USER MODULE */

    /**
     * Returns true if a module with the same identity as {@code module} exists in the address book.
     */
    boolean hasUserModule(UserModule module);

    /**
     * Returns true if a module with the same identity as {@code module} exists in the filtered user module list
     */
    boolean filteredListhasUserModule(UserModule module);


    /**
     * Deletes the given module. The module must exist in the address book.
     */
    void deleteUserModule(UserModule target);

    /**
     * Adds the given module. {@code module} must not already exist in the address book.
     */
    void addUserModule(UserModule module);

    /**
     * Returns an unmodifiable view of the filtered module list
     */
    ObservableList<UserModule> getFilteredUserModuleList();
    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredUserModuleList(Predicate<UserModule> predicate);
    void updateFilteredModuleList(Optional<String> facultyCode, Optional<Boolean> hasUser);

    ObservableList<Module> getFilteredModuleList();

    void setFilteredModuleList(Predicate<Module> predicate);
}
