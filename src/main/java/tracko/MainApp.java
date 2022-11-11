package tracko;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import tracko.commons.core.Config;
import tracko.commons.core.LogsCenter;
import tracko.commons.core.Version;
import tracko.commons.exceptions.DataConversionException;
import tracko.commons.util.ConfigUtil;
import tracko.commons.util.StringUtil;
import tracko.logic.Logic;
import tracko.logic.LogicManager;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.ReadOnlyTrackO;
import tracko.model.ReadOnlyUserPrefs;
import tracko.model.TrackO;
import tracko.model.UserPrefs;
import tracko.model.util.SampleDataUtil;
import tracko.storage.JsonTrackOStorage;
import tracko.storage.JsonUserPrefsStorage;
import tracko.storage.Storage;
import tracko.storage.StorageManager;
import tracko.storage.TrackOStorage;
import tracko.storage.UserPrefsStorage;
import tracko.ui.Ui;
import tracko.ui.UiManager;


/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing TrackO ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TrackOStorage trackOStorage = new JsonTrackOStorage(userPrefs.getTrackOFilePath());
        storage = new StorageManager(trackOStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s TrackO and {@code userPrefs}. <br>
     * The data from the sample TrackO will be used instead if {@code storage}'s TrackO is not found,
     * or an empty TrackO will be used instead if errors occur when reading {@code storage}'s TrackO.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTrackO> trackOOptional;
        ReadOnlyTrackO initialData;

        try {
            trackOOptional = storage.readTrackO();
            if (!trackOOptional.isPresent()) {
                logger.info("TrackO data file not found. Will be starting with sample data points");
            }
            initialData = trackOOptional.orElseGet(SampleDataUtil::getSampleTrackO);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty TrackO");
            initialData = new TrackO();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TrackO");
            initialData = new TrackO();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty TrackO");
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
        logger.info("Starting TrackO " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping TrackO ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
