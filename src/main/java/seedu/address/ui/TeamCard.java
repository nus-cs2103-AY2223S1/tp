package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.team.Team;
import seedu.address.logic.Logic;

public class TeamCard extends UiPart<Region> {
    private static final String FXML = "TeamListCard.fxml";

    public final Team team;

    private PersonListPanel personListPanel;

    @FXML
    private Label name;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane teamListPanelPlaceholder;

    public TeamCard(Team team, int index, Logic logic) {
        super(FXML);

        name.setText(team.getName().fullName);


//        personListPanel = new PersonListPanel(team.getMembers());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());


        //todo popoulate teamlistpanelplaceholder


        this.team = team;
    }
}
