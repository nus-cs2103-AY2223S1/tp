package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVEMOD;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Module;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;

/**
 * A command used to add or delete modules from the user or a contact.
 */
public class ModuleCommand extends Command {

    public static final String COMMAND_WORD = "module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds or deletes modules from contact identified by "
            + "the index number used in the displayed person list or user.\n"
            + "Parameters: user / INDEX (must be a positive integer) "
            + "[" + PREFIX_CURRENTMOD + "MOD]..."
            + "[" + PREFIX_PREVIOUSMOD + "MOD]..."
            + "[" + PREFIX_PLANNEDMOD + "MOD]..."
            + "[" + PREFIX_REMOVEMOD + "MOD]...\n"
            + "Example: " + COMMAND_WORD + " user "
            + PREFIX_CURRENTMOD + "CS2101 "
            + PREFIX_CURRENTMOD + "CS2103T "
            + PREFIX_PREVIOUSMOD + "CS2030S "
            + PREFIX_PLANNEDMOD + "CS2109S "
            + PREFIX_PLANNEDMOD + "CS3230 "
            + PREFIX_REMOVEMOD + "ST2334";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Modules: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public ModuleCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.commitAddressBook();
        return new CommandResult("");
    }

    /**
     * Stores the details to edit the person's module list with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditModuleDescriptor {
        private Set<CurrentModule> currModules;
        private Set<PreviousModule> prevModules;
        private Set<PlannedModule> planModules;
        private Set<Module> modulesToRemove;

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code currModules, prevModules, planModules, modsToremove} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setCurrModules(toCopy.currModules);
            setPrevModules(toCopy.prevModules);
            setPlanModules(toCopy.planModules);
            setModulesToRemove(toCopy.modulesToRemove);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(currModules, prevModules, planModules, modulesToRemove);
        }

        public void setCurrModules(Set<CurrentModule> currModules) {
            this.currModules = (currModules != null) ? new HashSet<>(currModules) : null;
        }

        public Set<CurrentModule> getCurrModules() {
            return (currModules != null) ? this.currModules : new HashSet<>();
        }

        public void setPrevModules(Set<PreviousModule> prevModules) {
            this.prevModules = (prevModules != null) ? new HashSet<>(prevModules) : null;
        }

        public Set<PreviousModule> getPrevModules() {
            return (prevModules != null) ? this.prevModules : new HashSet<>();
        }

        public void setPlanModules(Set<PlannedModule> planModules) {
            this.planModules = (planModules != null) ? new HashSet<>(planModules) : null;
        }

        public Set<PlannedModule> getPlanModules() {
            return (planModules != null) ? this.planModules : new HashSet<>();
        }

        public void setModulesToRemove(Set<Module> modulesToRemove) {
            this.modulesToRemove = (modulesToRemove != null) ? new HashSet<>(modulesToRemove) : null;
        }

        public Set<Module> getModulesToRemove() {
            return (modulesToRemove != null) ? this.modulesToRemove : new HashSet<>();
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

            return getCurrModules().equals(e.getCurrModules())
                    && getPrevModules().equals(e.getPrevModules())
                    && getPlanModules().equals(e.getPlanModules())
                    && getModulesToRemove().equals(e.getModulesToRemove());
        }
    }
}
