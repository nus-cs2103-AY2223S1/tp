package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import nus.climods.model.Model;
import nus.climods.storage.Storage;
import nus.climods.storage.exceptions.StorageException;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, and other fields set to their
     * default value.
     */
    public CommandResult(String feedbackToUser, String commandWord,
                         Storage storage, Model model) throws StorageException {
        this(feedbackToUser, false, false);

        switch (commandWord) {
        case (AddCommand.COMMAND_WORD):

        case (DeleteCommand.COMMAND_WORD):
            storage.saveUserModuleList(model.getUserModuleList());
            break;
        default:
        }
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
            && showHelp == otherCommandResult.showHelp
            && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }
}
