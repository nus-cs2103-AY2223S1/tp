package seedu.condonery;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.condonery.commons.core.Config;
import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.commons.core.Version;
import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.commons.util.ConfigUtil;
import seedu.condonery.commons.util.StringUtil;
import seedu.condonery.logic.Logic;
import seedu.condonery.logic.LogicManager;
import seedu.condonery.model.ClientDirectory;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.model.ReadOnlyClientDirectory;
import seedu.condonery.model.ReadOnlyPropertyDirectory;
import seedu.condonery.model.ReadOnlyUserPrefs;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.util.SampleDataUtil;
import seedu.condonery.storage.ClientDirectoryStorage;
import seedu.condonery.storage.JsonClientDirectoryStorage;
import seedu.condonery.storage.JsonPropertyDirectoryStorage;
import seedu.condonery.storage.JsonUserPrefsStorage;
import seedu.condonery.storage.PropertyDirectoryStorage;
import seedu.condonery.storage.Storage;
import seedu.condonery.storage.StorageManager;
import seedu.condonery.storage.UserPrefsStorage;
import seedu.condonery.ui.Ui;
import seedu.condonery.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Condonery ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PropertyDirectoryStorage propertyDirectoryStorage =
                new JsonPropertyDirectoryStorage(
                    userPrefs.getPropertyDirectoryFilePath()
                );
        ClientDirectoryStorage clientDirectoryStorage =
                new JsonClientDirectoryStorage(
                    userPrefs.getClientDirectoryFilePath());
        storage = new StorageManager(propertyDirectoryStorage, clientDirectoryStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPropertyDirectory> propertyDirectoryOptional;
        ReadOnlyPropertyDirectory initialPropertyDirectoryData;

        Optional<ReadOnlyClientDirectory> clientDirectoryOptional;
        ReadOnlyClientDirectory initialClientDirectoryData;

        try {
            propertyDirectoryOptional = storage.readPropertyDirectory();
            if (!propertyDirectoryOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PropertyDirectory");
            }
            initialPropertyDirectoryData =
                    propertyDirectoryOptional.orElseGet(SampleDataUtil::getSamplePropertyDirectory);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PropertyDirectory");
            initialPropertyDirectoryData = new PropertyDirectory();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PropertyDirectory");
            initialPropertyDirectoryData = new PropertyDirectory();
        }

        try {
            clientDirectoryOptional = storage.readClientDirectory();
            if (!clientDirectoryOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PropertyDirectory");
            }
            initialClientDirectoryData =
                    clientDirectoryOptional.orElseGet(SampleDataUtil::getSampleClientDirectory);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ClientDirectory");
            initialClientDirectoryData = new ClientDirectory();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ClientDirectory");
            initialClientDirectoryData = new ClientDirectory();
        }

        return new ModelManager(initialPropertyDirectoryData, initialClientDirectoryData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty UserPrefs");
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
        logger.info("Starting Condonery " + MainApp.VERSION);
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
