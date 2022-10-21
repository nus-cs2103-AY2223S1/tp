package seedu.address.ui.CommandWindows;

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
    private final HashSet<String> uniqueTags;
    private final CommandBox.CommandExecutor commandExecutor;

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
        uniqueTags = new HashSet<>(10);
        this.commandExecutor = commandExecutor;
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
    public String getCustomerNameInput() {
        return name.getText();
    }

    /**
     * Returns the user Customer Tags input as a HashSet.
     */
    public HashSet<String> getCustomerTagsInput() {
        return uniqueTags;
    }

    /**
     * Returns the user Customer Address input.
     */
    public String getCustomerAddressInput() {
        return address.getText();
    }

    /**
     * Returns the user Customer Phone input.
     */
    public String getCustomerPhoneInput() {
        return phone.getText();
    }

    /**
     * Returns the user Customer Email input.
     */
    public String getCustomerEmailInput() {
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
            setErrorLabel(e.getMessage());
        }
    }

    /**
     * Tries to create or edit a Customer with the given user inputs.
     */
    @FXML
    public abstract void handleCustomerCommand();

    @FXML
    private void handleCloseCustomerWindow() {
        name.clear();
        tagField.clear();
        tags.getChildren().clear();
        uniqueTags.clear();
        phone.clear();
        email.clear();
        address.clear();
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
}
