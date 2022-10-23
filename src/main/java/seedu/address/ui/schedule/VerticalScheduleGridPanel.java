package seedu.address.ui.schedule;


import static seedu.address.ui.schedule.ScheduleGridPanel.getStartTimeIndex;
import static seedu.address.ui.schedule.ScheduleGridPanel.getWeekdayIndex;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Weekdays;
import seedu.address.ui.UiPart;
/**
 * Creates a vertical schedule grid panel
 */
public class VerticalScheduleGridPanel extends UiPart<Region> {

    private static final String FXML= "schedule/VerticalScheduleGridPanel.fxml";
    private static final int SCALE_FACTOR = 2;
    private static final int COLUMN_SPAN = 1;
    private static final double ROWS_WIDTH = 95;
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 22;
    private static final int NUM_OF_ROWS = SCALE_FACTOR * (END_HOUR - START_HOUR) + COLUMN_SPAN;
    private final Logger logger = LogsCenter.getLogger(VerticalScheduleGridPanel.class);
    private final ObservableList<Schedule> schedules;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;

    /**
     * Constructs a vertical schedule grid panel
     */
    public VerticalScheduleGridPanel(ObservableList<Schedule> schedules) {
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



    /**
     * Builds the grid pane
     */
    public void constructGrid() {
        logger.fine("Constructing the vertical grid panel");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i < NUM_OF_ROWS; ++i) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(ROWS_WIDTH);
            gridPane.getRowConstraints().add(row);
        }
    }

    /**
     * Adds weekdays to the grid
     */
    public void addWeekdayToGrid() {
        for (int i = 0; i < 7; ++i) {
            SlotContainer weekday = new WeekdayCard(i);
            gridPane.add(weekday.getRoot(), i, 0, COLUMN_SPAN, 1);
        }
    }

    /**
     * Adds all schedule to the grid pane
     */
    public void addScheduleSlotToGrid() {
        logger.fine("Add all schedules to the vertical grid");
        int colIndex = 0;
        int rowIndex = 0;

        for (Schedule schedule: schedules) {
            double startHour = schedule.getHour(schedule.getStartTime());
            double duration = schedule.getDuration();
            Weekdays weekday = schedule.getWeekday();
            colIndex = getWeekdayIndex(weekday);
            rowIndex = getStartTimeIndex(startHour);
            SlotContainer slot = createSlot(schedule);
            gridPane.add(slot.getRoot(), colIndex, rowIndex, COLUMN_SPAN, getRowSpan(duration));
        }
    }

    /**
     * Creates a SlotContainer
     */
    public SlotContainer createSlot(Schedule schedule) {
        double duration = schedule.getDuration();
        double slotHeight = duration * SCALE_FACTOR * ROWS_WIDTH;
        return new ScheduleSlot(schedule);
    }

    private int getRowSpan(double duration) {
        return (int) (duration * SCALE_FACTOR);
    }
    

}
