package seedu.workbook.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class TipCard extends UiPart<Region> {

    private static final String FXML = "TipCard.fxml";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final int index;
    public final String tip;

    @FXML
    private Label indexLabel;
    @FXML
    private Label tipLabel;




    /**
     * Creates a {@code TipCard} with the given {@code Tip} and index to display.
     */
    public TipCard(int index, String tip) {
        super(FXML);
        this.tip = tip;
        this.index = index;

        this.indexLabel.setText(String.valueOf(this.index) + ". ");
        this.tipLabel.setText(this.tip);
    }

}
