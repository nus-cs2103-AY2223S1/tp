package seedu.address.ui;

import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import seedu.address.commons.util.CollectionUtil;

/**
 * An UI component that displays allows switching between assignment and contact details.
 */
public class TabPaneManager {
    private final TabPane tabPane;
    private final Tab personTabPage;
    private final Tab assignmentTabPage;

    /**
     * Constructor that manages the clickable TabPanes.
     *
     * @param pane TabPane
     * @param personTabPage list of person contact details
     * @param assignmentTabPage list of assignments under each person
     */
    public TabPaneManager(TabPane pane, Tab personTabPage, Tab assignmentTabPage) {
        CollectionUtil.requireAllNonNull(pane, personTabPage, assignmentTabPage);
        tabPane = pane;
        this.personTabPage = personTabPage;
        this.assignmentTabPage = assignmentTabPage;
    }

    /**
     * Change the current tab to person tab.
     */
    public void setPersonTabPage() {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(personTabPage);
    }

    /**
     * Change the current tab to group tab.
     */
    public void setAssignmentTabPage() {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(assignmentTabPage);
    }
}
