package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;

public class MainDisplay extends UiPart<Region>{

    private static final String FXML = "MainDisplay.fxml";

    @FXML
    private StackPane mainDisplay;

    public MainDisplay() {
        super(FXML);
    }

    /**
     * Sets the main display to show the details of a person object
     * @param person person object to be displayed
     */
    public void setPersonProfile(Person person) {
        requireNonNull(person);
        setMainDisplay(new PersonProfile(person).getRoot());
    }

    /**
     * Sets a region to be the only thing displayed on the main display.
     * @param region container to be displayed on main display
     */
    public void setMainDisplay(Region region) {
        mainDisplay.getChildren().clear();
        mainDisplay.getChildren().add(region);
    }
}
