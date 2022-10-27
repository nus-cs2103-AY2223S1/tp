package nus.climods.ui.module;

import java.util.HashMap;
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
import javafx.scene.layout.VBox;
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

    private final Module module;
    @FXML
    private VBox lessonInfo;
    /**
     * Inialise Lesson View of the corresponding module
     * @param module
     */
    public ViewLesson(Module module) {
        super(FXML);
        this.module = module;
        // Add timeslots for each lesson type
        for (SemestersEnum sem : SemestersEnum.values()) {
            HashMap<LessonType, Module.ModuleLessonIdMap> lessons = module.getLessons(sem);
            if (lessons == null) {
                continue;
            }
            lessonInfo.getChildren().add(addSemesterLessons(lessons));
        }
    }

    private Accordion addSemesterLessons(HashMap<LessonType, Module.ModuleLessonIdMap> sem) {
        Accordion a = new Accordion();
        a.getPanes()
                .addAll(sem.entrySet().stream()
                        .map(entry -> addLessonType(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));
        return a;
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
