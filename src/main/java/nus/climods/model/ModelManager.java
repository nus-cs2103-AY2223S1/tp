package nus.climods.model;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.commons.core.LogsCenter;
import nus.climods.model.module.CodeContainsKeywordsPredicate;
import nus.climods.model.module.Module;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.ReadOnlyModuleList;

/**
 * Represents the in-memory model of module list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final ModuleList moduleList;
    private final FilteredList<Module> filteredModuleList;

    /**
     * Initializes a ModelManager with the given moduleList and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(moduleList, userPrefs);

        logger.fine("Initializing with module list: " + moduleList + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.moduleList = new ModuleList(moduleList);
        this.filteredModuleList = new FilteredList<>(moduleList.getModules());
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    /**
     * Filter the list by faculty Code
     *
     * @return
     */
    public void updateFilteredModuleList(Optional<String> facultyCode, Optional<Boolean> hasUser) {
        // TODO: Implement filtering for saved modules
        CodeContainsKeywordsPredicate predicate = new CodeContainsKeywordsPredicate(facultyCode);
        filteredModuleList.setPredicate(predicate);
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
    public ReadOnlyModuleList getModuleList() {
        return moduleList;
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModuleList;


    }

    public void setFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        this.filteredModuleList.setPredicate(predicate);
    }
}
