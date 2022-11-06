package bookface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import bookface.commons.core.Config;
import bookface.commons.core.LogsCenter;
import bookface.commons.core.Version;
import bookface.commons.exceptions.DataConversionException;
import bookface.commons.util.ConfigUtil;
import bookface.commons.util.StringUtil;
import bookface.logic.Logic;
import bookface.logic.LogicManager;
import bookface.model.BookFace;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.ReadOnlyBookFace;
import bookface.model.ReadOnlyUserPrefs;
import bookface.model.UserPrefs;
import bookface.model.util.SampleDataUtil;
import bookface.storage.BookFaceStorage;
import bookface.storage.JsonBookFaceStorage;
import bookface.storage.JsonUserPrefsStorage;
import bookface.storage.Storage;
import bookface.storage.StorageManager;
import bookface.storage.UserPrefsStorage;
import bookface.ui.Ui;
import bookface.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

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
        logger.info("=============================[ Initializing BookFace ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        BookFaceStorage bookFaceStorage = new JsonBookFaceStorage(userPrefs.getBookFaceFilePath());
        storage = new StorageManager(bookFaceStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s BookFace and {@code userPrefs}. <br>
     * The data from the sample BookFace will be used instead if {@code storage}'s BookFace is not found,
     * or an empty BookFace will be used instead if errors occur when reading {@code storage}'s BookFace.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyBookFace> bookFaceOptional;
        ReadOnlyBookFace initialData;
        try {
            bookFaceOptional = storage.readBookFace();
            if (bookFaceOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample BookFace");
            }
            initialData = bookFaceOptional.orElseGet(SampleDataUtil::getSampleBookFace);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty BookFace");
            initialData = new BookFace();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty BookFace");
            initialData = new BookFace();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty BookFace");
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
        logger.info("Starting BookFace " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping BookFace ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
