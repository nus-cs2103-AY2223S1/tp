package seedu.rc4hdb.ui;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import seedu.rc4hdb.model.resident.fields.ResidentField;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.resident.fields.Tag;

/**
 * Panel containing the list of persons.
 */
public class ResidentTableView extends UiPart<Region> {

    private static final String FXML = "ResidentTableView.fxml";

    @FXML
    private TableView<Resident> residentTableView;

    private final TableColumn<Resident, ResidentField> emailColumn = new TableColumn<>(Email.IDENTIFIER);
    private final TableColumn<Resident, ResidentField> genderColumn = new TableColumn<>(Gender.IDENTIFIER);
    private final TableColumn<Resident, ResidentField> houseColumn = new TableColumn<>(House.IDENTIFIER);
    private final TableColumn<Resident, ResidentField> indexColumn = new TableColumn<>(ResidentField.INDEX_IDENTIFIER);
    private final TableColumn<Resident, ResidentField> matricColumn = new TableColumn<>(MatricNumber.IDENTIFIER);
    private final TableColumn<Resident, ResidentField> nameColumn = new TableColumn<>(Name.IDENTIFIER);
    private final TableColumn<Resident, ResidentField> phoneColumn = new TableColumn<>(Phone.IDENTIFIER);
    private final TableColumn<Resident, ResidentField> roomColumn = new TableColumn<>(Room.IDENTIFIER);
    private final TableColumn<Resident, Set<Tag>> tagColumn = new TableColumn<>(Tag.IDENTIFIER);

    private final ObservableList<String> visibleFields = FXCollections.observableArrayList(
            ResidentField.LOWERCASE_FIELDS);
    private final ObservableList<String> hiddenFields = FXCollections.observableArrayList();

    /**
     * Creates a {@code ResidentTableView} with the given {@code ObservableList}.
     */
    public ResidentTableView(ObservableList<Resident> residentList,
                             ObservableList<String> visibleFields,
                             ObservableList<String> hiddenFields) {
        super(FXML);
        requireAllNonNull(residentList, visibleFields, hiddenFields);

        assert(residentTableView != null);

        this.residentTableView.setItems(residentList);
        addColumns();
        populateRows();
        configureTableProperties();

        this.visibleFields.setAll(visibleFields);
        this.visibleFields.addListener(showColumnsOnChange());

        this.hiddenFields.setAll(hiddenFields);
        this.hiddenFields.addListener(hideColumnsOnChange());
    }

    /**
     * Sets the columns of the table with the formatters.
     */
    private void addColumns() {
        residentTableView.getColumns().add(indexColumn);
        residentTableView.getColumns().add(nameColumn);
        residentTableView.getColumns().add(phoneColumn);
        residentTableView.getColumns().add(emailColumn);
        residentTableView.getColumns().add(roomColumn);
        residentTableView.getColumns().add(genderColumn);
        residentTableView.getColumns().add(houseColumn);
        residentTableView.getColumns().add(matricColumn);
        residentTableView.getColumns().add(tagColumn);
    }

    /**
     * Populates the columns with data from the given {@code ObservableList}.
     */
    private void populateRows() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(Name.IDENTIFIER.toLowerCase()));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>(Phone.IDENTIFIER.toLowerCase()));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>(Email.IDENTIFIER.toLowerCase()));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>(Room.IDENTIFIER.toLowerCase()));
        indexColumn.setCellFactory(this::populateIndexColumn);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>(Gender.IDENTIFIER.toLowerCase()));
        houseColumn.setCellValueFactory(new PropertyValueFactory<>(House.IDENTIFIER.toLowerCase()));
        matricColumn.setCellValueFactory(new PropertyValueFactory<>("matricNumber"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<>(Tag.IDENTIFIER.toLowerCase()));
        tagColumn.setCellFactory(this::populateTagColumn);
    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/31126123/how-to-show-a-list-on-table-column-with-few-fields-of-list-items
     */
    private TableCell<Resident, Set<Tag>> populateTagColumn(TableColumn<Resident, Set<Tag>> column) {
        return new TableCell<>() {
            @Override
            public void updateItem(Set<Tag> tags, boolean empty) {
                super.updateItem(tags, empty);
                if (empty) {
                    setText(null);
                } else {
                    String tagsString = tags.stream().map(Tag::toString)
                            .collect(Collectors.joining(", "));
                    setText(tagsString);
                }
            }
        };
    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/33353014/creating-a-row-index-column-in-javafx
     */
    private TableCell<Resident, ResidentField> populateIndexColumn(TableColumn<Resident, ResidentField> column) {
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

    /**
     * Stylizes the {@code ResidentTableView} to maximise column width.
     */
    private void configureTableProperties() {
        this.residentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        indexColumn.setResizable(false);
        indexColumn.setPrefWidth(70);

        nameColumn.setMinWidth(300);

        phoneColumn.setResizable(false);
        phoneColumn.setPrefWidth(120);

        emailColumn.setMinWidth(300);

        roomColumn.setResizable(false);
        roomColumn.setPrefWidth(140);

        genderColumn.setResizable(false);
        genderColumn.setPrefWidth(90);

        houseColumn.setResizable(false);
        houseColumn.setPrefWidth(90);

        matricColumn.setResizable(false);
        matricColumn.setPrefWidth(140);
    }

    /**
     * Returns a listener that sets the TableColumns corresponding to the columns
     * in {@code visibleFields} to visible when the list changes.
     */
    private ListChangeListener<String> showColumnsOnChange() {
        return c -> {
            // Reset column visibilities to false
            residentTableView.getColumns().forEach(column -> column.setVisible(false));

            // Filter all columns (including index column) to obtain the columns to show
            // Recall that column headers is in title-case, i.e. first letter is capitalised
            residentTableView.getColumns()
                    .stream()
                    .filter(column -> this.visibleFields.contains(column.getText().toLowerCase()))
                    .forEach(column -> column.setVisible(true));

            // Ensure that index column is properly rendered
            indexColumn.setCellFactory(this::populateIndexColumn);
        };
    }

    /**
     * Returns a listener that sets the TableColumns corresponding to the columns
     * in {@code hiddenFields} to not visible when the list changes.
     */
    private ListChangeListener<String> hideColumnsOnChange() {
        return c -> {
            // Reset column visibilities to true
            residentTableView.getColumns().forEach(column -> column.setVisible(true));

            // Filter all columns (including index column) to obtain the columns to hide
            // Recall that column headers is in title-case, i.e. first letter is capitalised
            residentTableView.getColumns()
                    .stream()
                    .filter(column -> this.hiddenFields.contains(column.getText().toLowerCase()))
                    .forEach(column -> column.setVisible(false));

            // Ensure that index column is properly rendered
            indexColumn.setCellFactory(this::populateIndexColumn);
        };
    }

    /**
     * Updates the list of fields to be shown when invoking {@code show}.
     * @param fieldsToShow The list of fields to be shown
     */
    public void setVisibleFields(ObservableList<String> fieldsToShow) {
        this.visibleFields.setAll(fieldsToShow);
    }

    /**
     * Updates the list of fields to be hidden when invoking {@code hide}.
     * @param fieldsToHide The list of fields to be hidden
     */
    public void setHiddenFields(ObservableList<String> fieldsToHide) {
        this.hiddenFields.setAll(fieldsToHide);
    }
}

