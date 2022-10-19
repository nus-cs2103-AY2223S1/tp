package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.application.Application;

/**
 * Archives an application in the application list in CinternS.
 */
public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives the application identified by the index number used in the displayed application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_APPLICATION_SUCCESS = "Archived Application: %1$s";

    public static final String MESSAGE_APPLICATION_EXIST_IN_ARCHIVE = "Application is already archived.\n"
            + "Please use <list> command to show the current application list\n"
            + "Example: 1) " + ListCommand.COMMAND_WORD + "\n"
            + "               2) " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Creates an ArchiveCommand to archive the specified {@code Application}.
     * @param targetIndex of the application in the filtered application list to archive.
     */
    public ArchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToArchive = lastShownList.get(targetIndex.getZeroBased());

        if (applicationToArchive.isArchived()) {
            throw new CommandException(MESSAGE_APPLICATION_EXIST_IN_ARCHIVE);
        }

        model.archiveApplication(applicationToArchive);
        return new CommandResult(String.format(MESSAGE_ARCHIVE_APPLICATION_SUCCESS, applicationToArchive));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveCommand) other).targetIndex)); // state check
    }
}

