package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isHelpShown;

    /** The application should exit. */
    private final boolean isExit;

    /**
     * Pop-up window for add command should be shown to the user.
     */
    private final boolean isAddedByPopup;

    /**
     * Specifies which group to add.
     */
    private final String addType;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser,
                         boolean isHelpShown,
                         boolean isExit,
                         boolean isAddedByPopup,
                         String addType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isHelpShown = isHelpShown;
        this.isExit = isExit;
        this.isAddedByPopup = isAddedByPopup;
        this.addType = addType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean isHelpShown, boolean isExit) {
        this(feedbackToUser,
                isHelpShown,
                isExit,
                false,
                null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser,
                false,
                false,
                false,
                null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code HelpCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code HelpCommand}.
     */
    public static CommandResult createHelpCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser,
                true,
                false,
                false,
                null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code ExitCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code ExitCommand}.
     */
    public static CommandResult createExitCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser,
                false,
                true,
                false,
                null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code AddCommandWithPopup}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code AddCommandWithPopup}.
     */
    public static CommandResult createAddByPopupCommandResult(String feedbackToUser, String addType) {
        return new CommandResult(feedbackToUser,
                false,
                false,
                true,
                addType);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isHelpShown() {
        return isHelpShown;
    }

    public boolean isExit() {
        return isExit;
    }


    public boolean isAddedByPopup() {
        return isAddedByPopup;
    }

    public String getAddType() {
        return addType;
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
                && isHelpShown == otherCommandResult.isHelpShown
                && isExit == otherCommandResult.isExit
                && isAddedByPopup == otherCommandResult.isAddedByPopup
                && ((addType == null && otherCommandResult.addType == null)
                || (addType != null && addType.equals(otherCommandResult.addType)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser,
                isHelpShown,
                isExit,
                isAddedByPopup,
                addType);
    }

}
