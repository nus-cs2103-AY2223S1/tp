package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Button that contains a CalendarEvent.
 */
public class EventButton extends Button {
    private static final int maxWidth = 100;
    private static final int maxHeight = 30;
    @FXML
    private Button button;

    public EventButton(String text) {
        this.button = new Button(text);
    }


    public Button getAppointmentButton() {

        button.setMaxSize(maxWidth, maxHeight);
        button.setStyle("-fx-font-size: 7pt; -fx-background-color: white; "
                + "-fx-border-color: grey; -fx-border-radius: 5;");
        return button;
    }

}
