package seedu.address.ui.popupwindow;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seedu.address.logic.commands.Command;
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

/**
 * A panel for entering order information, which can be part of the {@code PopupPanelForBuyer}.
 */
public class PopupPanelForOrder extends PopUpPanel {

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

    /**
     * Constructs a {@code PopupPanelForOrder} by setting input sequence and prompt text style.
     */
    public PopupPanelForOrder() {
        super(FXML);
        speciesField.requestFocus();
        generateInputSequence(speciesField, colorField, colorPatternField,
                ageField, priceRangeField, byDateField, additionalRequestsField);
        setPromptTextStyle(speciesField, colorField, colorPatternField,
                ageField, priceRangeField, byDateField);
    }

    @Override
    public Command generateCommand() {
        // TODO: modify AddOrderCommand
        return null;
    }

    /**
     * Generates an {@code Order} from user inputs.
     * @param buyer Buyer of the order.
     * @return An {@code Order}.
     * @throws ParseException When user inputs cannot be parsed.
     */
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
        boolean allPartsFilled = checkGivenFieldsAllFilled(speciesField, colorField, colorPatternField,
                ageField, priceRangeField, byDateField);
        return allPartsFilled;
    }

}
