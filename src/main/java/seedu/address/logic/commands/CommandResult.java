package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final Person personToShow;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** New Book should be created for the user. */
    private final boolean showNewBook;

    /** Application should swap between address books */
    private final boolean showSwap;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, Person personToShow, boolean showHelp, boolean showNewBook,
                         boolean swap, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.personToShow = personToShow;
        this.showHelp = showHelp;
        this.showNewBook = showNewBook;
        this.showSwap = swap;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code personToShow},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Person personToShow) {
        this(feedbackToUser, personToShow, false, false, false, false);
    }

    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, null, showHelp, false, false, exit);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Person getPersonToShow() {
        return personToShow;
    }

    public boolean hasPersonToShow() {
        return personToShow != null;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isNewBook() {
        return showNewBook;
    }

    public boolean isSwap() {
        return showSwap;
    }

    public boolean isExit() {
        return exit;
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
