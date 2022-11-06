package seedu.masslinkers.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Student;

/**
 * Panel containing the list of modules.
 */
public class ModListPanel extends UiPart<Region> {
    private static final String FXML = "ModListPanel.fxml";
    private Student currentStudent;
    private final Logger logger = LogsCenter.getLogger(ModListPanel.class);

    @FXML
    private ListView<Mod> modListView;

    /**
     * Creates a {@code ModListPanel} with the given {@code ObservableList}.
     */
    public ModListPanel() {
        super(FXML);
    }

    public void setStudentModList(Student student) {
        if (student != null) {
            currentStudent = student;
            ObservableList<Mod> moduleList = student.getMods();
            modListView.setItems(moduleList);
            modListView.setCellFactory(listView -> new ModListViewCell());
        }
    }

    /**
     * Returns the current {@code Student}.
     */
    public Student getCurrentStudent() {
        return this.currentStudent;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    static class ModListViewCell extends ListCell<Mod> {
        @Override
        protected void updateItem(Mod module, boolean isEmpty) {
            super.updateItem(module, isEmpty);

            if (isEmpty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModCard(module, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Clears the mod panel.
     */
    public void clearModPanel() {
        modListView.setItems(null);
    }
}
