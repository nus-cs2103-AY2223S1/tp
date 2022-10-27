package coydir.ui;

import java.util.Comparator;

import coydir.model.person.Leave;
import coydir.model.person.Person;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * An UI component that displays detailed information of a {@code Person}.
 */
public class PersonInfo extends UiPart<Region> {
    private static final String FXML = "PersonInfo.fxml";

    @FXML
    private VBox personInfo;
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
    private Label onLeave;
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
     * @param person the person to be displayed
     */
    public void update(Person person) {
        name.setText(person.getName().fullName);
        employeeId.setText("Employee ID:  " + String.format("%6s", person.getEmployeeId().value).replace(' ', '0'));
        phone.setText("Phone number:  " + person.getPhone().value);
        email.setText("Email address:  " + person.getEmail().value);
        position.setText("Position:  " + person.getPosition().value);
        department.setText("Department:  " + person.getDepartment().value);
        address.setText("Address:  " + person.getAddress().value);
        totalLeave.setText("Total Leaves: " + person.getTotalNumberOfLeaves());
        leaveLeft.setText("Leaves Left: " + person.getLeavesLeft());
        onLeave.setText("On leave: " + person.onLeaveStatus());
        tags.getChildren().clear();
        leaveTable.setItems(person.getObservableListLeaves());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        TableColumn<Leave, String> index = new TableColumn<>("No.");
        index.setCellFactory(col -> {
            TableCell<Leave, String> indexCell = new TableCell<>();
            ReadOnlyObjectProperty<TableRow<Leave>> rowProperty = indexCell.tableRowProperty();
            ObjectBinding<String> rowBinding = Bindings.createObjectBinding(() -> {
                TableRow<Leave> row = rowProperty.get();
                if (row != null) {
                    int rowIndex = row.getIndex();
                    if (rowIndex < row.getTableView().getItems().size()) {
                        return Integer.toString(rowIndex + 1);
                    }
                }
                return null;
            }, rowProperty);
            indexCell.textProperty().bind(rowBinding);
            return indexCell;
        });
        index.setSortable(false);
        index.setReorderable(false);
        index.setMaxWidth(2000);

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
        leaveTable.getColumns().addAll(index, startDate, endDate, durations);
    }
}
