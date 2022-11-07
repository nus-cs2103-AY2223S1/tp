package seedu.boba.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.boba.commons.core.index.Index;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.customer.Customer;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the customer in the {@code bobaBotModel}'s customer list.
     */
    public static Index getMidIndex(BobaBotModel bobaBotModel) {
        return Index.fromOneBased(bobaBotModel.getFilteredPersonList().size() / 2);
    }

    /**
     * Returns the last index of the customer in the {@code bobaBotModel}'s customer list.
     */
    public static Index getLastIndex(BobaBotModel bobaBotModel) {
        return Index.fromOneBased(bobaBotModel.getFilteredPersonList().size());
    }

    /**
     * Returns the customer in the {@code bobaBotModel}'s customer list at {@code index}.
     */
    public static Customer getPerson(BobaBotModel bobaBotModel, Index index) {
        return bobaBotModel.getFilteredPersonList().get(index.getZeroBased());
    }
}
