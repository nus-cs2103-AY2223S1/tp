package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Panel containing the list of persons.
 */
public class PersonTablePanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonTablePanel.class);

    @FXML
    private TableView<Person> personTableView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonTablePanel(ObservableList<Person> personList) {
        super(FXML);
//        this.personTableView = new TableView<Person>(personList);
        TableColumn nameCol = new TableColumn("n/NAME");
        TableColumn phoneCol = new TableColumn("p/PHONE");
        TableColumn emailCol = new TableColumn("e/EMAIL");
        TableColumn roomCol = new TableColumn("r/FLOOR-UNIT");
        TableColumn genderCol = new TableColumn("g/GENDER");
        TableColumn houseCol = new TableColumn("h/HOUSE");
        TableColumn matricCol = new TableColumn("m/MATRIC");
        TableColumn tagCol = new TableColumn("t/TAG");
        this.personTableView.setItems(personList);
        this.personTableView.getColumns().addAll(nameCol, phoneCol, emailCol, roomCol, genderCol, houseCol,
                matricCol, tagCol);

        nameCol.setCellValueFactory(new PropertyValueFactory<Person, Name>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Person, Phone>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, Email>("email"));
//        room.setCellValueFactory(new PropertyValueFactory<Person, Room>("room"));
//        gender.setCellValueFactory(new PropertyValueFactory<Person, Gender>("gender"));
//        house.setCellValueFactory(new PropertyValueFactory<Person, House>("house"));
//        matric.setCellValueFactory(new PropertyValueFactory<Person, Matric>("matric"));
//        tag.setCellValueFactory(new PropertyValueFactory<Person, Tag>("tag"));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
