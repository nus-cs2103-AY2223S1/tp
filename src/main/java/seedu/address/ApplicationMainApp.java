package seedu.address;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.ApplicationLogic;
import seedu.address.logic.ApplicationLogicManager;
import seedu.address.model.*;
import seedu.address.model.util.SampleDataUtilApplicationBook;
import seedu.address.storage.*;
import seedu.address.ui.ApplicationUiManager;
import seedu.address.ui.Ui;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Runs the application.
 */
public class ApplicationMainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(ApplicationMainApp.class);

    protected Ui ui;
    protected ApplicationLogic applicationLogic;
    protected ApplicationStorage applicationStorage;
    protected ApplicationModel applicationModel;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ApplicationBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ApplicationBookStorage applicationBookStorage = new JsonApplicationBookStorage(userPrefs.getApplicationBookFilePath());
        applicationStorage = new ApplicationStorageManager(applicationBookStorage, userPrefsStorage);

        initLogging(config);

        applicationModel = initModelManager(applicationStorage, userPrefs);

        applicationLogic = new ApplicationLogicManager(applicationModel, applicationStorage);

        ui = new ApplicationUiManager(applicationLogic);
    }

    /**
     * Returns a {@code ApplicationModelManager} with the data from {@code applicationStorage}'s application book and {@code userPrefs}. <br>
     * The data from the sample application book will be used instead if {@code applicationStorage}'s application book is not found,
     * or an empty application book will be used instead if errors occur when reading {@code applicationStorage}'s application book.
     */
    private ApplicationModel initModelManager(ApplicationStorage applicationStorage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyApplicationBook> applicationBookOptional;
        ReadOnlyApplicationBook initialData;
        try {
            applicationBookOptional = applicationStorage.readApplicationBook();
            if (!applicationBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ApplicationBook");
            }
            initialData = applicationBookOptional.orElseGet(SampleDataUtilApplicationBook::getSampleApplicationBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ApplicationBook");
            initialData = new ApplicationBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ApplicationBook");
            initialData = new ApplicationBook();
        }

        return new ApplicationModelManager(initialData, userPrefs);
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
        logger.info("Starting ApplicationBook " + ApplicationMainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Application Book ] =============================");
        try {
            applicationStorage.saveUserPrefs(applicationModel.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
