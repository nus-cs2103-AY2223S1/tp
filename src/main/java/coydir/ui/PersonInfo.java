package coydir.ui;

import java.util.Comparator;

import coydir.model.person.Leave;
import coydir.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label position;
    @FXML
    private Label department;
    @FXML
    private Label address;
    @FXML
    private Label totalLeave;
    @FXML
    private Label leaveLeft;
    @FXML
    private FlowPane tags;
    @FXML
    private TableView<Leave> leaveTable;

    /**
     * Creates a {@code PersonInfo} to display the {@code Person} particulars.
     */
    public PersonInfo(Person person) {
        super(FXML);
        update(person);
    }

    /**
     * Initializing the leave table at the start of the program.
     */
    public void initializeLeaveTable() {
        leaveTable.setSelectionModel(null);
        leaveTable.setEditable(false);
        leaveTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        leaveTable.addEventFilter(MouseEvent.ANY, MouseEvent::consume);
    }

    /**
     * Update the person particulars in the {@code PersonInfo} panel.
     * @param person
     */
    public void update(Person person) {
        this.person = person;
        name.setText(person.getName().fullName);
        employeeId.setText("Employee ID:  " + String.format("%6s", person.getEmployeeId().value).replace(' ', '0'));
        phone.setText("Phone number:  " + person.getPhone().value);
        email.setText("Email address:  " + person.getEmail().value);
        position.setText("Position:  " + person.getPosition().value);
        department.setText("Department:  " + person.getDepartment().value);
        address.setText("Address:  " + person.getAddress().value);
        totalLeave.setText("Total Leaves: " + person.getTotalNumberOfLeaves());
        leaveLeft.setText("Leaves Left: " + person.getLeavesLeft());
        tags.getChildren().clear();
        leaveTable.setItems(person.getObservableListLeaves());
        TableColumn<Leave, String> startDate = new TableColumn<>("Start Date");
        startDate.setCellValueFactory(new PropertyValueFactory<>("col1"));
        startDate.setSortable(false);
        startDate.setReorderable(false);

        TableColumn<Leave, String> endDate = new TableColumn<>("End Date");
        endDate.setCellValueFactory(new PropertyValueFactory<>("col2"));
        endDate.setSortable(false);
        endDate.setReorderable(false);

        TableColumn<Leave, Integer> durations = new TableColumn<>("Durations");
        durations.setCellValueFactory(new PropertyValueFactory<>("col3"));
        durations.setSortable(false);
        durations.setReorderable(false);
        leaveTable.getColumns().clear();
        leaveTable.getColumns().addAll(startDate, endDate, durations);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
