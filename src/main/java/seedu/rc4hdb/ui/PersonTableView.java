package seedu.rc4hdb.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import seedu.rc4hdb.model.person.Person;

public class PersonTableView extends UiPart<Region> {
    private static final String FXML = "PersonTableView.fxml";

    @FXML
    private TableView<Person> tableView;

    private final TableColumn<Person, Object> indexColumn = new TableColumn<>("Index");
    private final TableColumn<Person, Object> nameColumn = new TableColumn<>("Name");
    private final TableColumn<Person, Object> phoneColumn = new TableColumn<>("Phone");
    private final TableColumn<Person, Object> addressColumn = new TableColumn<>("Address");
    private final TableColumn<Person, Object> emailColumn = new TableColumn<>("Email");
    private final TableColumn<Person, Object> tagsColumn = new TableColumn<>("Tags");

    public PersonTableView(ObservableList<Person> personList) {
        super(FXML);
        this.tableView.setItems(personList);
        this.tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.addColumns();
        this.populateRows();
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
}
