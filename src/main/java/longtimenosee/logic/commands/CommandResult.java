package longtimenosee.logic.commands;

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

    /** The application should display policies. */
    private final boolean showPolicy;

    /** The application should display clients. */
    private final boolean showClients;


    /** The application should display events. */
    private final boolean showEvent;

    private final boolean showIncome;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showPolicy, boolean showClient, boolean showEvent, boolean showIncome) {


        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showPolicy = showPolicy;
        this.showClients = showClient;
        this.showEvent = showEvent;
        this.showIncome = showIncome;

    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */

    public CommandResult(String feedbackToUser, boolean showPolicy, boolean showClients, boolean showEvent) {
        this(feedbackToUser, false, false, showPolicy, showClients, showEvent, false);

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

    public boolean isShowPolicy() {
        return showPolicy;
    }

    public boolean isShowClient() {
        return showClients;
    }
    public boolean isShowIncome() {
        return showIncome;
    }

    public boolean isShowEvent() {
        return showEvent;
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
        //Todo: Implement testing for showClients and showPolicy
//                && showClients == otherCommandResult.showClients
//                && showPolicy == otherCommandResult.showPolicy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showPolicy, showClients, showEvent, showIncome);
    }

}
