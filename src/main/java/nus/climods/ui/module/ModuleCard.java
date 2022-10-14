package nus.climods.ui.module;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import nus.climods.model.module.Module;
import nus.climods.ui.UiPart;
import nus.climods.ui.common.Pill;

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    private static final String MODULE_CREDITS_BG_COLOR = "#61AFEF";
    private static final String MODULE_CREDITS_TEXT_COLOR = "#FFFFFF";
    private static final int MODULE_CREDITS_FONT_SIZE = 13;

    private static final String SEMESTER_BG_COLOR = "#C678DD";
    private static final String SEMESTER_TEXT_COLOR = "#000000";
    private static final int SEMESTER_FONT_SIZE = 13;

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

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ModuleCard(Module module) {
        super(FXML);
        this.module = module;

        moduleCode.setText(module.getCode());
        title.setText(module.getTitle());
        department.setText(module.getDepartment());
        moduleInfo.getChildren()
            .addAll(module.getSemesters().stream().map(this::createSemesterPill).collect(Collectors.toList()));
        moduleInfo.getChildren().add(createModuleCreditsPill(module.getModuleCredit()));
    }
    private Pill createSemesterPill(int semesterNum) {
        return new Pill(String.format("Semester %s", semesterNum), SEMESTER_BG_COLOR, SEMESTER_TEXT_COLOR,
            SEMESTER_FONT_SIZE);
    }

    private Pill createModuleCreditsPill(String moduleCredits) {
        return new Pill(String.format("%s MCs", moduleCredits), MODULE_CREDITS_BG_COLOR, MODULE_CREDITS_TEXT_COLOR,
            MODULE_CREDITS_FONT_SIZE);
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
        ModuleCard card = (ModuleCard) other;
        return moduleCode.getText().equals(card.moduleCode.getText())
            && module.equals(card.module);
    }
}
