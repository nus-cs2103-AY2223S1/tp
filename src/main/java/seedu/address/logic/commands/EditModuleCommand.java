package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exceptions.DuplicateModuleException;

/**
 * Edits the task with the specified index number in the displayed task list.
 */
public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "editmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the module code of the module identified "
            + "by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MOD_CODE + "MODULE CODE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MOD_CODE + "cs2040 ";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_MODULE_NOT_EDITED = "The provided fields are the same as the current module";
    public static final String MESSAGE_NO_FIELDS_PROVIDED = "At module name to edit must be provided.";

    private final Index index;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * @param index of the task in the filtered module list to edit
     * @param editModuleDescriptor details to edit the module with
     */
    public EditModuleCommand(Index index, EditModuleDescriptor editModuleDescriptor) {
        requireAllNonNull(index, editModuleDescriptor);

        this.index = index;
        this.editModuleDescriptor = editModuleDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = moduleToEdit.edit(editModuleDescriptor);

        if (model.hasTaskWithModule(moduleToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_EDIT_AS_TIED_WITH_TASK);
        }

        if (moduleToEdit.isSameModuleCode(editedModule)) {
            throw new CommandException(MESSAGE_MODULE_NOT_EDITED);
        }

        try {
            model.replaceModule(moduleToEdit, editedModule);
        } catch (DuplicateModuleException e) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_MODULE);
        }

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditModuleCommand)) {
            return false;
        }

        // state check
        EditModuleCommand e = (EditModuleCommand) other;
        return index.equals(e.index)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the name to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditModuleDescriptor {
        private ModuleCode moduleCode;

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;

            return moduleCode.equals(e.moduleCode);
        }
    }
}

