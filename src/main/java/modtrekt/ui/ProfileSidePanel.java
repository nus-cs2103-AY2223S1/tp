package modtrekt.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import modtrekt.commons.util.StringUtil;
import modtrekt.logic.Logic;
import modtrekt.logic.commands.DoneModuleCommand;
import modtrekt.model.Model;

/**
 * Panel containing the progress (MCs, modules completed, CAP info) of the user.
 */
public class ProfileSidePanel extends UiPart<Region> {

    public static final String FXML = "ProfileSidePanel.fxml";

    @FXML
    private Label creditsCount;

    @FXML
    private Label activeTasks;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     * @param logic Logic
     */
    public ProfileSidePanel(Logic logic) {
        super(FXML);

        refresh(logic);
    }

    /**
     * Method to refresh user data.
     */
    public void refresh(Logic logic) {
        // Count MC Completed
        DoneModuleCommand.refresh(logic.getModuleList().getModuleList());
        int totalCredits = DoneModuleCommand.getTotalCredits();
        int totalTasks = logic.getTaskBook().getTaskList().filtered(Model.PREDICATE_HIDE_ARCHIVED_TASKS).size();
        creditsCount.setText(StringUtil.pluralize(totalCredits, "MC"));
        activeTasks.setText(String.valueOf(totalTasks));
    }
}
