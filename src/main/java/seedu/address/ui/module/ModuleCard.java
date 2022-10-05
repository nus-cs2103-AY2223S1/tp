package seedu.address.ui.module;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
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
    private Button workload;
    @FXML
    private FlowPane semesterData;
    private String buttonStyle = "-fx-border-width:0;-fx-background-radius: 10px;-fx-padding: 5;-fx-font-size: 12;-fx-font-weight:bold;";

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ModuleCard(Module module) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getModuleCode());
        title.setText(module.getTitle());
        department.setText(module.getDepartment());
        // TODO: find a more elegant way to add workload behind semeseter tag
        workload = new Button(module.getWorkload());
        workload.setStyle("-fx-background-color:#61AFEF;-fx-text-fill: #ffffff;" + buttonStyle);
        module.getSemesterData().stream()
                .forEach(tag -> semesterData.getChildren().add(new Button(tag)));
        semesterData.getChildren().forEach(child -> child.setStyle("-fx-background-color: #C678DD;-fx-text-fill: #000000;" + buttonStyle));
        semesterData.getChildren().add(workload);

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
