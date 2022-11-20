package seedu.address.ui.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.ui.PersonCard;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "schedule/ScheduleListCard.fxml";

    public final Schedule schedule;

    @FXML
    private HBox cardPane;

    @FXML
    private Label module;

    @FXML
    private Label weekday;

    @FXML
    private Label classType;

    @FXML
    private Label startTime;

    @FXML
    private Label endTime;

    @FXML
    private Label venue;

    @FXML
    private Label id;


    /**
     * Creates a {@code ScheduleCard} with the given {@code Schedule} and index to display.
     */

    public ScheduleCard(Schedule schedule, int displayedIndex) {
        super(FXML);
        this.schedule = schedule;
        id.setText(displayedIndex + ". ");
        module.setText(schedule.getModule().toUpperCase());
        weekday.setText(schedule.getWeekday().name() + ", ");
        classType.setText(schedule.getClassType().name() + "  [" + schedule.getClassGroup() + "]");
        startTime.setText(schedule.getStartTime() + " - ");
        endTime.setText(schedule.getEndTime());
        venue.setText("Venue: " + schedule.getVenue().toString());
        setColor(getColor(schedule.getDuration()));
    }

    public void setColor(String color) {
        cardPane.setStyle("-fx-font-size: 14; -fx-background-color: " + color + "; -fx-background-radius: 10px;");
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

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        ScheduleCard card = (ScheduleCard) other;
        return id.getText().equals(card.id.getText()) && schedule.equals(card.schedule);
    }

}
