package seedu.nutrigoals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.nutrigoals.commons.core.Config;
import seedu.nutrigoals.commons.core.LogsCenter;
import seedu.nutrigoals.commons.core.Version;
import seedu.nutrigoals.commons.exceptions.DataConversionException;
import seedu.nutrigoals.commons.util.ConfigUtil;
import seedu.nutrigoals.commons.util.StringUtil;
import seedu.nutrigoals.logic.Logic;
import seedu.nutrigoals.logic.LogicManager;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.ReadOnlyUserPrefs;
import seedu.nutrigoals.model.UserPrefs;
import seedu.nutrigoals.model.util.SampleDataUtil;
import seedu.nutrigoals.storage.JsonNutriGoalsStorage;
import seedu.nutrigoals.storage.JsonUserPrefsStorage;
import seedu.nutrigoals.storage.NutriGoalsStorage;
import seedu.nutrigoals.storage.Storage;
import seedu.nutrigoals.storage.StorageManager;
import seedu.nutrigoals.storage.UserPrefsStorage;
import seedu.nutrigoals.ui.Ui;
import seedu.nutrigoals.ui.UiManager;

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
        logger.info("=============================[ Initializing NutriGoals ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        NutriGoalsStorage nutriGoalsStorage = new JsonNutriGoalsStorage(userPrefs.getNutriGoalsFilePath());
        storage = new StorageManager(nutriGoalsStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s nutrigoals and {@code userPrefs}. <br>
     * The data from the sample nutrigoals will be used instead if {@code storage}'s nutrigoals is not found,
     * or an empty nutrigoals will be used instead if errors occur when reading {@code storage}'s nutrigoals.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyNutriGoals> nutriGoalsOptional;
        ReadOnlyNutriGoals initialData;
        try {
            nutriGoalsOptional = storage.readNutriGoals();
            if (!nutriGoalsOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample NutriGoals");
            }
            initialData = nutriGoalsOptional.orElseGet(SampleDataUtil::getSampleNutriGoals);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty NutriGoals");
            initialData = new NutriGoals();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty NutriGoals");
            initialData = new NutriGoals();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty NutriGoals");
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
        logger.info("Starting NutriGoals " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping NutriGoals ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
