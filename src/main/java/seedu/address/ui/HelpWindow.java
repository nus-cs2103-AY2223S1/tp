package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SortCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-w09-2.github.io/tp/UserGuide.html";
    public static final String LINE = "-".repeat(110);
    public static final String HELP_MESSAGE = LINE
            + "\nHere's a list of basic commands:"
            + "\n1) Add a new client:\n"
            + ">> " + AddCommand.COMMAND_WORD + " n/NAME p/PHONE e/EMAIL a/ADDRESS "
            + "i/YEARLY_INCOME m/MONTHLY_CONTRIBUTIONS \n \t \t "
            + "r/RISK_APPETITE ip/INVESTMENT_PLAN c/CLIENT_TYPE t/[TAG](Optional Field)"
            + "\n2) Add appointment for a client:\n"
            + ">> " + AddAppointmentCommand.COMMAND_WORD + " INDEX d/DATE_AND_TIME l/LOCATION"
            + "\n3) Editing a client's information:\n"
            + ">> " + EditCommand.COMMAND_WORD + " INDEX PREFIX/KEYWORD"
            + "\n4) Edit Appointment for a client:\n"
            + ">> " + EditAppointmentCommand.COMMAND_WORD + " PERSON_INDEX.APPOINTMENT_INDEX d/DATE_AND_TIME l/LOCATION"
            + "\n5) Delete an existing client:\n"
            + ">> " + DeleteCommand.COMMAND_WORD + " INDEX"
            + "\n6) Delete Appointment for a client:\n"
            + ">> " + DeleteAppointmentCommand.COMMAND_WORD + " PERSON_INDEX.APPOINTMENT_INDEX"
            + "\n7) Sorting client by keywords:\n"
            + "Types of KEYWORDS: name, appt, risk, income, monthly"
            + "\nThe sorting will be in ascending order by default, to make it descending add desc behind KEYWORD\n"
            + ">> " + SortCommand.COMMAND_WORD + " KEYWORD / " + SortCommand.COMMAND_WORD + " KEYWORD desc"
            + "\n8) Find client by keyword:\n"
            + ">> " + FindCommand.COMMAND_WORD + " PREFIX/KEYWORD [MORE_KEYWORDS]"
            + "\n9) List all contacts:\n"
            + ">> list"
            + "\n10) Clear all entries from list of clients:\n"
            + ">> clear"
            + "\n11) Exit the program:\n"
            + ">> exit\n"
            + LINE
            + "\nFor more information, please refer to our user guide:\n"
            + USERGUIDE_URL;

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
