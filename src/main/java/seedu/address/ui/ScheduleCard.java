package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.schedule.Schedule;

/**
 * A UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "ScheduleListCard.fxml";

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
        module.setText(schedule.getModule());
        weekday.setText(schedule.getWeekday().name());
        classType.setText(schedule.getClassType().name());
        startTime.setText(schedule.getStartTime());
        endTime.setText(schedule.getEndTime());
        venue.setText(schedule.getVenue().toString());

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
