package seedu.clinkedin.ui;

import java.util.logging.Logger;

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
import seedu.clinkedin.commons.core.LogsCenter;
import seedu.clinkedin.logic.parser.CliSyntax;
import seedu.clinkedin.ui.utils.CommandRow;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t13-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more detailed instructions, please refer to the user guide: "
            + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);

    private static final String FXML = "HelpWindow.fxml";

    private ObservableList<CommandRow> commandList;


    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandRow> commandTable;

    @FXML
    private TableColumn<CommandRow, String> commandName;

    @FXML
    private TableColumn<CommandRow, String> commandUsage;

    /**
     * Creates a new HelpWindow.
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        try {
            // Obtains the lists of commands and their usages from CLI syntax
            commandList = CliSyntax.getAllCommandRows();
            // Sets the table columns to display the command name and usage
            // and populates the table with the command list
            initialiseTableView();
        } catch (Exception e) {
            logger.warning("Error in getting command list");
        }
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Initialises the TableView help window with the command list.
     */
    public void initialiseTableView() {
        commandName.setCellValueFactory(new PropertyValueFactory<CommandRow, String>("commandName"));
        commandUsage.setCellValueFactory(new PropertyValueFactory<CommandRow, String>("commandUsage"));
        commandTable.setItems(commandList);
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
        logger.info("Showing help page about the application.");
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
