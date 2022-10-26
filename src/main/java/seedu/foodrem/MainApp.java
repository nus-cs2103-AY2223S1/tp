package seedu.foodrem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.foodrem.commons.core.Config;
import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.commons.core.Version;
import seedu.foodrem.commons.exceptions.DataConversionException;
import seedu.foodrem.commons.util.ConfigUtil;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.Logic;
import seedu.foodrem.logic.LogicManager;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.ReadOnlyUserPrefs;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.util.SampleDataUtil;
import seedu.foodrem.storage.FoodRemStorage;
import seedu.foodrem.storage.JsonFoodRemStorage;
import seedu.foodrem.storage.JsonUserPrefsStorage;
import seedu.foodrem.storage.Storage;
import seedu.foodrem.storage.StorageManager;
import seedu.foodrem.storage.UserPrefsStorage;
import seedu.foodrem.ui.Ui;
import seedu.foodrem.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {
    private static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private Ui ui;
    private Storage storage;
    private Model model;

    private String initialMessage = "Welcome to FoodRem!";

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing FoodRem ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        Config config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FoodRemStorage foodRemStorage = new JsonFoodRemStorage(userPrefs.getFoodRemFilePath());
        storage = new StorageManager(foodRemStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        Logic logic = new LogicManager(model, storage);
        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s foodRem and {@code userPrefs}. <br>
     * The data from the sample foodRem will be used instead if {@code storage}'s foodRem is not found,
     * or an empty foodRem will be used instead if errors occur when reading {@code storage}'s foodRem.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ReadOnlyFoodRem initialData;
        try {
            Optional<ReadOnlyFoodRem> foodRemOptional = storage.readFoodRem();
            if (foodRemOptional.isEmpty()) {
                initialMessage = "Data file not found. Will be starting with a sample FoodRem.";
                logger.info(initialMessage);
            }
            initialData = foodRemOptional.orElseGet(SampleDataUtil::getSampleFoodRem);
        } catch (DataConversionException e) {
            initialMessage = "Data file not in the correct format. Will be starting with an empty FoodRem.";
            logger.warning(initialMessage);
            initialData = new FoodRem();
        } catch (IOException e) {
            initialMessage = "Problem while reading from the file. Will be starting with an empty FoodRem";
            logger.warning(initialMessage);
            initialData = new FoodRem();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty FoodRem");
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
        logger.info("Starting FoodRem " + MainApp.VERSION);
        ui.start(primaryStage, initialMessage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping FoodRem ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
