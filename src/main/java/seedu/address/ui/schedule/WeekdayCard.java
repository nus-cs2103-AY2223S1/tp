package seedu.address.ui.schedule;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Creates a WeekdayCard
 */
public class WeekdayCard extends SlotContainer {

    private static final String FXML = "schedule/WeekdayCard.fxml";
    private static final List<String> WEEKDAY_LIST = new ArrayList<>();

    private int index;
    @FXML
    private Label weekday;

    /**
     * Constructs a WeekdayCard
     */
    public WeekdayCard(int index) {
        super(FXML);
        constructList();
        String day = WEEKDAY_LIST.get(index);
        weekday.setText(day);
    }

    private void constructList() {
        WEEKDAY_LIST.add("Mon");
        WEEKDAY_LIST.add("Tue");
        WEEKDAY_LIST.add("Wed");
        WEEKDAY_LIST.add("Thu");
        WEEKDAY_LIST.add("Fri");
        WEEKDAY_LIST.add("Sat");
        WEEKDAY_LIST.add("Sun");
    }
}
