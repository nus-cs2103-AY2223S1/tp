package nus.climods.ui.module;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openapitools.client.model.Lesson;
import org.openapitools.client.model.SemestersEnum;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import nus.climods.model.module.LessonTypeEnum;
import nus.climods.model.module.Module;
import nus.climods.ui.UiPart;
import nus.climods.ui.module.components.LessonPill;
import nus.climods.ui.module.components.ModuleCreditsPill;
import nus.climods.ui.module.components.SemesterPill;

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    private static final String MODULE_CREDITS_BG_COLOR = "#61AFEF";
    private static final String MODULE_CREDITS_TEXT_COLOR = "#FFFFFF";
    private static final int MODULE_CREDITS_FONT_SIZE = 11;

    private static final String SEMESTER_BG_COLOR = "#C678DD";
    private static final String SEMESTER_TEXT_COLOR = "#000000";
    private static final int SEMESTER_FONT_SIZE = 11;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a consequence, UI
     * elements' variable names cannot be set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label moduleCode;
    @FXML
    private Label department;
    @FXML
    private FlowPane moduleInfo;
    @FXML
    private VBox expandedModuleInfo;
    @FXML
    private Label moduleDescription;
    @FXML
    private Label prerequisite;
    @FXML
    private Label preclusion;

    @FXML
    private VBox lessonInfo;

    /**
     * Creates a {@code ModuleCard} with the given {@code module} to display.
     */
    public ModuleCard(Module module) {
        super(FXML);
        this.module = module;

        moduleCode.setText(module.getCode());
        title.setText(module.getTitle());
        department.setText(module.getDepartment());
        moduleInfo.getChildren()
                .addAll(module.getSemesters().stream().map(SemesterPill::new).collect(Collectors.toList()));
        moduleInfo.getChildren().add(new ModuleCreditsPill(module.getModuleCredit()));

        moduleDescription.setText(module.getDescription().replace("\n", " "));

        // managed controls whether it interrupts the flow i.e. display in CSS vs visibility
        // bind allows for managed to follow the visible property's changes
        expandedModuleInfo.managedProperty().bind(expandedModuleInfo.visibleProperty());
        // by default expanded is not visible
        expandedModuleInfo.setVisible(false);
        // add lesson info in a similar way as expanded module info
        lessonInfo.managedProperty().bind(lessonInfo.visibleProperty());
        lessonInfo.setVisible(false);

        if (module.isFocused()) {
            showDetailedModuleInformation();
        }
    }

    private void showDetailedModuleInformation() {
        expandedModuleInfo.setVisible(true);

        moduleDescription.setText(module.getDescription());
        moduleDescription.setWrapText(true);

        prerequisite.setText(module.getPrerequisite());
        preclusion.setText(module.getPreclusion());
        showLessonInformation();
    }

    private void showLessonInformation() {
        lessonInfo.setVisible(true);
        for (SemestersEnum sem : SemestersEnum.values()) {
            HashMap<LessonTypeEnum, Module.ModuleLessonIdMap> lessons = module.getLessons(sem);
            if (lessons == null) {
                continue;
            }
            lessonInfo.getChildren().addAll(new SemesterPill(sem), addSemesterLessons(lessons));
        }
    }

    private Accordion addSemesterLessons(HashMap<LessonTypeEnum, Module.ModuleLessonIdMap> sem) {
        Accordion a = new Accordion();
        a.setPadding(new Insets(10, 0, 10, 0));
        a.getPanes()
                .addAll(sem.entrySet().stream()
                        .map(entry -> addLessonType(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));
        return a;
    }

    private TitledPane addLessonType(LessonTypeEnum lessonType, Module.ModuleLessonIdMap slots) {
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
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> addSlot(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
        fc.setHgap(4);
        fc.setVgap(8);
        fc.setPadding(new Insets(10));
        return fc;
    }

    private LessonPill addSlot(String id, List<Lesson> lessons) {
        return new LessonPill(id, lessons);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard otherCard = (ModuleCard) other;
        return module.equals(otherCard.module);
    }
}
