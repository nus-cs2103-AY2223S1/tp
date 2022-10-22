package seedu.address.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class InspectionPanel extends UiPart<Region> {
    private static final String PERSON_IMAGE_PATH = "/images/person.png";
    private static final String PHONE_IMAGE_PATH = "/images/phone.png";
    private static final String EMAIL_IMAGE_PATH = "/images/mail.png";
    private static final String ADDRESS_IMAGE_PATH = "/images/home.png";

    private static final String FXML = "InspectionPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InspectionPanel.class);

    @FXML
    private ListView<Person> historyListView;

    @FXML
    private Label name;
    @FXML
    private ImageView nameImage;

    @FXML
    private Label phone;
    @FXML
    private ImageView phoneImage;

    @FXML
    private Label address;
    @FXML
    private ImageView addressImage;

    @FXML
    private Label email;
    @FXML
    private ImageView emailImage;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public InspectionPanel(ListView<Person> personListView, ObservableList<Person> personList) {
        super(FXML);
        historyListView.setItems(personList);
        historyListView.setCellFactory(listView -> new HistoryListViewCell());
        setImageViews();
        personListView.getSelectionModel().selectedItemProperty().addListener(
                //CHECKSTYLE.OFF: SeparatorWrap
                (obs, o, n) -> {
                    InspectionPanel.this.name.setText(n.getName().fullName);
                    InspectionPanel.this.email.setText(n.getEmail().value);
                    InspectionPanel.this.phone.setText(n.getPhone().value);
                    InspectionPanel.this.address.setText(n.getAddress().value);
                });

        personListView.getSelectionModel().select(0);
    }

    private void setImageViews() {
        nameImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(PERSON_IMAGE_PATH))));
        phoneImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(PHONE_IMAGE_PATH))));
        emailImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(EMAIL_IMAGE_PATH))));
        addressImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ADDRESS_IMAGE_PATH))));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class HistoryListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
