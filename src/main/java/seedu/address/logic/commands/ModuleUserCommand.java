package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
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
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.ExistingUser;

/**
 * A command used to add or delete modules from the user.
 */
public class ModuleUserCommand extends ModuleCommand {

    public static final String MESSAGE_NO_USER_TO_EDIT = "No user to be edited!";

    private final EditModuleDescriptor editModuleDescriptor;
    private final EmptyUser emptyUser = new EmptyUser();

    /**
     * @param editModuleDescriptor details to edit the person with
     */
    public ModuleUserCommand(EditModuleDescriptor editModuleDescriptor) {
        super();
        requireNonNull(editModuleDescriptor);

        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (!model.hasUser()) {
            throw new CommandException(MESSAGE_NO_USER_TO_EDIT);
        }

        assert !model.getUser().equals(emptyUser) : "user to edit should not be empty";

        ExistingUser userToEdit = (ExistingUser) model.getUser();
        ExistingUser editedUser = createEditedUser(userToEdit, editModuleDescriptor);

        model.setUser(editedUser);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedUser));
    }

    /**
     * Creates and returns a {@code User} with the details of {@code userToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static ExistingUser createEditedUser(ExistingUser userToEdit,
                                                 EditModuleDescriptor editModuleDescriptor) {
        assert userToEdit != null;

        Name name = userToEdit.getName();
        Phone phone = userToEdit.getPhone();
        Email email = userToEdit.getEmail();
        Address address = userToEdit.getAddress();
        Github github = userToEdit.getGithub();
        Set<CurrentModule> setCurrentModules = editModuleDescriptor.getCurrModules()
                .orElse(userToEdit.getCurrModules());
        Set<PreviousModule> setPreviousModules = editModuleDescriptor.getPrevModules()
                .orElse(userToEdit.getPrevModules());
        Set<PlannedModule> setPlannedModules = editModuleDescriptor.getPlanModules()
                .orElse(userToEdit.getPlanModules());

        return new ExistingUser(name, phone, email, address, github, setCurrentModules, setPreviousModules,
                setPlannedModules);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleIndexCommand)) {
            return false;
        }

        // state check
        ModuleUserCommand e = (ModuleUserCommand) other;
        return editModuleDescriptor.equals(e.editModuleDescriptor);
    }

}
