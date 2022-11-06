package hobbylist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import hobbylist.commons.core.Config;
import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.core.Version;
import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.commons.util.ConfigUtil;
import hobbylist.commons.util.StringUtil;
import hobbylist.logic.Logic;
import hobbylist.logic.LogicManager;
import hobbylist.model.HobbyList;
import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.ReadOnlyUserPrefs;
import hobbylist.model.UserPrefs;
import hobbylist.model.util.SampleDataUtil;
import hobbylist.storage.HobbyListStorage;
import hobbylist.storage.JsonHobbyListStorage;
import hobbylist.storage.JsonUserPrefsStorage;
import hobbylist.storage.Storage;
import hobbylist.storage.StorageManager;
import hobbylist.storage.UserPrefsStorage;
import hobbylist.ui.Ui;
import hobbylist.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing HobbyList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        HobbyListStorage hobbyListStorage = new JsonHobbyListStorage(userPrefs.getHobbyListFilePath());
        storage = new StorageManager(hobbyListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s HobbyList and {@code userPrefs}. <br>
     * The data from the sample HobbyList will be used instead if {@code storage}'s HobbyList is not found,
     * or an empty HobbyList will be used instead if errors occur when reading {@code storage}'s HobbyList.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyHobbyList> hobbyListOptional;
        ReadOnlyHobbyList initialData;
        try {
            hobbyListOptional = storage.readHobbyList();
            if (!hobbyListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample HobbyList");
            }
            initialData = hobbyListOptional.orElseGet(SampleDataUtil::getSampleHobbyList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty HobbyList");
            initialData = new HobbyList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty HobbyList");
            initialData = new HobbyList();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty HobbyList");
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
        logger.info("Starting HobbyList " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping HobbyList ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
