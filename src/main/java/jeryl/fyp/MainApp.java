package jeryl.fyp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import jeryl.fyp.commons.core.Config;
import jeryl.fyp.commons.core.LogsCenter;
import jeryl.fyp.commons.core.Version;
import jeryl.fyp.commons.exceptions.DataConversionException;
import jeryl.fyp.commons.util.ConfigUtil;
import jeryl.fyp.commons.util.StringUtil;
import jeryl.fyp.logic.Logic;
import jeryl.fyp.logic.LogicManager;
import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.ReadOnlyUserPrefs;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.util.SampleDataUtil;
import jeryl.fyp.storage.FypManagerStorage;
import jeryl.fyp.storage.JsonFypManagerStorage;
import jeryl.fyp.storage.JsonUserPrefsStorage;
import jeryl.fyp.storage.Storage;
import jeryl.fyp.storage.StorageManager;
import jeryl.fyp.storage.UserPrefsStorage;
import jeryl.fyp.ui.Ui;
import jeryl.fyp.ui.UiManager;

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
        logger.info("=============================[ Initializing FypManager ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FypManagerStorage fypManagerStorage = new JsonFypManagerStorage(userPrefs.getFypManagerFilePath());
        storage = new StorageManager(fypManagerStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s FYP manager and {@code userPrefs}. <br>
     * The data from the sample FYP manager will be used instead if {@code storage}'s FYP manager is not found,
     * or an empty FYP manager will be used instead if errors occur when reading {@code storage}'s FYP manager.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyFypManager> fypManagerOptional;
        ReadOnlyFypManager initialData;
        try {
            fypManagerOptional = storage.readFypManager();
            if (!fypManagerOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample FypManager");
            }
            initialData = fypManagerOptional.orElseGet(SampleDataUtil::getSampleFypManager);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty FypManager");
            initialData = new FypManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FypManager");
            initialData = new FypManager();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty FypManager");
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
        logger.info("Starting FypManager " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping FYP Manager ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
