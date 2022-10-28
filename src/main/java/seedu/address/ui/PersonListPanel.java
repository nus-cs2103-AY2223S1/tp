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
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private static final String FILTER_IMAGE_PATH = "/images/filter.png";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    @FXML
    private ImageView filteredImage;
    @FXML
    private HBox filteredBox;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
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

    public ListView<Person> getListView() {
        return personListView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (personListView.getSelectionModel().getSelectedIndex() != getIndex()) {
                    setOpacity(0.7);
                }
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }

            focusedProperty().addListener((ob, o, n) -> setOpacity(1));

            setOnMouseEntered(e -> setOpacity(1));
            setOnMouseExited(e -> {
                if (personListView.getSelectionModel().getSelectedIndex() != getIndex()) {
                    setOpacity(0.7);
                }
            });
        }
    }
}
