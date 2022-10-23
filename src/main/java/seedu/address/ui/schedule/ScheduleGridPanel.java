package seedu.address.ui.schedule;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Weekdays;
import seedu.address.ui.UiPart;


/**
 * Creates a schedule grid panel
 */
public class ScheduleGridPanel extends UiPart<Region> {

    private static final String FXML = "schedule/ScheduleGridPanel.fxml";
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 22;
    private static final int SCALE_FACTOR = 2;

    private static final int ROW_SPAN = 1;
    private static final double COLUMNS_WIDTH = 95;
    private static final int NUM_OF_COLUMNS = SCALE_FACTOR * (END_HOUR - START_HOUR) + ROW_SPAN;

    private static final int COLUMN_SPAN = 1;
    private static final double ROWS_WIDTH = 95;
    private static final int NUM_OF_ROWS = SCALE_FACTOR * (END_HOUR - START_HOUR) + COLUMN_SPAN;

    private final Logger logger = LogsCenter.getLogger(ScheduleGridPanel.class);
    private final ObservableList<Schedule> schedules;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;

    /**
     * Constructs a schedule grid panel
     */
    public ScheduleGridPanel(ObservableList<Schedule> schedules) {
        super(FXML);
        this.schedules = schedules;
    }

    /**
     * Constructs horizontal timetable
     */
    public void constructHorizontalTimetable() {
        constructHorizontalGrid();
        addWeekdayToHorizontalGrid();
        if (schedules.size() == 0) {
            logger.info("No schedules have been added");
        } else {
            addScheduleSlotToHorizontalGrid();
        }
    }

    /**
     * Constructs vertical timetable
     */
    public void constructVerticalTimetable() {
        constructVerticalGrid();
        addWeekdayToVerticalGrid();
        if (schedules.size() == 0) {
            logger.info("No schedules have been added");
        } else {
            addScheduleSlotToVerticalGrid();
        }
    }


    private int getColumnSpan(double duration) {
        return (int) (duration * SCALE_FACTOR);
    }

    private int getRowSpan(double duration) {
        return (int) (duration * SCALE_FACTOR);
    }

    /**
     * Get the index of slot's start time
     * @param hour beginning hour
     * @return the 0-based index
     */
    public int getStartTimeIndex(double hour) {
        return (int) ((hour - START_HOUR) * SCALE_FACTOR + 1);
    }

    /**
     * Gets the index of weekday
     * @param weekday
     * @return the 0-based index
     */
    public int getWeekdayIndex(Weekdays weekday) {
        switch (weekday) {
        case Monday:
            return 0;
        case Tuesday:
            return 1;
        case Wednesday:
            return 2;
        case Thursday:
            return 3;
        case Friday:
            return 4;
        case Saturday:
            return 5;
        case Sunday:
            return 6;
        default:
            return -1;
        }
    }

    /**
     * Builds the horizontal grid pane
     */
    public void constructHorizontalGrid() {
        logger.fine("Constructing the horizontal grid panel");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i < NUM_OF_COLUMNS; ++i) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(COLUMNS_WIDTH);
            gridPane.getColumnConstraints().add(column);
        }
    }

    /**
     * Builds the vertical grid pane
     */
    public void constructVerticalGrid() {
        logger.fine("Constructing the vertical grid panel");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i < NUM_OF_ROWS; ++i) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(ROWS_WIDTH);
            gridPane.getRowConstraints().add(row);
        }
    }

    /**
     * Creates a Horizontal SlotContainer
     */
    public SlotContainer createHorizontalSlot(Schedule schedule) {
        double duration = schedule.getDuration();
        double slotWidth = duration * SCALE_FACTOR * COLUMNS_WIDTH;
        return new ScheduleSlot(schedule);
    }

    /**
     * Creates a Vertical SlotContainer
     */
    public SlotContainer createVerticalSlot(Schedule schedule) {
        double duration = schedule.getDuration();
        double slotHeight = duration * SCALE_FACTOR * ROWS_WIDTH;
        return new ScheduleSlot(schedule);
    }


    /**
     * Adds weekdays to the horizontal grid
     */
    public void addWeekdayToHorizontalGrid() {
        for (int i = 0; i < 7; ++i) {
            SlotContainer weekday = new WeekdayCard(i);
            gridPane.add(weekday.getRoot(), 0, i, 1, ROW_SPAN);
        }
    }

    /**
     * Adds weekdays to the vertical grid
     */
    public void addWeekdayToVerticalGrid() {
        for (int i = 0; i < 7; ++i) {
            SlotContainer weekday = new WeekdayCard(i);
            gridPane.add(weekday.getRoot(), i, 0, COLUMN_SPAN, 1);
        }
    }


    /**
     * Adds all schedule to the horizontal grid pane
     */
    public void addScheduleSlotToHorizontalGrid() {
        logger.fine("Add all schedules to the grid");
        int rowIndex = 0;
        int colIndex = 0;

        for (Schedule schedule: schedules) {
            double startHour = schedule.getHour(schedule.getStartTime());
            double duration = schedule.getDuration();
            Weekdays weekday = schedule.getWeekday();
            rowIndex = getWeekdayIndex(weekday);
            colIndex = getStartTimeIndex(startHour);
            SlotContainer slot = createHorizontalSlot(schedule);
            gridPane.add(slot.getRoot(), colIndex, rowIndex, getColumnSpan(duration), ROW_SPAN);
        }
    }

    /**
     * Adds all schedule to the vertical grid pane
     */
    public void addScheduleSlotToVerticalGrid() {
        logger.fine("Add all schedules to the vertical grid");
        int colIndex = 0;
        int rowIndex = 0;

        for (Schedule schedule: schedules) {
            double startHour = schedule.getHour(schedule.getStartTime());
            double duration = schedule.getDuration();
            Weekdays weekday = schedule.getWeekday();
            colIndex = getWeekdayIndex(weekday);
            rowIndex = getStartTimeIndex(startHour);
            SlotContainer slot = createVerticalSlot(schedule);
            gridPane.add(slot.getRoot(), colIndex, rowIndex, COLUMN_SPAN, getRowSpan(duration));
        }
    }

}
