package seedu.travelr;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.travelr.commons.core.Config;
import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.commons.core.Version;
import seedu.travelr.commons.exceptions.DataConversionException;
import seedu.travelr.commons.util.ConfigUtil;
import seedu.travelr.commons.util.StringUtil;
import seedu.travelr.logic.Logic;
import seedu.travelr.logic.LogicManager;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.ReadOnlyUserPrefs;
import seedu.travelr.model.Travelr;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.util.SampleDataUtil;
import seedu.travelr.storage.JsonTravelrStorage;
import seedu.travelr.storage.JsonUserPrefsStorage;
import seedu.travelr.storage.Storage;
import seedu.travelr.storage.StorageManager;
import seedu.travelr.storage.TravelrStorage;
import seedu.travelr.storage.UserPrefsStorage;
import seedu.travelr.ui.Ui;
import seedu.travelr.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Travelr ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TravelrStorage travelrStorage = new JsonTravelrStorage(userPrefs.getTravelrFilePath());
        storage = new StorageManager(travelrStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Travelr and {@code userPrefs}. <br>
     * The data from the sample Travelr will be used instead if {@code storage}'s Travelr is not found,
     * or an empty Travelr will be used instead if errors occur when reading {@code storage}'s Travelr.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        // Storage works here -- initial data contains the events
        Optional<ReadOnlyTravelr> travelrOptional;
        ReadOnlyTravelr initialData;
        try {
            travelrOptional = storage.readTravelr();
            if (!travelrOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Travelr");
            }
            initialData = travelrOptional.orElseGet(SampleDataUtil::getSampleTravelr);
            // if loading does not work, this works for demo: initialData = SampleDataUtil.getSampleTravelr();
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Travelr");
            initialData = new Travelr();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Travelr");
            initialData = new Travelr();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Travelr");
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
        logger.info("Starting Travelr " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Travelr ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
