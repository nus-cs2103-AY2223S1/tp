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

    /** The application should exit. */
    private final boolean exit;

    /** The theme to change to, null if no change. */
    private final Theme theme;

    /** Should toggle the PersonCardView */
    private final boolean toggleCardView;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Theme theme) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.theme = theme;
        this.toggleCardView = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * and Optional fields set to empty
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean toggleCardView) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        theme = null;
        this.toggleCardView = toggleCardView;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
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

    public boolean isToggleCardView() {
        return toggleCardView;
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
