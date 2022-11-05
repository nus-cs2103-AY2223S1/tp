package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.ExistingUser;

/**
 * Edits the details of the user in the address book.
 */
public class EditUserCommand extends EditCommand {

    public static final String MESSAGE_EDIT_USER_SUCCESS = "Edited User: %1$s";
    public static final String MESSAGE_NO_USER_TO_EDIT = "No user to be edited!";

    private final EditUserCommand.EditPersonDescriptor editUserDescriptor;
    private final EmptyUser emptyUser = new EmptyUser();

    /**
     * @param editUserDescriptor details to edit the user with
     */
    public EditUserCommand(EditUserCommand.EditPersonDescriptor editUserDescriptor) {
        super();
        requireNonNull(editUserDescriptor);

        this.editUserDescriptor = new EditUserCommand.EditPersonDescriptor(editUserDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasUser()) {
            throw new CommandException(MESSAGE_NO_USER_TO_EDIT);
        }

        assert !model.getUser().equals(emptyUser) : "user to edit should not be empty";

        ExistingUser userToEdit = (ExistingUser) model.getUser();
        ExistingUser editedUser = createEditedUser(userToEdit, editUserDescriptor);

        model.setUser(editedUser);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_EDIT_USER_SUCCESS, editedUser));
    }

    /**
     * Creates and returns a {@code User} with the details of {@code userToEdit}
     * edited with {@code editUserDescriptor}.
     */
    private static ExistingUser createEditedUser(ExistingUser userToEdit, EditPersonDescriptor editUserDescriptor) {
        assert userToEdit != null;

        Name updatedName = editUserDescriptor.getName().orElse(userToEdit.getName());
        Phone updatedPhone = editUserDescriptor.getPhone().orElse(userToEdit.getPhone());
        Email updatedEmail = editUserDescriptor.getEmail().orElse(userToEdit.getEmail());
        Address updatedAddress = editUserDescriptor.getAddress().orElse(userToEdit.getAddress());
        Github updatedGithub = editUserDescriptor.getGithub().orElse(userToEdit.getGithub());
        Set<CurrentModule> currentModules = userToEdit.getCurrModules();
        Set<PreviousModule> previousModules = userToEdit.getPrevModules();
        Set<PlannedModule> plannedModules = userToEdit.getPlanModules();

        ExistingUser updatedUser = new ExistingUser(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedGithub, currentModules, previousModules, plannedModules);
        updatedUser.setLessons(userToEdit.getLessons());
        return updatedUser;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserCommand)) {
            return false;
        }

        // state check
        EditUserCommand e = (EditUserCommand) other;
        return editUserDescriptor.equals(e.editUserDescriptor);
    }
}
