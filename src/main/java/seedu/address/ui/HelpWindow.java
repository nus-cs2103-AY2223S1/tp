package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-w10-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private static final int MIN_ROW_HEIGHT = 60;
    private static final int MIN_COMMAND_WIDTH = 75;
    private static final int MIN_DESCRIPTION_WIDTH = 200;
    private static final double MIN_TABLE_WIDTH = 450;
    private static final double MIN_TABLE_HEIGHT = 300;
    private static Application hostServicesApp = new Application() {
        @Override
        public void start(Stage stage) {}
    };

    @FXML
    private Button copyButton;

    @FXML
    private Button openButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandFeatures> commandFeaturesTableView;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        this.initiateCommandFeaturesTableView();
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

    private void initiateCommandFeaturesTableView() {
        this.buildTable(commandFeaturesTableView, CommandFeatures.getCommandFeaturesList());
    }

    private void buildTable(TableView<CommandFeatures> tableView, ObservableList<CommandFeatures> commands) {
        int totalHeight = (6) * MIN_ROW_HEIGHT;

        tableView.setSelectionModel(null);
        tableView.setItems(commands);
        tableView.setFixedCellSize(MIN_ROW_HEIGHT);
        tableView.setMinSize(MIN_TABLE_WIDTH, MIN_TABLE_HEIGHT);
        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefHeight(totalHeight);

        TableColumn<CommandFeatures, String> commandCol = new TableColumn<>("Commands");
        commandCol.setMinWidth(MIN_COMMAND_WIDTH);
        commandCol.setCellValueFactory(new PropertyValueFactory<>("command"));
        commandCol.setEditable(false);
        commandCol.setResizable(false);
        commandCol.setReorderable(false);
        commandCol.setSortable(false);

        TableColumn<CommandFeatures, String> descriptionCol = new TableColumn<>(
                "Descriptions, Format, Example");
        descriptionCol.setMinWidth(MIN_DESCRIPTION_WIDTH);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setEditable(false);
        descriptionCol.setResizable(false);
        descriptionCol.setReorderable(false);
        descriptionCol.setSortable(false);

        commandCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.13));
        descriptionCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.80));

        tableView.getColumns().addAll(commandCol, descriptionCol);
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
     * Open the user guide on the browser for the user
     */
    @FXML
    private void openUrl() {
        hostServicesApp.getHostServices().showDocument(USERGUIDE_URL);
    }

}
