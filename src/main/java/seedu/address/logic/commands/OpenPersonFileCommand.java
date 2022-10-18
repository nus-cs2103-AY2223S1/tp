package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FilePath;
import seedu.address.model.person.Person;

/**
 * Opens the PDF file of an existing person in the address book.
 */
public class OpenPersonFileCommand extends Command {
    public static final String COMMAND_WORD = "file";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the PDF file that is associated with the person identified by the index number used in "
            + "the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_OPEN_PERSON_FILE_SUCCESS = "Opened File of Person: %1$s ";

    private final Index targetIndex;

    public OpenPersonFileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToOpenFile = lastShownList.get(targetIndex.getZeroBased());

        if (!personToOpenFile.hasFilePath()) {
            throw new CommandException(FilePath.MESSAGE_EMPTY_FILEPATH);
        }

        try {
            FileUtil.openPdfFile(personToOpenFile.getFilePath().toString());
        } catch (IOException e) {
            throw new CommandException(e.getMessage(), e);
        }
        return new CommandResult(String.format(MESSAGE_OPEN_PERSON_FILE_SUCCESS, personToOpenFile));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenPersonFileCommand // instanceof handles nulls
                && targetIndex.equals(((OpenPersonFileCommand) other).targetIndex)); // state check
    }
}
