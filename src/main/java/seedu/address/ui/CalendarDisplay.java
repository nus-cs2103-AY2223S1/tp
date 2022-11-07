package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
    private GridPane calendarGrid;
    @FXML
    private FlowPane topCalendar;
    private JumpBox jumpBox;

    /**
     * Creates a Calendar with the given list of CalendarEvents.
     */
    public CalendarDisplay(Logic logic, Stage primaryStage) {
        super(FXML);
        this.calendarLogic = new CalendarLogic(logic, primaryStage, calendarGrid, topCalendar);
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.jumpBox = calendarLogic.getJumpBox();
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
            calendarGrid.requestFocus();
        } else if (event.getCode().equals(KeyCode.N)) {
            calendarLogic.next();
            calendarGrid.requestFocus();
        }
    }

    public boolean isJumpBoxFocused() {
        return jumpBox.isJumpBoxFocused();
    }
}
