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
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;
import seedu.address.model.tag.Tag;

public class PopupPanelForSupplier extends UiPart<Region> implements PopUpPanel {

    private static final String FXML = "PopupPanelForSupplier.fxml";
    private final List<PopupPanelForPet> petComponents;

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

    public PopupPanelForSupplier() {
        super(FXML);
        petComponents = new ArrayList<>();
        nameField.requestFocus();
        generateInputSequence(nameField, phoneField, emailField, countryField, addressField, addComponentButton);
        setAddComponentButtonShortcut(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
    }

    @FXML
    public void addPetComponent(ActionEvent event) {
        PopupPanelForPet petComponent = new PopupPanelForPet();
        petComponents.add(petComponent);
        int numOfComponents = petComponents.size();
        componentPlaceholder.addRow(numOfComponents - 1, petComponent.getRoot());
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
        Supplier supplier = generateSupplier();
        List<Pet> pets = generatePets(supplier);
        supplier.addPets(pets.stream().map(Pet::getId).collect(Collectors.toList()));
        return new AddSupplierCommand(supplier, pets);
    }

    public Supplier generateSupplier() throws ParseException {
        Name name = ParserUtil.parseName(nameField.getText());
        Phone phone = ParserUtil.parsePhone(phoneField.getText());
        Email email = ParserUtil.parseEmail(emailField.getText());
        Address address = ParserUtil.parseAddress(addressField.getText());
        // TODO: implement location in constructor
        Location location = new Location(countryField.getText());
        Set<Tag> tags = new HashSet<>();
        return new Supplier(name, phone, email, address, tags, null);
    }

    public List<Pet> generatePets(Supplier supplier) throws ParseException {
        List<Pet> pets = new ArrayList<>();
        for (PopupPanelForPet petComponent : petComponents) {
            pets.add(petComponent.generatePet(supplier));
        }
        return pets;
    }

    @Override
    public boolean checkAllPartsFilled() {
        boolean contactFilled = checkGivenFieldsAllFilled(addressField, countryField,
                emailField, nameField, phoneField);
        if (!contactFilled) {
            return false;
        }
        for (PopupPanelForPet petComponent : petComponents) {
            if (!petComponent.checkAllPartsFilled()) {
                return false;
            }
        }
        return true;
    }


}
