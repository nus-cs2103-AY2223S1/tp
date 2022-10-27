package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import java.util.logging.Logger;


/**
 * Panel containing the list of persons.
 */
public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingListPanel.class);
    private MainDisplay mainDisplayListener;

    @FXML
    private ListView<Person> meetingListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public MeetingListPanel(ObservableList<Person> personList) {
        super(FXML);
        meetingListView.setItems(personList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());
    }

    /**
     * Stores a reference to a main display instance that listens to on click events from PersonCard
     * @param mainDisplay the main display that changes on click
     */
    public void addListener(MainDisplay mainDisplay) {
        mainDisplayListener = mainDisplay;
    }

    /**
     * Adds the mainDisplayListener to a personCard
     * @param meetingCard new PersonCard that main display listens to
     */
    public void setMeetingCardListener(MeetingCard meetingCard) {
        meetingCard.addMainDisplayListener(mainDisplayListener);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class MeetingListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                MeetingCard meetingCard = new MeetingCard(person, getIndex() + 1);
                setGraphic(meetingCard.getRoot());
                setMeetingCardListener(meetingCard);
            }
        }
    }

}
