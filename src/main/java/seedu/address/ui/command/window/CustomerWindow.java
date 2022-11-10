package seedu.address.ui.command.window;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox;
import seedu.address.ui.UiPart;

/**
 * Controller for to add or edit a Customer by the GUI.
 */
public abstract class CustomerWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CustomerWindow.class);
    private static final String FXML = "CustomerWindow.fxml";

    private final Stage windowStage;
    private final CommandBox.CommandExecutor commandExecutor;
    private final ErrorDisplay errorDisplay;
    private final TagsHandler tagsHandler;

    @FXML
    private Label customerWindowHeader;
    @FXML
    private TextField name;
    @FXML
    private TextField tagField;
    @FXML
    private FlowPane tags;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private HBox errorMessagePlaceholder;

    /**
     * Creates a new CustomerWindow.
     */
    public CustomerWindow(Consumer<UiPart<Stage>> addChildWindow, CommandBox.CommandExecutor commandExecutor,
                          Stage stage, String customerWindowHeaderName) {
        super(FXML, stage);
        windowStage = stage;
        addChildWindow.accept(this);
        customerWindowHeader.setText(customerWindowHeaderName);
        this.commandExecutor = commandExecutor;

        errorDisplay = new ErrorDisplay(errorMessagePlaceholder);
        tagsHandler = new TagsHandler(tagField, tags, errorDisplay);
    }

    /**
     * Shows the Customer window.
     */
    public void show() {
        logger.fine("Showing customer window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the CustomerWindow window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the CustomerWindow window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Returns the user Customer Name input.
     */
    String getCustomerNameInput() {
        return name.getText();
    }

    /**
     * Returns the user Customer Tags input as a HashSet.
     */
    HashSet<String> getCustomerTagsInput() {
        return tagsHandler.getTags();
    }

    /**
     * Returns the user Customer Address input.
     */
    String getCustomerAddressInput() {
        return address.getText();
    }

    /**
     * Returns the user Customer Phone input.
     */
    String getCustomerPhoneInput() {
        return phone.getText();
    }

    /**
     * Returns the user Customer Email input.
     */
    String getCustomerEmailInput() {
        return email.getText();
    }

    /**
     * Executes a given command by the GUI window.
     */
    public void executeCustomerCommand(String customerCommandInput) {
        try {
            commandExecutor.execute(customerCommandInput);
            handleCloseCustomerWindow();
            windowStage.close();
        } catch (CommandException | ParseException e) {
            errorDisplay.setError(e.getMessage());
        }
    }

    /**
     * Tries to create or edit a Customer with the given user inputs.
     */
    @FXML
    abstract void handleCustomerCommand();

    @FXML
    private void handleCloseCustomerWindow() {
        name.clear();
        tagsHandler.clear();
        phone.clear();
        email.clear();
        address.clear();
        errorDisplay.clearError();
    }
}
