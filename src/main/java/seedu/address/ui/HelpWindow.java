package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.NuSchedulerParser;
import seedu.address.logic.parser.event.EventCommandParser;
import seedu.address.logic.parser.profile.ProfileCommandParser;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t17-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Visit the complete ";
    public static final double WIDTH = 600.0;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label helpMessage;

    @FXML
    private Accordion generalAccordion;
    @FXML
    private Accordion profileAccordion;
    @FXML
    private Accordion eventAccordion;

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
            HBox hBox = new HBox();
            hBox.getChildren().add(new Label(description));
            hBox.setAlignment(Pos.TOP_LEFT);
            hBox.setMinWidth(WIDTH);
            generalAccordion.getPanes().add(new TitledPane(format, hBox));
        });

        Map<String, String> profileCommands = ProfileCommandParser.getProfileCommands();
        profileCommands.forEach((format, description) -> {
            HBox hBox = new HBox();
            hBox.getChildren().add(new Label(description));
            hBox.setAlignment(Pos.TOP_LEFT);
            hBox.setMinWidth(WIDTH);
            profileAccordion.getPanes().add(new TitledPane(format, hBox));
        });

        Map<String, String> eventCommands = EventCommandParser.getEventCommands();
        eventCommands.forEach((format, description) -> {
            HBox hBox = new HBox();
            hBox.getChildren().add(new Label(description));
            hBox.setAlignment(Pos.TOP_LEFT);
            hBox.setMinWidth(WIDTH);
            eventAccordion.getPanes().add(new TitledPane(format, hBox));
        });

        generalAccordion.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            if (!root.isFullScreen()) {
                root.sizeToScene();
            }
        });

        profileAccordion.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            if (!root.isFullScreen()) {
                root.sizeToScene();
            }
        });

        eventAccordion.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            if (!root.isFullScreen()) {
                root.sizeToScene();
            }
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
     * Opens the given url in default browser.
     */
    public static void openBrowserUrl(String url) {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the User Guide.
     */
    @FXML
    public void handleUserGuide() {
        openBrowserUrl(USERGUIDE_URL);
    }
}
