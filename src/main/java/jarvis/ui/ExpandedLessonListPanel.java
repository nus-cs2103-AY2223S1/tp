package jarvis.ui;

import java.util.logging.Logger;

import jarvis.commons.core.LogsCenter;
import jarvis.model.Lesson;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;


/**
 * Panel containing the list of lessons, fully expanded with all details shown.
 */
public class ExpandedLessonListPanel extends UiPart<Region> {

    private static final String FXML = "ExpandedLessonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpandedLessonListPanel.class);

    @FXML
    private ListView<Lesson> lessonListView;

    /**
     * Creates a {@code LessonListPanel} with the given {@code ObservableList}.
     */
    public ExpandedLessonListPanel(ObservableList<Lesson> lessonList) {
        super(FXML);
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Lesson} using a {@code ExpandedLessonCard}.
     */
    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpandedLessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }

}
