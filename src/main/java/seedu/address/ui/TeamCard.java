package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;
import seedu.address.model.team.Team;

/**
 *  An UI component that displays information of a {@code Team}.
 */
public class TeamCard extends UiPart<Region> {
    private static final String FXML = "TeamListCard.fxml";
    public final Team team;
    private PersonListPanel personListPanel;
    private TaskListPanel taskListPanel;

    @FXML
    private Label name;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane teamListPanelPlaceholder;

    @FXML
    private Label id;

    @FXML
    private ProgressBar taskProgressBar;

    @FXML
    private Label taskFraction;


    /**
     * Creates a {@code TeamCode} with the given {@code Team} and index to display.
     */
    public TeamCard(Team team, int index, Logic logic) {
        super(FXML);

        name.setText(team.getName().fullName);
        name.setWrapText(true);
        id.setText(index + ". ");

        personListPanel = new PersonListPanel(team.getMemberList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        taskListPanel = new TaskListPanel(team.getTasksList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        int numberOfCompletedTasks = team.getNoOfCompletedTasK();
        int totalNumberOfTasks = team.getTasks().getSize();
        double progressFraction = (numberOfCompletedTasks * 1.0) / (totalNumberOfTasks * 1.0);
        taskProgressBar.setProgress(progressFraction);

        taskFraction.setText(numberOfCompletedTasks + "/" + totalNumberOfTasks);


        this.team = team;
    }
}
