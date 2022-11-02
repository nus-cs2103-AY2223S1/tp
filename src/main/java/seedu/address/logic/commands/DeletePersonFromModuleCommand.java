package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.Task;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonIsInModulePredicate;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person from an existing module in Plannit.
 */
public class DeletePersonFromModuleCommand extends Command {

    public static final String COMMAND_WORD = "delete-person-from-module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a person (identified by name)"
            + " belonging in a module (identified by module code).\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_NAME + "John Doe";

    public static final String MESSAGE_DELETE_PERSON_FROM_MODULE_SUCCESS = "Deleted person from: %1$s";
    public static final String MESSAGE_NO_SUCH_PERSON_IN_MODULE = "The module %1$s does not have the "
            + "person %2$s tagged to it.";

    private final ModuleCode targetModuleCode;
    private final Name targetName;

    /**
     * Constructs a {@code DeletePersonFromModuleCommand} with a module code and person name.
     *
     * @param targetModuleCode The module code of the module to delete the person from.
     * @param targetName The person to be deleted in the module.
     */
    public DeletePersonFromModuleCommand(ModuleCode targetModuleCode, Name targetName) {
        requireAllNonNull(targetModuleCode, targetName);
        this.targetModuleCode = targetModuleCode;
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Module moduleToDeletePersonFrom = null;
        Person personToDeleteInModule = null;
        try {
            moduleToDeletePersonFrom = model.getModuleUsingModuleCode(targetModuleCode, true);
            personToDeleteInModule = model.getPersonUsingName(targetName, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_MODULE);
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_PERSON);
        }

        assert moduleToDeletePersonFrom != null;
        assert personToDeleteInModule != null;
        Module moduleWithPersonDeleted = null;
        try {
            moduleWithPersonDeleted = createModuleWithDeletedPerson(moduleToDeletePersonFrom, personToDeleteInModule);
        } catch (PersonNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_PERSON_IN_MODULE,
                    moduleToDeletePersonFrom.getModuleCode(),
                    personToDeleteInModule.getName()));
        }

        assert moduleWithPersonDeleted != null;
        model.setModule(moduleToDeletePersonFrom, moduleWithPersonDeleted);

        Boolean isNotHome = !model.getHomeStatusAsBoolean();
        if (isNotHome) {
            Predicate<Person> moduleContainsPersonPredicate = new PersonIsInModulePredicate(moduleWithPersonDeleted);
            model.updateFilteredPersonList(moduleContainsPersonPredicate);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_FROM_MODULE_SUCCESS,
                moduleToDeletePersonFrom.getModuleCode(),
                personToDeleteInModule.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonFromModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((DeletePersonFromModuleCommand) other).targetModuleCode)) // state check
                && targetName.equals(((DeletePersonFromModuleCommand) other).targetName);
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToDeletePersonFrom}
     * and the person {@code personInModuleToBeDeleted} deleted.
     */
    private static Module
            createModuleWithDeletedPerson(Module moduleToDeletePersonFrom, Person personToDeleteInModule) {
        assert moduleToDeletePersonFrom != null;
        assert personToDeleteInModule != null;

        ModuleCode moduleCode = moduleToDeletePersonFrom.getModuleCode();
        ModuleTitle moduleTitle = moduleToDeletePersonFrom.getModuleTitle();
        List<Task> moduleTasks = moduleToDeletePersonFrom.getTasks();
        Set<Link> moduleLinks = moduleToDeletePersonFrom.getLinks();
        Set<Person> modulePersons = moduleToDeletePersonFrom.copyPersons();

        if (!modulePersons.remove(personToDeleteInModule)) {
            throw new PersonNotFoundException();
        }

        return new Module(moduleCode, moduleTitle, moduleTasks,
                moduleLinks, modulePersons);
    }
}
