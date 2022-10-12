package modtrekt.ui.modules;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import modtrekt.commons.util.StringUtil;
import modtrekt.model.module.Module;
import modtrekt.ui.UiPart;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "modules/ModuleCard.fxml";
    private final Module module;

    @FXML
    private Text name;
    @FXML
    private Label code;
    @FXML
    private Label credits;
    @FXML
    private FlowPane badges;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} to display.
     *
     * @param module the module to display¸
     */
    public ModuleCard(Module module) {
        super(FXML);
        this.module = module;
        this.name.setText(module.getName().getFullName());
        this.code.setText(module.getCode().getValue());
        this.credits.setText(StringUtil.pluralize(module.getCredits().getIntValue(), "MC", "MCs"));
        // Add the task count badge, pluralizing `task` if necessary.
        Label taskBadge = new Label(StringUtil.pluralize(module.getTaskCountInt(), "task"));
        this.badges.getChildren().add(taskBadge);
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
        return code.getText().equals(card.code.getText()) && module.equals(card.module);
    }
}
