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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Controller for to add a Commission by the GUI.
 */
public class AddCommissionWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(AddCommissionWindow.class);
    private static final String FXML = "AddCommissionWindow.fxml";
    private static final String ERROR_BLANK_TAG_NAME = "Tag name should not be blank!";
    private static final String ERROR_DUPLICATED_TAG_NAME = "This tag has already been added!";

    private final Stage windowStage;
    private final HashSet<String> uniqueTags;
    private final CommandBox.CommandExecutor commandExecutor;

    @FXML
    private Button copyButton;

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
        uniqueTags = new HashSet<>(10);
        this.commandExecutor = commandExecutor;
        tagField.setOnKeyPressed(keyPressed -> {
            if (keyPressed.getCode() == KeyCode.ENTER) {
                handleAddTag();
            }
        });
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

        for (String tagString : uniqueTags) {
            addCommissionCommand += " " + PREFIX_TAG + tagString;
        }

        try {
            commandExecutor.execute(addCommissionCommand);
            handleCloseAddCommissionWindow();
            windowStage.close();
        } catch (CommandException | ParseException e) {
            setErrorLabel(e.getMessage());
        }
    }

    @FXML
    private void handleCloseAddCommissionWindow() {
        title.clear();
        tagField.clear();
        tags.getChildren().clear();
        uniqueTags.clear();
        deadline.clear();
        fee.clear();
        completed.clear();
        description.clear();
        errorMessagePlaceholder.getChildren().clear();
    }

    private void setErrorLabel(String errorMessage) {
        Label errorLabel = new Label(errorMessage);
        errorLabel.getStyleClass().add("commandWindowErrorMessage");
        errorLabel.setWrapText(true);
        errorLabel.setPrefWidth(688);
        errorMessagePlaceholder.getChildren().clear(); // clear previous error message
        errorMessagePlaceholder.getChildren().add(errorLabel);
    }

    private void handleAddTag() {
        String tagName = tagField.getText().trim();

        if (tagName.isEmpty()) {
            setErrorLabel(ERROR_BLANK_TAG_NAME);
            return;
        }

        if (!Tag.isValidTagName(tagName)) {
            setErrorLabel(Tag.MESSAGE_CONSTRAINTS);
            return;
        }

        if (uniqueTags.add(tagName)) {
            Label newTagName = new Label(tagName);
            Label deleteTag = new Label("X");
            HBox newTag = new HBox(newTagName, deleteTag);
            newTag.setSpacing(4);
            deleteTag.getStyleClass().add("deleteTagLabel");
            deleteTag.setOnMouseClicked(e -> {
                tags.getChildren().remove(newTag);
                uniqueTags.remove(tagName);
            });
            newTag.setMaxWidth(100);
            tags.getChildren().add(newTag);
            errorMessagePlaceholder.getChildren().clear(); // clear previous error message
        } else {
            setErrorLabel(ERROR_DUPLICATED_TAG_NAME);
        }

        tagField.clear();
    }
}
