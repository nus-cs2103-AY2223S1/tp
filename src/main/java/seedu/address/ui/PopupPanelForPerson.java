package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.logic.parser.CliSyntax;
public class PopupPanelForPerson extends UiPart<Region> implements PopUpPanel {

    private static final String FXML = "PopupPanelForPerson.fxml";
    private int numberOfItems = 0;

    @FXML
    private Button addComponentButton;

    @FXML
    private TextField addressField;

    @FXML
    private Label component;

    @FXML
    private GridPane componentPlaceholder;

    @FXML
    private TextField countryField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    public PopupPanelForPerson() {
        super(FXML);
        generateInputSequence(nameField, phoneField, emailField, countryField, addressField);
        addComponentButton.

    }



    @Override
    public String generateCommandText() {
        boolean allPartsFilled = givenFieldsAllFilled(nameField, phoneField, emailField, countryField, addressField);
        if (!allPartsFilled) {
            return "";
        } else {

        }
    }
}
