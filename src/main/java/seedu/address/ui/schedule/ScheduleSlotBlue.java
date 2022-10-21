package seedu.address.ui.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.module.schedule.Schedule;

/**
 * Creates a slot with blue color
 */
public class ScheduleSlotBlue extends SlotContainer {
    private static final String FXML = "schedule/ScheduleSlotBlue.fxml";

    protected String moduleCode;
    protected String duration;
    protected String classType;
    protected String venue;

    @javafx.fxml.FXML
    private Label module;

    @javafx.fxml.FXML
    private Label time;
    @FXML
    private Label type;
    @FXML
    private Label venueName;

    /**
     * Creates a schedule slot to be added to the timetable with relevant information.
     */
    public ScheduleSlotBlue(Schedule schedule) {
        super(FXML);
        this.moduleCode = schedule.getModule();
        this.duration = schedule.getPeriod();
        this.classType = schedule.getClassType().toString();
        this.venue = schedule.getVenue().toString();
        setText();
    }

    private void setText() {
        module.setText(moduleCode.toUpperCase());
        time.setText(duration);
        type.setText(classType);
        venueName.setText(venue);
    }
}
