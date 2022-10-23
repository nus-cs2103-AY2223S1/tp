package modtrekt.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
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

    public ProfileSidePanel() {
        super(FXML);
    }

    /**
     * Method to refresh user data.
     * @param type
     */
    public void refresh(String type) {
        int totalCredits = 0;
        if (type == "Done") {
            totalCredits = DoneModuleCommand.getTotalCredits();
        } else if (type == "Undone") {
            totalCredits = UndoneModuleCommand.getTotalCredits();
        }
        creditsCount.setText(totalCredits + " MCs");
    }
}
