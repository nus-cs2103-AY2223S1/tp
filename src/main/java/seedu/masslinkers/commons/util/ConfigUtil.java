package seedu.masslinkers.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.masslinkers.commons.core.Config;
import seedu.masslinkers.commons.exceptions.DataConversionException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
