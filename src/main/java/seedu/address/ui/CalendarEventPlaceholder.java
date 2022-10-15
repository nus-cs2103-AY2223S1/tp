package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.person.Person;

public class CalendarEventPlaceholder extends UiPart<Region> {

    private static final String FXML = "CalendarEventPlaceHolder.fxml";

    ObservableList<CalendarEvent> calendarEventsInDayOfMonth;

    @FXML
    private StackPane calendarEventListPlaceholder;


    public CalendarEventPlaceholder(ObservableList<CalendarEvent> calendarEventsInDayOfMonth) {
        super(FXML);
        this.calendarEventsInDayOfMonth = calendarEventsInDayOfMonth;

        fillInnerParts();
    }

    void fillInnerParts() {
        CalendarEventListPanel calendarEventListPanel= new CalendarEventListPanel(calendarEventsInDayOfMonth);
        calendarEventListPlaceholder.getChildren().add(calendarEventListPanel.getRoot());
    }
}
