package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

        EventHandler keyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    switcher.execute(getSelectedPerson());
                }
            }
        };
        setKeyEventHandler(keyEventHandler);

        EventHandler clickEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Double Click
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {
                        switcher.execute(getSelectedPerson());
                    }
                }
            }
        };
        setClickEventHandler(clickEventHandler);
    }

    public Person getSelectedPerson() {
        return personListView.getSelectionModel().getSelectedItem();
    }

    public void setKeyEventHandler(EventHandler<KeyEvent> eventHandler) {
        personListView.setOnKeyPressed(eventHandler);
    }

    public void setClickEventHandler(EventHandler<MouseEvent> eventHandler) {
        personListView.setOnMouseClicked(eventHandler);
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.List;
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
