package seedu.rc4hdb.logic.commands.storagemodelcommands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Contains helper methods for testing storage commands.
 */
public class StorageCommandTestUtil {

    public static final String VALID_FILE_NAME_STRING = "test";
    public static final String VALID_FILE_NAME_STRING_JSON = "data/" + VALID_FILE_NAME_STRING + ".json";
    public static final Path VALID_FILE_NAME_PATH = Path.of(VALID_FILE_NAME_STRING_JSON);

    public static final String INVALID_FILE_NAME_FULL_STOP = "test.";
    public static final String INVALID_FILE_NAME_WHITESPACE = "te st";
    public static final String INVALID_FILE_NAME_FORWARD_SLASH = "/test";
    public static final String INVALID_FILE_NAME_BACKSLASH = "\\test";

    /**
     * Creates a temporary file for testing purposes.
     */
    public static void createTempFile(Path tempFilePath) throws IOException {
        File tempFile = tempFilePath.toFile();
        tempFile.createNewFile();
        tempFile.deleteOnExit();
    }

}
