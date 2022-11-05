package seedu.modquik;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.modquik.commons.core.Config;
import seedu.modquik.commons.core.LogsCenter;
import seedu.modquik.commons.core.Version;
import seedu.modquik.commons.exceptions.DataConversionException;
import seedu.modquik.commons.util.ConfigUtil;
import seedu.modquik.commons.util.StringUtil;
import seedu.modquik.logic.Logic;
import seedu.modquik.logic.LogicManager;
import seedu.modquik.model.ModQuik;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.ReadOnlyUserPrefs;
import seedu.modquik.model.UserPrefs;
import seedu.modquik.model.util.SampleDataUtil;
import seedu.modquik.storage.ModQuikStorage;
import seedu.modquik.storage.JsonModQuikStorage;
import seedu.modquik.storage.JsonUserPrefsStorage;
import seedu.modquik.storage.Storage;
import seedu.modquik.storage.StorageManager;
import seedu.modquik.storage.UserPrefsStorage;
import seedu.modquik.ui.Ui;
import seedu.modquik.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 3, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ModQuik ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ModQuikStorage modQuikStorage = new JsonModQuikStorage(userPrefs.getModQuikFilePath());
        storage = new StorageManager(modQuikStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s modquik book and {@code userPrefs}. <br>
     * The data from the sample modquik book will be used instead if {@code storage}'s modquik book is not found,
     * or an empty modquik book will be used instead if errors occur when reading {@code storage}'s modquik book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyModQuik> addressBookOptional;
        ReadOnlyModQuik initialData;
        try {
            addressBookOptional = storage.readModQuik();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ModQuik");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleModQuik);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ModQuik");
            initialData = new ModQuik();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ModQuik");
            initialData = new ModQuik();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ModQuik");
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
        logger.info("Starting ModQuik " + MainApp.VERSION);
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
