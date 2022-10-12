package seedu.intrack;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.intrack.commons.core.Config;
import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.commons.core.Version;
import seedu.intrack.commons.exceptions.DataConversionException;
import seedu.intrack.commons.util.ConfigUtil;
import seedu.intrack.commons.util.StringUtil;
import seedu.intrack.logic.Logic;
import seedu.intrack.logic.LogicManager;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.ReadOnlyUserPrefs;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.util.SampleDataUtil;
import seedu.intrack.storage.InTrackStorage;
import seedu.intrack.storage.JsonInTrackStorage;
import seedu.intrack.storage.JsonUserPrefsStorage;
import seedu.intrack.storage.Storage;
import seedu.intrack.storage.StorageManager;
import seedu.intrack.storage.UserPrefsStorage;
import seedu.intrack.ui.Ui;
import seedu.intrack.ui.UiManager;

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
        logger.info("=============================[ Initializing InTrack ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        InTrackStorage inTrackStorage = new JsonInTrackStorage(userPrefs.getInTrackFilePath());
        storage = new StorageManager(inTrackStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s tracker and {@code userPrefs}. <br>
     * The data from the sample internship tracker will be used instead if {@code storage}'s tracker is not found,
     * or an empty tracker will be used instead if errors occur when reading {@code storage}'s internship tracker.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyInTrack> inTrackOptional;
        ReadOnlyInTrack initialData;
        try {
            inTrackOptional = storage.readInTrack();
            if (!inTrackOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample InTrack");
            }
            initialData = inTrackOptional.orElseGet(SampleDataUtil::getSampleInTrack);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty InTrack");
            initialData = new InTrack();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty InTrack");
            initialData = new InTrack();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty InTrack");
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
        logger.info("Starting InTrack " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Internship Tracker ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
