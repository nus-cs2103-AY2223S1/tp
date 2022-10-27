package seedu.address.testutil;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A utility class containing a list of {@code Path} objects to be used in tests.
 */
public class TypicalFilePaths {
    public static final Path PATH_TO_JERRY_JPG = Paths.get("src", "folder", "jerry.jpg");
    public static final Path PATH_TO_JERRY_WITH_SPACE_JPG =
            Paths.get("src", "folder", "jerry with space.jpg");
    public static final Path PATH_TO_TOM_JPG = Paths.get("src", "folder", "tom.jpg");
}
