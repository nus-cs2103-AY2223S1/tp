package seedu.address.ui.command.window;

import java.io.File;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox;
import seedu.address.ui.UiPart;

/**
 * Controller for to add or edit an Iteration by the GUI.
 */
public abstract class IterationWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CommissionWindow.class);
    private static final String FXML = "IterationWindow.fxml";

    private final Stage windowStage;
    private final CommandBox.CommandExecutor commandExecutor;
    private final ErrorDisplay errorDisplay;

    private String imagePath = "";

    @FXML
    private Label iterationWindowHeader;
    @FXML
    private TextField date;
    @FXML
    private TextField description;
    @FXML
    private TextField feedback;
    @FXML
    private Rectangle fileChooseArea;
    @FXML
    private ImageView imagePreview;
    @FXML
    private HBox errorMessagePlaceholder;

    /**
     * Creates a new IterationWindow.
     */
    public IterationWindow(Consumer<UiPart<Stage>> addChildWindow, CommandBox.CommandExecutor commandExecutor,
                            Stage stage, String iterationWindowHeaderName) {
        super(FXML, stage);
        windowStage = stage;
        addChildWindow.accept(this);
        iterationWindowHeader.setText(iterationWindowHeaderName);
        this.commandExecutor = commandExecutor;

        errorDisplay = new ErrorDisplay(errorMessagePlaceholder);
    }

    /**
     * Shows the IterationWindow window.
     */
    public void show() {
        logger.fine("Showing iteration window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the IterationWindow window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the IterationWindow window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the iteration window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    String getDateInput() {
        return date.getText();
    }

    String getDescriptionInput() {
        return description.getText();
    }

    String getFeedbackInput() {
        return feedback.getText();
    }

    String getImagePathInput() {
        return imagePath;
    }

    /**
     * Executes a given command by the GUI window.
     */
    public void executeIterationCommand(String iterationCommandInput) {
        try {
            commandExecutor.execute(iterationCommandInput);
            handleCloseIterationWindow();
            windowStage.close();
        } catch (CommandException | ParseException e) {
            errorDisplay.setError(e.getMessage());
        }
    }

    /**
     * Tries to create or edit a Commission with the given user inputs.
     */
    @FXML
    abstract void handleIterationCommand();

    @FXML
    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose iteration image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(windowStage);
        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
            Image imageSelected = new Image(selectedFile.toURI().toString());
            imagePreview.setImage(imageSelected);
        }
    }

    @FXML
    private void handleCloseIterationWindow() {
        date.clear();
        description.clear();
        feedback.clear();
        errorDisplay.clearError();
        imagePreview.setImage(null);
        imagePath = "";
    }
}
