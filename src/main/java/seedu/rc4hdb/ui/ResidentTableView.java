package seedu.rc4hdb.ui;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

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
import seedu.rc4hdb.model.resident.fields.Field;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

/**
 * Panel containing the list of persons.
 */
public class ResidentTableView extends UiPart<Region> {

    private static final String FXML = "ResidentTableView.fxml";

    private final TableColumn<Resident, Field> emailColumn = new TableColumn<>(ResidentFields.EMAIL);
    private final TableColumn<Resident, Field> genderColumn = new TableColumn<>(ResidentFields.GENDER);
    private final TableColumn<Resident, Field> houseColumn = new TableColumn<>(ResidentFields.HOUSE);
    private final TableColumn<Resident, Field> indexColumn = new TableColumn<>(ResidentFields.INDEX);
    private final TableColumn<Resident, Field> matricColumn = new TableColumn<>(ResidentFields.MATRIC);
    private final TableColumn<Resident, Field> nameColumn = new TableColumn<>(ResidentFields.NAME);
    private final TableColumn<Resident, Field> phoneColumn = new TableColumn<>(ResidentFields.PHONE);
    private final TableColumn<Resident, Field> roomColumn = new TableColumn<>(ResidentFields.ROOM);
    private final TableColumn<Resident, Field> tagColumn = new TableColumn<>(ResidentFields.TAG);

    @FXML
    private TableView<Resident> residentTableView;
    private final ObservableList<String> observableFields = FXCollections.observableArrayList();
    private final ObservableList<String> visibleFields = FXCollections.observableArrayList();
    private final ObservableList<String> hiddenFields = FXCollections.observableArrayList();

    /**
     * Creates a {@code ResidentTableView} with the given {@code ObservableList}.
     */
    public ResidentTableView(ObservableList<Resident> residentList, ObservableList<String> observableFields) {
        super(FXML);
        requireAllNonNull(residentList, observableFields);

        this.residentTableView.setItems(residentList);
        addColumns();
        populateRows();
        configureTableProperties();

        this.observableFields.setAll(observableFields);
        this.observableFields.addListener(getListChangeListener());
    }

    public ResidentTableView(ObservableList<Resident> residentList, ObservableList<String> observableFields,
                             ObservableList<String> visibleFields, ObservableList<String> hiddenFields) {
        super(FXML);
        requireAllNonNull(residentList, observableFields);

        this.residentTableView.setItems(residentList);
        addColumns();
        populateRows();
        configureTableProperties();

        this.observableFields.setAll(observableFields);
        this.observableFields.addListener(getListChangeListener());

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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        indexColumn.setCellFactory(this::populateIndexColumn);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        houseColumn.setCellValueFactory(new PropertyValueFactory<>("house"));
        matricColumn.setCellValueFactory(new PropertyValueFactory<>("matricNumber"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/33353014/creating-a-row-index-column-in-javafx
     */
    private TableCell<Resident, Field> populateIndexColumn(TableColumn<Resident, Field> column) {
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
        residentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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



    private ListChangeListener<String> getListChangeListener() {
        return c -> {
            // Reset column visibilities
            residentTableView.getColumns().forEach(column -> column.setVisible(true));

            // Filter all columns (including index column) to obtain required columns
            // Recall that column headers is in title-case, i.e. first letter is capitalised
            residentTableView.getColumns().stream()
                    .filter(column -> this.observableFields.contains(column.getText().toLowerCase()))
                    .forEach(column -> column.setVisible(false));
        };
    }

    private ListChangeListener<String> showColumnsOnChange() {
        System.out.println("Inside listener for show command.");
        return c -> {
            // Reset column visibilities to false
            residentTableView.getColumns().forEach(column -> column.setVisible(false));

            // Filter all columns (including index column) to obtain the columns to show
            // Recall that column headers is in title-case, i.e. first letter is capitalised
            residentTableView.getColumns().stream()
                    .filter(column -> this.visibleFields.contains(column.getText().toLowerCase()))
                    .forEach(column -> column.setVisible(true));
        };
    }

    private ListChangeListener<String> hideColumnsOnChange() {
        System.out.println("Inside listener for hide command.");
        return c -> {

            // Reset column visibilities to true
            residentTableView.getColumns().forEach(column -> column.setVisible(true));

            // Filter all columns (including index column) to obtain the columns to hide
            // Recall that column headers is in title-case, i.e. first letter is capitalised
            residentTableView.getColumns().stream()
                    .filter(column -> this.hiddenFields.contains(column.getText().toLowerCase()))
                    .forEach(column -> column.setVisible(false));
        };
    }

    public void setObservableFields(ObservableList<String> list) {
        this.observableFields.setAll(list);
    }

    public void setVisibleFields(ObservableList<String> fieldsToShow) {
        this.visibleFields.setAll(fieldsToShow);
    }

    public void setHiddenFields(ObservableList<String> fieldsToHide) {
        this.hiddenFields.setAll(fieldsToHide);
    }
}

