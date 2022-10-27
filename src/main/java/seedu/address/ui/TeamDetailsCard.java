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
    private Label teamDescription;
    @FXML
    private StackPane memberListPanel;
    @FXML
    private StackPane taskListPanel;
    @FXML
    private StackPane linkListPanel;

    private ResultDisplay resultDisplay;

    /**
     * Creates a {@code TeamDetailsCard} with the given {@code Team}.
     */
    public TeamDetailsCard(Logic logic, Team team, ResultDisplay resultDisplay) {
        super(FXML);
        this.logic = logic;
        teamNameDisplay.setText(team.getTeamName());
        teamDescription.setText(team.getDescription());
        MemberListPanel members = new MemberListPanel(logic.getFilteredMemberList());

        memberListPanel.getChildren().add(members.getRoot());

        TaskListPanel tasks = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanel.getChildren().add(tasks.getRoot());

        LinkListPanel links = new LinkListPanel(team.getLinkList(), resultDisplay);
        linkListPanel.getChildren().add(links.getRoot());

        teamNameDisplay.setWrapText(true);
        teamDescription.setWrapText(true);
    }
}
