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
    * The application should check a selected object.
     */
    private final boolean check;

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
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean list, String listType,
                         boolean addByPopup, String addType, boolean check, String checkType, Index index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.list = list;
        this.listType = listType;
        this.addByPopup = addByPopup;
        this.addType = addType;
        this.check = check;
        this.checkType = checkType;
        this.index = index;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null, false, null, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code HelpCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code HelpCommand}.
     */
    public static CommandResult createHelpCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, true, false, false, null,
                false, null, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code ExitCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code ExitCommand}.
     */
    public static CommandResult createExitCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, false, true, false, null,
                false, null, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code ListCommand}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code ListCommand}.
     */
    public static CommandResult createListCommandResult(String feedbackToUser, String listType) {
        return new CommandResult(feedbackToUser, false, false, true, listType,
                false, null, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} for {@code AddCommandWithPopup}.
     * @param feedbackToUser Feedback to the user.
     * @return A {@code CommandResult} for {@code AddCommandWithPopup}.
     */
    public static CommandResult createAddByPopupCommandResult(String feedbackToUser, String addType) {
        return new CommandResult(feedbackToUser, false, false, false, null,
                true, addType, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with check, checkType and Index fields specified.
     */
    public static CommandResult createCheckCommandResult(String feedbackToUser, String checkType, Index index) {
        return new CommandResult(feedbackToUser, false, false, false, null,
                false, null, true, checkType, index);
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

    public boolean isCheck() {
        return check;
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
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && list == otherCommandResult.list
                && ((listType == null && otherCommandResult.listType == null)
                    || (listType != null && listType.equals(otherCommandResult.listType)))
                && addByPopup == otherCommandResult.addByPopup
                && ((addType == null && otherCommandResult.addType == null)
                || (addType != null && addType.equals(otherCommandResult.addType)))
                && check == otherCommandResult.check
                && ((checkType == null && otherCommandResult.checkType == null)
                    || (checkType != null && checkType.equals(otherCommandResult.checkType)))
                && ((index == null && otherCommandResult.index == null)
                    || (index != null && index.equals(otherCommandResult.index)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, list, listType,
                addByPopup, addType, check, checkType, index);
    }

}
