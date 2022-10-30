package seedu.boba;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.boba.commons.core.Config;
import seedu.boba.commons.core.LogsCenter;
import seedu.boba.commons.core.Version;
import seedu.boba.commons.exceptions.DataConversionException;
import seedu.boba.commons.util.ConfigUtil;
import seedu.boba.commons.util.StringUtil;
import seedu.boba.logic.Logic;
import seedu.boba.logic.LogicManager;
import seedu.boba.model.BobaBot;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.ReadOnlyUserPrefs;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.util.SampleDataUtil;
import seedu.boba.storage.BobaBotStorage;
import seedu.boba.storage.JsonBobaBotStorage;
import seedu.boba.storage.JsonUserPrefsStorage;
import seedu.boba.storage.Storage;
import seedu.boba.storage.StorageManager;
import seedu.boba.storage.UserPrefsStorage;
import seedu.boba.ui.Ui;
import seedu.boba.ui.UiManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected BobaBotModel bobaBotModel;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing BobaBot ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        BobaBotStorage bobaBotStorage = new JsonBobaBotStorage(userPrefs.getBobaBotFilePath());
        storage = new StorageManager(bobaBotStorage, userPrefsStorage);

        initLogging(config);

        bobaBotModel = initModelManager(storage, userPrefs);

        logic = new LogicManager(bobaBotModel, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code BobaBotModelManager} with the data
     * from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private BobaBotModel initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyBobaBot> addressBookOptional;
        ReadOnlyBobaBot initialData;
        try {
            addressBookOptional = storage.readBobaBot();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample BobaBot");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleBobaBot);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty BobaBot");
            initialData = new BobaBot();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty BobaBot");
            initialData = new BobaBot();
        }

        return new BobaBotModelManager(initialData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty BobaBot");
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
        logger.info("Starting BobaBot " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(bobaBotModel.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
