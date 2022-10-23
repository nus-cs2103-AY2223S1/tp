package seedu.address.logic.commands;

import javafx.scene.input.*;
import seedu.address.commons.core.*;
import seedu.address.commons.core.index.*;
import seedu.address.logic.commands.exceptions.*;
import seedu.address.model.*;
import seedu.address.model.person.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Copy a client's details in FinBook.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies the details of the client identified by the"
            + " index number used in the displayed client list.\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String SHOWING_COPY_MESSAGE = "Client copied: %1$s";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to copy
     */
    public CopyCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(person.toClipboardString());
        clipboard.setContent(url);

        return new CommandResult(String.format(SHOWING_COPY_MESSAGE, person));

    }
}
