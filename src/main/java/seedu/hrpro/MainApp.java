package seedu.hrpro;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.hrpro.commons.core.Config;
import seedu.hrpro.commons.core.LogsCenter;
import seedu.hrpro.commons.core.Version;
import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.commons.util.ConfigUtil;
import seedu.hrpro.commons.util.StringUtil;
import seedu.hrpro.logic.Logic;
import seedu.hrpro.logic.LogicManager;
import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.ReadOnlyHrPro;
import seedu.hrpro.model.ReadOnlyUserPrefs;
import seedu.hrpro.model.UserPrefs;
import seedu.hrpro.model.util.SampleDataUtil;
import seedu.hrpro.storage.HrProStorage;
import seedu.hrpro.storage.JsonHrProStorage;
import seedu.hrpro.storage.JsonUserPrefsStorage;
import seedu.hrpro.storage.Storage;
import seedu.hrpro.storage.StorageManager;
import seedu.hrpro.storage.UserPrefsStorage;
import seedu.hrpro.ui.Ui;
import seedu.hrpro.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing HrPro ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        HrProStorage hrProStorage = new JsonHrProStorage(userPrefs.getHrProFilePath());
        storage = new StorageManager(hrProStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s hr pro and {@code userPrefs}. <br>
     * The data from the sample hr pro will be used instead if {@code storage}'s hr pro is not found,
     * or an empty hr pro will be used instead if errors occur when reading {@code storage}'s hr pro.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyHrPro> hrProOptional;
        ReadOnlyHrPro initialData;
        try {
            hrProOptional = storage.readHrPro();
            if (!hrProOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample HrPro");
            }
            initialData = hrProOptional.orElseGet(SampleDataUtil::getSampleHrPro);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty HrPro");
            initialData = new HrPro();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty HrPro");
            initialData = new HrPro();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty HrPro");
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
        logger.info("Starting HrPro " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping HR Pro ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
