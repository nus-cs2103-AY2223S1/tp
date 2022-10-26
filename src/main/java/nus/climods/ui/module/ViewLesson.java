package nus.climods.ui.module;

import java.util.List;
import java.util.stream.Collectors;

import org.openapitools.client.model.Lesson;
import org.openapitools.client.model.SemestersEnum;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import nus.climods.model.module.LessonType;
import nus.climods.model.module.Module;
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
    private final Module module;
    @FXML
    private Accordion allLessons;

    /**
     * Inialise Lesson View of the corresponding module
     * @param module
     */
    public ViewLesson(Module module) {
        super(FXML);
        this.module = module;
        // Add timeslots for each lesson type
        allLessons.getPanes()
                .addAll(module.getLessons(SemestersEnum.S1).entrySet().stream()
                        .map(entry -> addLessonType(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));
    }

    private TitledPane addLessonType(LessonType lessonType, Module.ModuleLessonIdMap slots) {
        TitledPane pane = new TitledPane();
        ScrollPane sc = new ScrollPane();
        pane.setText(String.format("%s: %s", module.getCode(), lessonType));
        sc.setContent(addLessonSlot(slots));
        sc.setFitToWidth(true);
        sc.setFitToHeight(true);
        pane.setContent(sc);
        return pane;
    }

    private FlowPane addLessonSlot(Module.ModuleLessonIdMap slots) {
        FlowPane fc = new FlowPane();
        fc.getChildren().addAll(slots.entrySet().stream()
                .map(entry -> addSlot(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
        fc.setHgap(4);
        fc.setVgap(8);
        return fc;
    }

    private LessonPill addSlot(String id, List<Lesson> lessons) {
        return new LessonPill(id, lessons);
    }
}
