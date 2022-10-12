package nus.climods.model;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import nus.climods.commons.core.GuiSettings;
import nus.climods.commons.core.LogsCenter;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.ReadOnlyModuleList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final ModuleList moduleList;
    private final UserPrefs userPrefs;
    // The current module list based on filters applied
    private ModuleList filteredModuleList;
    /**
     * Initializes a ModelManager with the given module list and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(moduleList, userPrefs);

        logger.fine("Initializing with module list: " + moduleList + " and user prefs " + userPrefs);

        this.moduleList = new ModuleList(moduleList);
        this.filteredModuleList = this.moduleList;
        this.userPrefs = new UserPrefs(userPrefs);
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

    /**
     * Filter the list by faculty Code
     * @return
     */
    public void updateFilteredModuleList(Optional<String> facultyCode, Optional<Boolean> hasUser) {
        // TODO: Implement filtering for saved modules
        if (facultyCode.isPresent()) {
            Pattern faculty_code_regex = Pattern.compile(String.format("^(?i)%s(?-i)$", facultyCode.get()));
            this.filteredModuleList = new ModuleList(filteredModuleList.getModules().stream().filter(
                            module -> faculty_code_regex.matcher(module.getCode()).find())
                    .collect(Collectors.toList()));
        }
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
