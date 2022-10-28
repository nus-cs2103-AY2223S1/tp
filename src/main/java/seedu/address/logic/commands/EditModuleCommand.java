package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;
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
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.exceptions.DuplicateModuleException;

/**
 * Edits the task with the specified index number in the displayed task list.
 */
public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "m " + COMMAND_WORD
            + ": Edits the module code, module name and module credit of the module identified "
            + "by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "[" + PREFIX_MOD_CODE + "MODULE CODE]* "
            + "[" + PREFIX_MOD_NAME + "MODULE NAME]* "
            + "[" + PREFIX_MOD_CREDIT + "MODULE CREDIT]*\n"
            + "Example: m " + COMMAND_WORD + " 1 "
            + PREFIX_MOD_CODE + "cs2040 "
            + PREFIX_MOD_NAME + "Data Structures and Algorithms "
            + PREFIX_MOD_CREDIT + "4";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s, Edited Name: %2$s, "
            + "Edited Credit: %3$s";
    public static final String MESSAGE_MODULE_NOT_EDITED = "The provided fields are the same as the current module";
    public static final String MESSAGE_NO_FIELDS_PROVIDED =
            String.format("Please provide at least one of the fields to edit: %1$sMODULE CODE, "
                    + "%2$sMODULE NAME, %3$sMODULE CREDIT", PREFIX_MOD_CODE, PREFIX_MOD_NAME, PREFIX_MOD_CREDIT);

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
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_MODULE_INDEX_TOO_LARGE, lastShownList.size() + 1));
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = moduleToEdit.edit(editModuleDescriptor);

        if (moduleToEdit.hasAllSameFields(editedModule)) {
            throw new CommandException(MESSAGE_MODULE_NOT_EDITED);
        }

        try {
            model.replaceModule(moduleToEdit, editedModule);

            boolean isModuleFieldUpdated = false;
            if (!moduleToEdit.isSameModule(editedModule)) {
                if (model.hasTaskWithModule(moduleToEdit)) {
                    model.updateModuleFieldForTask(moduleToEdit, editedModule);
                    isModuleFieldUpdated = true;
                }
                if (model.hasExamWithModule(moduleToEdit)) {
                    model.updateModuleFieldForExam(moduleToEdit, editedModule);
                    isModuleFieldUpdated = true;
                }
            }

            if (isModuleFieldUpdated) {
                return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule,
                        editedModule.getModuleName(), editedModule.getModuleCredit()) + "\n"
                        + "Warning! All the tasks and exams related to the initial module "
                        + "now have this edited Module as their new module.");
            }

        } catch (DuplicateModuleException e) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_MODULE);
        }

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule,
                editedModule.getModuleName(), editedModule.getModuleCredit()));
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
     * Stores the module code, module name and module credit to edit the target module with.
     * Each non-empty field value will replace the corresponding field value of the target module.
     */
    public static class EditModuleDescriptor {
        private ModuleCode moduleCode;
        private ModuleName moduleName;
        private ModuleCredit moduleCredit;

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, moduleName, moduleCredit);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public void setModuleName(ModuleName moduleName) {
            this.moduleName = moduleName;
        }

        public void setModuleCredit(ModuleCredit moduleCredit) {
            this.moduleCredit = moduleCredit;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public Optional<ModuleName> getModuleName() {
            return Optional.ofNullable(moduleName);
        }

        public Optional<ModuleCredit> getModuleCredit() {
            return Optional.ofNullable(moduleCredit);
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

            return moduleCode.equals(e.moduleCode)
                    && moduleName.equals(e.moduleName)
                    && moduleCredit.equals(e.moduleCredit);
        }
    }
}

