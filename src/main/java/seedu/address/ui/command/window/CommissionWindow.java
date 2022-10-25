package seedu.address.ui.command.window;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox;
import seedu.address.ui.UiPart;

/**
 * Controller for to add or edit a Commission by the GUI.
 */
public abstract class CommissionWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CommissionWindow.class);
    private static final String FXML = "CommissionWindow.fxml";

    private final Stage windowStage;
    private final CommandBox.CommandExecutor commandExecutor;
    private final ErrorDisplay errorDisplay;
    private final TagsHandler tagsHandler;

    @FXML
    private Label customerWindowHeader;
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
    private ToggleButton completed;
    @FXML
    private ToggleButton notCompleted;
    @FXML
    private TextField description;
    @FXML
    private HBox errorMessagePlaceholder;

    /**
     * Creates a new CommissionWindow.
     */
    public CommissionWindow(Consumer<UiPart<Stage>> addChildWindow, CommandBox.CommandExecutor commandExecutor,
                          Stage stage, String commissionWindowHeaderName) {
        super(FXML, stage);
        windowStage = stage;
        addChildWindow.accept(this);
        customerWindowHeader.setText(commissionWindowHeaderName);
        this.commandExecutor = commandExecutor;

        errorDisplay = new ErrorDisplay(errorMessagePlaceholder);
        tagsHandler = new TagsHandler(tagField, tags, errorDisplay);
    }

    /**
     * Shows the CommissionWindow window.
     */
    public void show() {
        logger.fine("Showing commission window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the CommissionWindow window is currently being shown.
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
     * Focuses on the commission window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Executes a given command by the GUI window.
     */
    public void executeCommissionCommand(String commissionCommandInput) {
        try {
            commandExecutor.execute(commissionCommandInput);
            handleCloseCommissionWindow();
            windowStage.close();
        } catch (CommandException | ParseException e) {
            errorDisplay.setError(e.getMessage());
        }
    }

    String getTitleInput() {
        return title.getText();
    }

    String getFeeInput() {
        return fee.getText();
    }

    String getDeadlineInput() {
        return deadline.getText();
    }

    String getCompletedInput() {
        return completed.isSelected() || notCompleted.isSelected()
                ? Boolean.toString(completed.isSelected()) : "";
    }

    String getDescriptionInput() {
        return description.getText();
    }

    /**
     * Returns the user Commission Tags input as a HashSet.
     */
    HashSet<String> getCommissionTagsInput() {
        return tagsHandler.getTags();
    }

    /**
     * Tries to create or edit a Commission with the given user inputs.
     */
    @FXML
    abstract void handleCommissionCommand();

    @FXML
    private void handleCloseCommissionWindow() {
        title.clear();
        tagsHandler.clear();
        deadline.clear();
        fee.clear();
        completed.setSelected(false);
        notCompleted.setSelected(false);
        description.clear();
        errorDisplay.clearError();
    }
}
