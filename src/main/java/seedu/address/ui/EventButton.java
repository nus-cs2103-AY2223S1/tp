package seedu.address.ui;

import static java.lang.Integer.MAX_VALUE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Button that contains a CalendarEvent.
 */
public class EventButton extends Button {
    private static final int MAX_WIDTH = MAX_VALUE;
    private static final int MAX_HEIGHT = 30;
    private static final String EVENT_BUTTON_STYLE = "-fx-font-size: 7pt; -fx-background-color: white; "
            + "-fx-border-color: grey; -fx-border-radius: 5;";

    @FXML
    private Button button;

    public EventButton(String text) {
        this.button = new Button(text);
    }


    public Button getAppointmentButton() {

        button.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        button.setStyle(EVENT_BUTTON_STYLE);
        return button;
    }

}
