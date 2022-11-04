package coydir.ui;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

import coydir.model.person.Leave;
import coydir.model.person.Person;
import javafx.beans.binding.ObjectExpression;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * An UI component that displays detailed information of a {@code Person}.
 */
public class PersonInfo extends UiPart<Region> {
    private static final String FXML = "PersonInfo.fxml";

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

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
    private Label performance;
    @FXML
    private FlowPane tags;
    @FXML
    private TableView<Leave> leaveTable;
    @FXML
    private LineChart<String, Number> lineChart;

    /**
     * Creates a {@code PersonInfo} to display the {@code Person} particulars.
     */
    public PersonInfo() {
        super(FXML);
        initializeLeaveTable();
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

    private static Node createDataNode(ObjectExpression<Number> value) {
        var label = new Label();
        label.textProperty().bind(value.asString());

        var pane = new Pane(label);
        pane.setShape(new Circle(6.0));
        pane.setScaleShape(false);

        label.translateYProperty().bind(label.heightProperty().divide(-1.5));

        return pane;
    }

    /**
     * Update the person particulars in the {@code PersonInfo} panel.
     * @param person the person to be displayed
     */
    public void update(Person person) {
        name.setText(person.getName().fullName);
        employeeId.setText("Employee ID:  " + person.getEmployeeId().value);
        phone.setText("Phone number:  " + person.getPhone().value);
        email.setText("Email address:  " + person.getEmail().value);
        position.setText("Position:  " + person.getPosition().value);
        department.setText("Department:  " + person.getDepartment().value);
        address.setText("Address:  " + person.getAddress().value);
        totalLeave.setText("Total Leaves: " + person.getTotalNumberOfLeaves());
        leaveLeft.setText("Leaves Left: " + person.getLeavesLeft());
        performance.setText("Performance: " + person.getRating().value);
        onLeave.setText("On leave: " + person.onLeaveStatus());
        tags.getChildren().clear();
        leaveTable.setItems(person.getObservableListLeaves());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        TableColumn<Leave, String> index = new TableColumn<>("No.");
        index.setCellFactory(col -> new TableCell<Leave, String>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString(index + 1));
                }
            }
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

        TableColumn<Leave, Integer> durations = new TableColumn<>("Duration");
        durations.setCellValueFactory(new PropertyValueFactory<>("col3"));
        durations.setSortable(false);
        durations.setReorderable(false);
        leaveTable.getColumns().clear();
        leaveTable.getColumns().addAll(index, startDate, endDate, durations);

        Collections.sort(person.getRatingHistory(), (a, b)->a.getTime().compareTo(b.getTime()));

        lineChart.setAnimated(false);
        lineChart.setTitle("Performance History");
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(5);
        yAxis.setLabel("Rating");
        xAxis.setLabel("Timestamp");

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Performance");

        for (int i = 0; i < person.getRatingHistory().size(); i++) {
            String timestamp = person.getRatingHistory().get(i).timestamp
                .format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
            Number value = Integer.parseInt(person.getRatingHistory().get(i).value);
            var data = new XYChart.Data<String, Number>(timestamp, value);
            data.setNode(createDataNode(data.YValueProperty()));
            series.getData().add(data);
        }

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }
}
