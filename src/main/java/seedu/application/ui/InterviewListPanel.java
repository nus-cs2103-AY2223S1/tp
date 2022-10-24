package seedu.application.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.application.Application;


/**
 * Panel containing the list of interviews.
 */
public class InterviewListPanel extends UiPart<Region> {
    private static final String FXML = "InterviewListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InterviewListPanel.class);

    @FXML
    private ListView<Application> interviewListView;

    /**
     * Creates an {@code InterviewListPanel} with the given {@code ObservableList}.
     */
    public InterviewListPanel(ObservableList<Application> applicationListWithInterview) {
        super(FXML);
        interviewListView.setItems(applicationListWithInterview);
        interviewListView.setCellFactory(listView -> new InterviewListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Interview} using an {@code InterviewCard}.
     */
    class InterviewListViewCell extends ListCell<Application> {
        @Override
        protected void updateItem(Application application, boolean empty) {
            super.updateItem(application, empty);

            if (empty || application == null || !application.hasInterview()) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InterviewCard(application, getIndex() + 1).getRoot());
            }
        }
    }

}
