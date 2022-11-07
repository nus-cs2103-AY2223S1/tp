package guitests.guihandles;

import javafx.scene.control.TextArea;

// @@author pyokagan-reused
// Test code adapted from AddressBook Level 4 https://se-education.org/addressbook-level4/ with modifications
/**
 * A handler for the {@code ResultDisplay} of the UI
 */
public class ResultDisplayHandle extends NodeHandle<TextArea> {

    public static final String RESULT_DISPLAY_ID = "#resultDisplay";

    public ResultDisplayHandle(TextArea resultDisplayNode) {
        super(resultDisplayNode);
    }

    /**
     * Returns the text in the result display.
     */
    public String getText() {
        return getRootNode().getText();
    }
}
// @@author
