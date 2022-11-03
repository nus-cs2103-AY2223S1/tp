package seedu.watson;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.watson.commons.core.Config;
import seedu.watson.commons.core.LogsCenter;
import seedu.watson.commons.core.Version;
import seedu.watson.commons.exceptions.DataConversionException;
import seedu.watson.commons.util.ConfigUtil;
import seedu.watson.commons.util.StringUtil;
import seedu.watson.logic.Logic;
import seedu.watson.logic.LogicManager;
import seedu.watson.model.Database;
import seedu.watson.model.Model;
import seedu.watson.model.ModelManager;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.ReadOnlyUserPrefs;
import seedu.watson.model.UserPrefs;
import seedu.watson.model.util.SampleDataUtil;
import seedu.watson.storage.DatabaseStorage;
import seedu.watson.storage.JsonDatabaseStorage;
import seedu.watson.storage.JsonUserPrefsStorage;
import seedu.watson.storage.Storage;
import seedu.watson.storage.StorageManager;
import seedu.watson.storage.UserPrefsStorage;
import seedu.watson.ui.Ui;
import seedu.watson.ui.UiManager;

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
        logger.info("=============================[ Initializing Database ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        DatabaseStorage databaseStorage = new JsonDatabaseStorage(userPrefs.getDatabaseFilePath());
        storage = new StorageManager(databaseStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Database " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveDatabase(model.getDatabase());
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s watson book and {@code userPrefs}. <br>
     * The data from the sample watson book will be used instead if {@code storage}'s watson book is not found,
     * or an empty watson book will be used instead if errors occur when reading {@code storage}'s watson book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyDatabase> addressBookOptional;
        ReadOnlyDatabase initialData;
        try {
            addressBookOptional = storage.readDatabase();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Database");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Database");
            initialData = new Database();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Database");
            initialData = new Database();
        }

        return new ModelManager(initialData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Database");
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
}
