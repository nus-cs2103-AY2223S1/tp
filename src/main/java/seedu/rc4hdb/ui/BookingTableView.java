package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.fields.BookingField;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.Hour;

/**
 * Represents the table of venues.
 */
public class BookingTableView extends UiPart<Region> {

    private static final String FXML = "BookingTableView.fxml";

    private final TableColumn<Booking, Day> dayColumn = new TableColumn<>("Day");
    private final TableColumn<Booking, Hour> firstColumn = new TableColumn<>("0800");
    private final TableColumn<Booking, Hour> secondColumn = new TableColumn<>("0900");
    private final TableColumn<Booking, Hour> thirdColumn = new TableColumn<>("1000");
    private final TableColumn<Booking, Hour> fourthColumn = new TableColumn<>("1100");
    private final TableColumn<Booking, Hour> fifthColumn = new TableColumn<>("1200");
    private final TableColumn<Booking, Hour> sixthColumn = new TableColumn<>("1300");
    private final TableColumn<Booking, Hour> seventhColumn = new TableColumn<>("1400");
    private final TableColumn<Booking, Hour> eighthColumn = new TableColumn<>("1500");
    private final TableColumn<Booking, Hour> ninthColumn = new TableColumn<>("1600");
    private final TableColumn<Booking, Hour> tenthColumn = new TableColumn<>("1700");
    private final TableColumn<Booking, Hour> eleventhColumn = new TableColumn<>("1800");
    private final TableColumn<Booking, Hour> twelfthColumn = new TableColumn<>("1900");
    private final TableColumn<Booking, Hour> thirteenthColumn = new TableColumn<>("2000");
    private final TableColumn<Booking, Hour> fourteenthColumn = new TableColumn<>("2100");
    private final TableColumn<Booking, Hour> fifteenthColumn = new TableColumn<>("2200");
    private final TableColumn<Booking, Hour> sixteenthColumn = new TableColumn<>("2300");

    private ObservableList<Booking> observableBookings = FXCollections.observableArrayList();
    private final static List<String> daysOfWeek = List.of("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

    @FXML
    private TableView<Booking> bookingTableView;

    /**
     * Constructor for a VenueTableView instance. The venue list is processed to remove expired bookings.
     * @param bookingList The list of venues
     */
    public BookingTableView(ObservableList<Booking> bookingList) {
        super(FXML);
        requireNonNull(bookingList);

        this.observableBookings  = bookingList;
        this.bookingTableView.setItems(bookingList);
        populateDayOfWeekColumn(dayColumn);
        addColumns();
        populateRows();
        configureTableProperties();

//        this.observableBookings.setAll(bookingList);
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

    private void populateRows() {
        this.dayColumn.setCellFactory(this::populateDayOfWeekColumn);
//        this.firstColumn.setCellValueFactory(new PropertyValueFactory<>("Resident"));
        separateByDay();
    }

    private void separateByDay() {
        Comparator<Booking> byDayOfWeek = new Comparator<Booking>() {
            @Override
            public int compare(Booking o1, Booking o2) {
                return o1.compare(o2);
            }
        };

        Stream<Booking> firstPeriod = this.observableBookings.stream()
                .filter(b -> b.getHourPeriod().getStart().toString().equals("8")) // improve here
                .sorted(byDayOfWeek);

//        for (int i = 0; i < 7; i++) {
//            int finalI = i;
//            if (!firstPeriod.anyMatch(b -> b.getDayOfWeek().equals(daysOfWeek.get(finalI)))) {
//                List<Integer> emptyDaysIndex = new ArrayList<>().add(i);
//            };
//        }
        //
//        String test = firstPeriod.map(c -> c.toString()).collect(Collectors.joining());
//        System.out.print(test);

    }



    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/33353014/creating-a-row-index-column-in-javafx
     */
    private TableCell<Booking, Day> populateDayOfWeekColumn(TableColumn<Booking, Day> column) {
        return new TableCell<>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (index > 6 || index < 0) {
                } else {
                    setText(daysOfWeek.get(index));
                }
            }
        };
    }

    private void configureTableProperties() {
        this.bookingTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    public void setObservableFields(ObservableList<Booking> list) {
        this.observableBookings.setAll(list);
    }
}
