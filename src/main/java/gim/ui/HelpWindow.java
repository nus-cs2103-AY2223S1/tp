package gim.ui;

import java.util.logging.Logger;

import gim.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t15-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = USERGUIDE_URL;

    public static final String HELP_PARA = "Hi there, welcome to GIM! Here is a list of supported commands.\n"
            + "1) :add - Adds an exercise\n"
            + "2) :del - Deletes an exercise\n"
            + "3) :list - Lists all exercise entries\n"
            + "4) :find - Finds exercises by their name\n"
            + "5) :clear - Clears the saved exercises and resets the data in the system\n"
            + "6) :sort - Sorts the exercises by date, if they have the same date, by name \n"
            + "7) :range - Displays the exercises saved over a range of days\n"
            + "8) :pr - Displays the stored Personal Records\n"
            + "9) :gen - Generates a sample workout based on your Personal Records\n"
            + "10) :help - displays the help menu\n"
            + "11) :wq - exits the application\n"
            + "Notes:\n"
            + "- For Add, Delete, Find, Range, Pr, Gen, simply type the command without"
            + " parameters and example usages will appear "
            + "in the result box on the top right.\n"
            + "- Other commands function without parameter inputs.\n"
            + "- Gen, find and pr commands support one or multiple exercises\n"
            + "If you have any more questions, feel free to visit our user guide below.";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TextArea helpPara;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        helpPara.setText(HELP_PARA);
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
