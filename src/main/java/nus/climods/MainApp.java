package nus.climods;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.ModulesApi;

import javafx.application.Application;
import javafx.stage.Stage;
import nus.climods.commons.core.Config;
import nus.climods.commons.core.LogsCenter;
import nus.climods.commons.core.Version;
import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.commons.util.ConfigUtil;
import nus.climods.commons.util.StringUtil;
import nus.climods.logic.Logic;
import nus.climods.logic.LogicManager;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.ReadOnlyUserPrefs;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.Module;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.storage.JsonUserPrefsStorage;
import nus.climods.storage.Storage;
import nus.climods.storage.StorageManager;
import nus.climods.storage.UserPrefsStorage;
import nus.climods.storage.module.user.JsonUserModuleListStorage;
import nus.climods.ui.Ui;
import nus.climods.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing CliMods ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        JsonUserModuleListStorage userModuleListStorage = new JsonUserModuleListStorage(userPrefs
            .getUserModuleListFilePath());

        storage = new StorageManager(userModuleListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);
        logic = new LogicManager(model, storage);
        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br> The
     * data from the sample address book will be used instead if {@code storage}'s address book is not found, or an
     * empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        String academicYear = userPrefs.getAcademicYear();

        List<Module> modules = Collections.emptyList();
        try {
            modules = loadModules(academicYear);
        } catch (ApiException apiException) {
            logger.severe("Failed to load module list from nusmods API!");
        }

        UniqueUserModuleList userModuleList;
        Optional<UniqueUserModuleList> userModuleListOptional = Optional.empty();
        try {
            userModuleListOptional = storage.getUserModuleListStorage().readUserModuleList();
        } catch (DataConversionException dataConversionException) {
            logger.warning("Data file not in the correct format!");
        } finally {
            userModuleList = loadStoredList(userModuleListOptional, new UniqueUserModuleList());
        }

        return new ModelManager(new ModuleList(modules), userModuleList, userPrefs);
    }

    private List<Module> loadModules(String academicYear) throws ApiException {
        return ModulesApi.getInstance().acadYearModuleInfoJsonGet(academicYear).stream()
            .map(moduleInfo -> new Module(moduleInfo, academicYear))
            .collect(Collectors.toList());
    }

    private <T> T loadStoredList(Optional<T> optionalList, T alternative) {
        if (optionalList.isEmpty()) {
            logger.info("Data file not found!");
            return alternative;
        } else {
            return optionalList.get();
        }
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br> The default file path
     * {@code Config#DEFAULT_CONFIG_FILE} will be used instead if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path, or a new {@code UserPrefs}
     * with default configuration if errors occur when reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty user module list");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting CliMods " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping CliMods ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
