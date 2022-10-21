package seedu.address.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class PopupPanelForBuyer extends UiPart<Region> implements PopUpPanel {

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
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    public PopupPanelForBuyer() {
        super(FXML);
        orderComponents = new ArrayList<>();
        nameField.requestFocus();
        generateInputSequence(nameField, phoneField, emailField, countryField, addressField, addComponentButton);
        setAddComponentButtonShortcut(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
    }

    @FXML
    public void addOrderComponent(ActionEvent event) {
        PopupPanelForOrder orderComponent = new PopupPanelForOrder();
        orderComponents.add(orderComponent);
        int numOfComponents = orderComponents.size();
        componentPlaceholder.addRow(numOfComponents - 1, orderComponent.getRoot());
    }

    public void setAddComponentButtonShortcut(KeyCombination keyCombination) {
        getRoot().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (keyCombination.match(event)) {
                addComponentButton.fire();
                event.consume();
            }
        });
    }

    @Override
    public Command generateCommand() throws ParseException {
        Buyer buyer = generateBuyer();
        List<Order> orders = generateOrders(buyer);
        buyer.addOrders(orders.stream().map(Order::getId).collect(Collectors.toList()));;
        return new AddBuyerCommand(buyer, orders);
    }

    public Buyer generateBuyer() throws ParseException {
        Name name = ParserUtil.parseName(nameField.getText());
        Phone phone = ParserUtil.parsePhone(phoneField.getText());
        Email email = ParserUtil.parseEmail(emailField.getText());
        Address address = ParserUtil.parseAddress(addressField.getText());
        // TODO: implement location in constructor
        Location location = new Location(countryField.getText());
        Set<Tag> tags = new HashSet<>();
        return new Buyer(name, phone, email, address, tags, null);
    }

    public List<Order> generateOrders(Buyer buyer) throws ParseException {
        List<Order> orders = new ArrayList<>();
        for (PopupPanelForOrder order : orderComponents) {
            orders.add(order.generateOrder(buyer));
        }
        return orders;
    }

    @Override
    public boolean checkAllPartsFilled() {
        boolean contactFilled = checkGivenFieldsAllFilled(addressField, countryField,
                emailField, nameField, phoneField);
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
