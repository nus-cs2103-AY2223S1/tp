package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
import seedu.address.model.tag.Tag;

/**
 * A command used to add or delete modules from an existing person identified using it's displayed index in the
 * address book.
 */
public class ModuleIndexCommand extends ModuleCommand {

    private final Index index;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * @param index of the person in the filtered person list to edit modules
     * @param editModuleDescriptor details to edit the person with
     */
    public ModuleIndexCommand(Index index, EditModuleDescriptor editModuleDescriptor) {
        super();
        requireNonNull(index);
        requireNonNull(editModuleDescriptor);

        this.index = index;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editModuleDescriptor);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             EditModuleDescriptor editModuleDescriptor) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Github github = personToEdit.getGithub();
        Set<Tag> tags = personToEdit.getTags();
        Set<CurrentModule> setCurrentModules = editModuleDescriptor.getCurrModules()
                .orElse(personToEdit.getCurrModules());
        Set<PreviousModule> setPreviousModules = editModuleDescriptor.getPrevModules()
                .orElse(personToEdit.getPrevModules());
        Set<PlannedModule> setPlannedModules = editModuleDescriptor.getPlanModules()
                .orElse(personToEdit.getPlanModules());

        return new Person(name, phone, email, address, github, tags, setCurrentModules, setPreviousModules,
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
        ModuleIndexCommand e = (ModuleIndexCommand) other;
        return index.equals(e.index)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }
}
