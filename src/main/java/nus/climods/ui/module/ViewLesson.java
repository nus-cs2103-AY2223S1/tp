package nus.climods.ui.module;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import nus.climods.ui.UiPart;
import nus.climods.ui.module.components.LessonPill;

/**
 * Displays the lesson type of a specific module
 * Can merge into ModuleCard if time permits
 */

public class ViewLesson extends UiPart<Node> {
    private static final String FXML = "ViewLesson.fxml";

    private static final String TITLED_PANE_ID = "lessonPane";

    private static final String LESSON_SLOTS_ID = "lessonSlots";

    private static final String PANE_HEADER = "%s : %s";

    private static final int VGAP = 8;

    private static final int HGAP = 4;
    private final ModuleStub module;
    @FXML
    private Accordion allLessons;

    /**
     * Inialise Lesson View of the corresponding module
     * @param module
     */
    public ViewLesson(ModuleStub module) {
        super(FXML);
        this.module = module;
        // Add timeslots for each lesson type
        allLessons.getPanes()
                .addAll(module.getLessons().entrySet().stream()
                        .map(entry -> addLessonType(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));
    }

    private TitledPane addLessonType(String lessonType, List<String []> slots) {
        TitledPane pane = new TitledPane();
        pane.setId(TITLED_PANE_ID);
        pane.setText(String.format(PANE_HEADER, module.getCode(), lessonType));
        pane.setContent(addLessonSlot(slots));
        return pane;
    }

    private FlowPane addLessonSlot(List<String []> slots) {
        FlowPane fc = new FlowPane();
        fc.setId(LESSON_SLOTS_ID);
        fc.getChildren().addAll(slots.stream().map(slot -> addSlot(slot))
                .collect(Collectors.toList()));
        fc.setHgap(HGAP);
        fc.setVgap(VGAP);
        return fc;
    }

    private LessonPill addSlot(String[] slot) {
        return new LessonPill(String.format("%s : %s", slot[0], slot[1]));
    }
}
