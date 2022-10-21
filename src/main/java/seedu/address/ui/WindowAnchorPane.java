package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Panel containing the list of persons.
 */
public class WindowAnchorPane extends UiPart<Region> {
    private static final double HORIZONTAL_DIVIDER = 0.45;
    private static final double VERTICAL_DIVIDER = 0.65;
    private static final double DISTANCE_BOTTOM = 20;

    private static final String FXML = "WindowAnchorPane.fxml";
    private final Logger logger = LogsCenter.getLogger(WindowAnchorPane.class);

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
        //Confine anchor pane container to width of stage
        container.setPrefWidth(stageWidth);
        container.setPrefHeight(stageHeight);

        //set anchors of personListPanel
        AnchorPane.setLeftAnchor(personList, 0.0);
        AnchorPane.setRightAnchor(personList, (1 - VERTICAL_DIVIDER) * stageWidth);
        AnchorPane.setTopAnchor(personList, 0.0);
        AnchorPane.setBottomAnchor(personList, (1 - HORIZONTAL_DIVIDER) * stageHeight);

        //set anchors of inspectPanel

        AnchorPane.setLeftAnchor(inspectionSection, 0.0);
        AnchorPane.setRightAnchor(inspectionSection, (1 - VERTICAL_DIVIDER * 0.95) * stageWidth);
        AnchorPane.setTopAnchor(inspectionSection, HORIZONTAL_DIVIDER * stageHeight);
        AnchorPane.setBottomAnchor(inspectionSection, DISTANCE_BOTTOM);


        //set anchors of noteListPanel
        AnchorPane.setLeftAnchor(noteList, (VERTICAL_DIVIDER - 0.03) * stageWidth);
        AnchorPane.setRightAnchor(noteList, 25.0);
        AnchorPane.setTopAnchor(noteList, 0.0);
        AnchorPane.setBottomAnchor(noteList, DISTANCE_BOTTOM);

        System.out.println("Height " + stageHeight + " Width " + stageWidth);
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }
}
