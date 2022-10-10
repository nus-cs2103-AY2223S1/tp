package seedu.rc4hdb.ui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import seedu.rc4hdb.model.person.Fields;
import seedu.rc4hdb.model.person.Person;

public class PersonTableView extends UiPart<Region> {
    private static final String FXML = "PersonTableView.fxml";

    // Initialise list with global field list since all fields should be shown at first
    private final ObservableList<String> observableFields = FXCollections.observableArrayList(Fields.fields);

    @FXML
    private TableView<Person> tableView;

    private final TableColumn<Person, Object> indexColumn = new TableColumn<>(Fields.INDEX_IDENTIFIER);
    private final TableColumn<Person, Object> nameColumn = new TableColumn<>(Fields.NAME_FIELD);
    private final TableColumn<Person, Object> phoneColumn = new TableColumn<>(Fields.PHONE_FIELD);
    private final TableColumn<Person, Object> addressColumn = new TableColumn<>(Fields.ADDRESS_FIELD);
    private final TableColumn<Person, Object> emailColumn = new TableColumn<>(Fields.EMAIL_FIELD);
    private final TableColumn<Person, Object> tagsColumn = new TableColumn<>(Fields.TAG_FIELD);

    public PersonTableView(ObservableList<Person> personList, ObservableList<String> observableFields) {
        super(FXML);
        this.tableView.setItems(personList);
        this.tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.addColumns();
        this.populateRows();

        // Listen to changes in the observable list and hide the columns accordingly
        this.observableFields.addListener(getListChangeListener());
        this.observableFields.setAll(observableFields);
    }

    private void addColumns() {
        this.tableView.getColumns().add(indexColumn);
        this.tableView.getColumns().add(nameColumn);
        this.tableView.getColumns().add(phoneColumn);
        this.tableView.getColumns().add(addressColumn);
        this.tableView.getColumns().add(emailColumn);
        this.tableView.getColumns().add(tagsColumn);
    }

    private void populateRows() {
        this.indexColumn.setCellFactory(this::populateIndexColumn);
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.addressColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/33353014/creating-a-row-index-column-in-javafx
     */
    private TableCell<Person, Object> populateIndexColumn(TableColumn<Person, Object> column) {
        return new TableCell<>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString(index + 1));
                }
            }
        };
    }

    private ListChangeListener<String> getListChangeListener() {
        return c -> {
            // Reset column visibilities
            tableView.getColumns().forEach(column -> column.setVisible(true));

            // Filter all columns (except index column) to obtain required columns
            // Recall that column headers is in title-case, i.e. first letter is capitalised
            tableView.getColumns().stream()
                    .filter(column -> this.observableFields.contains(column.getText().toLowerCase()))
                    .forEach(column -> column.setVisible(false));
        };
    }

    public void setObservableFields(ObservableList<String> list) {
        this.observableFields.setAll(list);
    }
}
