package seedu.address.ui.command.window;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_IMAGEPATH;

import java.util.function.Consumer;

import javafx.stage.Stage;
import seedu.address.logic.commands.iteration.AddIterationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox;
import seedu.address.ui.UiPart;

/**
 * Controller for to add an Iteration by the GUI.
 */
public class AddIterationWindow extends IterationWindow {

    private static final String ADD_ITERATION_WINDOW_NAME = "Add Iteration";
    private static final String ERROR_NO_IMAGE_PROVIDED = "Please select an image.";

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
        try {
            String addIterationCommand = getAddIterationCommand();
            executeIterationCommand(addIterationCommand);
        } catch (ParseException e) {
            setErrorMessage(e.getMessage());
        }
    }

    private String getAddIterationCommand() throws ParseException {
        String addIterationCommandInput = AddIterationCommand.COMMAND_WORD + " ";
        addIterationCommandInput += PREFIX_ITERATION_DATE + getDateInput() + " ";
        addIterationCommandInput += PREFIX_ITERATION_DESCRIPTION + getDescriptionInput() + " ";
        addIterationCommandInput += PREFIX_ITERATION_FEEDBACK + getFeedbackInput() + " ";

        if (getImagePathInput().isBlank()) {
            throw new ParseException(ERROR_NO_IMAGE_PROVIDED);
        }

        addIterationCommandInput += PREFIX_ITERATION_IMAGEPATH + getImagePathInput();
        return addIterationCommandInput;
    }
}
