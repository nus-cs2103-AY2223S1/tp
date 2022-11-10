package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    public static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

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
     * Returns the middle index of the person in the {@code model}'s buyer list.
     */
    public static Index getMidIndexFromBuyerList(Model model) {
        return Index.fromOneBased(model.getFilteredBuyerList().size() / 2);
    }

    /**
     * Returns the last index of the person in the {@code model}'s buyer list.
     */
    public static Index getLastIndexFromBuyerList(Model model) {
        return Index.fromOneBased(model.getFilteredBuyerList().size());
    }

    /**
     * Returns the buyer in the {@code model}'s buyer list at {@code index}.
     */
    public static Person getBuyer(Model model, Index index) {
        return model.getFilteredBuyerList().get(index.getZeroBased());
    }
}
