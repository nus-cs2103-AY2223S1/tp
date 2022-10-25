package nus.climods.ui.module;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import nus.climods.ui.UiPart;

/**
 * Displays the lesson type of a specific module
 * Can merge into ModuleCard if time permits
 */

public class ViewLesson extends UiPart<Node> {
    private static final String FXML = "ViewLesson.fxml";

    private static final String TITLED_PANE_ID = "lessonPane";
    private static final String ANCHOR_PANE_ID = "anchorPane";

    private static final String LESSON_SLOTS_ID = "lessonSlots";

    private static final String PANE_HEADER = "%s : %s";


    private final ModuleStub module;

    @FXML
    private Label title = new Label();
    @FXML
    private Label moduleCode = new Label();
    @FXML
    private Accordion allLessons = new Accordion();

    /**
     * Inialise Lesson View of the corresponding module
     * @param module
     */
    public ViewLesson(ModuleStub module) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getCode());
        title.setText(module.getTitle());
        // Add timeslots for each lesson type
        allLessons.getPanes()
                .addAll(module.getLessons().entrySet().stream()
                        .map(entry -> addLessonType(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));
    }

    private TitledPane addLessonType(String lessonType, List<String []> slots) {
        TitledPane pane = new TitledPane();
        AnchorPane ac = new AnchorPane();
        ac.setId(ANCHOR_PANE_ID);
        pane.setId(TITLED_PANE_ID);
        pane.setText(String.format(PANE_HEADER, moduleCode, lessonType));
        ac.getChildren().add(addLessonSlot(slots));
        pane.setContent(ac);
        return pane;
    }

    private FlowPane addLessonSlot(List<String []> slots) {
        FlowPane fc = new FlowPane();
        fc.setId(LESSON_SLOTS_ID);
        fc.getChildren().addAll(slots.stream().map(slot -> addSlot(slot))
                .collect(Collectors.toList()));
        return fc;
    }

    private Label addSlot(String[] slot) {
        Label l = new Label();
        l.setText(String.format("%s : [%s]", slot[0], slot[1]));
        return l;
    }
}
