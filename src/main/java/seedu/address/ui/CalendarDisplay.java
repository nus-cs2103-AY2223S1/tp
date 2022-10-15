package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import seedu.address.logic.Logic;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.calendar.CalendarMonth;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";

    private Calendar currentMonth;
    private CalendarMonth calendarMonth;

    @FXML
    private GridPane calendarDisplay;
    /*@FXML
    private StackPane zeroOne;
    @FXML
    private StackPane oneOne;
    @FXML
    private StackPane twoOne;
    @FXML
    private StackPane threeOne;
    @FXML
    private StackPane fourOne;
    @FXML
    private StackPane fiveOne;
    @FXML
    private StackPane sixOne;
    @FXML
    private StackPane zeroTwo;
    @FXML
    private StackPane oneTwo;
    @FXML
    private StackPane twoTwo;
    @FXML
    private StackPane threeTwo;
    @FXML
    private StackPane fourTwo;
    @FXML
    private StackPane fiveTwo;
    @FXML
    private StackPane sixTwo;
    @FXML
    private StackPane zeroThree;
    @FXML
    private StackPane oneThree;
    @FXML
    private StackPane twoThree;
    @FXML
    private StackPane threeThree;
    @FXML
    private StackPane fourThree;
    @FXML
    private StackPane fiveThree;
    @FXML
    private StackPane sixThree;
    @FXML
    private StackPane zeroFour;
    @FXML
    private StackPane oneFour;
    @FXML
    private StackPane twoFour;
    @FXML
    private StackPane threeFour;
    @FXML
    private StackPane fourFour;
    @FXML
    private StackPane fiveFour;
    @FXML
    private StackPane sixFour;
    @FXML
    private StackPane zeroFive;
    @FXML
    private StackPane oneFive;
    @FXML
    private StackPane twoFive;
*/





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
            ObservableList<CalendarEvent> calendarEventsInDayOfMonth = calendarMonth
                    .getCalendarEventInDayOfMonth(currentDay, currentMonth.get(Calendar.MONTH) + 1);

            Text tDate = new Text(" " + String.valueOf(currentDay));
            VBox vbox = new VBox();

            //vbox.setMinSize(150.00,100.00);
            vbox.setFillWidth(true);

            vbox.getChildren().add(tDate);
            calendarEventsInDayOfMonth.stream().forEach(x -> vbox.getChildren().add(getButton(x.toString())));
            calendarDisplay.add(vbox, dayOfWeek - 1, row);
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

    public Button getButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(100, 30.00);
        button.setStyle("-fx-font-size: 7pt; -fx-background-color: white; -fx-border-color: grey; -fx-border-radius: 5;" );
        return button;
    }
    /*private StackPane getFirstDayPlaceHolder(int dayOfWeek) {
        switch (dayOfWeek) {
        case 1:
            return zeroOne;
            break;
        case 2:
            return oneOne;
            break;
        case 3:
            return twoOne;
            break;
        case 4:
            return threeOne;
            break;
        case 5:
            return fourOne;
            break;
        case 6:
            return fiveOne;
            break;
        case 7:
            return sixOne;
        }
    }
*/
}
