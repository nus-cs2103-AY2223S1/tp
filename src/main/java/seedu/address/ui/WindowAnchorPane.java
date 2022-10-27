package seedu.address.ui;

import java.util.logging.Logger;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableDoubleValue;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Panel containing the list of persons.
 */
public class WindowAnchorPane extends UiPart<Region> {
    private static final double HORIZONTAL_DIVIDER = 0.45;
    private static final double DISTANCE_BOTTOM = 0;
    private static final double VERTICAL_DIVIDER_DEFAULT = 0.65;

    private static final String FXML = "WindowAnchorPane.fxml";
    private static final double OFFSET_HEIGHT = 100;
    private static final double RIGHT_PADDING = 25;
    private final Logger logger = LogsCenter.getLogger(WindowAnchorPane.class);

    private double verticalDivider = VERTICAL_DIVIDER_DEFAULT;

    private Logic logic;
    private PersonListPanel personListPanel;
    private NoteListPanel noteListPanel;
    private InspectionPanel inspectionPanel;

    @FXML
    private AnchorPane container;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane noteListPanelPlaceholder;

    @FXML
    private StackPane inspectionPanelPlaceholder;

    @FXML
    private VBox personList;

    @FXML
    private VBox noteList;

    @FXML
    private VBox inspectionSection;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public WindowAnchorPane(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        inspectionPanel = new InspectionPanel(personListPanel.getListView(), logic.getFilteredPersonList());
        inspectionPanelPlaceholder.getChildren().add(inspectionPanel.getRoot());

        noteListPanel = new NoteListPanel(logic.getFilteredNoteList());
        noteListPanelPlaceholder.getChildren().add(noteListPanel.getRoot());
    }

    /**
     * Resizes the children elements inside the AnchorPane
     * @param stageHeight the height of the stage this anchor pane is one
     * @param stageWidth the width of the stage this anchor pane is one
     */
    public void resizeElements(double stageHeight, double stageWidth) {
        System.out.println("Width " + stageWidth);
        System.out.println("Height " + stageHeight);
        //Confine anchor pane container to width of stage
        stageHeight -= OFFSET_HEIGHT;
        stageWidth -= RIGHT_PADDING;

        container.setMaxWidth(stageWidth);
        container.setMaxHeight(stageHeight);
        container.setPrefWidth(stageWidth);
        container.setPrefHeight(stageHeight);

        //set anchors of personListPanel
        AnchorPane.setLeftAnchor(personList, 0.0);
        AnchorPane.setRightAnchor(personList, (1.0 - verticalDivider) * stageWidth);
        AnchorPane.setTopAnchor(personList, 0.0);
        AnchorPane.setBottomAnchor(personList, (1 - HORIZONTAL_DIVIDER) * stageHeight);

        //set anchors of inspectPanel
        AnchorPane.setLeftAnchor(inspectionSection, 0.0);
        // Strange issue where the stage width is larger than the window width
        // 9000 is an experimental value, based on minStageWidth (900) * 10, so a
        // padding of 10 is added when the window is at the smallest
        // As the window gets wider, this padding decreases
        AnchorPane.setRightAnchor(inspectionSection,
                Math.max((1.0 - verticalDivider) * stageWidth, 9000 / stageWidth + 50));
        AnchorPane.setTopAnchor(inspectionSection, HORIZONTAL_DIVIDER * stageHeight);
        AnchorPane.setBottomAnchor(inspectionSection, DISTANCE_BOTTOM);


        //set anchors of noteListPanel
        AnchorPane.setLeftAnchor(noteList, (verticalDivider) * stageWidth);
        AnchorPane.setRightAnchor(noteList, 0.0);
        AnchorPane.setTopAnchor(noteList, 10.0);
        AnchorPane.setBottomAnchor(noteList, DISTANCE_BOTTOM);
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public void setNotesPaneVisibility(boolean isVisible, double stageHeight, double stageWidth) {
        Timeline anim = new Timeline();
        anim.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(
                new WritableDoubleValue() {
                    @Override
                    public double get() {
                        return WindowAnchorPane.this.verticalDivider;
                    }

                    @Override
                    public void set(double value) {
                        verticalDivider = value;
                        resizeElements(stageHeight, stageWidth);
                    }

                    @Override
                    public void setValue(Number value) {
                        verticalDivider = (double) value;
                        resizeElements(stageHeight, stageWidth);
                    }

                    @Override
                    public Number getValue() {
                        return WindowAnchorPane.this.verticalDivider;
                    }
                },
                // can't use 1 because the stage width is larger than window width
                isVisible ? VERTICAL_DIVIDER_DEFAULT : 0.955,
                Interpolator.LINEAR)));

        anim.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(
                noteList.opacityProperty(),
                isVisible ? 1 : 0,
                Interpolator.LINEAR)));

        anim.play();
    }
}
