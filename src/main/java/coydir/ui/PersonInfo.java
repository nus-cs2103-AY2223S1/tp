package coydir.ui;

import java.util.Comparator;

import coydir.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays detailed information of a {@code Person}.
 */
public class PersonInfo extends UiPart<Region> {
    private static final String FXML = "PersonInfo.fxml";

    private Person person;

    @FXML
    private HBox personInfo;
    @FXML
    private Label name;
    @FXML
    private Label employeeId;
    @FXML
    private Label position;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonInfo} to display the {@code Person} particulars.
     */
    public PersonInfo(Person person) {
        super(FXML);
        this.person = person;
        name.setText(this.person.getName().fullName);
        employeeId.setText("Employee ID:  " + String.format(
                "%6s", this.person.getEmployeeId().value).replace(' ', '0'));
        position.setText("Position:  " + this.person.getPosition().value);
        phone.setText("Phone number:  " + this.person.getPhone().value);
        address.setText("Address:  " + this.person.getAddress().value);
        email.setText("Email address:  " + this.person.getEmail().value);
        this.person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Update the person particulars in the {@code PersonInfo} panel.
     * @param person
     */
    public void update(Person person) {
        this.person = person;
        name.setText(person.getName().fullName);
        employeeId.setText("Employee ID:  " + String.format("%6s", person.getEmployeeId().value).replace(' ', '0'));
        position.setText("Position:  " + person.getPosition().value);
        phone.setText("Phone number:  " + person.getPhone().value);
        address.setText("Address:  " + person.getAddress().value);
        email.setText("Email address:  " + person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
