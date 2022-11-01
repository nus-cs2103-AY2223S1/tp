package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t14-1.github.io/tp/UserGuide.html";
    public static final String USERGUIDE_LABEL = "User guide:";

    public static final String SAVE_LOCATION_LABEL = "Data file location:";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label userGuideLabel;

    @FXML
    private Button userGuideUrl;

    @FXML
    private Label saveLocationLabel;

    @FXML
    private Label saveLocationStatus;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     * @param saveLocation Path to the save file.
     */
    public HelpWindow(Stage root, Path saveLocation) {
        super(FXML, root);

        userGuideLabel.setText(USERGUIDE_LABEL);

        userGuideUrl.setText(USERGUIDE_URL);
        handleTooltips();

        saveLocationLabel.setText(SAVE_LOCATION_LABEL);

        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
    }

    /**
     * Creates a new HelpWindow.
     *
     * @param saveLocation Path to the save file.
     */
    public HelpWindow(Path saveLocation) {
        this(new Stage(), saveLocation);
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

    /**
     * Adds tooltips to links.
     */
    private void handleTooltips() {
        Tooltip userGuideUrlTooltip = new Tooltip("Copy link");
        userGuideUrlTooltip.setShowDelay(TOOLTIP_DELAY);
        userGuideUrl.setTooltip(userGuideUrlTooltip);
    }
}
