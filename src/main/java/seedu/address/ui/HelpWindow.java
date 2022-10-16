package seedu.address.ui;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.NuSchedulerParser;
import seedu.address.logic.parser.profile.ProfileCommandParser;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t17-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Accordion generalAccordion;
    @FXML
    private Accordion profileAccordion;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);

        Map<String, String> generalCommands = NuSchedulerParser.getGeneralCommands();
        generalCommands.forEach((format, description) -> {
            Label label = new Label(description);
            label.setWrapText(true);
            HBox hBox = new HBox();
            hBox.getChildren().add(label);
            hBox.setAlignment(Pos.TOP_LEFT);
            hBox.prefWidth(288.0);
            generalAccordion.getPanes().add(new TitledPane(format, hBox));
        });

        Map<String, String> profileCommands = ProfileCommandParser.getProfileCommands();
        profileCommands.forEach((format, description) -> {
            Label label = new Label(description);
            label.setWrapText(true);
            HBox hBox = new HBox();
            hBox.getChildren().add(label);
            hBox.setAlignment(Pos.TOP_LEFT);
            hBox.prefWidth(288.0);
            profileAccordion.getPanes().add(new TitledPane(format, hBox));
        });

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
