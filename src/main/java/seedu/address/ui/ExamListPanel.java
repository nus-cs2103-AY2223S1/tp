package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.exam.Exam;


/**
 * ExamListPanel class represents a panel which contains a list of all
 * the exams.
 */
public class ExamListPanel extends UiPart<Region> {
    private static final String FXML = "ExamListPanel.fxml";

    @FXML
    private ListView<Exam> examListView;

    /**
     * The constructor of the ExamListPanel. Sets the list of exams
     * to the ListView.
     *
     * @param examList
     */
    public ExamListPanel(ObservableList<Exam> examList) {
        super(FXML);
        examListView.setItems(examList);
        examListView.setCellFactory(listView -> new ExamListViewCell());
    }

    static class ExamListViewCell extends ListCell<Exam> {
        @Override
        protected void updateItem(Exam exam, boolean empty) {
            super.updateItem(exam, empty);

            if (empty || exam == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExamCard(exam, getIndex() + 1).getRoot());
            }
        }
    }

}
