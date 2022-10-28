package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.social.exceptions.SocialException;

/**
 * Controller for a group page
 */
public class GroupWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "GroupWindow.fxml";

    private Logic logic;
    private final Group group;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel groupListPanel;

    @FXML
    private Button emailAllButton;

    @FXML
    private StackPane groupListPanelPlaceholder;

    /**
     * Creates a new GroupWindow.
     *
     * @param root Stage to use as the root of the GroupWindow.
     */
    public GroupWindow(Stage root, Group group, Logic logic, CommandBox.CommandSetter commandSetter) {
        super(FXML, root);
        this.logic = logic;
        this.group = group;

        groupListPanel = new PersonListPanel(this.logic.getGroupedPersonList(), commandSetter);
        groupListPanelPlaceholder.getChildren().add(groupListPanel.getRoot());

        root.setTitle("Group: " + group.groupName);
    }

    /**
     * Shows the group window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX
     *                               Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing group page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the group window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the group window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the group window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Emails all the contacts in the currently displayed group.
     */
    @FXML
    private void emailAll() throws SocialException, URISyntaxException, IOException {
        ObservableList<Person> groupPersons = this.logic.getGroupedPersonList();
        StringBuilder sb = new StringBuilder().append("mailto:");
        for (Person person: groupPersons) {
            if (person.getEmail() == null) {
                throw new SocialException("No Email Link");
            }
            sb.append(",").append(person.getEmail());
        }
        URI uri = new URI(sb.toString());
        Desktop desktop = java.awt.Desktop.getDesktop();
        desktop.browse(uri);
    }

}
