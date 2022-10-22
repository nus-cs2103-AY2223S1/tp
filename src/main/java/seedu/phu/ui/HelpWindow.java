package seedu.phu.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.phu.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String WELCOME_MESSAGE = "Welcome to PleaseHireUs!\n";
    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-w17-4.github.io/tp/UserGuide.html";
    public static final String USER_GUIDE_MESSAGE = "For more details refer to the user guide: " + USERGUIDE_URL;
    public static final String COMMAND_SUMMARY_INTRO =
            "Refer to the following table for a quick guide on how to use this app.";

    public static final String COMMAND_SUMMARY =
            "+--------------------------+--------------------------------------------------------------------+\n"
            + "| Action                   | Format                                                             |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| Add an internship        | add n/COMPANY_NAME p/POSITION [pr/APPLICATION_PROCESS] [d/DATE]    |\n"
            + "|                          | [ph/PHONE] [e/EMAIL] [web/WEBSITE] [r/REMARK]  [t/TAG]…            |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| Clear all entries        | clear                                                              |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| Delete an internship     | delete INDEX...                                                    |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| View internship details  | view INDEX                                                         |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| Edit internship details  | edit INDEX [n/COMPANY_NAME] [p/POSITION] [pr/APPLICATION_PROCESS]  |\n"
            + "|                          | [d/DATE] [ph/PHONE] [e/EMAIL] [r/REMARK] [web/WEBSITE] [t/TAG]...  |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| Find internship(s)       | find [c/CATEGORY] KEYWORDS...                                      |\n"
            + "| containing keyword(s)    |                                                                    |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| List all internship(s)   | list [c/CATEGORY [DESCENDING]]                                     |\n"
            + "| in specified order       |                                                                    |\n"
            + "+--------------------------+--------------------------------------------------------------------+\n"
            + "| List available commands  | help                                                               |\n"
            + "| and link to User Guide   |                                                                    |\n"
            + "+--------------------------+--------------------------------------------------------------------+";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label welcomeMessage;

    @FXML
    private Label userGuideMessage;

    @FXML
    private Label commandSummary;
    @FXML
    private Label commandSummaryIntro;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        welcomeMessage.setText(WELCOME_MESSAGE);
        commandSummaryIntro.setText(COMMAND_SUMMARY_INTRO);
        userGuideMessage.setText(USER_GUIDE_MESSAGE);
        commandSummary.setText(COMMAND_SUMMARY);
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
