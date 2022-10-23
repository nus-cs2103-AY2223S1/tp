package seedu.rc4hdb.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Represents the table of venues.
 */
public class VenueTableView extends UiPart<Region> {

    private static final String FXML = "VenueTableView.fxml";

    private final TableColumn<Venue, String> venueIndexColumn = new TableColumn<>("Index");
    private final TableColumn<Venue, String> venueNameColumn = new TableColumn<>("Name");

    @FXML
    private TableView<Venue> venueTableView;

    /**
     * Constructor for a VenueTableView instance. The venue list is processed to remove expired bookings.
     * @param venueList The list of venues
     */
    public VenueTableView(ObservableList<Venue> venueList) {
        super(FXML);
        requireNonNull(venueList);

        venueList.forEach(Venue::clearExpiredBookings);
        this.venueTableView.setItems(venueList);

        addColumns();
        populateRows();
        configureTableProperties();
    }

    private void addColumns() {
        this.venueTableView.getColumns().add(venueIndexColumn);
        this.venueTableView.getColumns().add(venueNameColumn);
    }

    private void populateRows() {
        this.venueIndexColumn.setCellValueFactory(new PropertyValueFactory<>("venueIndex"));
        this.venueNameColumn.setCellValueFactory(new PropertyValueFactory<>("venueName"));
    }

    private void configureTableProperties() {
        this.venueTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
