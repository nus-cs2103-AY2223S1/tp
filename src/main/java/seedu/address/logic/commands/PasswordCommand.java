package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sets or updates the FinBook password.
 */
public class PasswordCommand extends Command {

    public static final String COMMAND_WORD = "password";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a new password or updates the existing password.\n"
            + "Parameters: "
            + "[" + PREFIX_OLD_PASSWORD + "OLDPASSWORD] "
            + PREFIX_NEW_PASSWORD + "NEWPASSWORD\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + PREFIX_NEW_PASSWORD + "foobar\n"
            + "- Sets the password to \"foobar\", given that a password has not yet been set\n"
            + COMMAND_WORD + " " + PREFIX_OLD_PASSWORD + "foobar " + PREFIX_NEW_PASSWORD + "barfoo"
            + "- Updates the password to \"barfoo\", given that the current password is \"foobar\"\n";

    public static final String MESSAGE_SET_PASSWORD_SUCCESS = "Successfully set new FinBook password.";
    public static final String MESSAGE_UPDATE_PASSWORD_SUCCESS = "Successfully updated FinBook password.";
    public static final String MESSAGE_PASSWORD_ALREADY_SET = "Password has already been set.";
    public static final String MESSAGE_INCORRECT_PASSWORD = "Incorrect old password.";
    public static final String MESSAGE_PASSWORD_UPDATE_ERROR =
            "Error encountered while updating FinBook password: \n%1$s";

    private boolean isUpdatePassword;
    private String oldPassword;
    private String newPassword;

    /**
     * @param newPassword New password to be set
     */
    public PasswordCommand(String newPassword) {
        requireNonNull(newPassword);
        this.isUpdatePassword = false;
        this.oldPassword = "";
        this.newPassword = newPassword;
    }

    /**
     * @param oldPassword Old password to be updated
     * @param newPassword New password to be set
     */
    public PasswordCommand(String oldPassword, String newPassword) {
        requireNonNull(oldPassword);
        requireNonNull(newPassword);
        this.isUpdatePassword = true;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            if (isUpdatePassword) {
                if (!model.isPasswordSet()) {
                    model.updatePassword("", newPassword);
                    return new CommandResult(MESSAGE_SET_PASSWORD_SUCCESS);
                } else if (model.isPasswordCorrect(oldPassword)) {
                    model.updatePassword(oldPassword, newPassword);
                    return new CommandResult(MESSAGE_UPDATE_PASSWORD_SUCCESS);
                } else {
                    throw new CommandException(MESSAGE_INCORRECT_PASSWORD);
                }
            } else {
                if (!model.isPasswordSet()) {
                    model.updatePassword("", newPassword);
                    return new CommandResult(MESSAGE_SET_PASSWORD_SUCCESS);
                } else {
                    throw new CommandException(MESSAGE_PASSWORD_ALREADY_SET);
                }
            }
        } catch (Exception e) {
            throw new CommandException(String.format(MESSAGE_PASSWORD_UPDATE_ERROR, e.getMessage()));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PasswordCommand)) {
            return false;
        }

        // state check
        PasswordCommand e = (PasswordCommand) other;
        return isUpdatePassword == e.isUpdatePassword
                && oldPassword.equals(e.oldPassword) && newPassword.equals(e.newPassword);
    }
}
