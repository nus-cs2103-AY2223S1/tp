package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    @FXML
    private HBox topCalendar;



    public CalendarDisplay(ObservableList<CalendarEvent> calendarEventObservableList) {
        super(FXML);
        this.calendarMonth = new CalendarMonth(calendarEventObservableList);
        currentMonth = new GregorianCalendar();
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);
        drawCalendar();
    }

    private void drawCalendar() {
        drawHeader();
        drawBody();
    }

    private void drawHeader() {
        Text textHeader = getTextHeader();
        Button prevButtonHeader = getButtonHeader("Prev");
        Button nextButtonHeader = getButtonHeader("Next");

        topCalendar.getChildren().addAll(textHeader, prevButtonHeader, nextButtonHeader);
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

            vbox.getChildren().add(tDate);
            calendarEventsInDayOfMonth.stream().forEach(x -> vbox.getChildren().add(getAppointmentButton(x.toString())));
            calendarDisplay.add(vbox, dayOfWeek - 1, row);
            currentDay++;
            dayOfWeek++;
        }
    }


    private String getDayName(int n) {
        switch (n) {
        case 1:
            return "Sunday";
        case 2:
            return "Monday";
        case 3:
            return "Tuesday";
        case 4:
            return "Wednesday";
        case 5:
            return "Thursday";
        case 6:
            return "Friday";
        case 7:
            return "Saturday";
        }
        return "Invalid Input";
    }

    private String getMonthName(int n) {
        String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        return monthNames[n];
    }

    public Button getAppointmentButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(100, 30.00);
        button.setStyle("-fx-font-size: 7pt; -fx-background-color: white; -fx-border-color: grey; -fx-border-radius: 5;" );
        return button;
    }
    public Text getTextHeader() {
        String monthString = getMonthName(currentMonth.get(Calendar.MONTH));
        String yearString = String.valueOf(currentMonth.get(Calendar.YEAR));
        Text header = new Text(monthString + ", " + yearString);
        header.setStyle("-fx-font-size: 15pt; -fx-text-fill: white; -fx-background-color: #fff" );
        header.setFill(Color.WHITE);
        return header;
    }
    public Button getButtonHeader(String text) {
        if (text.equals("Prev")) {
            Button prevButton = new Button(text);
            prevButton.setOnAction(e -> previous());
            prevButton.setStyle("-fx-border-color: grey; -fx-border-radius: 5;");
            return prevButton;
        } else {
            Button nextButton = new Button(text);
            nextButton.setOnAction(e -> next());
            nextButton.setStyle("-fx-border-color: grey; -fx-border-radius: 5;");
            return nextButton;
        }
    }

    private void previous() {
        topCalendar.getChildren().clear();
        calendarDisplay.getChildren().clear();
        currentMonth = getPreviousMonth(currentMonth);
        drawCalendar();
    }

    private void next() {
        topCalendar.getChildren().clear();
        calendarDisplay.getChildren().clear();
        currentMonth = getNextMonth(currentMonth);
        drawCalendar();
    }

    private GregorianCalendar getPreviousMonth(Calendar cal) {
        int prevMonth;
        int prevYear;
        int currentMonth = cal.get(Calendar.MONTH);
        //If December, reset back to January, Add a year
        if(currentMonth == 0) {
            prevMonth = 11;
            prevYear = cal.get(Calendar.YEAR) - 1;
        } else { //else add a month, retain the year
            prevMonth = currentMonth - 1;
            prevYear = cal.get(Calendar.YEAR);
        }
        return new GregorianCalendar(prevYear, prevMonth, 1);
    }

    private GregorianCalendar getNextMonth(Calendar cal) {
        int futureMonth;
        int futureYear;
        int currentMonth = cal.get(Calendar.MONTH);
        //If January, reset back to December, decrement a year
        if(currentMonth == 11) {
            futureMonth = 0;
            futureYear = cal.get(Calendar.YEAR) + 1;
        } else { //else decrement a month, retain the year
            futureMonth = currentMonth + 1;
            futureYear = cal.get(Calendar.YEAR);
        }
        return new GregorianCalendar(futureYear, futureMonth, 1);
    }

}
