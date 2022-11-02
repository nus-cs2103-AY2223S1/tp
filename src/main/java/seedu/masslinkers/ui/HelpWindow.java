package seedu.masslinkers.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.masslinkers.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t11-4.github.io/tp/UserGuide.html\n";
    public static final String COMMAND_SUMMARY = "\nCOMMAND SUMMARY\n"
            + "▪ help\n"
            + "▪ add: add n/NAME t/TELEGRAM [g/GITHUB] [p/PHONE] [e/EMAIL] [i/INTEREST] [m/MODULE]\n"
            + "▪ list\n"
            + "▪ edit: edit INDEX [n/NAME] [t/TELEGRAM] [g/GITHUB] [p/PHONE] [e/EMAIL] [i/INTEREST]\n"
            + "▪ find: find KEYWORD [MORE_KEYWORDS]\n"
            + "▪ addInt: addInt INDEX INTEREST [MORE_INTERESTS]\n"
            + "▪ deleteInt: deleteInt INDEX INTEREST [MORE_INTERESTS]\n"
            + "▪ findInt: findInt INTEREST [MORE_INTERESTS]\n"
            + "▪ delete: delete INDEX\n"
            + "▪ mod:\n"
                + "\t▫ add: mod add INDEX MODULE [MORE_MODULES]\n"
                + "\t▫ delete: mod delete INDEX MODULE [MORE_MODULES]\n"
                + "\t▫ mark: mod mark INDEX MODULE [MORE_MODULES]\n"
                + "\t▫ unmark: mod unmark INDEX MODULE [MORE_MODULES]\n"
                + "\t▫ mark all: mod mark all\n"
                + "\t▫ find: mod find MODULE [MORE_MODULES]\n"
                + "\t▫ find taking: mod find taking MODULE [MORE_MODULES]\n"
                + "\t▫ find taken: mod find taken MODULE [MORE_MODULES]\n"
            + "▪ exit\n";
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

    //@@author carriezhengjr
    /**
     * Opens the user guide in the browser.
     */
    @FXML
    private void openGuide() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://ay2223s1-cs2103t-t11-4.github.io/tp/UserGuide.html"));
    }
}
