package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;
import seedu.address.model.team.Team;

/**
 * A UI Component that displays the full information of a {@code Team}.
 */
public class TeamDetailsCard extends UiPart<Region> {

    private static final String FXML = "TeamDetailsCard.fxml";

    private Team team;
    private Logic logic;

    @FXML
    private HBox cardPane;
    @FXML
    private Label teamNameDisplay;
    @FXML
    private StackPane memberListPanel;
    @FXML
    private StackPane taskListPanel;

    /**
     * Creates a {@code TeamDetailsCard} with the given {@code Team}.
     */
    public TeamDetailsCard(Logic logic, Team team) {
        super(FXML);
        this.logic = logic;
        teamNameDisplay.setText(team.getTeamName());

        MemberListPanel members = new MemberListPanel(logic.getFilteredMemberList());

        memberListPanel.getChildren().add(members.getRoot());

        TaskListPanel tasks = new TaskListPanel(team.getTaskList());
        taskListPanel.getChildren().add(tasks.getRoot());

        teamNameDisplay.setWrapText(true);
    }
}
