package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Button that contains a CalendarEvent.
 */
public class EventButton extends Button {

    @FXML
    private Button button;

    public EventButton(String text) {
        this.button = new Button(text);
    }


    public Button getAppointmentButton() {

        button.setMaxSize(100, 30.00);
        button.setStyle("-fx-font-size: 7pt; -fx-background-color: white; "
                + "-fx-border-color: grey; -fx-border-radius: 5;");
        return button;
    }

}
