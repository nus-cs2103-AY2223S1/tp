package seedu.address.ui.schedule;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
// Solution below adapted from https://github.com/AY2021S1-CS2103T-W13-3/tp with some modifications
/**
 * Creates a WeekdayCard in the vertical mode
 */
public class WeekdayCardVertical extends SlotContainer {

    private static final String FXML = "schedule/WeekdayCardVertical.fxml";
    private static final List<String> WEEKDAY_LIST = new ArrayList<>();

    private int index;
    @FXML
    private Label weekday;

    /**
     * Constructs a WeekdayCard
     */
    public WeekdayCardVertical(int index) {
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
