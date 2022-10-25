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
                         boolean check, String checkType, Index index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.list = list;
        this.listType = listType;
        this.check = check;
        this.checkType = checkType;
        this.index = index;
    }


    /**
     * Constructs a {@code CommandResult} with showHelp and showExit fields specified.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.list = false;
        this.listType = null;
        this.check = false;
        this.checkType = null;
        this.index = null;
    }

    /**
     * Constructs a {@code CommandResult} with list and listType fields specified.
     */
    public CommandResult(String feedbackToUser, boolean list, String listType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.list = list;
        this.listType = listType;
        this.check = false;
        this.checkType = null;
        this.index = null;
    }

    /**
     * Constructs a {@code CommandResult} with check, checkType and Index fields specified.
     */
    public CommandResult(String feedbackToUser, boolean check, String checkType, Index index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.list = false;
        this.listType = null;
        this.check = check;
        this.checkType = checkType;
        this.index = index;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null, false, null, null);
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
                && check == otherCommandResult.check
                && ((checkType == null && otherCommandResult.checkType == null)
                    || (checkType != null && checkType.equals(otherCommandResult.checkType)))
                && ((index == null && otherCommandResult.index == null)
                    || (index != null && index.equals(otherCommandResult.index)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, list, listType, check, checkType, index);
    }

}
