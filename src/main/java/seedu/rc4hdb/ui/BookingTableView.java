package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.Hour;

/**
 * Represents the table of venues.
 */
public class BookingTableView extends UiPart<Region> {

    private static final String FXML = "BookingTableView.fxml";

    private static final List<String> daysOfWeek = List.of("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

    private final TableColumn<DailySchedule, Day> dayColumn = new TableColumn<>("Day");
    private final TableColumn<DailySchedule, Hour> firstColumn = new TableColumn<>("0800");
    private final TableColumn<DailySchedule, Hour> secondColumn = new TableColumn<>("0900");
    private final TableColumn<DailySchedule, Hour> thirdColumn = new TableColumn<>("1000");
    private final TableColumn<DailySchedule, Hour> fourthColumn = new TableColumn<>("1100");
    private final TableColumn<DailySchedule, Hour> fifthColumn = new TableColumn<>("1200");
    private final TableColumn<DailySchedule, Hour> sixthColumn = new TableColumn<>("1300");
    private final TableColumn<DailySchedule, Hour> seventhColumn = new TableColumn<>("1400");
    private final TableColumn<DailySchedule, Hour> eighthColumn = new TableColumn<>("1500");
    private final TableColumn<DailySchedule, Hour> ninthColumn = new TableColumn<>("1600");
    private final TableColumn<DailySchedule, Hour> tenthColumn = new TableColumn<>("1700");
    private final TableColumn<DailySchedule, Hour> eleventhColumn = new TableColumn<>("1800");
    private final TableColumn<DailySchedule, Hour> twelfthColumn = new TableColumn<>("1900");
    private final TableColumn<DailySchedule, Hour> thirteenthColumn = new TableColumn<>("2000");
    private final TableColumn<DailySchedule, Hour> fourteenthColumn = new TableColumn<>("2100");
    private final TableColumn<DailySchedule, Hour> fifteenthColumn = new TableColumn<>("2200");
    private final TableColumn<DailySchedule, Hour> sixteenthColumn = new TableColumn<>("2300");

    @FXML
    private TableView<DailySchedule> bookingTableView;
    private ObservableList<DailySchedule> weeklySchedule;

    /**
     * Constructor for a VenueTableView instance. The venue list is processed to remove expired bookings.
     * @param bookingList The list of venues
     */
    public BookingTableView(List<Booking> bookingList) {
        super(FXML);
        requireNonNull(bookingList);

        this.bookingTableView.setItems(weeklySchedule);
        populateDayOfWeekColumn(dayColumn);
        addColumns();
        populateRows();
        configureTableProperties();
    }

    public ObservableList<DailySchedule> getWeeklySchedule(List<Booking> bookingList) {
        HashMap<Day, List<Booking>> bookingMap = new HashMap<>();
        for (Day day : Day.daysOfWeek.stream().map()) {

        }
    }

    private void addColumns() {
        this.bookingTableView.getColumns().add(dayColumn);
        this.bookingTableView.getColumns().add(firstColumn);
        this.bookingTableView.getColumns().add(secondColumn);
        this.bookingTableView.getColumns().add(thirdColumn);
        this.bookingTableView.getColumns().add(fourthColumn);
        this.bookingTableView.getColumns().add(fifthColumn);
        this.bookingTableView.getColumns().add(sixthColumn);
        this.bookingTableView.getColumns().add(seventhColumn);
        this.bookingTableView.getColumns().add(eighthColumn);
        this.bookingTableView.getColumns().add(ninthColumn);
        this.bookingTableView.getColumns().add(tenthColumn);
        this.bookingTableView.getColumns().add(eleventhColumn);
        this.bookingTableView.getColumns().add(twelfthColumn);
        this.bookingTableView.getColumns().add(thirteenthColumn);
        this.bookingTableView.getColumns().add(fourteenthColumn);
        this.bookingTableView.getColumns().add(fifteenthColumn);
        this.bookingTableView.getColumns().add(sixteenthColumn);
    }

    private void populateRows(List<Booking> bookings) {
        this.dayColumn.setCellFactory(this::populateDayOfWeekColumn);

    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/33353014/creating-a-row-index-column-in-javafx
     */
    private TableCell<DailySchedule, Day> populateDayOfWeekColumn(TableColumn<DailySchedule, Day> column) {
        return new TableCell<>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (index > 6 || index < 0) {
                } else {
                    setText(dailySchedule.getDay().value);
                }
            }
        };
    }

    private TableCell<>

    private void configureTableProperties() {
        this.bookingTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    public void updateTable(List<Booking> bookings) {
        populateRows(bookings);
    }
}
