package bookface.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import bookface.commons.core.index.Index;
import bookface.commons.util.StringUtil;
import bookface.model.Model;
import bookface.model.ObjectContainsKeywordsPredicate;
import bookface.model.person.Person;

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
     * Returns the middle index of the person in the {@code model}'s person list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPersonList().size() / 2);
    }

    /**
     * Returns the last index of the person in the {@code model}'s person list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPersonList().size());
    }

    /**
     * Returns the person in the {@code model}'s person list at {@code index}.
     */
    public static Person getPerson(Model model, Index index) {
        return model.getFilteredPersonList().get(index.getZeroBased());
    }

    /**
     * Parses {@code userInput} from a String into a {@code ObjectContainsKeywordsPredicate}.
     */
    public static ObjectContainsKeywordsPredicate<Person, String>
        preparePredicateToCheckPersonForPartialWordIgnoreCase(String userInput) {
        return new ObjectContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")), person ->
                keyword -> StringUtil.containsPartialWordIgnoreCase(person.getName().fullName, keyword));
    }

    /**
     * Parses {@code userInput} from a List into a {@code ObjectContainsKeywordsPredicate}.
     */
    public static ObjectContainsKeywordsPredicate<Person, String>
        preparePredicateToCheckPersonForPartialWordIgnoreCase(List<? extends String> userInput) {
        return new ObjectContainsKeywordsPredicate<>(userInput, person ->
                keyword -> StringUtil.containsPartialWordIgnoreCase(person.getName().fullName, keyword));
    }
}
