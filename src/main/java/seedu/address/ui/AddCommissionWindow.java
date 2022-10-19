package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.HashSet;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Controller for to add a Commission by the GUI.
 */
public class AddCommissionWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(AddCommissionWindow.class);
    private static final String FXML = "AddCommissionWindow.fxml";
    private final Stage windowStage;
    private final HashSet<String> uniqueTags;
    private final CommandBox.CommandExecutor commandExecutor;

    @FXML
    private Button copyButton;

    @FXML
    private TextField title;
    @FXML
    private TextField tags;
    @FXML
    private TextField deadline;
    @FXML
    private TextField fee;
    @FXML
    private TextField completed;
    @FXML
    private TextArea description;
    @FXML
    private HBox errorMessagePlaceholder;

    /**
     * Creates a new AddCommissionWindow.
     */
    public AddCommissionWindow(MainWindow mainWindow, CommandBox.CommandExecutor commandExecutor, Stage stage) {
        super(FXML, stage);
        windowStage = stage;
        mainWindow.addChildWindow(this);
        uniqueTags = new HashSet<>();
        this.commandExecutor = commandExecutor;
    }

    /**
     * Shows the AddCommissionWindow window.
     */
    public void show() {
        logger.fine("Showing add commission window.");
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
    private void addCommission() {
        String addCommissionCommand = "addcom";
        addCommissionCommand += " " + PREFIX_TITLE + title.getText();
        addCommissionCommand += " " + PREFIX_FEE + fee.getText();
        addCommissionCommand += " " + PREFIX_DEADLINE + deadline.getText();
        addCommissionCommand += " " + PREFIX_STATUS + completed.getText();
        addCommissionCommand += " " + PREFIX_DESCRIPTION + description.getText();

        for (String tagString : uniqueTags) {
            addCommissionCommand += " " + PREFIX_TAG + tagString;
        }

        try {
            commandExecutor.execute(addCommissionCommand);
            handleCloseAddCommissionWindow();
            windowStage.close();
        } catch (CommandException | ParseException e) {
            errorMessagePlaceholder.getChildren().clear(); // clear previous error message
            errorMessagePlaceholder.getChildren().add(getErrorLabel(e.getMessage()));
        }
    }

    @FXML
    private void handleCloseAddCommissionWindow() {
        title.clear();
        tags.clear();
        deadline.clear();
        fee.clear();
        completed.clear();
        description.clear();
        errorMessagePlaceholder.getChildren().clear();
    }

    private Label getErrorLabel(String errorMessage) {
        Label errorLabel = new Label(errorMessage);
        errorLabel.getStyleClass().add("commandWindowErrorMessage");
        errorLabel.setWrapText(true);
        errorLabel.setPrefWidth(688);
        return errorLabel;
    }
}
