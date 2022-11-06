package seedu.taassist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.taassist.commons.core.Config;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.commons.core.Version;
import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.commons.util.ConfigUtil;
import seedu.taassist.commons.util.FileUtil;
import seedu.taassist.commons.util.StringUtil;
import seedu.taassist.logic.Logic;
import seedu.taassist.logic.LogicManager;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.ReadOnlyUserPrefs;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.util.SampleDataUtil;
import seedu.taassist.storage.JsonTaAssistStorage;
import seedu.taassist.storage.JsonUserPrefsStorage;
import seedu.taassist.storage.Storage;
import seedu.taassist.storage.StorageManager;
import seedu.taassist.storage.TaAssistStorage;
import seedu.taassist.storage.UserPrefsStorage;
import seedu.taassist.ui.Ui;
import seedu.taassist.ui.UiManager;

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
    public void start(Stage primaryStage) {
        logger.info("=============================[ Initializing TaAssist ]===========================");

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TaAssistStorage taAssistStorage = new JsonTaAssistStorage(userPrefs.getTaAssistFilePath());
        storage = new StorageManager(taAssistStorage, userPrefsStorage);

        initLogging(config);

        try {
            Path taAssistFilePath = userPrefs.getTaAssistFilePath();
            if (FileUtil.isFileExists(taAssistFilePath)) {
                storage.backupFile(taAssistFilePath);
            }
        } catch (IOException e) {
            logger.warning("Failed to backup data file");
        }

        model = initModelManager(storage, userPrefs);
        logic = new LogicManager(model, storage);
        ui = new UiManager(logic);

        logger.info("Starting TaAssist " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s TaAssist and {@code userPrefs}. <br>
     * The data from the sample TaAssist will be used instead if {@code storage}'s TaAssist is not found,
     * or an empty TaAssist will be used instead if errors occur when reading {@code storage}'s TaAssist.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTaAssist> taAssistOptional;
        ReadOnlyTaAssist initialData;
        try {
            taAssistOptional = storage.readTaAssist();
            if (!taAssistOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample TaAssist");
            }
            initialData = taAssistOptional.orElseGet(SampleDataUtil::getSampleTaAssist);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Alerting user.");
            confirmOrExit("Data file not in the correct format. Do you want to continue with an empty data file?");
            initialData = new TaAssist();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Alerting user.");
            confirmOrExit("Problem while reading from the file. Do you want to continue with an empty data file?");
            initialData = new TaAssist();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Shows an error alert with the given {@code message} and gets the user's confirmation.
     * If the user does not confirm, then the application will exit.
     */
    private void confirmOrExit(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Error");

        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.NO) {
            Platform.exit();
            System.exit(0);
        }
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
            logger.warning("Problem while reading from the file. Will be starting with an empty TaAssist");
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
    public void stop() {
        logger.info("============================ [ Stopping TA-Assist ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
