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
public class EventsLabeler extends UiPart<Region> {

    @FXML
    private Label eventsTextField;
    private static final String FXML = "EventsLabeler.fxml";

    /**
     * Labels event.
     */
    public EventsLabeler(Image icon) {
        super(FXML);
        ImageView eventsIcon = new ImageView(icon);
        eventsIcon.setFitWidth(25);
        eventsIcon.setFitHeight(25);
        eventsTextField.setGraphic(eventsIcon);
        eventsTextField.setContentDisplay(ContentDisplay.RIGHT);
    }



}
