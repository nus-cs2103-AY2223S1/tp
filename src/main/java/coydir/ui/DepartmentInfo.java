package coydir.ui;

import java.util.ArrayList;
import java.util.List;

import coydir.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * An UI component that displays detailed information of a {@code Department}.
 */
public class DepartmentInfo extends UiPart<Region> {
    private static final String FXML = "DepartmentInfo.fxml";

    private ObservableList<Person> unfilteredPersonList;

    private List<Person> filteredPersonListByDepartment = new ArrayList<>();

    @FXML
    private VBox departmentInfo;
    @FXML
    private Label departmentName;
    @FXML
    private Label numberOfEmployee;
    @FXML
    private Label numberOfEmployeeAvailable;
    @FXML
    private Label numberOfEmployeeOnLeave;
    @FXML
    private Label rating;
    @FXML
    private TableView<Person> ratingTable;

    /**
     * Creates a {@code PersonInfo} to display the {@code Person} particulars.
     */
    public DepartmentInfo(ObservableList<Person> unfilteredPersonList) {
        super(FXML);
        this.unfilteredPersonList = unfilteredPersonList;
        initializeRatingTable();
    }

    /**
     * Initializes the leave table at the start of the program.
     */
    public void initializeRatingTable() {
        ratingTable.setSelectionModel(null);
        ratingTable.setEditable(false);
        ratingTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ratingTable.addEventFilter(MouseEvent.ANY, MouseEvent::consume);
    }

    /**
     * Update the person particulars in the {@code PersonInfo} panel.
     * @param unfilteredPersonList the full person list
     */
    public void update(ObservableList<Person> unfilteredPersonList, String department) {
        this.unfilteredPersonList = unfilteredPersonList;
        departmentName.setText(department + " Department");
        numberOfEmployee.setText("Number of employees: " + countNumberOfEmployee(department));
        numberOfEmployeeAvailable.setText("Number of employees available: "
                + countNumberOfEmployeeAvailable(department));
        numberOfEmployeeOnLeave.setText("Number of employees on leave: " + countNumberOfEmployeeOnLeave(department));

        updateFilteredPersonListByDepartment(unfilteredPersonList, department);
        ratingTable.setItems(FXCollections.observableArrayList(filteredPersonListByDepartment));

        TableColumn<Person, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("col1"));
        id.setSortable(false);
        id.setReorderable(false);

        TableColumn<Person, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("col2"));
        name.setSortable(false);
        name.setReorderable(false);

        TableColumn<Person, Integer> rating = new TableColumn<>("Rating");
        rating.setCellValueFactory(new PropertyValueFactory<>("col3"));
        rating.setSortable(false);
        rating.setReorderable(false);
        ratingTable.getColumns().clear();
        ratingTable.getColumns().addAll(id, name, rating);
    }

    private int countNumberOfEmployee(String department) {
        int count = 0;
        for (Person p: unfilteredPersonList) {
            if (p.getDepartment().value.equals(department)) {
                count++;
            }
        }
        return count;
    }

    private int countNumberOfEmployeeAvailable(String department) {
        int count = 0;
        for (Person p: unfilteredPersonList) {
            if (p.getDepartment().value.equals(department) && !p.isOnLeave()) {
                count++;
            }
        }
        return count;
    }

    private int countNumberOfEmployeeOnLeave(String department) {
        int count = 0;
        for (Person p: unfilteredPersonList) {
            if (p.getDepartment().value.equals(department) && p.isOnLeave()) {
                count++;
            }
        }
        return count;
    }

    private void updateFilteredPersonListByDepartment(ObservableList<Person> unfilteredPersonList, String department) {
        filteredPersonListByDepartment.clear();
        for (Person p: unfilteredPersonList) {
            if (p.getDepartment().value.equals(department)) {
                filteredPersonListByDepartment.add(p);
            }
        }
    }
}
