package seedu.rc4hdb.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
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

    private final TableColumn<Person, String> indexCol = new TableColumn<>();
    private final TableColumn<Person, Name> nameCol = new TableColumn<>("n/NAME");
    private final TableColumn<Person, Phone> phoneCol = new TableColumn<>("p/PHONE");
    private final TableColumn<Person, Email> emailCol = new TableColumn<>("e/EMAIL");
    /*
    To be added when Resident Class is implemented.

    private final TableColumn<Person, Room> roomCol = new TableColumn<>("r/FLOOR-UNIT");
    private final TableColumn<Person, Gender> genderCol = new TableColumn<>("g/GENDER");
    private final TableColumn<Person, House> houseCol = new TableColumn<>("h/HOUSE");
    private final TableColumn<Person, Matric> matricCol = new TableColumn<>("m/MATRIC");
     */
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
        setColumnWidth();
        populateTable();
    }

    /**
     * Sets the columns of the table with the formatters.
     */
    private void setTableColumns() {
        personTableView.getColumns().add(indexCol);
        personTableView.getColumns().add(nameCol);
        personTableView.getColumns().add(phoneCol);
        personTableView.getColumns().add(emailCol);

        /*
        To be added when Resident Class is implemented.

        personTableView.getColumns().add(roomCol);
        personTableView.getColumns().add(genderCol);
        personTableView.getColumns().add(houseCol);
        personTableView.getColumns().add(matricCol);
         */

        personTableView.getColumns().add(tagCol);
    }

    /**
     * Populates the columns with data from the given {@code ObservableList}.
     */
    private void populateTable() {
        populateIndexCol();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        /*
        To be added when Resident Class is implemented.

        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        houseCol.setCellValueFactory(new PropertyValueFactory<>("house"));
        matricCol.setCellValueFactory(new PropertyValueFactory<>("matric"));
         */

        /*
        To be implemented when Tag class is reworked

        tagCol.setCellValueFactory(new PropertyValueFactory<>("tag"));
         */
    }

    /**
     * Populates the index column of the {@code PersonTableView}.
     */
    private void populateIndexCol() {
        indexCol.setCellFactory(col -> new TableCell<>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                int oneIndex = index + 1;
                if (isEmpty() || oneIndex < 1) {
                    setText(null);
                } else {
                    setText(Integer.toString(oneIndex));
                }
            }
        });
    }

    /**
     * Stylizes the {@code PersonTableView} to maximise column width.
     */
    private void setColumnWidth() {
        personTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        indexCol.setResizable(false);
        indexCol.setPrefWidth(70);
    }

}

