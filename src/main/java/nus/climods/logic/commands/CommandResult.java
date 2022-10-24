package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import nus.climods.model.Model;

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

    /* The application should save userModuleList */
    private boolean isSave;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        isSave = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, and other fields set to their
     * default value.
     */
    public CommandResult(String feedbackToUser, String commandWord,
                         Model model) {
        this(feedbackToUser, false, false);

        switch (commandWord) {
        case (AddCommand.COMMAND_WORD):

        case (DeleteCommand.COMMAND_WORD):
            isSave = true;
            break;
        default:
            isSave = false;
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

    public boolean isSave() { return isSave; }

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
            && showHelp == otherCommandResult.isShowHelp()
            && exit == otherCommandResult.isExit()
            && isSave == otherCommandResult.isSave();
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }
}
