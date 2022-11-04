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
import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TeachersPet;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.ClassStorage;
import seedu.address.storage.JsonTeachersPetStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.TeachersPetStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

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
    protected ClassStorage classStorage;
    protected static boolean isInInvalidFormat;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing TeachersPet ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TeachersPetStorage teachersPetStorage = new JsonTeachersPetStorage(userPrefs.getTeachersPetFilePath());
        storage = new StorageManager(teachersPetStorage, userPrefsStorage);

        initLogging(config);
        isInInvalidFormat = false;

        Model initializedModel = initModelManager(storage, userPrefs);
        try {
            classStorage = new ClassStorage(initializedModel);
            model = initializedModel;
        } catch (DataConversionException e) {
            logger.warning(ClassStorage.MESSAGE_INITIALIZE_CLASS_STORAGE_FAILURE
                    + " Will be starting with an empty TeachersPet");
            model = new ModelManager(new TeachersPet(), userPrefs);
            classStorage = new ClassStorage(model);
            isInInvalidFormat = true;
        }
        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     * The app will present empty data if invalid data format shows up when reading {@code storage}'s address book,
     * but the teachersPet json file will still contain the invalid data to allow user to correct them.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTeachersPet> teachersPetOptional;
        ReadOnlyTeachersPet initialData;
        try {
            teachersPetOptional = storage.readTeachersPet();
            if (!teachersPetOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample TeachersPet");
            }
            initialData = teachersPetOptional.orElseGet(SampleDataUtil::getSampleTeachersPet);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty TeachersPet");
            initialData = new TeachersPet();
            isInInvalidFormat = true;
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TeachersPet");
            initialData = new TeachersPet();
            isInInvalidFormat = true;
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
            logger.warning("Problem while reading from the file. Will be starting with an empty TeachersPet");
            initializedPrefs = new UserPrefs();
            isInInvalidFormat = true;
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    public static boolean isInInvalidFormat() {
        return isInInvalidFormat;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting TeachersPet " + MainApp.VERSION);
        ui.start(primaryStage);
        if (isInInvalidFormat) {
            ui.showInvalidFormatInJsonFile();
        }
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            if (!isInInvalidFormat) {
                storage.saveUserPrefs(model.getUserPrefs());
            }
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
