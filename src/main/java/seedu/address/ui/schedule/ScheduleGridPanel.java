package seedu.address.ui.schedule;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Weekdays;
import seedu.address.ui.UiPart;
import seedu.address.ui.theme.Theme;

/**
 * Creates a schedule grid panel
 */
public class ScheduleGridPanel extends UiPart<Region> {

    private static final String FXML = "schedule/ScheduleGridPanel.fxml";
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 22;
    private static final int SCALE_FACTOR = 2;

    private static final int ROW_SPAN = 1;
    private static final int COLUMN_SPAN = 1;
    private static final double COLUMNS_WIDTH = 100;
    private static final double ROWS_WIDTH = 50;
    private static final int NUM_OF_COLUMNS = SCALE_FACTOR * (END_HOUR - START_HOUR) + ROW_SPAN * 2;
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

    private int getNumOfColumns() {
        double end = 0;
        for (Schedule schedule: schedules) {
            if (end < schedule.getHour(schedule.getEndTime())) {
                end = schedule.getHour(schedule.getEndTime());
            }
        }
        return (int) (end - 6) * 2;
    }

    /**
     * Builds the horizontal grid pane
     */
    public void constructHorizontalGrid() {
        logger.fine("Constructing the horizontal grid panel");
        // gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        int numOfColumns = getNumOfColumns();
        for (int i = 0; i < numOfColumns; ++i) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(COLUMNS_WIDTH);
            gridPane.getColumnConstraints().add(column);
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
     * Adds weekdays to the horizontal grid
     */
    public void addWeekdayToHorizontalGrid() {
        for (int i = 0; i < 7; ++i) {
            SlotContainer weekday = new WeekdayCard(i);
            gridPane.add(weekday.getRoot(), 0, i, 1 * 2, ROW_SPAN);
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
     * Sets the style of scrollPane
     */
    public void setScrollPaneStyle(Theme theme) {
        if (theme.equals(Theme.DARK)) {
            scrollPane.getStylesheets().add("view/schedule/css/scrollPaneDark.css");
        } else {
            scrollPane.getStylesheets().add("view/schedule/css/scrollPaneLight.css");
        }
    }

    /**
     * Sets the style of gridPane
     */
    // public void setGridPaneStyle(Theme theme) {
    //     if (theme.equals(Theme.DARK)) {
    //       gridPane.setStyle("-fx-background-color: rgba(29,29,35,0.71)");
    //   } else {
    //       gridPane.setStyle("-fx-background-color: rgba(245,241,207,0.89)");
    //   }
    //}
}
