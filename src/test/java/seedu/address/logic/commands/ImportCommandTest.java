package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.filename.FileName;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ImportCommandTest {
    private static final Path TEST_PATH = Paths.get("import", "test.csv");
    private static final File TEST_FILE = new File(TEST_PATH.toUri());
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        if (TEST_FILE.exists()) {
            TEST_FILE.delete();
        }
    }

    @Test
    public void execute_fileNameSpecifiedDoesNotExist_failure() {
        assertCommandFailure(new ImportCommand(new FileName("test")), model, ImportCommand.FILE_DOES_NOTE_EXITS);
    }
}
