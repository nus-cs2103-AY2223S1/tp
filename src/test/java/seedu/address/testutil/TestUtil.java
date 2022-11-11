package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecords.RECORD1;
import static seedu.address.testutil.TypicalRecords.RECORD2;
import static seedu.address.testutil.TypicalRecords.RECORD3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

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
     * Returns a Model for FindRecordCommand and DeleteRecordCommand test.
     */
    public static Model prepareModel() {
        Person benson = new PersonBuilder().withName("Benson Meier")
                .withBirthdate("08-08-1988")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withTags("owesMoney", "friends").withRecordList(RECORD1, RECORD2, RECORD3).build();

        Model testModal = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        testModal.setFilteredRecordList(benson);
        testModal.setRecordListDisplayed(true);
        return testModal;
    }
}
