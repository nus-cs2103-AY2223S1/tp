package seedu.address.ui;

import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTP;
import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTPS;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import seedu.address.model.module.Module;
import seedu.address.model.module.task.Task;

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final Desktop desktop = Desktop.getDesktop();
    private static final String FXML = "ModuleListCard.fxml";
    private static final String MESSAGE_LINK_LAUNCH_FAILURE = "Error: Link cannot be launched by your desktop";
    private static final String LINK_TEXT_COLOR = "-fx-text-fill: #FFCC66"; //Light Yellow


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
    // Independent UI parts
    private TaskListPanel taskListPanel;
    @FXML
    private StackPane taskListPanelPlaceholder;

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
                        .add(createHyperLinkNode(link.linkAlias, link.linkUrl)));
        ObservableList<Task> taskList = module.getTasks();
        taskListPanel = new TaskListPanel(taskList);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

    private static Hyperlink createHyperLinkNode(String linkAlias, String linkUrl) {
        Hyperlink hyperLinkNode = new Hyperlink(linkAlias);
        hyperLinkNode.setStyle(LINK_TEXT_COLOR);
        boolean isLinkUrlPaddedWithHttps = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTPS);
        boolean isLinkUrlPaddedWithHttp = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTP);
        if (!(isLinkUrlPaddedWithHttps || isLinkUrlPaddedWithHttp)) {
            linkUrl = LINK_HEADER_PROTOCOL_HTTP + linkUrl;
        }
        final String finalLinkUrl = linkUrl;
        hyperLinkNode.setOnAction(e -> {
            try {
                desktop.browse(URI.create(finalLinkUrl));
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(MESSAGE_LINK_LAUNCH_FAILURE);
                alert.showAndWait();
            }
        });
        final Tooltip linkUrlHint = new Tooltip();
        linkUrlHint.setText(linkUrl);
        linkUrlHint.setShowDelay(Duration.seconds(0.5));
        hyperLinkNode.setTooltip(linkUrlHint);
        return hyperLinkNode;
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
