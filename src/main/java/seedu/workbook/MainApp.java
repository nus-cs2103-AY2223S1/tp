package seedu.workbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.workbook.commons.core.Config;
import seedu.workbook.commons.core.LogsCenter;
import seedu.workbook.commons.core.Version;
import seedu.workbook.commons.exceptions.DataConversionException;
import seedu.workbook.commons.util.ConfigUtil;
import seedu.workbook.commons.util.StringUtil;
import seedu.workbook.logic.Logic;
import seedu.workbook.logic.LogicManager;
import seedu.workbook.model.Model;
import seedu.workbook.model.ModelManager;
import seedu.workbook.model.ReadOnlyUserPrefs;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.UserPrefs;
import seedu.workbook.model.WorkBook;
import seedu.workbook.model.util.SampleDataUtil;
import seedu.workbook.storage.JsonUserPrefsStorage;
import seedu.workbook.storage.JsonWorkBookStorage;
import seedu.workbook.storage.Storage;
import seedu.workbook.storage.StorageManager;
import seedu.workbook.storage.UserPrefsStorage;
import seedu.workbook.storage.WorkBookStorage;
import seedu.workbook.ui.Ui;
import seedu.workbook.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing WorkBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        WorkBookStorage workBookStorage = new JsonWorkBookStorage(userPrefs.getWorkBookFilePath());
        storage = new StorageManager(workBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s work book and {@code userPrefs}. <br>
     * The data from the sample work book will be used instead if {@code storage}'s work book is not found,
     * or an empty work book will be used instead if errors occur when reading {@code storage}'s work book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyWorkBook> workBookOptional;
        ReadOnlyWorkBook initialData;
        try {
            workBookOptional = storage.readWorkBook();
            if (!workBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample WorkBook");
            }
            initialData = workBookOptional.orElseGet(SampleDataUtil::getSampleWorkBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty WorkBook");
            initialData = new WorkBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty WorkBook");
            initialData = new WorkBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty WorkBook");
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
        logger.info("Starting WorkBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Work Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
