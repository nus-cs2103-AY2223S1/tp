package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

import java.util.List;

/**
 * A UI Component that displays the full information of a {@code Team}.
 */
public class TeamDetailsCard extends UiPart<Region> {

    private static final String FXML = "TeamDetailsCard.fxml";

    private Team team;

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
    public TeamDetailsCard(Team team) {
        super(FXML);
        teamNameDisplay.setText(team.getTeamName());
//        memberListView.setItems(team.getTeamMembers());

        MemberListPanel members = new MemberListPanel(team.getTeamMembers());
        memberListPanel.getChildren().add(members.getRoot());

        TaskListPanel tasks = new TaskListPanel(team.getTaskList());
        taskListPanel.getChildren().add(tasks.getRoot());

//        taskListView.setItems(team.getTaskList());
        teamNameDisplay.setWrapText(true);
    }
}
