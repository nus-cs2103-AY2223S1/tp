package seedu.address.ui;

import static java.lang.Integer.MAX_VALUE;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seedu.address.model.calendar.CalendarEvent;

/**
 * Button that contains a CalendarEvent.
 */
public class EventButton extends Button {
    private static final int MAX_WIDTH = MAX_VALUE;
    private static final int MAX_HEIGHT = 30;
    private static final String EVENT_BUTTON_STYLE = "-fx-font-size: 8pt; -fx-border-color: grey; -fx-border-radius: 5;"
            + "-fx-min-width: 100;";
    private static final double ORIGIN = 0.0;
    private Stage primaryStage;
    private CalendarPopup calendarPopup;

    /**
     * Creates a {@code EventButton} with the given Appointment details.
     */
    public EventButton(CalendarEvent calendarEvent, Stage primaryStage) {
        super(calendarEvent.getTimeFormat() + " " + calendarEvent.getName());
        this.primaryStage = primaryStage;
        this.calendarPopup = new CalendarPopup(calendarEvent, this);
        initialiseEventButton();
    }

    private void initialiseEventButton() {
        this.setStyle(EVENT_BUTTON_STYLE);
        this.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        this.setOnAction(this::handleOnPressed);
        this.focusedProperty().addListener(this::handleFocusedEvent);
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            if (calendarPopup.isShowing()) {
                calendarPopup.hide();
            }
        };
        primaryStage.heightProperty().addListener(stageSizeListener);
        primaryStage.widthProperty().addListener(stageSizeListener);
    }

    @FXML
    private void handleOnPressed(ActionEvent event) {
        if (!calendarPopup.isShowing()) {
            displayToolTip();
        }
    }

    @FXML
    private void handleFocusedEvent(Observable observable) {
        if (!calendarPopup.isShowing() && isFocused()) {
            displayToolTip();
        }
        if (!isFocused()) {
            calendarPopup.hide();
        }
    }

    private void displayToolTip() {
        Point2D p = localToScene(ORIGIN, ORIGIN);
        calendarPopup.show(this, p.getX()
                + this.getScene().getX() + this.getScene().getWindow().getX(), p.getY()
                + this.getScene().getY() + this.getScene().getWindow().getY() + 15);
    }
}
