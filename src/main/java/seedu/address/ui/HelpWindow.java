package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t11-4.github.io/tp/UserGuide.html\n";
    public static final String COMMAND_SUMMARY = "\nCOMMAND SUMMARY\n"
            + "– help\n"
            + "– add: add n/NAME g/GITHUB_REPO_LINK [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]\n"
            + "– list\n"
            + "– edit: edit INDEX [n/NAME] [g/GITHUB_REPO_LINK] [p/PHONE] [e/EMAIL] [t/TAG]\n"
            + "– sort: sort TAG\n"
            + "– find: find KEYWORD\n"
            + "– delete: delete INDEX\n"
            + "– exit\n";
    public static final String HELP_MESSAGE = "Refer to the user guide for more details: "
            + USERGUIDE_URL
            + COMMAND_SUMMARY;


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
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
     * Opens the user guide in the browser.
     */
    @FXML
    private void openGuide() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://ay2223s1-cs2103t-t11-4.github.io/tp/UserGuide.html"));
    }
}
