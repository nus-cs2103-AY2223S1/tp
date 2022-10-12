package coydir.ui;

import java.util.logging.Logger;

import coydir.commons.core.LogsCenter;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.binding.Bindings;
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

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t15-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Click for more details: ";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private static final int ROW_HEIGHT = 30;
    private static final int SAFETY_MARGIN = 35;

    private static Application hostServicesApp = new Application() {
            @Override
            public void start(Stage stage) {}
        };

    @FXML
    private Button openButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView basicTableView;

    @FXML
    private TableView advancedTableView;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        this.initAllTableViews();
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

    private void initAllTableViews() {
        this.initTableView(this.basicTableView, CommandFormat.getBasicCommands());
        this.initTableView(this.advancedTableView, CommandFormat.getAdvancedCommands());
    }

    private void initTableView(TableView<CommandFormat> tableView, ObservableList<CommandFormat> commands) {
        tableView.setSelectionModel(null);
        tableView.setItems(commands);
        tableView.prefHeightProperty().bind(
                Bindings.size(tableView.getItems())
                .multiply(ROW_HEIGHT)
                .add(SAFETY_MARGIN));

        TableColumn<CommandFormat, String> commandCol = new TableColumn<>("Command");
        commandCol.setCellValueFactory(new PropertyValueFactory<>("command"));
        commandCol.setSortable(false);
        commandCol.setResizable(false);
        commandCol.setReorderable(false);

        TableColumn<CommandFormat, String> usageCol = new TableColumn<>("Usage");
        usageCol.setCellValueFactory(new PropertyValueFactory<>("usage"));
        usageCol.setSortable(false);
        usageCol.setResizable(false);
        usageCol.setReorderable(false);

        commandCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.13));
        usageCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.85));

        tableView.getColumns().add(commandCol);
        tableView.getColumns().add(usageCol);
    }

    /**
     * Opens the URL to the user guide in the default browser.
     */
    @FXML
    private void openWebsite() {
        HelpWindow.hostServicesApp.getHostServices().showDocument(USERGUIDE_URL);
    }
}
