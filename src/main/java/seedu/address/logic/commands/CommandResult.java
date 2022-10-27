package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.Themes.Theme;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Help information for the specified command to be shown. */
    private final String showHelpFor;

    /** The application should exit. */
    private final boolean exit;

    /** The theme to change to, null if no change. */
    private final Theme theme;

    /** Should toggle the list mode */
    private final boolean toggleListMode;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, String showHelpFor, boolean exit,
                         Theme theme, boolean toggleListMode) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showHelpFor = showHelpFor;
        this.exit = exit;
        this.theme = theme;
        this.toggleListMode = toggleListMode;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Theme theme) {
        this(feedbackToUser, false, "", false, theme, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields
     * and Optional fields set to empty
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, "", exit, null, false);
    }

    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean toggleListMode) {
        this(feedbackToUser, showHelp, "", exit, null, toggleListMode);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, "", false, null, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields
     * and Optional fields set to empty
     */
    public CommandResult(String feedbackToUser, boolean showHelp, String showHelpFor) {
        this(feedbackToUser, showHelp, showHelpFor, false, null, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public String getShowHelpFor() {
        return this.showHelpFor;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * Returns true if theme is not null. Meaning there is a call to change the theme.
     * @return true if theme is not null.
     */
    public boolean isThemeChange() {
        return theme != null;
    }

    public Theme getTheme() {
        return theme;
    }

    public boolean isToggleListMode() {
        return toggleListMode;
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
