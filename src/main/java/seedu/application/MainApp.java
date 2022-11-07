package seedu.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.application.commons.core.Config;
import seedu.application.commons.core.LogsCenter;
import seedu.application.commons.core.Version;
import seedu.application.commons.exceptions.DataConversionException;
import seedu.application.commons.util.ConfigUtil;
import seedu.application.commons.util.StringUtil;
import seedu.application.logic.Logic;
import seedu.application.logic.LogicManager;
import seedu.application.model.ApplicationBook;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.ReadOnlyUserPrefs;
import seedu.application.model.UserPrefs;
import seedu.application.model.util.SampleDataUtil;
import seedu.application.storage.ApplicationBookStorage;
import seedu.application.storage.JsonApplicationBookStorage;
import seedu.application.storage.JsonUserPrefsStorage;
import seedu.application.storage.Storage;
import seedu.application.storage.StorageManager;
import seedu.application.storage.UserPrefsStorage;
import seedu.application.ui.Ui;
import seedu.application.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, false);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ApplicationBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ApplicationBookStorage applicationBookStorage =
                new JsonApplicationBookStorage(userPrefs.getApplicationBookFilePath());
        storage = new StorageManager(applicationBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from
     * {@code storage}'s application book and {@code userPrefs}. <br>
     * The data from the sample application book will be used instead
     * if {@code storage}'s application book is not found,
     * or an empty application book will be used instead if errors occur when
     * reading {@code storage}'s application book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyApplicationBook> applicationBookOptional;
        ReadOnlyApplicationBook initialData;
        try {
            applicationBookOptional = storage.readApplicationBook();
            if (!applicationBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ApplicationBook");
            }
            initialData = applicationBookOptional.orElseGet(SampleDataUtil::getSampleApplicationBookWithInterviews);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ApplicationBook");
            initialData = new ApplicationBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ApplicationBook");
            initialData = new ApplicationBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ApplicationBook");
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
        logger.info("Starting ApplicationBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Application Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
