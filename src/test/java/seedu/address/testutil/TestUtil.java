package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

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
     * Returns the middle index of the profile in the {@code model}'s profile list.
     */
    public static Index getProfileMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredProfileList().size() / 2);
    }

    /**
     * Returns the middle index of the event in the {@code model}'s event list.
     */
    public static Index getEventMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredEventList().size() / 2);
    }

    /**
     * Returns the last index of the profile in the {@code model}'s profile list.
     */
    public static Index getProfileLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredProfileList().size());
    }

    /**
     * Returns the last index of the event in the {@code model}'s event list.
     */
    public static Index getEventLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredEventList().size());
    }

    /**
     * Returns the profile in the {@code model}'s profile list at {@code index}.
     */
    public static Profile getProfile(Model model, Index index) {
        return model.getFilteredProfileList().get(index.getZeroBased());
    }

    /**
     * Returns the event in the {@code model}'s event list at {@code index}.
     */
    public static Event getEvent(Model model, Index index) {
        return model.getFilteredEventList().get(index.getZeroBased());
    }

}
