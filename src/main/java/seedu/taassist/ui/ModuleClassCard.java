package seedu.taassist.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * An UI component that displays information of a {@code ModuleClass}.
 */
public class ModuleClassCard extends UiPart<Region> {

    private static final String FXML = "ModuleClassCard.fxml";

    public final ModuleClass moduleClass;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;

    /**
     * Creates a {@code ModuleClassCode} with the given {@code ModuleClass} and index to display.
     */
    public ModuleClassCard(ModuleClass moduleClass) {
        super(FXML);
        this.moduleClass = moduleClass;
        name.setText(moduleClass.getClassName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleClassCard)) {
            return false;
        }

        // state check
        ModuleClassCard card = (ModuleClassCard) other;
        return moduleClass.equals(card.moduleClass);
    }
}
