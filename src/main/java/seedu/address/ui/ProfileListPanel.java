package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.profile.Profile;

/**
 * Panel containing the list of profiles.
 */
public class ProfileListPanel extends UiPart<Region> {
    private static final String FXML = "ProfileListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProfileListPanel.class);

    @FXML
    private Label profileListTitle;
    @FXML
    private ListView<Profile> profileListView;

    /**
     * Creates a {@code ProfileListPanel} with the given {@code ObservableList}.
     */
    public ProfileListPanel(ObservableList<Profile> profileList) {
        super(FXML);
        profileListTitle.setText("Profiles");
        profileListView.setItems(profileList);
        profileListView.setCellFactory(listView -> new ProfileListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Profile} using a {@code ProfileCard}.
     */
    class ProfileListViewCell extends ListCell<Profile> {
        @Override
        protected void updateItem(Profile profile, boolean empty) {
            super.updateItem(profile, empty);

            if (empty || profile == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProfileCard(profile, getIndex() + 1).getRoot());
            }
        }
    }

}
