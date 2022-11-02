package seedu.masslinkers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.masslinkers.commons.core.Config;
import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.commons.core.Version;
import seedu.masslinkers.commons.exceptions.DataConversionException;
import seedu.masslinkers.commons.util.ConfigUtil;
import seedu.masslinkers.commons.util.StringUtil;
import seedu.masslinkers.logic.Logic;
import seedu.masslinkers.logic.LogicManager;
import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
import seedu.masslinkers.model.ReadOnlyUserPrefs;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.util.SampleDataUtil;
import seedu.masslinkers.storage.JsonMassLinkersStorage;
import seedu.masslinkers.storage.JsonUserPrefsStorage;
import seedu.masslinkers.storage.MassLinkersStorage;
import seedu.masslinkers.storage.Storage;
import seedu.masslinkers.storage.StorageManager;
import seedu.masslinkers.storage.UserPrefsStorage;
import seedu.masslinkers.ui.Ui;
import seedu.masslinkers.ui.UiManager;
//@@author
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
        logger.info("=============================[ Initializing MassLinkers ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        MassLinkersStorage massLinkersStorage = new JsonMassLinkersStorage(userPrefs.getMassLinkersFilePath());
        storage = new StorageManager(massLinkersStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s mass linkers and {@code userPrefs}. <br>
     * The data from the sample mass linkers will be used instead if {@code storage}'s mass linkers is not found,
     * or an empty mass linkers will be used instead if errors occur when reading {@code storage}'s mass linkers.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyMassLinkers> massLinkersOptional;
        ReadOnlyMassLinkers initialData;
        try {
            massLinkersOptional = storage.readMassLinkers();
            if (!massLinkersOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample MassLinkers");
            }
            initialData = massLinkersOptional.orElseGet(SampleDataUtil::getSampleMassLinkers);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty MassLinkers");
            initialData = new MassLinkers();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty MassLinkers");
            initialData = new MassLinkers();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty MassLinkers");
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
        logger.info("Starting MassLinkers " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
