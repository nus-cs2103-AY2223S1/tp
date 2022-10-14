package modtrekt;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import modtrekt.commons.core.Config;
import modtrekt.commons.core.LogsCenter;
import modtrekt.commons.core.Version;
import modtrekt.commons.exceptions.DataConversionException;
import modtrekt.commons.util.ConfigUtil;
import modtrekt.commons.util.StringUtil;
import modtrekt.logic.Logic;
import modtrekt.logic.LogicManager;
import modtrekt.model.Model;
import modtrekt.model.ModelManager;
import modtrekt.model.ModuleList;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.ReadOnlyUserPrefs;
import modtrekt.model.TaskBook;
import modtrekt.model.UserPrefs;
import modtrekt.model.util.SampleDataUtil;
import modtrekt.storage.JsonModuleListStorage;
import modtrekt.storage.JsonTaskBookStorage;
import modtrekt.storage.JsonUserPrefsStorage;
import modtrekt.storage.ModuleListStorage;
import modtrekt.storage.Storage;
import modtrekt.storage.StorageManager;
import modtrekt.storage.TaskBookStorage;
import modtrekt.storage.UserPrefsStorage;
import modtrekt.ui.Ui;
import modtrekt.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ModuleList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TaskBookStorage taskBookStorage = new JsonTaskBookStorage(userPrefs.getTaskBookFilePath());
        ModuleListStorage moduleListStorage = new JsonModuleListStorage(userPrefs.getModuleListFilePath());
        storage = new StorageManager(taskBookStorage, moduleListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s TaskBook and ModelList {@code
     * userPrefs}. <br>
     * The data from the sample TaskBook and ModelList will be used instead if {@code storage}'s TaskBook and
     * ModelList is not found,
     * or an empty TaskBook and ModelList will be used instead if errors occur when reading {@code storage}'s
     * TaskBook and ModelList.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTaskBook> taskBookOptional;
        ReadOnlyTaskBook taskBook;
        Optional<ReadOnlyModuleList> moduleListOptional;
        ReadOnlyModuleList moduleList;
        try {
            taskBookOptional = storage.readTaskBook();
            if (!taskBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample TaskBook");
            }
            taskBook = taskBookOptional.orElseGet(SampleDataUtil::getSampleTaskBook);

            moduleListOptional = storage.readModuleList();
            if (!moduleListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ModuleList");
            }
            moduleList = moduleListOptional.orElseGet(SampleDataUtil::getSampleModuleList);
        } catch (DataConversionException e) {
            logger.warning(
                    "Data file not in the correct format. Will be starting with an empty ModuleList and TaskBook.");
            taskBook = new TaskBook();
            moduleList = new ModuleList();
        } catch (IOException e) {
            logger.warning(
                    "Problem while reading from the file. Will be starting with an empty ModuleList and TaskBook.");
            taskBook = new TaskBook();
            moduleList = new ModuleList();
        }
        return new ModelManager(moduleList, taskBook, userPrefs);
    }


    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
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
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
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
            logger.warning(
                    "Problem while reading from the file. Will be starting with an empty ModuleList and TaskBook");
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
        logger.info("Starting ModuleList " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ModtRekt ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
