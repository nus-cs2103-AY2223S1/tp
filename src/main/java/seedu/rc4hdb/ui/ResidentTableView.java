package seedu.rc4hdb.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;

/**
 * Panel containing the list of persons.
 */
public class ResidentTableView extends UiPart<Region> {

    private static final String FXML = "ResidentTableView.fxml";

    private final TableColumn<Resident, String> indexCol = new TableColumn<>();
    private final TableColumn<Resident, Name> nameCol = new TableColumn<>("n/NAME");
    private final TableColumn<Resident, Phone> phoneCol = new TableColumn<>("p/PHONE");
    private final TableColumn<Resident, Email> emailCol = new TableColumn<>("e/EMAIL");
    private final TableColumn<Resident, Room> roomCol = new TableColumn<>("r/FLOOR-UNIT");
    private final TableColumn<Resident, Gender> genderCol = new TableColumn<>("g/GENDER");
    private final TableColumn<Resident, House> houseCol = new TableColumn<>("h/HOUSE");
    private final TableColumn<Resident, MatricNumber> matricCol = new TableColumn<>("m/MATRIC");
    private final TableColumn<Resident, Tag> tagCol = new TableColumn<>("t/TAG");

    @FXML
    private TableView<Resident> residentTableView;

    /**
     * Creates a {@code ResidentTableView} with the given {@code ObservableList}.
     */
    public ResidentTableView(ObservableList<Resident> residentList) {
        super(FXML);
        residentTableView.setItems(residentList);
        setTableColumns();
        setColumnWidth();
        populateTable();
    }

    /**
     * Sets the columns of the table with the formatters.
     */
    private void setTableColumns() {
        residentTableView.getColumns().add(indexCol);
        residentTableView.getColumns().add(nameCol);
        residentTableView.getColumns().add(phoneCol);
        residentTableView.getColumns().add(emailCol);
        residentTableView.getColumns().add(roomCol);
        residentTableView.getColumns().add(genderCol);
        residentTableView.getColumns().add(houseCol);
        residentTableView.getColumns().add(matricCol);
        residentTableView.getColumns().add(tagCol);
    }

    /**
     * Populates the columns with data from the given {@code ObservableList}.
     */
    private void populateTable() {
        populateIndexCol();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        houseCol.setCellValueFactory(new PropertyValueFactory<>("house"));
        matricCol.setCellValueFactory(new PropertyValueFactory<>("matricNumber"));
        tagCol.setCellValueFactory(new PropertyValueFactory<>("tags"));
    }

    /**
     * Populates the index column of the {@code ResidentTableView}.
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
     * Stylizes the {@code ResidentTableView} to maximise column width.
     */
    private void setColumnWidth() {
        residentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        indexCol.setResizable(false);
        indexCol.setPrefWidth(70);
    }

}

