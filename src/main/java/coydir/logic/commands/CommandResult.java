package coydir.logic.commands;

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

    /** The application should display a person's profile. */
    private final boolean viewPerson;

    /** The application should display information on a department. */
    private final boolean viewDepartment;

    private int viewIndex;

    private String department;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.viewPerson = false;
        this.viewDepartment = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * Used only for the {@code View} command.
     */
    public CommandResult(String feedbackToUser, int index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.viewPerson = true;
        this.viewDepartment = false;
        this.viewIndex = index;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * Used only for the {@code View Department} command.
     */
    public CommandResult(String feedbackToUser, String department) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.viewPerson = false;
        this.viewDepartment = true;
        this.viewIndex = 0;
        this.department = department;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
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

    public boolean isViewPerson() {
        return viewPerson;
    }

    public boolean isViewDepartment() {
        return viewDepartment;
    }

    public int getViewIndex() {
        return viewIndex;
    }

    public String getDepartment() {
        return department;
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
                && viewPerson == otherCommandResult.viewPerson
                && viewIndex == otherCommandResult.viewIndex
                && viewDepartment == otherCommandResult.viewDepartment
                && department == otherCommandResult.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, viewPerson, viewIndex, viewDepartment, department);
    }

}
