package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.task.Task;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

/**
 * Adds a link to an existing module in Plannit.
 */
public class AddLinkCommand extends Command {

    public static final String COMMAND_WORD = "add-link";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Link: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a link to the module identified "
            + "by the index number used in the displayed module list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE_LINK + "coursemology.org";

    public static final String MESSAGE_ADD_LINK_SUCCESS = "Added link to module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one link must be added.";
    public static final String MESSAGE_DUPLICATE_LINK = "The link/s already exists in the module index ";

    private final Index index;
    private final Set<Link> links;

    /**
     * Creates an AddLinkCommand for the addition of links to a module
     * @param index index of the module based on the filtered module list
     * @param links links to add to the module
     */
    public AddLinkCommand(Index index, Set<Link> links) {
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
        Module editedModule = createEditedModule(moduleToEdit, links);

        if (moduleToEdit.getLinks().equals(editedModule.getLinks())) {
            throw new CommandException(MESSAGE_DUPLICATE_LINK + index.getOneBased());
        }

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_ADD_LINK_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * appended with {@code links}.
     */
    private static Module createEditedModule(Module moduleToEdit, Set<Link> linksToAdd) {
        assert moduleToEdit != null;

        ModuleCode moduleCode = moduleToEdit.getModuleCode();
        ModuleTitle moduleTitle = moduleToEdit.getModuleTitle();
        Set<Task> moduleTasks = moduleToEdit.getTasks();
        Set<Link> updatedLinks = moduleToEdit.copyLinks();
        updatedLinks.addAll(linksToAdd);
        return new Module(moduleCode, moduleTitle, moduleTasks, updatedLinks);
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
        return index.equals(c.index) && links.equals(c.links);
    }
}
