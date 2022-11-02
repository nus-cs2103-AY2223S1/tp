package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
//@@author
/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Reset mod panel. */
    private final boolean resetModPanel;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private final boolean updateStudentPanel;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean resetModPanel, boolean updateStudentPanel) {
        this.resetModPanel = resetModPanel;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.updateStudentPanel = updateStudentPanel;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isResetModPanel() {
        return resetModPanel;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isUpdateStudentPanel() {
        return updateStudentPanel;
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
                && exit == otherCommandResult.exit
                && resetModPanel == otherCommandResult.resetModPanel
                && updateStudentPanel == otherCommandResult.updateStudentPanel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
