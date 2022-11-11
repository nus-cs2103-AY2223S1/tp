package eatwhere.foodguide.ui;

import java.util.logging.Logger;

import eatwhere.foodguide.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-w11-1.github.io/tp/UserGuide.html";
    public static final String HELP_URL = "The full user guide can be found at:\n " + USERGUIDE_URL + "\n\n";

    public static final String COMMAND_USAGE = "List of commands:\n\n"
            + "help - Opens this help window\n"
            + "Usage: help\n\n"
            + "list - List out all eateries in the database\n"
            + "Usage: list [-h]\n\n"
            + "exit - Exits the app\n"
            + "Usage: exit\n\n"
            + "find - Search for eateries by name\n"
            + "Usage: find KEYWORD... [-r NUMBER] [-h]\n\n"
            + "findTag - Search for eateries by tag\n"
            + "Usage: findTag TAGNAME... [-r NUMBER] [-h]\n\n"
            + "findLocation - Search for eateries by location\n"
            + "Usage: findLocation LOCATIONNAME... [-r NUMBER] [-h]\n\n"
            + "findCuisine - Search for eateries by cuisine\n"
            + "Usage: findCuisine CUISINENAME... [-r NUMBER] [-h]\n\n"
            + "findPrice - Search for eateries by price\n"
            + "Usage: findPrice PRICE... [-r NUMBER] [-h]\n\n"
            + "tag - Add tag(s) to an eatery\n"
            + "Usage: tag INDEX -t TAGNAME1 [-t TAGNAME2]... [-h]\n\n"
            + "untag - Removes tag(s) from an eatery\n"
            + "Usage: untag INDEX -t TAGNAME1 [-t TAGNAME2]... [-h]\n\n"
            + "fav - Favorites an eatery\n"
            + "Usage: fav INDEX [-h]\n\n"
            + "unfav - Unfavorites an eatery\n"
            + "Usage: unfav INDEX [-h]"
            + "add - Add an eatery\n"
            + "Usage: add -n NAME -l LOCATION -c CUISINE -p PRICE [-t TAGNAME]... [-h]\n\n"
            + "delete - Delete an eatery\n"
            + "Usage: delete INDEX [-h]\n\n"
            + "edit - Edits an eatery\n"
            + "Usage: edit [-n NAME] [-l LOCATION] [-c CUISINE] [-p PRICE] [-t TAGNAME]... [-h]\n\n"
            + "clear - Clears the database\n"
            + "Usage: clear";


    public static final String HELP_MESSAGE = HELP_URL + COMMAND_USAGE;

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
