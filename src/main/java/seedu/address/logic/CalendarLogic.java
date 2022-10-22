package seedu.address.logic;

import static javafx.scene.paint.Color.WHITE;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.calendar.CalendarMonth;
import seedu.address.ui.CalendarEventListPanel;
import seedu.address.ui.NextButton;
import seedu.address.ui.PreviousButton;
import seedu.address.ui.RefreshButton;

/**
 * The manager of the logic for the Calendar.
 */
public class CalendarLogic {
    private static final String[] MONTH_NAMES = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    private static final String[] DAY_NAMES = {
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };
    private static final String switchButtonStyle = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final String SWITCH_BUTTON_STYLE = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final String TEXT_HEADER_STYLE = "-fx-font-size: 15pt; -fx-text-fill: white; "
            + "-fx-background-color: #fff";
    private PreviousButton prevButton = new PreviousButton("Prev", this);
    private NextButton nextButton = new NextButton("Next", this);
    private RefreshButton refreshButton = new RefreshButton("Refresh", this);
    @FXML
    private GridPane calendarDisplay;
    @FXML
    private HBox topCalendar;
    private Stage primaryStage;
    private Logic logic;
    private Calendar currentMonth;
    private CalendarMonth calendarMonth;

    /**
     * Constructs a {@code CalendarLogic} with the given {@code Logic}, {@code Stage}
     * {@code GridPane} and {@code HBox}.
     */
    public CalendarLogic(Logic logic, Stage primaryStage, GridPane calendarDisplay, HBox topCalendar) {
        requireAllNonNull(logic, primaryStage, calendarDisplay, topCalendar);
        this.calendarDisplay = calendarDisplay;
        this.topCalendar = topCalendar;
        this.logic = logic;
        this.primaryStage = primaryStage;
    }

    /**
     * Initialises the logic components for the Calendar.
     */
    public void initialiseLogic() {
        calendarMonth = new CalendarMonth(logic.getFilteredCalendarEventList());
        currentMonth = new GregorianCalendar();
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);
        calendarDisplay.setOnKeyPressed(e -> handleKeyPressed(e));
    }

    /**
     * Draws the Ui for the Calendar.
     */
    public void drawCalendar() {
        drawHeader();
        drawBody();
    }

    private void drawHeader() {
        Text textHeader = getTextHeader();
        topCalendar.getChildren().addAll(textHeader, prevButton.getRoot(), nextButton.getRoot(), refreshButton);
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

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.B)) {
            previous();
            calendarDisplay.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            next();
            calendarDisplay.requestFocus();
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

    /**
     * Refreshes the CalendarEvents.
     */
    public void refresh() {
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
        updateCalendarMonth();
    }

    /**
     * Displays the CalendarEvents in the next month.
     */
    public void next() {
        this.calendarMonth = new CalendarMonth(logic.getFilteredCalendarEventList());
        currentMonth = getNextMonth(currentMonth);
        updateCalendarMonth();
    }

    private void updateCalendarMonth() {
        Text newMonthHeader = getTextHeader();
        topCalendar.getChildren().set(0, newMonthHeader);
        topCalendar.setMargin(newMonthHeader, new Insets(0, 50, 0, 0));
        resetCalendarBody();
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

    private void resetGridPane() {
        topCalendar.getChildren().clear();
        Node node = calendarDisplay.getChildren().get(0);
        calendarDisplay.getChildren().clear();
        calendarDisplay.getChildren().add(0, node);
    }

    private void resetCalendarBody() {
        Node node = calendarDisplay.getChildren().get(0);
        calendarDisplay.getChildren().clear();
        calendarDisplay.getChildren().add(0, node);
    }
}
