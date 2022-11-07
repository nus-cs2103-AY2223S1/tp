package bookface.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import bookface.commons.util.StringUtil;
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
