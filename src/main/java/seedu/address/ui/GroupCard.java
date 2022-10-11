package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;

public class GroupCard extends UiPart<Region> {

    private static final String FXML = "GroupListCard.fxml";

    public final Group group;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private FlowPane members;

    public GroupCard(Group group) {
        super(FXML);
        this.group = group;
        name.setText(group.getName().toString());
        group.getMembers().stream()
                .sorted(Comparator.comparing(member ->
                    member.getName().toString()))
                .forEach(member ->
                    members.getChildren().add(new Label(member.getName().toString())));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCard)) {
            return false;
        }

        // state check
        GroupCard card = (GroupCard) other;
        return name.getText().equals(card.name.getText())
                && group.equals(card.group);
    }

}
