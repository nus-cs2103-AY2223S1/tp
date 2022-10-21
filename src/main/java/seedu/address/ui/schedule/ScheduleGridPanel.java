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


/**
 * Creates a schedule grid panel
 */
public class ScheduleGridPanel extends UiPart<Region> {

    private static final String FXML = "schedule/ScheduleGridPanel.fxml";
    private static final int SCALE_FACTOR = 2;
    private static final int ROW_SPAN = 1;
    private static final double COLUMNS_WIDTH = 95;
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 22;

    private static final int NUM_OF_COLUMNS = SCALE_FACTOR * (END_HOUR - START_HOUR) + ROW_SPAN;
    private final Logger logger = LogsCenter.getLogger(ScheduleGridPanel.class);
    private final ObservableList<Schedule> schedules;
    @javafx.fxml.FXML
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
     * Starts constructing
     */
    public void construct() {
        constructGrid();
        addWeekdayToGrid();
        if (schedules.size() == 0) {
            logger.info("No schedules have been added");
        } else {
            addScheduleSlotToGrid();
        }
    }

    private int getColIndex(double hour) {
        return (int) ((hour - START_HOUR) * SCALE_FACTOR + 1);
    }

    private int getColumnSpan(double duration) {
        return (int) (duration * SCALE_FACTOR);
    }

    private int getRowIndex(Weekdays weekday) {
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
     * Builds the grid pane
     */
    public void constructGrid() {
        logger.fine("Constructing the grid panel");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i < NUM_OF_COLUMNS; ++i) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(COLUMNS_WIDTH);
            gridPane.getColumnConstraints().add(column);
        }
    }

    /**
     * Creates a SlotContainer
     */
    public SlotContainer createSlot(Schedule schedule) {
        double duration = schedule.getDuration();
        double slotWidth = duration * SCALE_FACTOR * COLUMNS_WIDTH;
        return new ScheduleSlot(schedule);
    }

    /**
     * Adds weekdays to the grid
     */
    public void addWeekdayToGrid() {
        for (int i = 0; i < 7; ++i) {
            SlotContainer weekday = new WeekdayCard(i);
            gridPane.add(weekday.getRoot(), 0, i, 1, ROW_SPAN);
        }
    }
    /**
     * Adds all schedule to the grid pane
     */
    public void addScheduleSlotToGrid() {
        logger.fine("Add all schedules to the grid");
        int rowIndex = 0;
        int colIndex = 0;

        for (Schedule schedule: schedules) {
            double startHour = schedule.getHour(schedule.getStartTime());
            double duration = schedule.getDuration();
            Weekdays weekday = schedule.getWeekday();
            rowIndex = getRowIndex(weekday);
            colIndex = getColIndex(startHour);
            SlotContainer slot = createSlot(schedule);
            gridPane.add(slot.getRoot(), colIndex, rowIndex, getColumnSpan(duration), ROW_SPAN);
        }
    }
}
