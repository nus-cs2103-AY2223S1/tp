package seedu.address.ui;

import static javafx.scene.paint.Color.WHITE;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.address.model.calendar.CalendarEvent;

/**
 * Panel containing the list of CalendarEvents.
 */
public class CalendarEventListPanel extends VBox {

    private ObservableList<CalendarEvent> calendarDayEvents;

    @FXML
    private VBox calendarEventList;

    /**
     * Creates a {@code CalendarEventListPanel} with the given {@code ObservableList}.
     */
    public CalendarEventListPanel(ObservableList<CalendarEvent> calendarDayEvents) {

        this.calendarDayEvents = calendarDayEvents;
        this.calendarEventList = new VBox();

    }
    public VBox getCalendarEventList(int currentDay) {
        Text tDate = getTextDate(currentDay);

        calendarEventList.getChildren().add(tDate);
        calendarDayEvents.stream()
                .forEach(x -> calendarEventList.getChildren()
                        .add(new EventButton(x.toString()).getAppointmentButton()));
        return calendarEventList;
    }

    public Text getTextDate(int currentDay) {
        Text tDate = new Text(" " + String.valueOf(currentDay));
        tDate.setFill(WHITE);
        return tDate;
    }

}
