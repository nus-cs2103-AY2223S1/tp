package modtrekt.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import modtrekt.logic.Logic;
import modtrekt.logic.commands.DoneModuleCommand;
import modtrekt.logic.commands.UndoneModuleCommand;

/**
 * Panel containing the progress (MCs, modules completed, CAP info) of the user.
 */
public class ProfileSidePanel extends UiPart<Region> {

    public static final String FXML = "ProfileSidePanel.fxml";

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label creditsCount;

    @FXML
    private Label inspirationalQuote;

    @FXML
    private Label currentCapLabel;

    @FXML
    private Label semesterLabel;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     * @param logic
     */
    public ProfileSidePanel(Logic logic) {
        super(FXML);

        DoneModuleCommand.refresh(logic.getModuleList().getModuleList());
        refresh();
    }

    /**
     * Method to refresh user data.
     */
    public void refresh() {
        int totalCredits = DoneModuleCommand.getTotalCredits();
        creditsCount.setText(totalCredits + " MCs");
    }
}
