package seedu.clinkedin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;
import seedu.clinkedin.commons.core.LogsCenter;
import seedu.clinkedin.model.ReadOnlyAddressBook;
import seedu.clinkedin.model.person.Person;

/**
 * A UI component that displays the number of persons in the clinkedin book.
 * @author A0214632N
 */
public class PersonCountDisplay extends UiPart<Region> {

    /**
     * FXMl file to be loaded
     */
    private static final String FXML = "PersonCountDisplay.fxml";

    /**
     * Standard message to be displayed when there are no persons in the clinkedin
     * book
     */
    private static final String MESSAGE_PERSON_COUNT_ZERO = "No candidates in clinkedin book";

    /**
     * The logger for this class.
     */
    private final Logger logger = LogsCenter.getLogger(PersonCountDisplay.class);

    /**
     * The text flow to display the total count of persons in the clinkedin book
     */
    @FXML
    private TextFlow personCountDisplay;

    /**
     * Constructor for PersonCountDisplay
     * @param filteredList The filtered list of persons in the clinkedin book
     * @param totalList    The list of all persons in the clinkedin book
     */
    public PersonCountDisplay(ObservableList<Person> filteredList, ReadOnlyAddressBook totalList) {
        super(FXML);
        setPersonCountMessage(filteredList, totalList);
    }

    /**
     * Returns the message to be displayed in the person count label.
     * This function runs during the initialisation of the PersonCountDisplay
     * and dynamically when the addressbook changes from each command.
     * @param filteredList The filtered list of persons in the clinkedin book
     * @param totalList    The list of all persons in the clinkedin book
     */
    public void setPersonCountMessage(ObservableList<Person> filteredList, ReadOnlyAddressBook totalList) {
        int filteredCount = filteredList.size();
        int totalCount = totalList.getCount();
        List<Label> personCountMessage = new ArrayList<>();
        if (totalCount == 0) {
            personCountMessage.add(new Label(MESSAGE_PERSON_COUNT_ZERO));
        } else {
            Label filteredCountText = new Label(String.format("%d", filteredCount));
            Label totalCountText = new Label(String.format("%d", totalCount));
            filteredCountText.setStyle("-fx-font-weight: bold");
            totalCountText.setStyle("-fx-font-weight: bold");
            personCountMessage
                    .addAll(List.of(new Label("Showing"), filteredCountText, new Label("Candidates out of"),
                            totalCountText, new Label("total Candidates")));
        }
        personCountDisplay.getChildren().setAll(personCountMessage);
    }
}
