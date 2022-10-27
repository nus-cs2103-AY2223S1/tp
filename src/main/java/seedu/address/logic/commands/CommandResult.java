package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isHelpShown;

    /** The application should exit. */
    private final boolean isExit;

    /**
     * The application should show the list.
     */
    private final boolean isList;

    /**
     * Specifies which group to list.
     */
    private final String listType;

    /**
     * Pop-up window for add command should be shown to the user.
     */
    private final boolean isAddedByPopup;

    /**
     * Specifies which group to add.
     */
    private final String addType;

    /**
    * The application should check a selected object.
     */
    private final boolean isCheck;

    /**
     * Specifies which group of objects to check.
     */
    private final String checkType;

    /**
     * Specifies the index of object to check
     */
    private final Index index;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser,
                         boolean isHelpShown,
                         boolean isExit,
                         boolean isList,
                         String listType,
                         boolean isAddedByPopup,
                         String addType,
                         boolean isCheck,
                         String checkType,
                         Index index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isHelpShown = isHelpShown;
        this.isExit = isExit;
        this.isList = isList;
        this.listType = listType;
        this.isAddedByPopup = isAddedByPopup;
        this.addType = addType;
        this.isCheck = isCheck;
        this.checkType = checkType;
        this.index = index;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean isHelpShown, boolean isExit) {
        this(feedbackToUser,
                isHelpShown,
                isExit,
                false,
                null,
                false,
                null,
                false,
                null,
                null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean isHelpShown, String listType) {
        this(feedbackToUser,
                isHelpShown,
                false,
                true,
                listType,
                false,
                null,
                false,
                null,
                null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser,
                false,
                false,
                false,
                null,
                false,
                null,
                false,
                null,
                null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code HelpCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code HelpCommand}.
     */
    public static CommandResult createHelpCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser,
                true,
                false,
                false,
                null,
                false,
                null,
                false,
                null,
                null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code ExitCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code ExitCommand}.
     */
    public static CommandResult createExitCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser,
                false,
                true,
                false,
                null,
                false,
                null,
                false,
                null,
                null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code ListCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code ListCommand}.
     */
    public static CommandResult createListCommandResult(String feedbackToUser, String listType) {
        return new CommandResult(feedbackToUser,
                false,
                false,
                true,
                listType,
                false,
                null,
                false,
                null,
                null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code AddCommandWithPopup}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code AddCommandWithPopup}.
     */
    public static CommandResult createAddByPopupCommandResult(String feedbackToUser, String addType) {
        return new CommandResult(feedbackToUser,
                false,
                false,
                false,
                null,
                true,
                addType,
                false,
                null,
                null);
    }

    /**
     * Constructs a {@code CommandResult} with check, checkType and Index fields specified.
     */
    public static CommandResult createCheckCommandResult(String feedbackToUser, String checkType, Index index) {
        return new CommandResult(feedbackToUser,
                false,
                false,
                false,
                null,
                false,
                null,
                true,
                checkType,
                index);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isHelpShown() {
        return isHelpShown;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isList() {
        return isList;
    }

    public boolean isAddedByPopup() {
        return isAddedByPopup;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public String getCheckType() {
        return checkType;
    }

    public Index getIndex() {
        return index;
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
                && isHelpShown == otherCommandResult.isHelpShown
                && isExit == otherCommandResult.isExit
                && isList == otherCommandResult.isList
                && ((listType == null && otherCommandResult.listType == null)
                    || (listType != null && listType.equals(otherCommandResult.listType)))
                && isAddedByPopup == otherCommandResult.isAddedByPopup
                && ((addType == null && otherCommandResult.addType == null)
                || (addType != null && addType.equals(otherCommandResult.addType)))
                && isCheck == otherCommandResult.isCheck
                && ((checkType == null && otherCommandResult.checkType == null)
                    || (checkType != null && checkType.equals(otherCommandResult.checkType)))
                && ((index == null && otherCommandResult.index == null)
                    || (index != null && index.equals(otherCommandResult.index)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser,
                isHelpShown,
                isExit,
                isList,
                listType,
                isAddedByPopup,
                addType,
                isCheck,
                checkType,
                index);
    }

}
