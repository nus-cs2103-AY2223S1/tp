package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Copy a client's details in FinBook.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies the details of the client identified by the"
            + " index number used in the displayed client list.\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String COPY_MESSAGE_SUCCESS = "Client's details copied: %1$s";

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

        StringSelection stringSelection = new StringSelection(person.toClipboardString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        return new CommandResult(String.format(COPY_MESSAGE_SUCCESS, person.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof CopyCommand // instanceof handles nulls
                && index.equals(((CopyCommand) other).index))); //index check
    }
}
