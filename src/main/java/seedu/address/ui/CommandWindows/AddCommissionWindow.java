package seedu.address.ui.CommandWindows;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.HashSet;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox;
import seedu.address.ui.MainWindow;
import seedu.address.ui.UiPart;

/**
 * Controller for to add a Commission by the GUI.
 */
public class AddCommissionWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(AddCommissionWindow.class);
    private static final String FXML = "AddCommissionWindow.fxml";

    private final Stage windowStage;
    private final CommandBox.CommandExecutor commandExecutor;
    private final ErrorDisplay errorDisplay;
    private final TagsHandler tagsHandler;

    @FXML
    private TextField title;
    @FXML
    private TextField tagField;
    @FXML
    private FlowPane tags;
    @FXML
    private TextField deadline;
    @FXML
    private TextField fee;
    @FXML
    private TextField completed;
    @FXML
    private TextField description;
    @FXML
    private HBox errorMessagePlaceholder;

    /**
     * Creates a new AddCommissionWindow.
     */
    public AddCommissionWindow(MainWindow mainWindow, CommandBox.CommandExecutor commandExecutor, Stage stage) {
        super(FXML, stage);
        windowStage = stage;
        mainWindow.addChildWindow(this);
        this.commandExecutor = commandExecutor;

        errorDisplay = new ErrorDisplay(errorMessagePlaceholder);
        tagsHandler = new TagsHandler(tagField, tags, errorDisplay);
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
     * Returns true if the AddCommissionWindow window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the CommissionWindow window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the add commission window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Tries to create a commission with the given user inputs.
     */
    @FXML
    private void addCommission() {
        String addCommissionCommand = "addcom";
        addCommissionCommand += " " + PREFIX_TITLE + title.getText();
        addCommissionCommand += " " + PREFIX_FEE + fee.getText();
        addCommissionCommand += " " + PREFIX_DEADLINE + deadline.getText();
        addCommissionCommand += " " + PREFIX_STATUS + completed.getText();
        addCommissionCommand += " " + PREFIX_DESCRIPTION + description.getText();

        HashSet<String> uniqueTags = tagsHandler.getTags();
        for (String tagString : uniqueTags) {
            addCommissionCommand += " " + PREFIX_TAG + tagString;
        }

        try {
            commandExecutor.execute(addCommissionCommand);
            handleCloseAddCommissionWindow();
            windowStage.close();
        } catch (CommandException | ParseException e) {
            errorDisplay.setError(e.getMessage());
        }
    }

    @FXML
    private void handleCloseAddCommissionWindow() {
        title.clear();
        tagsHandler.clear();
        deadline.clear();
        fee.clear();
        completed.clear();
        description.clear();
        errorDisplay.clearError();
    }
}
