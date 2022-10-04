package seedu.guest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.guest.commons.core.Config;
import seedu.guest.commons.core.LogsCenter;
import seedu.guest.commons.core.Version;
import seedu.guest.commons.exceptions.DataConversionException;
import seedu.guest.commons.util.ConfigUtil;
import seedu.guest.commons.util.StringUtil;
import seedu.guest.logic.Logic;
import seedu.guest.logic.LogicManager;
import seedu.guest.model.GuestBook;
import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.ReadOnlyGuestBook;
import seedu.guest.model.ReadOnlyUserPrefs;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.util.SampleDataUtil;
import seedu.guest.storage.GuestBookStorage;
import seedu.guest.storage.JsonGuestBookStorage;
import seedu.guest.storage.JsonUserPrefsStorage;
import seedu.guest.storage.Storage;
import seedu.guest.storage.StorageManager;
import seedu.guest.storage.UserPrefsStorage;
import seedu.guest.ui.Ui;
import seedu.guest.ui.UiManager;

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
        logger.info("=============================[ Initializing GuestBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        GuestBookStorage guestBookStorage = new JsonGuestBookStorage(userPrefs.getGuestBookFilePath());
        storage = new StorageManager(guestBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s guest book and {@code userPrefs}. <br>
     * The data from the sample guest book will be used instead if {@code storage}'s guest book is not found,
     * or an empty guest book will be used instead if errors occur when reading {@code storage}'s guest book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyGuestBook> guestBookOptional;
        ReadOnlyGuestBook initialData;
        try {
            guestBookOptional = storage.readGuestBook();
            if (!guestBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample GuestBook");
            }
            initialData = guestBookOptional.orElseGet(SampleDataUtil::getSampleGuestBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty GuestBook");
            initialData = new GuestBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty GuestBook");
            initialData = new GuestBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty GuestBook");
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
        logger.info("Starting GuestBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Guest Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
