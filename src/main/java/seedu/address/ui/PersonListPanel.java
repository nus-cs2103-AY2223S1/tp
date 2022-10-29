package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends MainPanel {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, DetailPanelSwitcher switcher) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());

        personListView.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                switcher.execute(getSelectedPerson());
            }
        });

        // Double click to open person detail panel
        personListView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    switcher.execute(getSelectedPerson());
                }
            }
        });
    }

    /**
     * Focus the list view and the first person in the view
     */
    public void focus() {
        personListView.requestFocus();
        SelectionModel<Person> model = personListView.getSelectionModel();

        if (model.isEmpty()) {
            model.selectFirst();
        }
    }

    public void clearSelectedPerson() {
        personListView.getSelectionModel().clearSelection();
    }

    /**
     * Returns the selected person in the list
     */
    public Person getSelectedPerson() {
        return personListView.getSelectionModel().getSelectedItem();
    }

    public <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        personListView.addEventHandler(eventType, eventHandler);
    }

    public <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        personListView.addEventFilter(eventType, eventHandler);
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.List;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {

        PersonListViewCell() {
            super();
            prefWidthProperty().bind(personListView.widthProperty().subtract(20));
            setMaxWidth(USE_PREF_SIZE);
        }

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            this.setMaxWidth(Region.USE_PREF_SIZE);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Represents a function that switch to detail panel.
     */
    @FunctionalInterface
    public interface DetailPanelSwitcher {
        /**
         * Switch to detail panel with the person.
         */
        void execute(Person person);
    }
}
