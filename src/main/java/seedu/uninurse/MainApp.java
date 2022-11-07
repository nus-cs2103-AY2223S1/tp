package seedu.uninurse;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.uninurse.commons.core.Config;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.commons.core.Version;
import seedu.uninurse.commons.exceptions.DataConversionException;
import seedu.uninurse.commons.util.ConfigUtil;
import seedu.uninurse.commons.util.StringUtil;
import seedu.uninurse.logic.Logic;
import seedu.uninurse.logic.LogicManager;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.ReadOnlyUserPrefs;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.util.SampleDataUtil;
import seedu.uninurse.storage.JsonUninurseBookStorage;
import seedu.uninurse.storage.JsonUserPrefsStorage;
import seedu.uninurse.storage.Storage;
import seedu.uninurse.storage.StorageManager;
import seedu.uninurse.storage.UninurseBookStorage;
import seedu.uninurse.storage.UserPrefsStorage;
import seedu.uninurse.ui.Ui;
import seedu.uninurse.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing UninurseBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        UninurseBookStorage uninurseBookStorage = new JsonUninurseBookStorage(userPrefs.getUninurseBookFilePath());
        storage = new StorageManager(uninurseBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s uninurse book and {@code userPrefs}. <br>
     * The data from the sample uninurse book will be used instead if {@code storage}'s uninurse book is not found,
     * or an empty uninurse book will be used instead if errors occur when reading {@code storage}'s uninurse book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyUninurseBook> uninurseBookOptional;
        ReadOnlyUninurseBook initialData;
        try {
            uninurseBookOptional = storage.readUninurseBook();
            if (!uninurseBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample UninurseBook");
            }
            initialData = uninurseBookOptional.orElseGet(SampleDataUtil::getSampleUninurseBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty UninurseBook");
            initialData = new UninurseBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty UninurseBook");
            initialData = new UninurseBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty UninurseBook");
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
        logger.info("Starting UninurseBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping UniNurse Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
