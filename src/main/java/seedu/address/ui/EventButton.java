package seedu.address.ui;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.model.calendar.CalendarEvent;

/**
 * Button that contains a CalendarEvent.
 */
public class EventButton extends UiPart<Region> {
    private static final String FXML = "EventButton.fxml";
    private static final String EVENT_BUTTON_STYLE = "-fx-font-size: 8pt; -fx-border-radius: 5;";
    private static final String GREY_BORDER = "-fx-border-color: grey;";
    private static final String ORANGE_BORDER = "-fx-border-color: orange;";
    private static final double ORIGIN = 0.0;
    private static final int TOOLTIP_OFFSET = 15;
    private Stage primaryStage;
    private CalendarEvent calendarEvent;
    @FXML
    private CalendarPopup calendarPopup;
    @FXML
    private Button eventButton;

    /**
     * Creates a {@code EventButton} with the given Appointment details.
     */
    public EventButton(CalendarEvent calendarEvent, Stage primaryStage) {
        super(FXML);
        this.calendarEvent = calendarEvent;
        this.primaryStage = primaryStage;
        this.calendarPopup = new CalendarPopup(calendarEvent, eventButton);
        initialiseEventButton();
    }

    private void initialiseEventButton() {
        eventButton.setText(calendarEvent.getTimeFormat() + " " + calendarEvent.getName());
        eventButton.focusedProperty().addListener(this::handleFocusedEvent);
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            if (calendarPopup.getRoot().isShowing()) {
                calendarPopup.getRoot().hide();
            }
        };
        primaryStage.heightProperty().addListener(stageSizeListener);
        primaryStage.widthProperty().addListener(stageSizeListener);
    }

    @FXML
    private void handleOnAction(ActionEvent event) {
        if (!calendarPopup.getRoot().isShowing()) {
            displayToolTip();
        }
    }

    @FXML
    private void handleFocusedEvent(Observable observable) {
        if (!calendarPopup.getRoot().isShowing() && eventButton.isFocused()) {
            eventButton.setStyle(EVENT_BUTTON_STYLE + ORANGE_BORDER);
            displayToolTip();
        }
        if (!eventButton.isFocused()) {
            eventButton.setStyle(EVENT_BUTTON_STYLE + GREY_BORDER);
            calendarPopup.getRoot().hide();
        }
    }

    private void displayToolTip() {
        Point2D p = eventButton.localToScene(ORIGIN, ORIGIN);
        calendarPopup.getRoot().show(eventButton, p.getX()
                - eventButton.getScene().getX() + eventButton.getScene().getWindow().getX(), p.getY()
                + eventButton.getScene().getY() + eventButton.getScene().getWindow().getY() + TOOLTIP_OFFSET);
    }
}
