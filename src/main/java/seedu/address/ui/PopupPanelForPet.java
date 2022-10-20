package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.parser.CliSyntax;

public class PopupPanelForPet extends UiPart<Region> implements PopUpPanel {

    private static final String FXML = "PopupPanelForPet.fxml";

    @FXML
    private TextField colorField;

    @FXML
    private TextField colorPatternField;

    @FXML
    private TextField dateOfBirthField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField petNameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField speciesField;

    @FXML
    private TextField weightField;

    public PopupPanelForPet() {
        super(FXML);
        generateInputSequence(petNameField, speciesField, heightField, weightField,
                dateOfBirthField, colorField, colorPatternField, priceField);
    }

/*    @Override
    public void setTextFieldOrder() {
        goToNextFieldWithEnter(petNameField, speciesField);
        goToNextFieldWithEnter(speciesField, heightField);
        goToNextFieldWithEnter(heightField, weightField);
        goToNextFieldWithEnter(weightField, dateOfBirthField);
        goToNextFieldWithEnter(dateOfBirthField, colorField);
        goToNextFieldWithEnter(colorField, colorPatternField);
        goToNextFieldWithEnter(colorPatternField, priceField);
    }*/

    @Override
    public String generateCommandText() {
        boolean allPartsFilled = givenFieldsAllFilled(colorField, colorPatternField, dateOfBirthField,
                heightField, petNameField, priceField, speciesField, weightField);
        if (!allPartsFilled) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(CliSyntax.PREFIX_PET_COLOR).append(colorField.getText())
                    .append(CliSyntax.PREFIX_PET_COLOR_PATTERN).append(colorPatternField.getText())
                    .append(CliSyntax.PREFIX_PET_DATE_OF_BIRTH).append(dateOfBirthField.getText())
                    .append(CliSyntax.PREFIX_PET_HEIGHT).append(heightField.getText())
                    .append(CliSyntax.PREFIX_PET_NAME).append(petNameField.getText())
                    .append(CliSyntax.PREFIX_PET_PRICE).append(priceField.getText())
                    .append(CliSyntax.PREFIX_PET_SPECIES).append(speciesField.getText())
                    .append(CliSyntax.PREFIX_PET_WEIGHT).append(weightField.getText());
            return builder.toString();
        }
    }

}
