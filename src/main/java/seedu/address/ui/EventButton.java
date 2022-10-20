package seedu.address.ui;

import static java.lang.Integer.MAX_VALUE;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;

/**
 * Button that contains a CalendarEvent.
 */
public class EventButton extends Button {
    private static final int MAX_WIDTH = MAX_VALUE;
    private static final int MAX_HEIGHT = 30;
    private static final String EVENT_BUTTON_STYLE = "-fx-font-size: 8pt; -fx-border-color: grey; -fx-border-radius: 5;"
            + "-fx-min-width: 100;";
    private Stage primaryStage;
    private CalendarPopup calendarPopup;

    /**
     * Creates a {@code CalendarButton} with the given Appointment details.
     */
    public EventButton(Name name, String time, Location location, String date, Stage primaryStage) {
        super(time + " " + name);
        this.setStyle(EVENT_BUTTON_STYLE);
        this.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        this.setOnAction(x -> {
            displayToolTip(x);
        });
        this.primaryStage = primaryStage;
        this.focusedProperty().addListener(x -> displayToolTip(x));
        this.calendarPopup = new CalendarPopup(name, time, location, date, this);
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            if (calendarPopup.isShowing()) {
                calendarPopup.hide();
            }
        };
        primaryStage.heightProperty().addListener(stageSizeListener);
        primaryStage.widthProperty().addListener(stageSizeListener);
    }

    @FXML
    private void displayToolTip(ActionEvent event) {
        Point2D p = localToScene(0.0, 0.0);
        if (!calendarPopup.isShowing()) {
            calendarPopup.show(this, p.getX()
                    + this.getScene().getX() + this.getScene().getWindow().getX(), p.getY()
                    + this.getScene().getY() + this.getScene().getWindow().getY() + 15);
        }
    }

    @FXML
    private void displayToolTip(Observable observable) {
        Point2D p = localToScene(0.0, 0.0);
        if (!calendarPopup.isShowing() && isFocused()) {
            calendarPopup.show(this, p.getX()
                    + this.getScene().getX() + this.getScene().getWindow().getX(), p.getY()
                    + this.getScene().getY() + this.getScene().getWindow().getY() + 15);
        }
        if (!isFocused()) {
            calendarPopup.hide();
        }
    }
}
