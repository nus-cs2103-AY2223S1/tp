package seedu.travelr.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.travelr.commons.core.LogsCenter;

/**
 * Everything related HelpWindow, including fxml and css is based on AY2223S1-CS2103T-W17-4 team
 * Pleasehireus implementation of it which can be seen <a href="https://github.com/AY2223S1-CS2103T-W17-4/tp">here</a>
 *
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String WELCOME_MESSAGE = "Welcome to TravelR!!";
    public static final String MESSAGE = "The following table contains the app's command summary";
    public static final String UG_URL = "https://ay2223s1-cs2103t-w17-1.github.io/tp/UserGuide.html";
    public static final String USER_GUIDE_MESSAGE = "For more details refer to the user guide: " + UG_URL;
    public static final String COMMAND_SUMMARY =
            "+-----------------------------+---------------------------------------------------+\n"
                    + "| Action                      | Format                                            |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Add a trip                  | add n/TITLE d/DESCRIPTION l/LOCATION D/DATE       |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Add an event                | add-e n/TITLE d/DESCRIPTION                       |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Delete an event             | delete-e INDEX                                    |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Delete a trip               | delete INDEX                                      |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Add an event to a trip      | add-et n/EVENT_TITLE t/TRIP_TITLE                 |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Remove an event from trip   | delete-et n/EVENT_TITLE t/TRIP_TITLE              |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| View trips list             | list                                              |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| View events list            | list-e                                            |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Mark a trip as done         | mark INDEX                                        |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Mark a trip as not done     | unmark INDEX                                      |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| View completed events and   | completed                                         |\n"
                    + "| trips                       |                                                   |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| View all trips and events   | view                                              |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| View lifetime summary       | summary                                           |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| View events in a trip       | select INDEX                                      |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Clear the storage and data  | clear                                             |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Sort events in bucket list  | sort-e [r/]                                       |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| Sort Trips                  | sort [by/FACTOR] [r/]                             |\n"
                    + "+-----------------------------+---------------------------------------------------+\n"
                    + "| List available commands     | help                                              |\n"
                    + "| and link to User Guide      |                                                   |\n"
                    + "+-----------------------------+---------------------------------------------------+";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label message;

    //@@author AY2223S1-CS2103T-W17-4

    @FXML
    private Label welcomeMessage;

    @FXML
    private Label userGuideMessage;

    @FXML
    private Label commandSummary;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        message.setText(MESSAGE);
        userGuideMessage.setText(USER_GUIDE_MESSAGE);
        commandSummary.setText(COMMAND_SUMMARY);
        welcomeMessage.setText(WELCOME_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
        setKeys();
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
        getRoot().setHeight(650);
        getRoot().setMaxWidth(900);
        //getRoot().setMaximized(true);
        getRoot().centerOnScreen();
    }

    /**
     * Bind keys as shortcuts for help window.
     */
    private void setKeys() {
        //Bind ESC to minimized help window
        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                hide();
            }
        });

        //Bind C to copy user guide url
        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.C == event.getCode()) {
                copyUrl();
            }
        });
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
        url.putString(UG_URL);
        clipboard.setContent(url);
    }
}
