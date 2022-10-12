package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import seedu.address.model.module.Module;

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final Desktop desktop = Desktop.getDesktop();
    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names
     * cannot be set to such keywords an exception will be thrown by JavaFX
     * during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label id;
    @FXML
    private Label moduleTitle;
    @FXML
    private FlowPane tasks;
    @FXML
    private FlowPane links;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to
     * display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCodeAsUpperCaseString());
        moduleTitle.setText(module.getModuleTitleAsUpperCaseString());
        module.getLinks().stream()
                .forEach(link -> links.getChildren()
                        .add(createHyperLinkNode(link.linkName)));
        // ToDo: Add Ui components for tasks and links.
    }

    private static Hyperlink createHyperLinkNode(String linkUrl) {
        Hyperlink node = new Hyperlink(linkUrl);
        node.setStyle("-fx-text-fill: #FFCC66");
        if (!linkUrl.substring(0, 4).equals("http")) {
            linkUrl = "https://" + linkUrl;
        }
        final String finalLinkUrl = linkUrl;
        node.setOnAction(e -> {
            try {
                desktop.browse(URI.create(finalLinkUrl));
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Link cannot be launched by your desktop");
                alert.showAndWait();
            }
        });
        return node;
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
        return id.getText().equals(card.id.getText())
                && module.equals(card.module);
    }
}
