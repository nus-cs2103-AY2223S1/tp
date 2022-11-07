package seedu.address.logic;

import static javafx.scene.paint.Color.WHITE;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.model.appointment.Date;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.calendar.CalendarMonth;
import seedu.address.ui.calendar.CalendarDisplay;
import seedu.address.ui.calendar.CalendarEventListPanel;

//@@author wongyewjon
//Solution below adapted from http://www.java2s.com/ref/java/javafx-gridpane-layout-calendar.html
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
    private static final String TEXT_HEADER_STYLE = "-fx-font-size: 15pt; -fx-text-fill: white; "
            + "-fx-background-color: #fff";

    private static final String EMPTY_MESSAGE = "";
    private static final String SUCCESS_MESSAGE = "success";
    private static final String WRONG_FORMAT_MESSAGE = "failure";
    private Stage primaryStage;
    private Logic logic;
    private Calendar currentMonth;
    private CalendarMonth calendarMonth;
    private ObservableList<CalendarEvent> filteredCalendarEventList;
    private CalendarDisplay calendarDisplay;

    //@@author wongyewjon
    /**
     * Constructs a {@code CalendarLogic} with the given {@code Logic}, {@code Stage}
     * {@code GridPane} and {@code HBox}.
     */
    public CalendarLogic(Logic logic, Stage primaryStage, CalendarDisplay calendarDisplay) {
        requireAllNonNull(logic, primaryStage, calendarDisplay);
        this.logic = logic;
        this.primaryStage = primaryStage;
        this.calendarDisplay = calendarDisplay;
        ListChangeListener<CalendarEvent> temp = (x) -> {
            x.next();
            refresh();
        };
        this.filteredCalendarEventList = logic.getFilteredCalendarEventList();
        filteredCalendarEventList.addListener(temp);
    }

    //@@author wongyewjon
    /**
     * Initialises the logic components for the Calendar.
     */
    public void initialiseLogic() {
        calendarMonth = new CalendarMonth(filteredCalendarEventList);
        currentMonth = new GregorianCalendar();
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);
    }

    //@@author wongyewjon
    /**
     * Fills body of the Calendar with the individual date components.
     */
    public void drawBody() {
        // Draw days of the week
        for (int day = 1; day <= 7; day++) {
            Text tDayName = new Text(" " + getDayName(day));
            tDayName.setFill(WHITE);
            calendarDisplay.addToCalendarGrid(tDayName, day - 1, 0);
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
            calendarDisplay.addToCalendarGrid(calendarEventList, dayOfWeek - 1, row);
            currentDay++;
            dayOfWeek++;
        }
    }

    //@@author wongyewjon
    private String getDayName(int n) {
        return DAY_NAMES[n - 1];
    }

    //@@author wongyewjon
    private String getMonthName(int n) {
        return MONTH_NAMES[n];
    }

    //@@author wongyewjon
    /**
     * Returns the {@code Text} representing the current month.
     */
    public Text getTextHeader() {
        String monthString = getMonthName(currentMonth.get(Calendar.MONTH));
        String yearString = String.valueOf(currentMonth.get(Calendar.YEAR));
        Text header = new Text(monthString + ", " + yearString);
        header.setStyle(TEXT_HEADER_STYLE);
        header.setFill(WHITE);
        return header;
    }

    //@@author wongyewjon
    /**
     * Refreshes the CalendarEvents.
     */
    public void refresh() {
        calendarDisplay.resetGridPane();
        this.calendarMonth = new CalendarMonth(filteredCalendarEventList);
        calendarDisplay.drawCalendar();
    }

    //@@author wongyewjon
    /**
     * Displays the CalendarEvents in the previous month.
     */
    public void previous() {
        this.calendarMonth = new CalendarMonth(filteredCalendarEventList);
        currentMonth = getPreviousMonth(currentMonth);
        calendarDisplay.setTextValidation(EMPTY_MESSAGE);
        updateCalendarMonth();
    }

    //@@author wongyewjon
    /**
     * Displays the CalendarEvents in the next month.
     */
    public void next() {
        this.calendarMonth = new CalendarMonth(filteredCalendarEventList);
        currentMonth = getNextMonth(currentMonth);
        calendarDisplay.setTextValidation(EMPTY_MESSAGE);
        updateCalendarMonth();
    }

    //@@author wongyewjon
    /**
     * Displays the CalendarEvents given by the user input.
     */
    public void jump() {
        this.calendarMonth = new CalendarMonth(logic.getFilteredCalendarEventList());
        currentMonth = getJumpMonth(currentMonth);
        updateCalendarMonth();
    }

    //@@author wongyewjon
    private GregorianCalendar getJumpMonth(Calendar cal) throws DateTimeParseException {
        String inputDate = calendarDisplay.getJumpBoxText();
        calendarDisplay.clearJumpBox();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-uuuu");
            formatter = formatter.withResolverStyle(ResolverStyle.STRICT);
            LocalDate ld = LocalDate.parse(inputDate, formatter);
            Date jumpDate = new Date(ld);
            int newMonth = jumpDate.getMonth() - 1;
            int newYear = jumpDate.getYear();
            calendarDisplay.setTextValidation(SUCCESS_MESSAGE);
            return new GregorianCalendar(newYear, newMonth, 1);
        } catch (DateTimeParseException e) {
            if (e.getCause() == null) {
                calendarDisplay.setTextValidation(WRONG_FORMAT_MESSAGE);
            } else {
                String str = e.getCause().getMessage();
                calendarDisplay.setTextValidation(str);
            }
        }
        return new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
    }

    //@@author wongyewjon
    private void updateCalendarMonth() {
        Text newMonthHeader = getTextHeader();
        calendarDisplay.setTopCalendarHeader(0, newMonthHeader);
        calendarDisplay.resetMargin(newMonthHeader);
        calendarDisplay.resetCalendarBody();
        drawBody();
    }

    //@@author wongyewjon
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

    //@@author wongyewjon
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
}
