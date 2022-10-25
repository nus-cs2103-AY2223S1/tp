package seedu.address.ui.command.window;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_IMAGEPATH;

import java.util.function.Consumer;

import javafx.stage.Stage;
import seedu.address.logic.commands.iteration.AddIterationCommand;
import seedu.address.ui.CommandBox;
import seedu.address.ui.UiPart;

/**
 * Controller for to add an Iteration by the GUI.
 */
public class AddIterationWindow extends IterationWindow {

    private static final String ADD_ITERATION_WINDOW_NAME = "Add Iteration";

    /**
     * Creates a new AddIterationWindow.
     */
    public AddIterationWindow(Consumer<UiPart<Stage>> addChildWindow,
                             CommandBox.CommandExecutor commandExecutor,
                             Stage stage) {
        super(addChildWindow, commandExecutor, stage, ADD_ITERATION_WINDOW_NAME);
    }

    /**
     * Tries to create an Iteration with the given user inputs.
     */
    @Override
    public void handleIterationCommand() {
        String addIterationCommand = getAddIterationCommand();
        executeIterationCommand(addIterationCommand);
    }

    private String getAddIterationCommand() {
        String addIterationCommandInput = AddIterationCommand.COMMAND_WORD + " ";
        addIterationCommandInput += PREFIX_ITERATION_DATE + getDateInput() + " ";
        addIterationCommandInput += PREFIX_ITERATION_DESCRIPTION + getDescriptionInput() + " ";
        addIterationCommandInput += PREFIX_ITERATION_FEEDBACK + getFeedbackInput() + " ";
        addIterationCommandInput += PREFIX_ITERATION_IMAGEPATH + getImagePathInput();
        return addIterationCommandInput;
    }
}
