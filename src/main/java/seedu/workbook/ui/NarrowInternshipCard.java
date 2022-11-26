package seedu.workbook.ui;


import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.workbook.model.internship.Internship;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class NarrowInternshipCard extends UiPart<Region> {

    private static final String FXML = "NarrowInternshipListCard.fxml";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;


    @FXML
    private HBox cardPane;
    @FXML
    private Label company;
    @FXML
    private Label role;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label stage;
    @FXML
    private Label dateTime;
    @FXML
    private FlowPane tags;
    @FXML
    private Button tipsButton;


    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public NarrowInternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;


        id.setText(displayedIndex + ". ");
        company.setText(internship.getCompany().name);
        role.setText(internship.getRole().value);
        email.setText(internship.getEmail().value);
        stage.setText(internship.getStage().value);
        dateTime.setText(internship.getDateTime().value);
        internship.getLanguageTags().stream()
                .sorted(Comparator.comparing(languageTag -> languageTag.tagName))
                .forEach(languageTag -> tags.getChildren().add(new LanguageTagLabel(languageTag.tagName)));
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NarrowInternshipCard)) {
            return false;
        }

        // state check
        NarrowInternshipCard card = (NarrowInternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }

}
