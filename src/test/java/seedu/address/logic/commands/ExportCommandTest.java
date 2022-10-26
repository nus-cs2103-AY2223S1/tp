package seedu.address.logic.commands;

// import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
// import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import seedu.address.commons.core.filename.FileName;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


class ExportCommandTest {
    private static final Path TEST_PATH = Paths.get("export", "test.csv");
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

    /*
    @Test
    public void execute_fileNameSpecifiedIsNotDuplicate_fileCreatedSuccessfully() {
        assertCommandSuccess(new ExportCommand(new FileName("test")),
                model, ExportCommand.MESSAGE_EXPORT_SUCCESS, expectedModel);
        TEST_FILE.delete();
    }

    @Test
    public void execute_fileNameSpecifiedIsDuplicate_failure() {
        try {
            TEST_FILE.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertCommandFailure(new ExportCommand(new FileName("test")),
                model, ExportCommand.DUPLICATE_FILE_NAME);
        TEST_FILE.delete();
    }
     */
}
