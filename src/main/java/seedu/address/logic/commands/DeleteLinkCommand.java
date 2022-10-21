package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK;

import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.task.Task;

/**
 * Deletes a link to an existing module in Plannit.
 */
public class DeleteLinkCommand extends Command {
    public static final String COMMAND_WORD = "delete-link";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Link: %2$s";

    public static final String MESSAGE_USAGE = "[" + COMMAND_WORD + "]: Deletes a link from a module "
            + "using its module code. ("
            + "A 'm/' flag should be appended to the front the module code; "
            + "A 'l/' flag should be appended to the front of each link)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "GEA1000 "
            + PREFIX_MODULE_LINK + "coursemology.org";

    public static final String MESSAGE_DELETE_LINK_SUCCESS = "Deleted link from module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one link must be deleted.";
    public static final String MESSAGE_MISSING_LINK = "This link does not currently exist"
            + " in the module with module code [";

    private final ModuleCode moduleCode;
    private final Set<Link> links;

    /**
     * Creates a DeleteLinkCommand for the deletion of links from a module
     * @param moduleCode module code of the module in which links will be deleted
     * @param links links to delete from the specified module
     */
    public DeleteLinkCommand(ModuleCode moduleCode, Set<Link> links) {
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
            throw new CommandException(String.format(MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                    moduleCode.getModuleCodeAsUpperCaseString()));
        }
        assert moduleToEdit != null;
        Module editedModule = createEditedModule(moduleToEdit, links);

        model.setModule(moduleToEdit, editedModule);
        return new CommandResult(String.format(MESSAGE_DELETE_LINK_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * without the links specified in {@code links}.
     */
    private static Module createEditedModule(Module moduleToEdit, Set<Link> linksToRemove)
            throws CommandException {
        assert moduleToEdit != null;

        ModuleCode moduleCode = moduleToEdit.getModuleCode();
        ModuleTitle moduleTitle = moduleToEdit.getModuleTitle();
        List<Task> moduleTasks = moduleToEdit.getTasks();
        Set<Link> originalLinksCopy = moduleToEdit.copyLinks();
        Set<Link> updatedLinks = removeLinksFromSet(originalLinksCopy, linksToRemove, moduleCode);
        return new Module(moduleCode, moduleTitle, moduleTasks, updatedLinks);
    }

    //Partial deletion of links is not supported
    //(where only some links from linksToRemove are found in originalLinksCopy)
    private static Set<Link> removeLinksFromSet(
            Set<Link> originalLinksCopy, Set<Link> linksToRemove, ModuleCode moduleCode)
            throws CommandException {
        for (Link link : linksToRemove) {
            if (!originalLinksCopy.contains(link)) {
                throw new CommandException(MESSAGE_MISSING_LINK
                        + moduleCode.getModuleCodeAsUpperCaseString() + "] [" + link.linkName + "]");
            }
        }
        originalLinksCopy.removeAll(linksToRemove);
        return originalLinksCopy;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteLinkCommand)) {
            return false;
        }

        // state check
        DeleteLinkCommand c = (DeleteLinkCommand) other;
        return moduleCode.equals(c.moduleCode) && links.equals(c.links);
    }
}
