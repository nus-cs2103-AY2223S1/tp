package seedu.classify;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.classify.commons.core.Config;
import seedu.classify.commons.core.LogsCenter;
import seedu.classify.commons.core.Version;
import seedu.classify.commons.exceptions.DataConversionException;
import seedu.classify.commons.util.ConfigUtil;
import seedu.classify.commons.util.StringUtil;
import seedu.classify.logic.Logic;
import seedu.classify.logic.LogicManager;
import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.ReadOnlyUserPrefs;
import seedu.classify.model.StudentRecord;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.util.SampleDataUtil;
import seedu.classify.storage.JsonStudentRecordStorage;
import seedu.classify.storage.JsonUserPrefsStorage;
import seedu.classify.storage.Storage;
import seedu.classify.storage.StorageManager;
import seedu.classify.storage.StudentRecordStorage;
import seedu.classify.storage.UserPrefsStorage;
import seedu.classify.ui.Ui;
import seedu.classify.ui.UiManager;

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
        logger.info("=============================[ Initializing Class-ify ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        StudentRecordStorage studentRecordStorage = new JsonStudentRecordStorage(userPrefs.getStudentRecordFilePath());
        storage = new StorageManager(studentRecordStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Student Record and {@code userPrefs}. <br>
     * The data from the sample Student Record will be used instead if {@code storage}'s Student Record is not found,
     * or an empty Student Record will be used instead if errors occur when reading {@code storage}'s Student Record.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyStudentRecord> studentRecordOptional;
        ReadOnlyStudentRecord initialData;
        try {
            studentRecordOptional = storage.readStudentRecord();
            if (!studentRecordOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Class-ify Record");
            }
            initialData = studentRecordOptional.orElseGet(SampleDataUtil::getSampleStudentRecord);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Class-ify Record");
            initialData = new StudentRecord();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Class-ify Record");
            initialData = new StudentRecord();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Class-ify");
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
        logger.info("Starting Class-ify " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Class-ify ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
