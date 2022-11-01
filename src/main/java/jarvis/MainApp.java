package jarvis;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import jarvis.commons.core.Config;
import jarvis.commons.core.LogsCenter;
import jarvis.commons.core.Version;
import jarvis.commons.exceptions.DataConversionException;
import jarvis.commons.util.ConfigUtil;
import jarvis.commons.util.StringUtil;
import jarvis.logic.Logic;
import jarvis.logic.LogicManager;
import jarvis.model.LessonBook;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.ReadOnlyUserPrefs;
import jarvis.model.StudentBook;
import jarvis.model.TaskBook;
import jarvis.model.UserPrefs;
import jarvis.model.util.SampleLessonUtil;
import jarvis.model.util.SampleStudentUtil;
import jarvis.model.util.SampleTaskUtil;
import jarvis.storage.JsonLessonBookStorage;
import jarvis.storage.JsonStudentBookStorage;
import jarvis.storage.JsonTaskBookStorage;
import jarvis.storage.JsonUserPrefsStorage;
import jarvis.storage.LessonBookStorage;
import jarvis.storage.Storage;
import jarvis.storage.StorageManager;
import jarvis.storage.StudentBookStorage;
import jarvis.storage.TaskBookStorage;
import jarvis.storage.UserPrefsStorage;
import jarvis.ui.Ui;
import jarvis.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

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
        logger.info("=============================[ Initializing JARVIS ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        StudentBookStorage studentBookStorage = new JsonStudentBookStorage(userPrefs.getStudentBookFilePath());
        TaskBookStorage taskBookStorage = new JsonTaskBookStorage(userPrefs.getTaskBookFilePath());
        LessonBookStorage lessonBookStorage = new JsonLessonBookStorage(userPrefs.getLessonBookFilePath());
        storage = new StorageManager(studentBookStorage, taskBookStorage, lessonBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s student, task and lesson books and
     * {@code userPrefs}. <br> The data from the sample student, task or lesson book will be used instead if
     * {@code storage}'s student, task or lesson book is not found, or an empty student, task or lesson book will
     * be used instead if errors occur when reading {@code storage}'s student, task or lesson book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyStudentBook> studentBookOptional;
        Optional<ReadOnlyTaskBook> taskBookOptional;
        Optional<ReadOnlyLessonBook> lessonBookOptional;
        ReadOnlyStudentBook initialStudentData = new StudentBook();
        ReadOnlyTaskBook initialTaskData = new TaskBook();
        ReadOnlyLessonBook initialLessonData = new LessonBook();
        try {
            studentBookOptional = storage.readStudentBook();
            if (!studentBookOptional.isPresent()) {
                logger.info("Student data file not found. Will be starting with a sample student book");
            }
            initialStudentData = studentBookOptional.orElseGet(SampleStudentUtil::getSampleStudentBook);

            taskBookOptional = storage.readTaskBook();
            if (!taskBookOptional.isPresent()) {
                logger.info("Task data file not found. Will be starting with a sample task book");
            }
            initialTaskData = taskBookOptional.orElseGet(SampleTaskUtil::getSampleTaskBook);

            lessonBookOptional = storage.readLessonBook();
            if (!lessonBookOptional.isPresent()) {
                logger.info("Lesson data file not found. Will be starting with a sample lesson book");
            }
            initialLessonData = lessonBookOptional.orElseGet(SampleLessonUtil::getSampleLessonBook);
        } catch (DataConversionException e) {
            logger.warning("Data file(s) not in the correct format. Will be starting with empty book(s)");
        } catch (IOException e) {
            logger.warning("Problem while reading from the file(s). Will be starting with empty book(s)");
        }
        return new ModelManager(initialStudentData, initialTaskData, initialLessonData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty JARVIS");
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
        logger.info("Starting JARVIS " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping JARVIS ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
