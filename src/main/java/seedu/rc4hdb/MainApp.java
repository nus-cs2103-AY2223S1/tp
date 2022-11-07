package seedu.rc4hdb;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.rc4hdb.commons.core.Config;
import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.commons.core.Version;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.commons.util.ConfigUtil;
import seedu.rc4hdb.commons.util.StringUtil;
import seedu.rc4hdb.logic.Logic;
import seedu.rc4hdb.logic.LogicManager;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.util.SampleDataUtil;
import seedu.rc4hdb.storage.DataStorage;
import seedu.rc4hdb.storage.DataStorageManager;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.userprefs.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.userprefs.UserPrefsStorage;
import seedu.rc4hdb.ui.Ui;
import seedu.rc4hdb.ui.UiManager;

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
        logger.info("=============================[ Initializing RC4HDB ]================================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        DataStorage dataStorage = new DataStorageManager(userPrefs.getDataStorageFilePath());
        storage = new StorageManager(dataStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s resident book and {@code userPrefs}. <br>
     * The data from the sample resident book will be used instead if {@code storage}'s resident book is not found,
     * or an empty resident book will be used instead if errors occur when reading {@code storage}'s resident book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ReadOnlyResidentBook initialResidentData = fetchResidentData(storage);
        ReadOnlyVenueBook initialVenueData = fetchVenueData(storage);
        return new ModelManager(initialResidentData, initialVenueData, userPrefs);
    }

    /**
     * Fetches the resident data from the resident data file.
     */
    private ReadOnlyResidentBook fetchResidentData(Storage storage) {
        try {
            return storage.readResidentBook().orElseGet(() -> {
                logger.info("Data file not found. Will be starting with a sample VenueBook");
                return SampleDataUtil.getSampleResidentBook();
            });
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty data file");
            return new ResidentBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty data file");
            return new ResidentBook();
        }
    }

    /**
     * Fetches the venue data from the venue data file.
     */
    private ReadOnlyVenueBook fetchVenueData(Storage storage) {
        try {
            return storage.readVenueBook().orElseGet(() -> {
                logger.info("Data file not found. Will be starting with a sample VenueBook");
                return SampleDataUtil.getSampleVenueBook();
            });
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty data file");
            return new VenueBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty data file");
            return new VenueBook();
        }
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
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
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
        logger.info("Starting RC4HDB " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping RC4 HDB ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
