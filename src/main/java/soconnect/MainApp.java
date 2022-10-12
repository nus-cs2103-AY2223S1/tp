package soconnect;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import soconnect.commons.core.Config;
import soconnect.commons.core.LogsCenter;
import soconnect.commons.core.Version;
import soconnect.commons.exceptions.DataConversionException;
import soconnect.commons.util.ConfigUtil;
import soconnect.commons.util.StringUtil;
import soconnect.logic.Logic;
import soconnect.logic.LogicManager;
import soconnect.model.SoConnect;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyUserPrefs;
import soconnect.model.UserPrefs;
import soconnect.model.util.SampleDataUtil;
import soconnect.storage.SoConnectStorage;
import soconnect.storage.JsonSoConnectStorage;
import soconnect.storage.JsonUserPrefsStorage;
import soconnect.storage.Storage;
import soconnect.storage.StorageManager;
import soconnect.storage.UserPrefsStorage;
import soconnect.ui.Ui;
import soconnect.ui.UiManager;

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
        logger.info("=============================[ Initializing SoConnect ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        SoConnectStorage soConnectStorage = new JsonSoConnectStorage(userPrefs.getSoConnectFilePath());
        storage = new StorageManager(soConnectStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s SoConnect and {@code userPrefs}. <br>
     * The data from the sample SoConnect will be used instead if {@code storage}'s SoConnect is not found,
     * or an empty SoConnect will be used instead if errors occur when reading {@code storage}'s SoConnect.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlySoConnect> soConnectOptional;
        ReadOnlySoConnect initialData;
        try {
            soConnectOptional = storage.readSoConnect();
            if (soConnectOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample SoConnect");
            }
            initialData = soConnectOptional.orElseGet(SampleDataUtil::getSampleSoConnect);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty SoConnect");
            initialData = new SoConnect();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty SoConnect");
            initialData = new SoConnect();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty SoConnect");
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
        logger.info("Starting SoConnect " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping SoConnect ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
