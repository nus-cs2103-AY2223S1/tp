package seedu.address.ui.calendar;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.logic.CalendarLogic;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a Calendar.
 */
public class CalendarDisplay extends UiPart<Region> {
    private static final String FXML = "CalendarDisplay.fxml";
    private static final Insets MARGIN = new Insets(0, 50, 0, 0);
    private Stage primaryStage;
    private Logic logic;
    private CalendarLogic calendarLogic;
    private GridPane calendarGrid = new CalendarGrid().getRoot();
    @FXML
    private FlowPane topCalendar = new TopCalendar().getRoot();
    @FXML
    private AnchorPane calendarAnchorPane;
    @FXML
    private VBox calendarDisplay;
    @FXML
    private ScrollPane calendarScrollPane;
    private TextValidation textValidation = new TextValidation();
    private JumpBox jumpBox;
    private PreviousButton prevButton;
    private NextButton nextButton;

    /**
     * Creates a Calendar with the given list of CalendarEvents.
     */
    public CalendarDisplay(Logic logic, Stage primaryStage) {
        super(FXML);
        calendarDisplay.getChildren().setAll(topCalendar, calendarScrollPane);
        calendarAnchorPane.getChildren().add(calendarGrid);
        this.calendarLogic = new CalendarLogic(logic, primaryStage, this);
        prevButton = new PreviousButton("Prev", calendarLogic);
        nextButton = new NextButton("Next", calendarLogic);
        jumpBox = new JumpBox(calendarLogic);
        this.primaryStage = primaryStage;
        this.logic = logic;
        calendarLogic.initialiseLogic();
        drawCalendar();

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

    /**
     * Draws the Ui for the Calendar.
     */
    public void drawCalendar() {
        drawHeader();
        calendarLogic.drawBody();
    }
    public void resetMargin(Node node) {
        topCalendar.setMargin(node, MARGIN);
    }

    /**
     * Resets the calendarGrid.
     */
    public void resetGridPane() {
        topCalendar.getChildren().clear();
        Node node = calendarGrid.getChildren().get(0);
        calendarGrid.getChildren().clear();
        calendarGrid.getChildren().add(0, node);
    }

    /**
     * Resets the calendarGrid's body.
     */
    public void resetCalendarBody() {
        Node node = calendarGrid.getChildren().get(0);
        calendarGrid.getChildren().clear();
        calendarGrid.getChildren().add(0, node);
    }

    private void drawHeader() {
        Text textHeader = calendarLogic.getTextHeader();
        topCalendar.getChildren().addAll(textHeader, prevButton.getRoot(), nextButton.getRoot(),
                jumpBox.getRoot(), textValidation.getRoot());
        topCalendar.setMargin(textHeader, MARGIN);
    }

    public String getJumpBoxText() {
        return jumpBox.getText();
    }

    public void clearJumpBox() {
        jumpBox.clear();
    }

    /**
     * Adds the node to be contained within the calendarGrid.
     */
    public void addToCalendarGrid(Node node, int columnIndex, int rowIndex) {
        calendarGrid.add(node, columnIndex, rowIndex);
    }

    public void setTextValidation(String validation) {
        textValidation.setTextValidation(validation);
    }

    public void setTopCalendarHeader(int index, Node node) {
        topCalendar.getChildren().set(index, node);
    }

    public boolean isJumpBoxFocused() {
        return jumpBox.isJumpBoxFocused();
    }
}
