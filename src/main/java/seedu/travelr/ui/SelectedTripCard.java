package seedu.travelr.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.travelr.model.trip.ObservableTrip;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class SelectedTripCard extends UiPart<Region> {

    private static final String FXML = "SelectedTripCard.fxml";
    private Image completed;
    private ObservableTrip selectedTrip;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label selectedTripLabel;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label tripLocation;
    @FXML
    private Label tripDate;

    /**
     * Creates a selectedTrip to display
     *
     * @param selectedTrip SelectedTrip to display.
     */
    public SelectedTripCard(ObservableTrip selectedTrip, Image completed) {
        super(FXML);
        this.selectedTrip = selectedTrip;
        title.textProperty().addListener((ob, o, n) -> setSelectedTripLabel(title.getText()));
        title.textProperty().bind(selectedTrip.getObservableTitle());
        tripLocation.textProperty().bind(selectedTrip.getObservableLocation());
        tripDate.textProperty().bind(selectedTrip.getObservableDate());
        description.textProperty().bind(selectedTrip.getObservableDescription());
    }

    private void setSelectedTripLabel(String title) {
        if (title == null) {
            selectedTripLabel.setText("No Trips Selected!");
        } else {
            selectedTripLabel.setText("Selected Trip");
            if (selectedTrip.getObservableBoolean().get()) {
                setIcon(this.title, completed, 22);
                this.title.setContentDisplay(ContentDisplay.RIGHT);
            }
            setIcon(tripLocation, new Image("/images/location.png"), 15);
            setIcon(tripDate, new Image("/images/calendar.png"), 15);
        }
    }

    private void setIcon(Label label, Image img, int size) {
        ImageView icon = new ImageView(img);
        icon.setFitWidth(size);
        icon.setFitHeight(size);
        label.setGraphic(icon);
    }
}
