package seedu.address.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.note.Note;

/**
 * Panel containing the list of persons.
 */
public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NoteListPanel.fxml";
    private static final String FILTER_IMAGE_PATH = "/images/filter.png";
    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @FXML
    private ListView<Note> noteListView;
    @FXML
    private ImageView notebookLogo;

    @FXML
    private HBox filteredBox;

    @FXML
    private ImageView filteredImage;

    /**
     * Creates a {@code NoteListPanel} with the given {@code ObservableList}.
     */
    public NoteListPanel(ObservableList<Note> noteList) {
        super(FXML);
        noteListView.setItems(noteList);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
        notebookLogo.setImage(
                new Image(Objects.requireNonNull(getClass().getResource("/images/notebook.png"))
                        .toString()));
        filteredImage.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(FILTER_IMAGE_PATH))));
        filteredBox.setOpacity(0);
    }

    public void setFilteredBoxIcon(boolean isVisible) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                new KeyValue(filteredBox.opacityProperty(), isVisible ? 1 : 0)));
        timeline.play();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Note} using a {@code NoteCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                NoteCard noteCard = new NoteCard(note, getIndex() + 1);
                noteCard.bindWidth(noteListView, 20);
                setGraphic(noteCard.getRoot());
            }
        }
    }

}
