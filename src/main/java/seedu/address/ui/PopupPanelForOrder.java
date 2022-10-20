package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.parser.CliSyntax;

public class PopupPanelForOrder extends UiPart<Region> implements PopUpPanel {

    private static final String FXML = "PopupPanelForOrder.fxml";

    @FXML
    private TextArea additionalRequestsField;
    @FXML
    private TextField ageField;

    @FXML
    private TextField byDateField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField colorPatternField;

    @FXML
    private TextField priceRangeField;

    @FXML
    private TextField speciesField;

    public PopupPanelForOrder() {
        super(FXML);
        generateInputSequence(speciesField, colorField, colorPatternField,
                ageField, priceRangeField, byDateField, additionalRequestsField);
    }
    @Override
    public String generateCommandText() {
        boolean allPartsFilled = givenFieldsAllFilled(ageField, byDateField, colorField,
                colorPatternField, priceRangeField, speciesField, additionalRequestsField);
        if (!allPartsFilled) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(CliSyntax.PREFIX_ORDER_AGE).append(ageField.getText())
                    .append(CliSyntax.PREFIX_ORDER_DATE).append(byDateField.getText())
                    .append(CliSyntax.PREFIX_ORDER_COLOR).append(colorField.getText())
                    .append(CliSyntax.PREFIX_ORDER_COLOR_PATTERN).append(colorPatternField.getText())
                    .append(CliSyntax.PREFIX_ORDER_PRICE_RANGE).append(priceRangeField.getText())
                    .append(CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS).append(additionalRequestsField.getText());
            return builder.toString();
        }
    }
}
