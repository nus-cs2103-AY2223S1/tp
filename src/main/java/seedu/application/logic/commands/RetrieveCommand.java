package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.application.Application;

/**
 * Retrieves an archived application back to the application list in CinternS.
 */
public class RetrieveCommand extends Command {
    public static final String COMMAND_WORD = "retrieve";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieve the application identified by the index number used in the displayed archive list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_APPLICATION_IS_NOT_ARCHIVE = "The application provided by the"
            + " index is not archived.\n"
            + "Please switch to archive list using <list-archive> command before using <retrieve> command.\n"
            + "Example: 1) " + ListArchiveCommand.COMMAND_WORD + "\n"
            + "              2) " + COMMAND_WORD + " 1";

    public static final String MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX_RETRIEVE = "The application index "
            + "provided is invalid!\n"
            + "Please note that the index refers to the last shown list below\n"
            + "To ensure the retrieve function works, please use <list-archive> command to show the current"
            + " archived application list.\n"
            + "Example: 1) " + ListArchiveCommand.COMMAND_WORD + "\n"
            + "               2) " + COMMAND_WORD + " 1";

    public static final String MESSAGE_RETRIEVE_APPLICATION_SUCCESS = "Retrieved Application: %1$s";

    private final Index targetIndex;

    /**
     * Creates an RetrieveCommand to retrieve the specified {@code Application}.
     * @param targetIndex of the application in the filtered application list to retrieve.
     */
    public RetrieveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX_RETRIEVE);
        }

        Application applicationToRetrieve = lastShownList.get(targetIndex.getZeroBased());

        if (!applicationToRetrieve.isArchived()) {
            throw new CommandException(MESSAGE_APPLICATION_IS_NOT_ARCHIVE);
        }
        model.retrieveApplication(applicationToRetrieve);
        model.updateApplicationListWithInterview();
        return new CommandResult(String.format(MESSAGE_RETRIEVE_APPLICATION_SUCCESS, applicationToRetrieve));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RetrieveCommand // instanceof handles nulls
                && targetIndex.equals(((RetrieveCommand) other).targetIndex)); // state check
    }
}
