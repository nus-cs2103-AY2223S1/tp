package friday;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import friday.commons.core.LogsCenter;
import friday.commons.util.FileUtil;
import javafx.application.Application;

/**
 * Represents the parsed command-line parameters given to the application.
 */
public class AppParameters {
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    private Path configPath;

    public Path getConfigPath() {
        return configPath;
    }

    public void setConfigPath(Path configPath) {
        this.configPath = configPath;
    }

    /**
     * Parses the application command-line parameters.
     */
    public static AppParameters parse(Application.Parameters parameters) {
        AppParameters appParameters = new AppParameters();
        Map<String, String> namedParameters = parameters.getNamed();

        String configPathParameter = namedParameters.get("config");
        if (configPathParameter != null && !FileUtil.isValidPath(configPathParameter)) {
            logger.warning("Invalid config path " + configPathParameter + ". Using default config path.");
            configPathParameter = null;
        }
        appParameters.setConfigPath(configPathParameter != null ? Paths.get(configPathParameter) : null);

        return appParameters;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppParameters)) {
            return false;
        }

        AppParameters otherAppParameters = (AppParameters) other;
        return Objects.equals(getConfigPath(), otherAppParameters.getConfigPath());
    }

    @Override
    public int hashCode() {
        return configPath.hashCode();
    }
}
