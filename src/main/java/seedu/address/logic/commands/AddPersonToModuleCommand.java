package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import seedu.address.model.person.exceptions.PersonAlreadyExistsInModuleException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Adds a person to an existing module in Plannit.
 */
public class AddPersonToModuleCommand extends Command {

    public static final String COMMAND_WORD = "add-person-to-module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person (identified by name)"
            + "to a module (identified by module code).\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_NAME + "John Doe";

    public static final String MESSAGE_ADD_PERSON_TO_MODULE_SUCCESS = "Added person %2$s to module %1$s";
    public static final String MESSAGE_PERSON_ALREADY_EXISTS_IN_MODULE = "The module %1$s already "
            + "has the person %2$s tagged to it.";

    private final ModuleCode targetModuleCode;
    private final Name targetName;

    /**
     * Constructs an {@code AddPersonToModuleCommand} with a module code and person name.
     *
     * @param targetModuleCode The module code of the module to add the person to.
     * @param targetName The person to be added to the module.
     */
    public AddPersonToModuleCommand(ModuleCode targetModuleCode, Name targetName) {
        requireAllNonNull(targetModuleCode, targetName);
        this.targetModuleCode = targetModuleCode;
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Module moduleToAddPersonTo = null;
        Person personToAddToModule = null;
        try {
            moduleToAddPersonTo = model.getModuleUsingModuleCode(targetModuleCode, true);
            personToAddToModule = model.getPersonUsingName(targetName, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_MODULE);
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_PERSON);
        }

        assert moduleToAddPersonTo != null;
        assert personToAddToModule != null;
        Module moduleWithPersonAdded;
        try {
            moduleWithPersonAdded = createModuleWithAddedPerson(moduleToAddPersonTo, personToAddToModule);
        } catch (PersonAlreadyExistsInModuleException e) {
            throw new CommandException(String.format(MESSAGE_PERSON_ALREADY_EXISTS_IN_MODULE,
                    moduleToAddPersonTo.getModuleCode(),
                    personToAddToModule.getName()));
        }

        assert moduleWithPersonAdded != null;
        model.setModule(moduleToAddPersonTo, moduleWithPersonAdded);
        return new CommandResult(String.format(MESSAGE_ADD_PERSON_TO_MODULE_SUCCESS,
                moduleToAddPersonTo.getModuleCode(),
                personToAddToModule.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonToModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((AddPersonToModuleCommand) other).targetModuleCode)) // state check
                && targetName.equals(((AddPersonToModuleCommand) other).targetName);
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToAddPersonTo}
     * and the person {@code personToAddInModule} added.
     */
    private static Module createModuleWithAddedPerson(
            Module moduleToAddPersonTo, Person personToAddInModule) {
        assert moduleToAddPersonTo != null;
        assert personToAddInModule != null;

        ModuleCode moduleCode = moduleToAddPersonTo.getModuleCode();
        ModuleTitle moduleTitle = moduleToAddPersonTo.getModuleTitle();
        List<Task> moduleTasks = moduleToAddPersonTo.getTasks();
        Set<Link> moduleLinks = moduleToAddPersonTo.getLinks();
        Set<Person> modulePersons = new HashSet<>(moduleToAddPersonTo.getPersons());

        if (!modulePersons.add(personToAddInModule)) {
            throw new PersonAlreadyExistsInModuleException();
        }

        return new Module(moduleCode, moduleTitle, moduleTasks,
                moduleLinks, modulePersons);
    }
}
