package seedu.address.ui.schedule;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.module.schedule.Schedule;

/**
 * Creates a slot with blue color
 */
public class ScheduleSlot extends SlotContainer {
    private static final String FXML = "schedule/ScheduleSlot.fxml";

    private static final String[] COLORS = {"#FF8787", "#F8C4B4", "#E5EBB2", "#BCE29E", "#B8E8FC", "#B1AFFF",
        "#A7D2CB"};

    protected String moduleCode;
    protected String duration;
    protected String classType;
    protected String venue;

    @FXML
    private Label module;
    @FXML
    private Label time;
    @FXML
    private Label type;
    @FXML
    private Label venueName;


    /**
     * Creates a schedule slot to be added to the timetable with relevant information.
     */
    public ScheduleSlot(Schedule schedule) {
        super(FXML);
        this.moduleCode = schedule.getModule();
        this.duration = schedule.getPeriod();
        this.classType = schedule.getClassType().toString() + "  [" + schedule.getClassGroup() + "]";
        this.venue = schedule.getVenue().toString();
        setText();
        setColor(getColor(schedule.getDuration()));

    }
    private void setText() {
        module.setText(moduleCode.toUpperCase());
        time.setText(duration);
        type.setText(classType);
        venueName.setText(venue);
    }

    private String getColor(double duration) {
        if (duration <= 1) {
            return "#ffb6c1"; // PINK
        } else if (duration == 1.5) {
            return "#F8C4B4"; // DARK RED
        } else if (duration == 2) {
            return "#E5EBB2"; // YELLOW
        } else if (duration <= 3) {
            return "#BCE29E"; // GREEN
        } else {
            return "#B8E8FC"; // BLUE
        }
    }

    /**
     * Change color when user clicks the slot.
     */
    @FXML
    public void handleOnClick() {
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        String randomColor = COLORS[randomNumber];
        setColor(randomColor);
    }
}
