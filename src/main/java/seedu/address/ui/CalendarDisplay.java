package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.logic.Logic;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.calendar.CalendarMonth;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";

    private Calendar currentMonth;
    private Logic logic;
    private CalendarMonth calendarMonth;

    @FXML
    private GridPane calendarDisplay;

    public CalendarDisplay(ObservableList<CalendarEvent> calendarEventObservableList) {
        super(FXML);
        this.calendarMonth = new CalendarMonth(calendarEventObservableList);
        currentMonth = new GregorianCalendar();
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);
        drawCalendar();
    }

    private void drawCalendar() {
        drawBody();
    }

    private void drawBody() {

        // Draw days of the week
        for (int day = 1; day <= 7; day++) {
            Text tDayName = new Text(" " + getDayName(day));
            calendarDisplay.add(tDayName, day - 1, 0);
        }

        // Draw days in month
        int currentDay = currentMonth.get(Calendar.DAY_OF_MONTH);
        int daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
        int row = 1;
        for (int i = currentDay; i <= daysInMonth; i++) {
            if (dayOfWeek == 8) {
                dayOfWeek = 1;
                row++;
            }
            Text tDate = new Text(" " + String.valueOf(currentDay) + "\n appt1"
            + "\n appt2222222222222222222222222222222222" +
                    "\n apt3" +
                    "\n appt4" +
                    "\napp5"
            );
            calendarDisplay.add(tDate, dayOfWeek - 1, row);
            currentDay++;
            dayOfWeek++;

        }
    }


    private String getDayName(int n) {
        StringBuilder sb = new StringBuilder();
        switch (n) {
        case 1:
            sb.append("Sunday");
            break;
        case 2:
            sb.append("Monday");
            break;
        case 3:
            sb.append("Tuesday");
            break;
        case 4:
            sb.append("Wednesday");
            break;
        case 5:
            sb.append("Thursday");
            break;
        case 6:
            sb.append("Friday");
            break;
        case 7:
            sb.append("Saturday");
        }
        return sb.toString();
    }

    private String getMonthName(int n) {
        String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        return monthNames[n];
    }

}
