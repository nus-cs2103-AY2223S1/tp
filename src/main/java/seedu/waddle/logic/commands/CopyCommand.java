package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.core.Messages.MESSAGE_COPY_ERROR;

import java.awt.AWTError;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Lists all persons in the address book to the user.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_SUCCESS = "%1$s copied to clipboard!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Itinerary selectedItinerary = StageManager.getInstance().getSelectedItinerary();
        String itineraryText = selectedItinerary.getTextRepresentation();
        StringSelection stringSelection = new StringSelection(itineraryText);
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } catch (IllegalStateException | AWTError | HeadlessException e) {
            throw new CommandException(MESSAGE_COPY_ERROR);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, selectedItinerary.getDescription()));
    }
}
