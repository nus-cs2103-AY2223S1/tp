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
    /**
     * The application should show the list.
     */
    private final boolean list;
    /**
     * Specifies which group to list.
     */
    private final String listType;
    /**
     * Pop-up window for add command should be shown to the user.
     */
    private final boolean addByPopup;
    /**
     * Specifies which group to add.
     */
    private final String addType;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean list, String listType, boolean addByPopup, String addType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.list = list;
        this.listType = listType;
        this.addByPopup = addByPopup;
        this.addType = addType;
    }

    /**
     * Constructs a {@code CommandResult} for {@code HelpCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code HelpCommand}.
     */
    public static CommandResult createHelpCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, true, false, false, null, false, null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code ExitCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code ExitCommand}.
     */
    public static CommandResult createExitCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, false, true, false, null, false, null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code ListCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code ListCommand}.
     */
    public static CommandResult createListCommandResult(String feedbackToUser, String listType) {
        return new CommandResult(feedbackToUser, false, false, true, listType, false, null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code AddCommandWithPopup}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code AddCommandWithPopup}.
     */
    public static CommandResult createAddByPopupCommandResult(String feedbackToUser, String addType) {
        return new CommandResult(feedbackToUser, false, false, false, null, true, addType);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null, false, null);
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

    public boolean isList() {
        return list;
    }

    public boolean isAddByPopup() {
        return addByPopup;
    }

    public String getListType() {
        return listType;
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
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && list == otherCommandResult.list;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, list);
    }

}
