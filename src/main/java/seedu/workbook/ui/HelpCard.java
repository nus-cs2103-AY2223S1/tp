package seedu.workbook.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.workbook.ui.util.HelpUtil;
import seedu.workbook.ui.util.HelpUtil.Command;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class HelpCard extends UiPart<Region> {

    private static final String FXML = "HelpCard.fxml";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Command command;

    @FXML
    private Label commandLabel;
    @FXML
    private Label exampleLabel;




    /**
     * Creates a {@code HelpCard} with the given {@code Command} and example to display.
     */
    public HelpCard(Command command) {
        super(FXML);
        this.command = command;

        commandLabel.setText(HelpUtil.getCommandHeader(command));
        exampleLabel.setText(HelpUtil.getCommandExample(command));
    }

}
