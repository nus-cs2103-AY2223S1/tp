package hobbylist.ui;

import java.util.logging.Logger;

import hobbylist.commons.core.LogsCenter;
import hobbylist.model.activity.Activity;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of activities.
 */
public class ActivityListPanel extends UiPart<Region> {
    private static final String FXML = "ActivityListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ActivityListPanel.class);

    private SelectedActivityPanel selectedActivityPanel = new SelectedActivityPanel();

    @FXML
    private ListView<Activity> activityListView;

    @FXML
    private AnchorPane selectedActivityPanelPlaceholder;

    /**
     * Creates a {@code ActivityListPanel} with the given {@code ObservableList}.
     */
    public ActivityListPanel(ObservableList<Activity> activityList, ObservableList<Activity> selectedActivity) {
        super(FXML);
        activityListView.setItems(activityList);
        activityListView.setCellFactory(listView -> new ActivityListViewCell());
        setSelectedActivityPanel(selectedActivity);
        selectedActivityPanelPlaceholder.getChildren().add(selectedActivityPanel.getRoot());
        updateSelectedIfChange(selectedActivity);
    }

    public void setSelectedActivityPanel(ObservableList<Activity> selectedActivity) {
        if (selectedActivity.isEmpty()) {
            selectedActivityPanel.setNoActivityDetails();
        } else {
            selectedActivityPanel.setActivityDetails(selectedActivity);
        }
    }

    private void updateSelectedIfChange(ObservableList<Activity> selectedActivity) {
        selectedActivity.addListener(new ListChangeListener<Activity>() {
            @Override
            public void onChanged(Change<? extends Activity> change) {
                setSelectedActivityPanel(selectedActivity);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Activity} using an {@code ActivityCard}.
     */
    class ActivityListViewCell extends ListCell<Activity> {
        @Override
        protected void updateItem(Activity activity, boolean empty) {
            super.updateItem(activity, empty);

            if (empty || activity == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ActivityCard(activity, getIndex() + 1).getRoot());
            }
        }
    }

}
