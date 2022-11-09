package seedu.guest.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.guest.commons.core.index.Index;
import seedu.guest.model.Model;
import seedu.guest.model.guest.Guest;

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
     * Returns the middle index of the guest in the {@code model}'s guest list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredGuestList().size() / 2);
    }

    /**
     * Returns the last index of the guest in the {@code model}'s guest list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredGuestList().size());
    }

    /**
     * Returns the guest in the {@code model}'s guest list at {@code index}.
     */
    public static Guest getGuest(Model model, Index index) {
        return model.getFilteredGuestList().get(index.getZeroBased());
    }
}
