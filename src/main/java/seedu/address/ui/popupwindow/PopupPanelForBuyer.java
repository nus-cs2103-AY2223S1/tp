package seedu.address.ui.popupwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.addcommands.AddBuyerCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * A panel for entering buyer information, which can be used to fill the placeholder in the pop-up window.
 * It can contain any number of {@code PopupPanelForOrder}.
 */
public class PopupPanelForBuyer extends PopUpPanel {

    private static final String FXML = "PopupPanelForBuyer.fxml";
    private final List<PopupPanelForOrder> orderComponents;

    @FXML
    private Button addComponentButton;

    @FXML
    private TextField addressField;

    @FXML
    private GridPane componentPlaceholder;

    @FXML
    private TextField countryField;

    @FXML
    private Button deleteComponentButton;
    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;


    @FXML
    private ScrollPane scrollPane;

    /**
     * Constructs a {@code PopupPanelForBuyer} by setting input sequence, prompt text style and keyboard shortcut.
     */
    public PopupPanelForBuyer() {
        super(FXML);
        orderComponents = new ArrayList<>();
        nameField.requestFocus();
        generateInputSequence(nameField, phoneField, emailField, countryField, addressField, addComponentButton);
        setPromptTextStyle(nameField, phoneField, emailField, countryField, addressField);
        generateButtonShortcut(addComponentButton, new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        generateButtonShortcut(deleteComponentButton, new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
    }

    /**
     * Adds an order component to the temporary storage list and the displayed grid pane
     * when the {@code addComponentButton} is pressed.
     *
     * @param event An action event.
     */
    @FXML
    public void addOrderComponent(ActionEvent event) {
        PopupPanelForOrder orderComponent = new PopupPanelForOrder();
        orderComponents.add(orderComponent);
        int numOfComponents = orderComponents.size();
        componentPlaceholder.addRow(numOfComponents - 1, orderComponent.getRoot());
    }

    /**
     * Deletes an order component from the temporary storage list and the displayed grid pane
     * when the {@code deleteComponentButton} is pressed.
     *
     * @param event An action event.
     */
    @FXML
    void deleteOrderComponent(ActionEvent event) {
        // Solution adapted from
        // https://stackoverflow.com/questions/23002532/javafx-2-how-do-i-delete-a-row-or-column-in-gridpane
        int numOfComponents = orderComponents.size();
        if (numOfComponents > 0) {
            orderComponents.remove(numOfComponents - 1);
            componentPlaceholder.getChildren().removeIf(node -> GridPane.getRowIndex(node) == numOfComponents - 1);
        }
    }

    @Override
    public Command generateCommand() throws ParseException {
        Buyer buyer = generateBuyer();
        List<Order> orders = generateOrders(buyer);
        buyer.addOrders(orders.stream().map(Order::getId).collect(Collectors.toList()));;
        return new AddBuyerCommand(buyer, orders);
    }

    /**
     * Generates a {@code Buyer} from user inputs.
     *
     * @return A {@code Buyer}.
     * @throws ParseException When the user inputs cannot be parsed.
     */
    public Buyer generateBuyer() throws ParseException {
        Name name = ParserUtil.parseName(nameField.getText());
        Phone phone = ParserUtil.parsePhone(phoneField.getText());
        Email email = ParserUtil.parseEmail(emailField.getText());
        Address address = ParserUtil.parseAddress(addressField.getText());
        Location location = new Location(countryField.getText());
        return new Buyer(name, phone, email, address, location, null);
    }

    /**
     * Generates a list of {@code Order} from the user inputs.
     *
     * @param buyer Buyer of the orders.
     * @return A list of {@code Order}.
     * @throws ParseException When the user inputs cannot be parsed.
     */
    public List<Order> generateOrders(Buyer buyer) throws ParseException {
        List<Order> orders = new ArrayList<>();
        for (PopupPanelForOrder order : orderComponents) {
            orders.add(order.generateOrder(buyer));
        }
        return orders;
    }

    @Override
    public boolean checkAllPartsFilled() {
        boolean contactFilled = checkGivenFieldsAllFilled(nameField, phoneField,
                emailField, countryField, addressField);
        if (!contactFilled) {
            return false;
        }
        for (PopupPanelForOrder orderComponent : orderComponents) {
            if (!orderComponent.checkAllPartsFilled()) {
                return false;
            }
        }
        return true;
    }

}
