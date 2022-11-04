package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_URL;

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
import seedu.address.model.person.Person;

/**
 * Add link(s) to an existing module in Plannit.
 */
public class AddLinkCommand extends Command {

    public static final String COMMAND_WORD = "add-link";
    public static final String MESSAGE_USAGE = "[" + COMMAND_WORD + "]: Add link(s) to a module "
            + "using its module code, link URL, and a user-defined alias.\n"
            + "A 'm/' flag should be appended to the front the module code;\n"
            + "a 'l/' flag should be appended to the front of each link URL;\n"
            + "a 'la/' flag should be appended to the front of each link alias.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "GEA1000 "
            + PREFIX_MODULE_LINK_ALIAS + "coursemo " + PREFIX_MODULE_LINK_URL + "coursemology.org";

    public static final String MESSAGE_ADD_LINK_SUCCESS = "Successfully added link(s) to module code [%1$s]!";
    public static final String MESSAGE_NOT_EDITED = "At least one link url and alias must be added.";
    public static final String MESSAGE_DUPLICATE_LINK_ALIAS = "The link alias [%1$s] already exists"
            + " in the module with module code [%2$s].";
    public static final String MESSAGE_DUPLICATE_LINK_URL = "The link URL [%1$s] already exists"
            + " in the module with module code [%2$s] under the alias [%3$s].";

    private final ModuleCode moduleCode;
    private final Set<Link> links;

    /**
     * Creates an AddLinkCommand for the addition of links to a module
     * @param moduleCode module code of the module in which links will be added
     * @param links links to add to the specified module
     */
    public AddLinkCommand(ModuleCode moduleCode, Set<Link> links) {
        requireAllNonNull(moduleCode, links);
        this.moduleCode = moduleCode;
        this.links = links;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Module moduleToEdit = null;
        try {
            moduleToEdit =
                    model.getModuleUsingModuleCode(moduleCode, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                    moduleCode.getModuleCodeAsUpperCaseString()));
        }
        assert moduleToEdit != null;
        Module editedModule = createEditedModule(moduleToEdit, links);

        model.setModule(moduleToEdit, editedModule);
        return new CommandResult(String.format(MESSAGE_ADD_LINK_SUCCESS,
                moduleCode.getModuleCodeAsUpperCaseString()));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit} appended with {@code linksToAdd}.
     * Partial addition of links is not allowed, which means that none of the links wil be added if there exists
     * a link that contains either a pre-existing link alias or URL in {@code moduleToEdit}.
     */
    private static Module createEditedModule(Module moduleToEdit, Set<Link> linksToAdd) throws CommandException {
        assert moduleToEdit != null;

        ModuleCode moduleCode = moduleToEdit.getModuleCode();
        ModuleTitle moduleTitle = moduleToEdit.getModuleTitle();
        List<Task> moduleTasks = moduleToEdit.getTasks();
        Set<Link> updatedLinks = moduleToEdit.copyLinks();
        Set<Person> modulePersons = moduleToEdit.getPersons();

        for (Link newLink : linksToAdd) {
            boolean isExistingLinkAlias = updatedLinks.stream()
                    .anyMatch(link -> (link.isSameLinkAlias(newLink)));
            if (isExistingLinkAlias) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_LINK_ALIAS,
                        newLink.linkAlias, moduleCode.getModuleCodeAsUpperCaseString()));
            }
            boolean isExistingLinkUrl = updatedLinks.stream()
                    .anyMatch(link -> (link.isSameLinkUrlIgnoreProtocol(newLink)));
            if (isExistingLinkUrl) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_LINK_URL,
                        newLink.linkUrl, moduleCode.getModuleCodeAsUpperCaseString(), newLink.linkAlias));
            }
            updatedLinks.add(newLink);
        }
        return new Module(moduleCode, moduleTitle, moduleTasks, updatedLinks, modulePersons);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLinkCommand)) {
            return false;
        }

        // state check
        AddLinkCommand c = (AddLinkCommand) other;
        return moduleCode.equals(c.moduleCode) && links.equals(c.links);
    }
}
