package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleContainsKeywordPredicate;

/**
 * Finds and list all TAs in TutHub who are teaching the module code being searched.
 * Keyword matching is case insensitive.
 */
public class FindByModuleCommand extends Command {

    public static final String COMMAND_WORD = "findbymodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors who are teaching "
            + "the specified module code (case-insensitive) being searched"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2040";

    private final ModuleContainsKeywordPredicate predicate;

    public FindByModuleCommand(ModuleContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        System.out.println(model.getFilteredPersonList());
        return new CommandResult((
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByModuleCommand // instanceof handles nulls
                && predicate.equals(((FindByModuleCommand) other).predicate)); // state check
    }
}
