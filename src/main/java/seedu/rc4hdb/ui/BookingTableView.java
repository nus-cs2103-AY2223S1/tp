package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.DailySchedule;
import seedu.rc4hdb.model.venues.booking.Booking;
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
    private ObservableList<DailySchedule> weeklySchedule = FXCollections.observableArrayList();

    /**
     * Constructor for a VenueTableView instance. The venue list is processed to remove expired bookings.
     * @param bookingList The list of venues
     */
    public BookingTableView(ObservableList<Booking> bookingList) {
        super(FXML);
        requireNonNull(bookingList);

        this.bookingTableView.setItems(weeklySchedule);
        this.hourColumn = hours.stream()
                .map(title -> new TableColumn<DailySchedule, Resident[]>(title))
                .collect(Collectors.toList());
        addColumns();
        populateRows();
        configureTableProperties();

        updateTable(bookingList);
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
        this.bookingTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.dayColumn.setSortable(false);

        for (int i = 0; i < 15; i++) {
            this.bookingTableView.getColumns().get(i).setSortable(false);
            this.bookingTableView.getColumns().get(i).setResizable(false);
        }

    }

    public void updateTable(List<Booking> bookings) {
        this.weeklySchedule.setAll(DailySchedule.generateWeeklySchedule(bookings));
    }

}
