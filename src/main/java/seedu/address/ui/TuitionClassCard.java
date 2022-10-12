package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TuitionClassCard extends UiPart<Region> {

    private static final String FXML = "TuitionClassCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TuitionClass tuitionClass;

    @FXML
    protected HBox cardPane;
    @FXML
    protected Label name;
    @FXML
    protected Label id;
    @FXML
    protected Label day;
    @FXML
    protected Label level;
    @FXML
    protected Label subject;
    @FXML
    protected Label time;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TuitionClassCard(TuitionClass tuitionClass, int displayedIndex) {
        super(FXML);
        this.tuitionClass = tuitionClass;
        id.setText(displayedIndex + ". ");
        name.setText(tuitionClass.getName().name);
        day.setText("Day: " + tuitionClass.getDay().day);
        time.setText("Time: " + tuitionClass.getTime().toString());
        level.setText("Level: " + tuitionClass.getLevel().level);
        subject.setText("Subject: " + tuitionClass.getSubject().subject);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionClassCard)) {
            return false;
        }

        // state check
        TuitionClassCard card = (TuitionClassCard) other;
        return id.getText().equals(card.id.getText())
                && tuitionClass.equals(card.tuitionClass);
    }
}
