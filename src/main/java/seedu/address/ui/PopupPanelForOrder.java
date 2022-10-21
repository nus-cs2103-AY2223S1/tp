package seedu.address.ui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.person.Buyer;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

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
        speciesField.requestFocus();
        generateInputSequence(speciesField, colorField, colorPatternField,
                ageField, priceRangeField, byDateField, additionalRequestsField);
    }

    public String generateCommandText() {
        boolean allPartsFilled = checkGivenFieldsAllFilled(ageField, byDateField, colorField,
                colorPatternField, priceRangeField, speciesField, additionalRequestsField);
        if (!allPartsFilled) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(AddOrderCommand.COMMAND_WORD).append(" ")
                    .append(CliSyntax.PREFIX_ORDER_AGE).append(ageField.getText())
                    .append(CliSyntax.PREFIX_ORDER_DATE).append(byDateField.getText())
                    .append(CliSyntax.PREFIX_ORDER_COLOR).append(colorField.getText())
                    .append(CliSyntax.PREFIX_ORDER_COLOR_PATTERN).append(colorPatternField.getText())
                    .append(CliSyntax.PREFIX_ORDER_PRICE_RANGE).append(priceRangeField.getText())
                    .append(CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS).append(additionalRequestsField.getText());
            return builder.toString();
        }
    }

    @Override
    public Command generateCommand() {
        // TODO: implement this
        return null;
    }

    public Order generateOrder(Buyer buyer) throws ParseException {
        Species species = ParserUtil.parseSpecies(speciesField.getText());
        Color color = ParserUtil.parseColor(colorField.getText());
        ColorPattern colorPattern = ParserUtil.parseColorPattern(colorPatternField.getText());
        Age age = ParserUtil.parseAge(ageField.getText());
        PriceRange priceRange = ParserUtil.parsePriceRange(priceRangeField.getText());
        LocalDate localDate = ParserUtil.parseDate(byDateField.getText());
        String description = additionalRequestsField.getText().replaceAll("\n", System.lineSeparator());
        AdditionalRequests additionalRequests = new AdditionalRequests(description);
        Request request = new Request(age, color, colorPattern, species);
        Price price = new Price(-1);
        return new Order(buyer, priceRange, request, additionalRequests, localDate, price, OrderStatus.PENDING);
    }

    @Override
    public boolean checkAllPartsFilled() {
        boolean allPartsFilled = checkGivenFieldsAllFilled(ageField, byDateField, colorField,
                colorPatternField, priceRangeField, speciesField);
        return allPartsFilled;
    }

}
