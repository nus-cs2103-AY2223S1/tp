package nus.climods.model;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.SemestersEnum;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.commons.core.LogsCenter;
import nus.climods.model.module.LessonTypeEnum;
import nus.climods.model.module.Module;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.model.module.UserModule;
import nus.climods.model.module.predicate.ModulesByCodesPredicate;
import nus.climods.model.module.predicate.ViewModulePredicate;


/**
 * Represents the in-memory model of module list data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModuleList moduleList;
    private final UniqueUserModuleList userModuleList;

    private final FilteredList<Module> filteredModuleList;
    private final SortedList<Module> filteredAndSortedModuleList;
    private final Comparator<? super Module> defaultModuleListComparator;

    private final FilteredList<UserModule> filteredUserModuleList;

    private final UserPrefs userPrefs;

    private Module moduleInFocus;

    /**
     * Initializes a ModelManager with the given moduleList and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, UniqueUserModuleList userModuleList,
        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(moduleList, userPrefs);

        logger.fine("Initializing with module list: " + moduleList + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.moduleList = new ModuleList(moduleList);
        this.userModuleList = userModuleList;

        this.filteredModuleList = new FilteredList<>(moduleList.getModules());
        this.filteredAndSortedModuleList = new SortedList<>(filteredModuleList);
        this.defaultModuleListComparator = filteredAndSortedModuleList.getComparator();

        this.filteredUserModuleList = new FilteredList<>(userModuleList.asUnmodifiableObservableList());
    }

    //=========== Module ==================================================================================

    @Override
    public ReadOnlyModuleList getModuleList() {
        return moduleList;
    }

    @Override
    public Optional<Module> getListModule(String moduleCode) {
        return getModuleList().getListModule(moduleCode);
    }

    public boolean isModuleOffered(String moduleCode) {
        return this.moduleList.hasModule(moduleCode);
    }

    @Override
    public boolean isModuleOfferedInSemester(String moduleCode, SemestersEnum semester) {
        Optional<Module> module = getModule(moduleCode);

        if (module.isEmpty()) {
            return false;
        }

        return module.get().availableInSemester(semester);
    }

    //will keep these here first jic we plan to use the wrapper to standardize for pickcommand
    @Override
    public Set<LessonTypeEnum> unselectableLessonType(String moduleCode, SemestersEnum semester) {
        Optional<Module> module = getListModule(moduleCode);
        if (module.isEmpty()) {
            return null;
        }

        try {
            module.get().loadMoreData();
        } catch (ApiException e) {
            return null;
        }

        return module.get().getUnselectableLessonTypeEnums(semester);
    }

    @Override
    public boolean isModuleLessonOffered(String moduleCode, SemestersEnum semester,
                                         LessonTypeEnum lessonType) {
        Optional<Module> module = getListModule(moduleCode);
        if (module.isEmpty()) {
            return false;
        }

        try {
            module.get().loadMoreData();
        } catch (ApiException e) {
            return false;
        }
        return module.get().hasLessonTypeEnum(lessonType, semester);
    }

    @Override
    public boolean isModuleLessonClassOffered(String moduleCode, SemestersEnum semester,
                                         LessonTypeEnum lessonType, String classCode) {
        Optional<Module> module = getListModule(moduleCode);
        if (module.isEmpty()) {
            return false;
        }

        try {
            module.get().loadMoreData();
        } catch (ApiException e) {
            return false;
        }

        return module.get().hasLessonId(classCode, semester, lessonType);
    }


    @Override
    public Optional<Module> getModule(String moduleCode) {
        return this.moduleList.getModule(moduleCode);
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredAndSortedModuleList;
    }

    @Override
    public void setFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        this.filteredModuleList.setPredicate(predicate);
        // Reset to default comparator
        this.filteredAndSortedModuleList.setComparator(defaultModuleListComparator);
    }

    @Override
    public void setFilteredModuleList(Predicate<Module> predicate, Comparator<Module> comparator) {
        setFilteredModuleList(predicate);

        requireNonNull(comparator);
        this.filteredAndSortedModuleList.setComparator(comparator);
    }

    @Override
    public void setModuleInFocus(Module module) throws ApiException {
        module.requestFocus();
        moduleInFocus = module;

        setFilteredModuleList(new ViewModulePredicate(module.getCode()));
    }

    @Override
    public boolean showModules(List<String> moduleCodes) {
        long validCodes = moduleCodes.stream().filter(this::isModuleOffered).count();
        if (validCodes == 0) {
            return false;
        }
        this.setFilteredModuleList(new ModulesByCodesPredicate(moduleCodes));
        return true;
    }

    @Override
    public void clearModuleInFocus() {
        if (moduleInFocus != null) {
            moduleInFocus.clearFocus();
        }
        moduleInFocus = null;
    }


    //=========== UserModule ==================================================================================

    @Override
    public UniqueUserModuleList getUserModuleList() {
        return userModuleList;
    }

    @Override
    public boolean hasUserModule(UserModule module) {
        requireNonNull(module);
        return userModuleList.contains(module);
    }

    @Override
    public void addUserModule(UserModule module) {
        userModuleList.add(module);
    }

    @Override
    public void deleteUserModule(String moduleCode) {
        requireNonNull(moduleCode);
        userModuleList.remove(moduleCode);
    }

    @Override
    public ObservableList<UserModule> getFilteredUserModuleList() {
        return filteredUserModuleList;
    }

    @Override
    public boolean filteredListHasUserModule(UserModule module) {
        return this.getFilteredUserModuleList().contains(module);
    }

    @Override
    public void updateFilteredUserModuleList(Predicate<UserModule> predicate) {
        requireNonNull(predicate);
        this.filteredUserModuleList.setPredicate(predicate);
    }

    public Optional<UserModule> getUserModule(String toGet) {
        return userModuleList.get(toGet);
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

    //=========== GuiSettings ==================================================================================

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }
}
