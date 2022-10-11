package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;

/**
 * Go to the exact module in address book whose module code is the argument keywords.
 * Keyword matching is case insensitive.
 */
public class GoToCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Go to specified module (case-insensitive).\n"
            + "Example: " + COMMAND_WORD + " CS1231S";

    private final ModuleCodeMatchesKeywordPredicate predicate;

    public GoToCommand(ModuleCodeMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);

        if (model.getFilteredModuleList().isEmpty()) {
            return new CommandResult(Messages.MESSAGE_NO_SUCH_MODULE);
        }

        return new CommandResult(Messages.MESSAGE_MODULE_LISTED);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoToCommand // instanceof handles nulls
                && predicate.equals(((GoToCommand) other).predicate)); // state check
    }
}
