package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import javafx.scene.text.TextFlow;
import seedu.address.commons.core.LogsCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.fxml.FXML;

public class PersonCountDisplay extends UiPart<Region> {
    private static final String FXML = "PersonCountDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonCountDisplay.class);
    private static final String MESSAGE_PERSON_COUNT_ZERO = "No candidates in address book";

    @FXML
    private TextFlow personCountDisplay;

    public PersonCountDisplay(int filteredCount, int totalCount) {
        super(FXML);
        personCountDisplay.getChildren().addAll(getPersonCountMessage(filteredCount, totalCount));
    }

    /**
     * Returns the message to be displayed in the person count label.
     */
    private List<Label> getPersonCountMessage(int filteredCount, int totalCount) {
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
        return personCountMessage;
    }
}
