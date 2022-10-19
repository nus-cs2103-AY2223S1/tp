package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tuitionclass.TuitionClass;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class TuitionClassDescription extends UiPart<Region> {

    private static final String FXML = "TuitionClassDescription.fxml";

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
    protected Label time;
    @FXML
    protected Label level;
    @FXML
    protected Label subject;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TuitionClassDescription(TuitionClass tuitionClass, int displayedIndex) {
        super(FXML);
        this.tuitionClass = tuitionClass;
        id.setText(displayedIndex + ". ");
        name.setText(tuitionClass.getName().name);
        day.setText(tuitionClass.getDay().day);
        time.setText(tuitionClass.getTime().toString());
        level.setText(tuitionClass.getLevel().level);
        subject.setText(tuitionClass.getSubject().subject);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionClassDescription)) {
            return false;
        }

        // state check
        TuitionClassDescription card = (TuitionClassDescription) other;
        return id.getText().equals(card.id.getText())
                && tuitionClass.equals(card.tuitionClass);
    }
}
