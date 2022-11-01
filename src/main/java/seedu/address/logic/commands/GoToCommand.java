package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonIsInModulePredicate;

/**
 * Go to the exact module in Plannit whose module code is the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class GoToCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Go to specified module (case-insensitive).\n"
            + "Example: " + COMMAND_WORD + " CS1231S";

    private final ModuleCodeMatchesKeywordPredicate predicate;
    private final ModuleCode moduleCode;

    /**
     * Constructs a {@code GoToCommand}, with a {@code Predicate} that filters {@code Module}s and a {@code ModuleCode}.
     *
     * @param predicate A predicate that filters {@code Module}.
     * @param moduleCode A valid {@code ModuleCode}.
     */
    public GoToCommand(ModuleCodeMatchesKeywordPredicate predicate, ModuleCode moduleCode) {
        this.predicate = predicate;
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            // Update Module List
            Module moduleToGoTo = model.getModuleUsingModuleCode(moduleCode, false);
            model.updateFilteredModuleList(predicate);

            // Verify that GoTo displays exactly one module.
            assert model.getFilteredModuleList().size() == 1;

            // Update Person List
            Predicate<Person> moduleContainsPersonPredicate = new PersonIsInModulePredicate(moduleToGoTo);
            model.updateFilteredPersonList(moduleContainsPersonPredicate);
            model.setHomeStatus(false);

            return new CommandResult(Messages.MESSAGE_MODULE_LISTED);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_MODULE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoToCommand // instanceof handles nulls
                && predicate.equals(((GoToCommand) other).predicate)
                && moduleCode.equals(((GoToCommand) other).moduleCode)); // state check
    }
}
