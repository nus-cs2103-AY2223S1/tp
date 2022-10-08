package seedu.rc4hdb.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.model.person.Email;
import seedu.rc4hdb.model.person.Name;
import seedu.rc4hdb.model.person.Person;
import seedu.rc4hdb.model.person.Phone;
import seedu.rc4hdb.model.tag.Tag;

/**
 * Panel containing the list of persons.
 */
public class PersonTableView extends UiPart<Region> {
    private static final String FXML = "PersonTableView.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonTableView.class);

    private final TableColumn<Person, Name> nameCol = new TableColumn<>("n/NAME");
    private final TableColumn<Person, Phone> phoneCol = new TableColumn<>("p/PHONE");
    private final TableColumn<Person, Email> emailCol = new TableColumn<>("e/EMAIL");
    //private final TableColumn<Person, Room> roomCol = new TableColumn<>("r/FLOOR-UNIT");
    //private final TableColumn<Person, Gender> genderCol = new TableColumn<>("g/GENDER");
    //private final TableColumn<Person, House> houseCol = new TableColumn<>("h/HOUSE");
    //private final TableColumn<Person, Matric> matricCol = new TableColumn<>("m/MATRIC");
    private final TableColumn<Person, Tag> tagCol = new TableColumn<>("t/TAG");

    @FXML
    private TableView<Person> personTableView;

    /**
     * Creates a {@code PersonTableView} with the given {@code ObservableList}.
     */
    public PersonTableView(ObservableList<Person> personList) {
        super(FXML);
        personTableView.setItems(personList);
        setTableColumns();
        populateTable();
        personTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Sets the columns of the table with the formatters.
     */
    private void setTableColumns() {
        personTableView.getColumns().add(nameCol);
        personTableView.getColumns().add(phoneCol);
        personTableView.getColumns().add(emailCol);
        personTableView.getColumns().add(tagCol);
        // to add roomCol, genderCol, houseCol, matricCol between email and tag after their implementation
    }

    /**
     * Populates the columns with it's respective Person data.
     */
    private void populateTable() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        //tagCol.setCellValueFactory(new PropertyValueFactory<>("tag")); // to implement

        //room.setCellValueFactory(new PropertyValueFactory<>("room"));
        //gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        //house.setCellValueFactory(new PropertyValueFactory<>("house"));
        //matric.setCellValueFactory(new PropertyValueFactory<>("matric"));
    }

}

