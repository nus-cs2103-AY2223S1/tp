package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t13-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more detailed instructions, please refer to the user guide: "
            + USERGUIDE_URL;

    public static final String COMMAND_LIST = "Command\n"
            + "\nModifying address book entries: \n"
            + "add <parameters>\n"
            + "delete <index>\n"
            + "clear\n"
            + "\nModifying information of a candidate: \n"
            + "edit <index> <parameters>\n"
            + "addTag <index> <parameters>\n"
            + "deleteTag <index> <parameters>\n"
            + "create <tag_type> <parameters>\n"
            + "editTagType <old_tag>-<new_tag> <old_alias>-<new_alias>\n"
            + "deleteTagType <tag_type>\n"
            + "note <index> <note>\n"
            + "\nViewing Candidates:\n"
            + "list\n"
            + "find <keyword>\n"
            + "\nGeneral:\n"
            + "help\n"
            + "exit\n";

    public static final String DESCRIPTION_LIST = "Description\n\n"
            + "\nAdds a candidate to the address book.\n"
            + "Delete a candidate by index\n"
            + "Clear all contacts\n"
            + "\n\n"
            + "Edits an existing candidate in the address book by index\n"
            + "Adds a tag to an existing candidate in the address book\n"
            + "Deletes a tag of an existing candidate in the address book\n"
            + "Creates a custom tag type apart from the existing tag types\n"
            + "Edits the name and alias of an existing tag type to new_tag and new_alias\n"
            + "Deletes an existing tag type and its corresponding tag alias\n"
            + "Adds/Edits additional optional information (notes) to the candidate\n"
            + "\n\n"
            + "List all contacts\n"
            + "Find contacts by keywords\n"
            + "\n\n"
            + "Show all available commands\n"
            + "Quit bot";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label commandList;

    @FXML
    private Label descriptionList;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        commandList.setText(COMMAND_LIST);
        descriptionList.setText(DESCRIPTION_LIST);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
