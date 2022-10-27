package seedu.address.ui;

import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTP;
import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTPS;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
    private static final String FXML = "ModuleListCard.fxml";
    private static final String MESSAGE_LINK_LAUNCH_FAILURE = "Error: Your desktop prevents"
            + " link URLs to be opened from Plannit. "
            + "Please enable system security permissions for Plannit to use this feature.";
    private static final String OS_NAME_LOWERCASE_WINDOWS = "windows";
    private static final String WINDOWS_OPEN_LINK_COMMAND_KEY = "rundll32 url.dll,FileProtocolHandler ";
    private static final String OS_NAME_LOWERCASE_MAC = "mac";
    private static final String MAC_OPEN_LINK_COMMAND_KEY = "open ";
    private static final String OS_NAME_LOWERCASE_LINUX = "linux";
    private static final String LINUX_OPEN_LINK_COMMAND_KEY = "xdg-open";
    private static final String LINK_BORDER_COLOR = "-fx-border-color: #FFCC66;"; //Light Yellow
    private static final String LINK_BORDER_RADIUS = "-fx-border-radius: 2;";
    private static final String LINK_TEXT_COLOR = "-fx-text-fill: white";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names
     * cannot be set to such keywords an exception will be thrown by JavaFX
     * during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;
    public final ObservableList<Boolean> isOnHome;
    private final NoLinksCard noLinksCard = new NoLinksCard();
    private final NoTasksCard noTasksCard = new NoTasksCard();

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label id;
    @FXML
    private Label moduleTitle;
    private TaskListPanel taskListPanel;
    @FXML
    private StackPane taskListPanelPlaceholder;
    @FXML
    private FlowPane links;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to
     * display.
     */
    public ModuleCard(Module module, int displayedIndex,
                      ObservableList<Boolean> isHomeStatus) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCodeAsUpperCaseString());
        moduleTitle.setText(module.getModuleTitleAsUpperCaseString());

        Boolean hasLinksAdded = module.getLinks().size() > 0;
        if (hasLinksAdded) {
            module.getLinks().stream()
                    .forEach(link -> links.getChildren()
                            .add(createHyperLinkNode(link.linkAlias, link.linkUrl)));
            links.setPadding(new Insets(5, 0, 5, 0));
            links.setHgap(5);
            links.setVgap(5);
        } else {
            links.getChildren().add(noLinksCard.getRoot());
        }

        isOnHome = isHomeStatus;
        Boolean isNotOnHome = !isOnHome.get(0);
        Boolean hasTasksAdded = module.getTasks().size() > 0;
        if (isNotOnHome && hasTasksAdded) {
            ObservableList<Task> taskList = module.getTasks();
            taskListPanel = new TaskListPanel(taskList);
            taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
        } else if (isNotOnHome && !hasTasksAdded) {
            taskListPanelPlaceholder.getChildren().add(noTasksCard.getRoot());
        }
    }

    private static Hyperlink createHyperLinkNode(String linkAlias, String linkUrl) {
        Hyperlink hyperLinkNode = new Hyperlink(linkAlias);
        hyperLinkNode.setStyle(LINK_BORDER_COLOR + LINK_BORDER_RADIUS + LINK_TEXT_COLOR);
        final Tooltip linkUrlHint = new Tooltip();
        linkUrlHint.setText(linkUrl);
        linkUrlHint.setShowDelay(Duration.seconds(0.5));
        hyperLinkNode.setTooltip(linkUrlHint);

        boolean isLinkUrlPaddedWithHttps = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTPS);
        boolean isLinkUrlPaddedWithHttp = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTP);
        if (!(isLinkUrlPaddedWithHttps || isLinkUrlPaddedWithHttp)) {
            linkUrl = LINK_HEADER_PROTOCOL_HTTP + linkUrl;
        }
        final String finalLinkUrl = linkUrl;
        hyperLinkNode.setOnAction(e -> {
            try {
                launchLinkFromPlannit(finalLinkUrl);
            } catch (IOException | SecurityException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(MESSAGE_LINK_LAUNCH_FAILURE);
                alert.showAndWait();
            }
        });
        return hyperLinkNode;
    }

    //@@author shwene-reused
    //Reused from https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
    //with slight modification
    private static void launchLinkFromPlannit(String linkUrl) throws IOException, SecurityException {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        if (os.contains(OS_NAME_LOWERCASE_WINDOWS)) {
            rt.exec(WINDOWS_OPEN_LINK_COMMAND_KEY + linkUrl);
        } else if (os.contains(OS_NAME_LOWERCASE_MAC)) {
            rt.exec(MAC_OPEN_LINK_COMMAND_KEY + linkUrl);
        } else if (os.contains(OS_NAME_LOWERCASE_LINUX)) {
            String[] cmd = {LINUX_OPEN_LINK_COMMAND_KEY, linkUrl};
            rt.exec(cmd);
        }
    }
    //@@author

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
                && module.equals(card.module)
                && isOnHome.equals(card.isOnHome);
    }

    /**
     * Handles onClick event of a task list panel's card.
     */
    @FXML
    public void handleClick() {
        boolean isTaskListPanelChildless =
                taskListPanelPlaceholder.getChildren().size() == 0;
        boolean isTaskListEmpty = module.getTasks().size() == 0;

        if (isTaskListPanelChildless && isTaskListEmpty) {
            taskListPanelPlaceholder.getChildren().add(noTasksCard.getRoot());
        } else if (isTaskListPanelChildless && !isTaskListEmpty) {
            ObservableList<Task> taskList = module.getTasks();
            taskListPanel = new TaskListPanel(taskList);
            taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
        } else if (!isTaskListPanelChildless) {
            taskListPanelPlaceholder.getChildren().clear();
        }
    }
}
