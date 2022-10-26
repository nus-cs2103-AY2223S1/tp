package seedu.address.ui.command.window;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.HashSet;
import java.util.function.Consumer;

import javafx.stage.Stage;
import seedu.address.ui.CommandBox;
import seedu.address.ui.UiPart;

/**
 * Controller for to add a Commission by the GUI.
 */
public class AddCommissionWindow extends CommissionWindow {

    private static final String ADD_COMMISSION_WINDOW_NAME = "Add Commission";

    /**
     * Creates a new AddCommissionWindow.
     */
    public AddCommissionWindow(Consumer<UiPart<Stage>> addChildWindow,
                               CommandBox.CommandExecutor commandExecutor,
                               Stage stage) {
        super(addChildWindow, commandExecutor, stage, ADD_COMMISSION_WINDOW_NAME);
    }

    /**
     * Tries to create a commission with the given user inputs.
     */
    @Override
    void handleCommissionCommand() {
        String addCommissionCommand = "addcom";
        addCommissionCommand += " " + PREFIX_TITLE + getTitleInput();
        addCommissionCommand += " " + PREFIX_FEE + getFeeInput();
        addCommissionCommand += " " + PREFIX_DEADLINE + getDeadlineInput();
        addCommissionCommand += " " + PREFIX_STATUS + getCompletedInput();

        if (!getDescriptionInput().isBlank()) {
            addCommissionCommand += " " + PREFIX_DESCRIPTION + getDescriptionInput();
        }

        HashSet<String> uniqueTags = getCommissionTagsInput();
        for (String tagString : uniqueTags) {
            addCommissionCommand += " " + PREFIX_TAG + tagString;
        }

        executeCommissionCommand(addCommissionCommand);
    }
}
