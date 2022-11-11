package tracko.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import tracko.commons.core.index.Index;
import tracko.model.Model;
import tracko.model.order.Order;

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
     * Returns the middle index of the person in the {@code model}'s order list.
     */
    public static Index getMidIndexOfOrderList(Model model) {
        return Index.fromOneBased(model.getOrderList().size() / 2);
    }

    /**
     * Returns the last index of the person in the {@code model}'s order list.
     */
    public static Index getLastIndexOfOrderList(Model model) {
        return Index.fromOneBased(model.getOrderList().size());
    }

    /**
     * Returns the person in the {@code model}'s person list at {@code index}.
     */
    public static Order getOrder(Model model, Index index) {
        return model.getOrderList().get(index.getZeroBased());
    }
}
