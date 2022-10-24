package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;

/**
 * An UI component that displays more information of a {@code Module}.
 */
public class ModuleInfoCard extends UiPart<Region> {
    private static final String FXML = "ModuleInfoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label moduleName;
    @FXML
    private Label moduleDescription;
    @FXML
    private Label moduleCode;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleInfoCode} with the given {@code Module}.
     */
    public ModuleInfoCard(Module module) {
        super(FXML);
        this.module = module;
        moduleName.setText(module.getName().fullName);
        moduleCode.setText(module.getCode().fullCode);
        moduleDescription.setText(module.getDescription().fullDescription);
        module.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleInfoCard)) {
            return false;
        }

        // state check
        ModuleInfoCard card = (ModuleInfoCard) other;
        return moduleCode.getText().equals(card.moduleCode.getText())
                && module.equals(card.module);
    }
}
