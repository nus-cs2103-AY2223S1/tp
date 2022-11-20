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

    /** Light mode should be shown to the user. */
    private final boolean showLight;

    /** Dark mode should be shown to the user. */
    private final boolean showDark;

    /** The application should exit. */
    private final boolean exit;

    /** Module information should be shown to the user. */
    private final boolean showModuleList;

    /** Student information should be shown to the user. */
    private final boolean showStudentList;

    /** Target module information should be shown to the user. */
    private final boolean showTargetModule;

    /** Module information should be shown to the user. */
    private final boolean showModule;

    /** Schedule information should be shown to the user. */
    private final boolean showScheduleList;

    /** Horizontal Timetable information should be shown to the user. */
    private final boolean showHorizontalTimeTable;

    /** Vertical Timetable information should be shown to the user. */
    private final boolean showVerticalTimeTable;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showModuleList, boolean showStudentList,
                         boolean showTargetModule, boolean showModule,
                         boolean showScheduleList, boolean showHorizontalTimeTable,
                         boolean showVerticalTimeTable, boolean showLight, boolean showDark) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showModuleList = showModuleList;
        this.showStudentList = showStudentList;
        this.showTargetModule = showTargetModule;
        this.showModule = showModule;
        this.showScheduleList = showScheduleList;
        this.showHorizontalTimeTable = showHorizontalTimeTable;
        this.showVerticalTimeTable = showVerticalTimeTable;
        this.showLight = showLight;
        this.showDark = showDark;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false,
                false, false, false, false,
                false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * and showTimeTable(vertical/horizontal) set to the default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showModuleList, boolean showStudentList, boolean showTargetModule,
                         boolean showModule, boolean showScheduleList, boolean showLight, boolean showDark) {
        this(feedbackToUser, showHelp, exit, showModuleList,
                showStudentList, showTargetModule, showModule, showScheduleList,
                false, false, false, false);
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

    public boolean isShowTargetModule() {
        return showTargetModule;
    }
    public boolean isShowModule() {
        return showModule;
    }

    public boolean isShowScheduleList() {
        return showScheduleList;
    }

    public boolean isShowHorizontalTimeTable() {
        return showHorizontalTimeTable;
    }

    public boolean isShowVerticalTimeTable() {
        return showVerticalTimeTable;
    }

    public boolean isShowLight() {
        return showLight;
    }

    public boolean isShowDark() {
        return showDark;
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
                && showTargetModule == otherCommandResult.showTargetModule
                && showModule == otherCommandResult.showModule
                && showScheduleList == otherCommandResult.showScheduleList
                && showHorizontalTimeTable == otherCommandResult.showHorizontalTimeTable
                && showVerticalTimeTable == otherCommandResult.showVerticalTimeTable
                && showLight == otherCommandResult.showLight
                && showDark == otherCommandResult.showDark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showModuleList,
                showStudentList, showTargetModule, showModule, showScheduleList, showHorizontalTimeTable,
                showVerticalTimeTable, showLight, showDark);
    }

}
