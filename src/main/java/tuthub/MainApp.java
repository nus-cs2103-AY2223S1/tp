package tuthub;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import tuthub.commons.core.Config;
import tuthub.commons.core.LogsCenter;
import tuthub.commons.core.Version;
import tuthub.commons.exceptions.DataConversionException;
import tuthub.commons.util.ConfigUtil;
import tuthub.commons.util.StringUtil;
import tuthub.logic.Logic;
import tuthub.logic.LogicManager;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.ReadOnlyUserPrefs;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;
import tuthub.model.util.SampleDataUtil;
import tuthub.storage.JsonTuthubStorage;
import tuthub.storage.JsonUserPrefsStorage;
import tuthub.storage.Storage;
import tuthub.storage.StorageManager;
import tuthub.storage.TuthubStorage;
import tuthub.storage.UserPrefsStorage;
import tuthub.ui.Ui;
import tuthub.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Tuthub ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());


        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TuthubStorage tuthubStorage = new JsonTuthubStorage(userPrefs.getTuthubFilePath());
        storage = new StorageManager(tuthubStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s tuthub and {@code userPrefs}. <br>
     * The data from the sample tuthub will be used instead if {@code storage}'s tuthub is not found,
     * or an empty tuthub will be used instead if errors occur when reading {@code storage}'s tuthub.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTuthub> tuthubOptional;
        ReadOnlyTuthub initialData;
        try {
            tuthubOptional = storage.readTuthub();
            if (!tuthubOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Tuthub");
            }
            initialData = tuthubOptional.orElseGet(SampleDataUtil::getSampleTuthub);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Tuthub");
            initialData = new Tuthub();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Tuthub");
            initialData = new Tuthub();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Tuthub");
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
        logger.info("Starting Tuthub " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Tuthub ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
