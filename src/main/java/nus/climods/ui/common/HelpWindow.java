package nus.climods.ui.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import nus.climods.commons.core.GuiSettings;
import nus.climods.commons.core.LogsCenter;
import nus.climods.ui.UiPart;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String CLIMODS_BASE_URL = "https://ay2223s1-cs2103-f14-1.github.io/tp/";
    public static final String USERGUIDE_URL = CLIMODS_BASE_URL + "UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private WebView webView;

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
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        displayUserManual();
    }

    public void setWindowDefaultSize(GuiSettings guiSettings) {
        final double constantFactor = 0.8;
        getRoot().setHeight(guiSettings.getWindowHeight() * constantFactor);
        getRoot().setWidth(guiSettings.getWindowWidth() * constantFactor);
        if (guiSettings.getWindowCoordinates() != null) {
            getRoot().setX(guiSettings.getWindowCoordinates().getX() * constantFactor);
            getRoot().setY(guiSettings.getWindowCoordinates().getY() * constantFactor);
        }
        getRoot().centerOnScreen();
    }

    /**
     * Displays the UserManual that is hosted on the github website.
     * If the connection is down, render the backup html file.
     */
    private void displayUserManual() {
        WebEngine webEngine = webView.getEngine();
        boolean isConnectedToInternet = hasInternet();

        if (isConnectedToInternet) {
            preventRedirection(webEngine);
            webEngine.load(USERGUIDE_URL);
        } else {
            loadLocalUserManual(webEngine);
        }
    }

    private void loadLocalUserManual(WebEngine webEngine) {
        try {
            Stream<String> lines = Files.lines(
                Paths.get(ClassLoader.getSystemResource("html/UserManual.html").toURI())
            );
            webEngine.loadContent(lines.collect(Collectors.joining("\n")));
        } catch (URISyntaxException | IOException e) {
            logger.warning("User Manual not found!");
        }
    }

    private boolean hasInternet() {
        try {
            URL url = new URL(USERGUIDE_URL);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return true;
        } catch (IOException ioException) {
            return false;
        }
    }

    private void preventRedirection(WebEngine webEngine) {
        webEngine.locationProperty().addListener((obs, oldLocation, newLocation) -> {
            if (newLocation != null && !newLocation.startsWith(CLIMODS_BASE_URL)) {
                showWarningMessage("You are assessing a website outside of CLIMods.  "
                    + "You will be redirected to our website.");
                if (oldLocation.contains("#")) {
                    webEngine.load(oldLocation.substring(0, oldLocation.indexOf("#")));
                } else {
                    webEngine.load(oldLocation);
                }
            }
        });
    }

    private void showWarningMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        Label warningMessage = new Label(message);
        warningMessage.setWrapText(true);
        alert.getDialogPane().setContent(warningMessage);
        alert.showAndWait();
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
