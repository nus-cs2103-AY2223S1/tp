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
import seedu.address.ui.theme.Theme;

/**
 * Creates a schedule grid panel
 */
public class ScheduleGridPanelVertical extends UiPart<Region> {

    private static final String FXML = "schedule/ScheduleGridPanelVertical.fxml";
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 22;
    private static final int SCALE_FACTOR = 2;

    private static final int ROW_SPAN = 1;
    private static final int COLUMN_SPAN = 1;
    private static final double COLUMNS_WIDTH = 200;
    private static final double ROWS_WIDTH = 70;
    private static final int NUM_OF_COLUMNS = SCALE_FACTOR * (END_HOUR - START_HOUR) + ROW_SPAN * 2;
    private static final int NUM_OF_ROWS = SCALE_FACTOR * (END_HOUR - START_HOUR) + COLUMN_SPAN;
    private final Logger logger = LogsCenter.getLogger(ScheduleGridPanel.class);
    private final ObservableList<Schedule> schedules;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPaneVertical;

    /**
     * Constructs a schedule grid panel
     */
    public ScheduleGridPanelVertical(ObservableList<Schedule> schedules) {
        super(FXML);
        this.schedules = schedules;
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
        return (int) ((hour - START_HOUR) * SCALE_FACTOR + 1 * 2);
    }
    /**
     * Get the index of slot's start time
     * @param hour beginning hour
     * @return the 0-based index
     */
    public int getVerticalStartTimeIndex(double hour) {
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
     * Builds the vertical grid pane
     */
    public void constructVerticalGrid() {
        logger.fine("Constructing the vertical grid panel");
        // gridPaneVertical.setGridLinesVisible(true);
        gridPaneVertical.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i < NUM_OF_ROWS; ++i) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(ROWS_WIDTH);
            gridPaneVertical.getRowConstraints().add(row);
        }
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
     * Adds weekdays to the vertical grid
     */
    public void addWeekdayToVerticalGrid() {
        for (int i = 0; i < 7; ++i) {
            SlotContainer weekday = new WeekdayCardVertical(i);
            gridPaneVertical.add(weekday.getRoot(), i, 0, COLUMN_SPAN, 1);
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(COLUMNS_WIDTH);
            gridPaneVertical.getColumnConstraints().add(column);
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
            rowIndex = getVerticalStartTimeIndex(startHour);
            SlotContainer slot = createVerticalSlot(schedule);
            gridPaneVertical.add(slot.getRoot(), colIndex, rowIndex, COLUMN_SPAN, getRowSpan(duration));
        }
    }

    /**
     * Sets the style of scrollPane
     */
    public void setScrollPaneStyle(Theme theme) {
        if (theme.equals(Theme.DARK)) {
            scrollPane.getStylesheets().add("view/schedule/css/scrollPaneDark.css");
        } else {
            scrollPane.getStylesheets().add("view/schedule/css/scrollPaneLight.css");
        }
    }

    // /**
    //  * Sets the style of gridPane
    //  */
    // public void setGridPaneStyle(Theme theme) {
    //    if (theme.equals(Theme.DARK)) {
    //        gridPaneVertical.setStyle("-fx-background-color: rgba(29,29,35,0.71)");
    //    } else {
    //        gridPaneVertical.setStyle("-fx-background-color: rgba(245,241,207,0.89)");
    //   }
    // }
}
