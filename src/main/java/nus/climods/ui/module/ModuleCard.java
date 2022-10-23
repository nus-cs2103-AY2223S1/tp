package nus.climods.ui.module;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import nus.climods.model.module.Module;
import nus.climods.ui.UiPart;
import nus.climods.ui.module.components.ModuleCreditsPill;
import nus.climods.ui.module.components.SemesterPill;

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

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
            .addAll(module.getSemesters().stream().map(SemesterPill::new).collect(Collectors.toList()));
        moduleInfo.getChildren().add(new ModuleCreditsPill(module.getModuleCredit()));
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
