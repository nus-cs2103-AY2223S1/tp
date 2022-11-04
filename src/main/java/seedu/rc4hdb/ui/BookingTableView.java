package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.DailySchedule;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.fields.Day;

/**
 * Represents the table of venues.
 */
public class BookingTableView extends UiPart<Region> {

    private static final String FXML = "BookingTableView.fxml";

    private static final List<String> hours = List.of("0800", "0900", "1000", "1100", "1200", "1300", "1400", "1500",
            "1600", "1700", "1800", "1900", "2000", "2100", "2200");

    private static final int START_HOUR = 8;

    private final TableColumn<DailySchedule, Day> dayColumn = new TableColumn<>("Day");
    private final List<TableColumn<DailySchedule, Resident[]>> hourColumn;

    @FXML
    private TableView<DailySchedule> bookingTableView;
    @FXML
    private VBox bookingTableContainer;

    private ObservableList<DailySchedule> weeklySchedule = FXCollections.observableArrayList();

    /**
     * Constructor for a BookingTableView.
     * @param currentlyDisplayedVenue The venue whose bookings are to be displayed.
     */
    public BookingTableView(ObservableValue<Venue> currentlyDisplayedVenue) {
        super(FXML);
        requireNonNull(currentlyDisplayedVenue);

        // Set up booking table
        this.bookingTableView.setItems(weeklySchedule);
        this.hourColumn = hours.stream()
                .map(title -> new TableColumn<DailySchedule, Resident[]>(title))
                .collect(Collectors.toList());
        addColumns();
        populateRows();
        configureTableProperties();
        updateTable(null, null, currentlyDisplayedVenue.getValue());

        // Set up listener
        currentlyDisplayedVenue.addListener(this::updateTable);
    }

    private void addColumns() {
        this.bookingTableView.getColumns().add(dayColumn);
        this.bookingTableView.getColumns().addAll(hourColumn);
    }

    private void populateRows() {
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        dayColumn.setCellFactory(this::populateDayColumn);
        for (int i = 0; i < hourColumn.size(); i++) {
            int finalI = i;
            hourColumn.get(i).setCellValueFactory(new PropertyValueFactory<>("bookedBy"));
            hourColumn.get(i).setCellFactory(tc -> populateNthColumn(tc, finalI));
        }
    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/33353014/creating-a-row-index-column-in-javafx
     */
    private TableCell<DailySchedule, Day> populateDayColumn(TableColumn<DailySchedule, Day> column) {
        return new TableCell<>() {
            @Override
            public void updateItem(Day day, boolean empty) {
                super.updateItem(day, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(day.value);
                }
            }
        };
    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/31126123/how-to-show-a-list-on-table-column-with-few-fields-of-list-items
     */
    private TableCell<DailySchedule, Resident[]> populateNthColumn(TableColumn<DailySchedule,
            Resident[]> column, int n) {
        return new TableCell<>() {
            @Override
            public void updateItem(Resident[] bookedBy, boolean empty) {
                super.updateItem(bookedBy, empty);
                if (empty || bookedBy == null || bookedBy[n + START_HOUR] == null) {
                    setText(null);
                } else {
                    setText(bookedBy[n + START_HOUR].getName().value);
                }
            }
        };
    }

    private void configureTableProperties() {
        dayColumn.setResizable(false);
        dayColumn.setSortable(false);
        dayColumn.setReorderable(false);

        for (TableColumn<DailySchedule, Resident[]> column : hourColumn) {
            column.setResizable(false);
            column.setSortable(false);
            column.setReorderable(false);
        }
    }

    /**
     * Listener for updating the booking table.
     */
    public void updateTable(ObservableValue<? extends Venue> observable, Venue oldValue, Venue newValue) {
        if (newValue != null) {
            this.weeklySchedule.setAll(DailySchedule.generateWeeklySchedule(newValue.getBookings()));
        }
    }

}
