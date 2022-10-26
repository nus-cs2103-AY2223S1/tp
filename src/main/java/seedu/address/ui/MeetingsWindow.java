package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class MeetingsWindow extends UiPart<Stage> {
    public static final String MEETINGS_MESSAGE = "These are the meetings you have";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "MeetingsWindow.fxml";


    @FXML
    private Label meetingsMessage;

    /**
     * Creates a new MeetingsWindow.
     *
     * @param root Stage to use as the root of the MeetingsWindow.
     */
    public MeetingsWindow(Stage root) {
        super(FXML, root);
        meetingsMessage.setText(MEETINGS_MESSAGE);
    }

    /**
     * Creates a new MeetingsWindow.
     */
    public MeetingsWindow() {
        this(new Stage());
    }

    public void show() {
        logger.fine("Showing meetings page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the meetings window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the meetings window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the meetings window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    //Todo: getMeetings function
}
