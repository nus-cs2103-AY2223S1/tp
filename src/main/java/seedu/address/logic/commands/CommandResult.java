package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * Enum describing the state of the UI to trigger actions
     */
    public enum UiState {
        ShowHelp,
        Exit,
        Inspect,
        HideNotes, ShowNotes, None
    }

    private final UiState state;

    private final String[] args;

    private final String feedbackToUser;

    /**
     * Constructs a {@code CommandResult} with the no additional state.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.state = UiState.None;
        this.args = new String[0];
    }

    /**
     * Constructs a {@code CommandResult} with the specified state.
     */
    public CommandResult(String feedbackToUser, UiState state, String... args) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.state = state;
        this.args = args;
    }

    /**
     * Constructs a {@code CommandResult} with the specified state.
     */
    public CommandResult(String feedbackToUser, UiState state) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.state = state;
        this.args = new String[0];
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
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
                && getUiState() == otherCommandResult.getUiState()
                && Arrays.equals(getArgs(), otherCommandResult.getArgs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, getUiState(), Arrays.hashCode(getArgs()));
    }

    public UiState getUiState() {
        return state;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "[Feedback: " + getFeedbackToUser() + "] [UI State: " + getUiState()
                + "] [" + Arrays.toString(getArgs()) + "]";
    }
}
