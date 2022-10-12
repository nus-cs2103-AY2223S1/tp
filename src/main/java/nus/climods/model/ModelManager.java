package nus.climods.model;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.commons.core.LogsCenter;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.model.module.UserModule;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModuleList moduleList;
    private final UniqueUserModuleList uniqueUserModuleList;

    private final FilteredList<UserModule> filteredUserModules;
    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given moduleList and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, UniqueUserModuleList uniqueUserModuleList,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(moduleList, userPrefs);

        logger.fine("Initializing with address book: " + moduleList + " and user prefs " + userPrefs);

        this.moduleList = new ModuleList(moduleList);
        this.userPrefs = new UserPrefs(userPrefs);
        this.uniqueUserModuleList = uniqueUserModuleList;
        filteredUserModules = new FilteredList<UserModule>(uniqueUserModuleList.asUnmodifiableObservableList());
    }

    //=========== UserModule ==================================================================================

    @Override
    public boolean hasUserModule(UserModule module) {
        requireNonNull(module);
        return uniqueUserModuleList.contains(module);
    }

    @Override
    public void deleteUserModule(UserModule target) {
        requireNonNull(target);
        uniqueUserModuleList.remove(target);
    }

    @Override
    public void addUserModule(UserModule module) {
        uniqueUserModuleList.add(module);

    }

    @Override
    public ObservableList<UserModule> getFilteredUserModuleList() {
        return filteredUserModules;
    }

    @Override
    public void updateFilteredUserModuleList(Predicate<UserModule> predicate) {
        requireNonNull(predicate);
        filteredUserModules.setPredicate(predicate);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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
}
