package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PersonBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonPersonBookStorage;
import seedu.address.storage.JsonPropertyBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.PersonBookStorage;
import seedu.address.storage.PropertyBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

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
        logger.info("=============================[ Initializing PersonBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PersonBookStorage personBookStorage = new JsonPersonBookStorage(userPrefs.getPersonBookFilePath());
        PropertyBookStorage propertyBookStorage = new JsonPropertyBookStorage(userPrefs.getPropertyBookFilePath());
        storage = new StorageManager(personBookStorage, propertyBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s person book, property book and
     * {@code userPrefs}. <br> The data from the sample person book and property book will be used instead if those in
     * {@code storage} cannot be found, or an empty person book and property book will be used instead if errors
     * occur when reading those in {@code storage}.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPersonBook> personBookOptional;
        ReadOnlyPersonBook personBook;
        Optional<ReadOnlyPropertyBook> propertyBookOptional;
        ReadOnlyPropertyBook propertyBook;

        try {
            personBookOptional = storage.readPersonBook();
            if (!personBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PersonBook");
            }
            personBook = personBookOptional.orElseGet(SampleDataUtil::getSamplePersonBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PersonBook");
            personBook = new PersonBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PersonBook");
            personBook = new PersonBook();
        }

        try {
            propertyBookOptional = storage.readPropertyBook();
            if (!propertyBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PropertyBook");
            }
            propertyBook = propertyBookOptional.orElseGet(SampleDataUtil::getSamplePropertyBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PropertyBook");
            propertyBook = new PropertyBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PropertyBook");
            propertyBook = new PropertyBook();
        }

        return new ModelManager(personBook, propertyBook, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty PersonBook");
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
        logger.info("Starting PersonBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Cobb ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
