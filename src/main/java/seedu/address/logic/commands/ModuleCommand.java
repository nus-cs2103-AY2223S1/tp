package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVEMOD;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.CurrentModule;
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
    public static final String MESSAGE_MOD_NOT_IN_LIST = "Module to be removed is not in list!";

    public ModuleCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
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

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code currModules, prevModules, planModules} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setCurrModules(toCopy.currModules);
            setPrevModules(toCopy.prevModules);
            setPlanModules(toCopy.planModules);
        }

        public void setCurrModules(Set<CurrentModule> currModules) {
            this.currModules = (currModules != null) ? new HashSet<>(currModules) : null;
        }

        public Optional<Set<CurrentModule>> getCurrModules() {
            return (currModules != null) ? Optional.of(Collections.unmodifiableSet(currModules)) : Optional.empty();
        }

        public void setPrevModules(Set<PreviousModule> prevModules) {
            this.prevModules = (prevModules != null) ? new HashSet<>(prevModules) : null;
        }

        public Optional<Set<PreviousModule>> getPrevModules() {
            return (prevModules != null) ? Optional.of(Collections.unmodifiableSet(prevModules)) : Optional.empty();
        }

        public void setPlanModules(Set<PlannedModule> planModules) {
            this.planModules = (planModules != null) ? new HashSet<>(planModules) : null;
        }

        public Optional<Set<PlannedModule>> getPlanModules() {
            return (planModules != null) ? Optional.of(Collections.unmodifiableSet(planModules)) : Optional.empty();
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
                    && getPlanModules().equals(e.getPlanModules());
        }
    }
}
