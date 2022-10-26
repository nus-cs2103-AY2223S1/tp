package nus.climods.model;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.SemestersEnum;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.model.module.UserModule;

/**
 * The API of the Model component.
 */
public interface Model {
    //=========== UserPrefs ==================================================================================

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    //=========== GuiSettings ==================================================================================

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //=========== Module ==================================================================================

    ReadOnlyModuleList getModuleList();

    /**
     * Returns Optional Module corresponding to the supplied module code
     */
    Optional<Module> getListModule(String moduleCode);

    boolean isModuleOffered(String moduleCode);

    boolean isModuleOfferedInSemester(String moduleCode, SemestersEnum semester);

    Optional<Module> getModule(String moduleCode);

    ObservableList<Module> getFilteredModuleList();

    void setFilteredModuleList(Predicate<Module> predicate);

    void setFilteredModuleList(Predicate<Module> predicate, Comparator<Module> comparator);

    /**
     * Sets module (currently for view) in full module list to the module specified by moduleCode
     */
    void setModuleInFocus(Module module) throws ApiException;

    /**
     * Resets active module to be inactive
     */
    void clearModuleInFocus();

    //=========== UserModule ==================================================================================

    UniqueUserModuleList getUserModuleList();

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

    boolean filteredListHasUserModule(UserModule module);

    /**
     * Returns true if a module with the same identity as {@code module} exists in the address book.
     */
    boolean hasUserModule(UserModule module);

    /**
     * Adds the given module. {@code module} must not already exist in the address book.
     */
    void addUserModule(UserModule module);

    /**
     * Deletes the given module code
     */
    void deleteUserModule(String target);
}
