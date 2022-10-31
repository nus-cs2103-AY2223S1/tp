package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.logic.CalendarLogic;
import seedu.address.logic.Logic;

/**
 * A UI component that displays information of a Calendar.
 */
public class CalendarDisplay extends UiPart<Region> {
    private static final String FXML = "CalendarDisplay.fxml";
    private Stage primaryStage;
    private Logic logic;
    private CalendarLogic calendarLogic;
    @FXML
    private GridPane calendarDisplay;
    @FXML
    private HBox topCalendar;

    /**
     * Creates a Calendar with the given list of CalendarEvents.
     */
    public CalendarDisplay(Logic logic, Stage primaryStage) {
        super(FXML);
        this.calendarLogic = new CalendarLogic(logic, primaryStage, calendarDisplay, topCalendar);
        this.primaryStage = primaryStage;
        this.logic = logic;
        calendarLogic.initialiseLogic();
        calendarLogic.drawCalendar();
    }

    /**
     * Handles the {@code KeyEvent} and toggles either the next or previous Calendar month.
     *
     * @param event the KeyEvent to be handled.
     */
    @FXML
    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.B)) {
            calendarLogic.previous();
            calendarDisplay.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            calendarLogic.next();
            calendarDisplay.requestFocus();
        }
    }
}
