package nus.climods.model;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.SemestersEnum;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.model.module.LessonTypeEnum;
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
     * Returns Optional Module corresponding to the supplied module code.
     */
    Optional<Module> getListModule(String moduleCode);

    /**
     * Returns set of unselectableLessonType.
     * @param moduleCode
     * @param semester
     * @return
     */
    Set<LessonTypeEnum> unselectableLessonType(String moduleCode, SemestersEnum semester);

    /**
     * Check if Module is offered / module code is valid.
     * @param moduleCode
     * @return boolean
     */
    boolean isModuleOffered(String moduleCode);

    /**
     * Check if Module is offered in semester
     * @param moduleCode
     * @param semester
     * @return boolean
     */
    boolean isModuleOfferedInSemester(String moduleCode, SemestersEnum semester);

    /**
     * Check if lesson type is offered in the module in the semester.
     * @param moduleCode
     * @param semester
     * @param lessonType
     * @return boolean
     */
    boolean isModuleLessonOffered(String moduleCode, SemestersEnum semester,
                                         LessonTypeEnum lessonType);

    /**
     * Check if lesson code is offered in the module in the semester based on lesson type.
     * @param moduleCode
     * @param semester
     * @param lessonType
     * @param classCode
     * @return boolean
     */
    boolean isModuleLessonClassOffered(String moduleCode, SemestersEnum semester,
                                         LessonTypeEnum lessonType, String classCode);

    /**
     * Getter method that gets Optional Module by checking String moduleCode input.
     * @param moduleCode
     * @return Optional with Module inside
     */
    Optional<Module> getModule(String moduleCode);

    /**
     * Getter method that gets FilteredModulelist
     * @return ObservableList
     */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Set new predicate for FilteredModuleList.
     * @param predicate
     */
    void setFilteredModuleList(Predicate<Module> predicate);

    /**
     * Set new predicate for FilteredModuleList with comparator.
     * @param predicate
     */
    void setFilteredModuleList(Predicate<Module> predicate, Comparator<Module> comparator);

    /**
     * Sets module (currently for view) in full module list to the module specified by moduleCode
     */
    void setModuleInFocus(Module module) throws ApiException;

    /**
     * Show modules specified in moduleCodes list. If no module codes are in the current curriculum,
     *      showModules returns false and does not show any new modules.
     * @param moduleCodes List of module codes specifying modules to show
     * @return true if at least one of the given module codes is a valid module in current curriculum
     */
    boolean showModules(List<String> moduleCodes);

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

    /**
     * returns UserModule to be found in the list if present.
     * @param toGet
     * @return
     */
    Optional<UserModule> getUserModule(String toGet);

}
