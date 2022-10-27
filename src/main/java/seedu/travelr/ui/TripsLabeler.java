package seedu.travelr.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class TripsLabeler extends UiPart<Region> {

    private static final String FXML = "TripsLabeler.fxml";

    @FXML
    private Label tripsTextField;


    /**
     * Labels trips.
     */
    public TripsLabeler(Image icon) {
        super(FXML);
        ImageView tripsIcon = new ImageView(icon);
        tripsIcon.setFitWidth(25);
        tripsIcon.setFitHeight(25);
        tripsTextField.setGraphic(tripsIcon);
        tripsTextField.setContentDisplay(ContentDisplay.RIGHT);

    }


}
