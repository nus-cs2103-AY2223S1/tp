package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Module information should be shown to the user. */
    private final boolean showModuleList;

    /** Student information should be shown to the user. */
    private final boolean showStudentList;

    /** Module information should be shown to the user. */
    private final boolean showModule;

    /** Schedule information should be shown to the user. */

    private final boolean showScheduleList;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showModuleList, boolean showStudentList,
                         boolean showModule, boolean showScheduleList) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showModuleList = showModuleList;
        this.showStudentList = showStudentList;
        this.showModule = showModule;
        this.showScheduleList = showScheduleList;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false,
                false, false, false);
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

    public boolean isShowModuleList() {
        return showModuleList;
    }

    public boolean isShowStudentList() {
        return showStudentList;
    }

    public boolean isShowModule() {
        return showModule;
    }

    public boolean isShowScheduleList() {
        return showScheduleList;
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
                && showModuleList == otherCommandResult.showModuleList
                && showStudentList == otherCommandResult.showStudentList
                && showModule == otherCommandResult.showModule
                && showScheduleList == otherCommandResult.showScheduleList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showModuleList,
                showStudentList, showModule, showScheduleList);
    }

}
