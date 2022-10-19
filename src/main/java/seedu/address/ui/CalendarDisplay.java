package seedu.address.ui;


import static javafx.scene.paint.Color.WHITE;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.logic.Logic;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.calendar.CalendarMonth;

/**
 * A UI component that displays information of a Calendar.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";

    private static final String[] MONTH_NAMES = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    private static final String[] DAY_NAMES = {
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };
    private static final String switchButtonStyle = "-fx-border-color: grey; -fx-border-radius: 5;";
    private PrevButton prevButton = new PrevButton("Prev", this);
    private NextButton nextButton = new NextButton("Next", this);
    private Calendar currentMonth;
    private CalendarMonth calendarMonth;
    private Stage primaryStage;
    private static final String SWITCH_BUTTON_STYLE = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final String TEXT_HEADER_STYLE = "-fx-font-size: 15pt; -fx-text-fill: white; "
            + "-fx-background-color: #fff";
    private Logic logic;
    @FXML
    private GridPane calendarDisplay;
    @FXML
    private HBox topCalendar;

    /**
     * Creates a Calendar with the given list of CalendarEvents.
     */
    public CalendarDisplay(Logic logic, Stage primaryStage) {
        super(FXML);
        this.calendarMonth = new CalendarMonth(logic.getFilteredCalendarEventList());
        this.primaryStage = primaryStage;
        currentMonth = new GregorianCalendar();
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);
        this.logic = logic;
        drawCalendar();
        calendarDisplay.setOnKeyPressed(e -> handleKeyPressed(e));
    }

    private void drawCalendar() {
        drawHeader();
        drawBody();
    }

    private void drawHeader() {
        Text textHeader = getTextHeader();
        Button refreshButtonHeader = getRefreshButton();
        topCalendar.getChildren().addAll(textHeader, prevButton, nextButton, refreshButtonHeader);
        topCalendar.setMargin(textHeader, new Insets(0, 50, 0, 0));
    }

    private void drawBody() {

        // Draw days of the week
        for (int day = 1; day <= 7; day++) {
            Text tDayName = new Text(" " + getDayName(day));
            tDayName.setFill(WHITE);
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
                    .getCalendarEventInDayOfMonth(currentDay, currentMonth.get(Calendar.MONTH) + 1,
                            currentMonth.get(Calendar.YEAR));

            CalendarEventListPanel calendarEventListPanel = new CalendarEventListPanel(calendarEventsInDayOfMonth,
                    primaryStage);
            VBox calendarEventList = calendarEventListPanel.getCalendarEventList(currentDay);
            calendarDisplay.add(calendarEventList, dayOfWeek - 1, row);
            currentDay++;
            dayOfWeek++;
        }
    }

    private String getDayName(int n) {
        return DAY_NAMES[n - 1];
    }

    private String getMonthName(int n) {
        return MONTH_NAMES[n];
    }

    public Text getTextHeader() {
        String monthString = getMonthName(currentMonth.get(Calendar.MONTH));
        String yearString = String.valueOf(currentMonth.get(Calendar.YEAR));
        Text header = new Text(monthString + ", " + yearString);
        header.setStyle(TEXT_HEADER_STYLE);
        header.setFill(WHITE);
        return header;
    }

    public Button getRefreshButton() {
            Button nextButton = new Button("Refresh");
            nextButton.setOnAction(e -> refresh());
            nextButton.setStyle(SWITCH_BUTTON_STYLE);
            return nextButton;
    }

    private void refresh() {
        resetGridPane();
        this.calendarMonth = new CalendarMonth(logic.getFilteredCalendarEventList());
        drawCalendar();
    }

    /**
     * Displays the CalendarEvents in the previous month.
     */
    public void previous() {
        this.calendarMonth = new CalendarMonth(logic.getFilteredCalendarEventList());
        currentMonth = getPreviousMonth(currentMonth);
        topCalendar.getChildren().set(0, getTextHeader());
        resetGridPane();
        drawBody();
    }

    /**
     * Displays the CalendarEvents in the next month.
     */
    public void next() {
        //resetGridPane();
        this.calendarMonth = new CalendarMonth(logic.getFilteredCalendarEventList());
        currentMonth = getNextMonth(currentMonth);
        topCalendar.getChildren().set(0, getTextHeader());
        resetGridPane();
        drawBody();
    }

    private GregorianCalendar getPreviousMonth(Calendar cal) {
        int prevMonth;
        int prevYear;
        int currentMonth = cal.get(Calendar.MONTH);
        //If December, reset back to January, Add a year
        if (currentMonth == 0) {
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
        if (currentMonth == 11) {
            futureMonth = 0;
            futureYear = cal.get(Calendar.YEAR) + 1;
        } else { //else decrement a month, retain the year
            futureMonth = currentMonth + 1;
            futureYear = cal.get(Calendar.YEAR);
        }
        return new GregorianCalendar(futureYear, futureMonth, 1);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.B)) {
            previous();
            calendarDisplay.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            next();
            calendarDisplay.requestFocus();
        }
    }

    private void resetGridPane() {
        Node node = calendarDisplay.getChildren().get(0);
        calendarDisplay.getChildren().clear();
        calendarDisplay.getChildren().add(0, node);
    }
}
