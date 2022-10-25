package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.net.URL;

import org.junit.jupiter.api.Test;

import javafx.fxml.FXML;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.TypicalBuyers;

public class BuyerListPanelTest {

    private static final String MISSING_FILE_PATH = "UiPartTest/missingFile.fxml";
    private static final String INVALID_FILE_PATH = "UiPartTest/invalidFile.fxml";
    private static final String VALID_FILE_PATH = "UiPartTest/validFile.fxml";
    private static final String VALID_FILE_WITH_FX_ROOT_PATH = "UiPartTest/validFileWithFxRoot.fxml";
    private static final TestFxmlObject VALID_FILE_ROOT = new TestFxmlObject("Hello World!");

    private static Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private static Storage storage = new StorageManager(null, null);

    @Test
    public void constructor_nullFileUrl_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TestUiPart<Object>((URL) null));
        assertThrows(NullPointerException.class, () -> new TestUiPart<Object>((URL) null, new Object()));
    }

    /**
     * UiPart used for testing.
     * It should only be used with invalid FXML files or the valid file located.
     */
    private static class TestUiPart<T> extends UiPart<T> {

        @FXML
        private TestFxmlObject validFileRoot; // Check that @FXML annotations work

        TestUiPart(URL fxmlFileUrl, T root) {
            super(fxmlFileUrl, root);
        }

        TestUiPart(String fxmlFileName, T root) {
            super(fxmlFileName, root);
        }

        TestUiPart(URL fxmlFileUrl) {
            super(fxmlFileUrl);
            assertEquals(VALID_FILE_ROOT, validFileRoot);
        }

        TestUiPart(String fxmlFileName) {
            super(fxmlFileName);
            assertEquals(VALID_FILE_ROOT, validFileRoot);
        }

    }
}
