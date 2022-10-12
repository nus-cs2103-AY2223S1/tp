package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.task.Task;

/**
 * Deletes a link to an existing module in Plannit.
 */
public class DeleteLinkCommand extends Command {
    public static final String COMMAND_WORD = "delete-link";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Link: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a link from the module identified "
            + "by the index number used in the displayed module list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE_LINK + "coursemology.org";

    public static final String MESSAGE_DELETE_LINK_SUCCESS = "Deleted link from module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one link must be deleted.";
    public static final String MESSAGE_MISSING_LINK = "This link does not currently exist in module index [";

    private final Index index;
    private final Set<Link> links;

    /**
     * Creates a DeleteLinkCommand for the deletion of links from a module
     * @param index index of the module based on the filtered module list
     * @param links links to delete from the module
     */
    public DeleteLinkCommand(Index index, Set<Link> links) {
        requireAllNonNull(index, links);
        this.index = index;
        this.links = links;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Module> lastShownList = model.getFilteredModuleList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = createEditedModule(moduleToEdit, links, index);

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_DELETE_LINK_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * without the links specified in {@code links}.
     */
    private static Module createEditedModule(Module moduleToEdit, Set<Link> linksToRemove, Index index)
            throws CommandException {
        assert moduleToEdit != null;

        ModuleCode moduleCode = moduleToEdit.getModuleCode();
        ModuleTitle moduleTitle = moduleToEdit.getModuleTitle();
        List<Task> moduleTasks = moduleToEdit.getTasks();
        Set<Link> originalLinksCopy = moduleToEdit.copyLinks();
        Set<Link> updatedLinks = removeLinksFromSet(originalLinksCopy, linksToRemove, index);
        return new Module(moduleCode, moduleTitle, moduleTasks, updatedLinks);
    }

    //Partial deletion of links is not supported
    //(where only some links from linksToRemove are found in originalLinksCopy)
    private static Set<Link> removeLinksFromSet(Set<Link> originalLinksCopy, Set<Link> linksToRemove, Index index)
            throws CommandException {
        for (Link link : linksToRemove) {
            if (!originalLinksCopy.contains(link)) {
                throw new CommandException(MESSAGE_MISSING_LINK + index.getOneBased() + "] [" + link.linkName + "]");
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
        return index.equals(c.index) && links.equals(c.links);
    }
}
