package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.ModuleContainsKeywordsPredicate;

/**
 * Finds and list all TAs in TutHub who are teaching the module code being searched.
 * Keyword matching is case insensitive.
 */
public class FindByModuleCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors who are teaching "
            + "the specified module code (case-insensitive) being searched"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE + " CS2040";

    private final ModuleContainsKeywordsPredicate predicate;

    public FindByModuleCommand(ModuleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorList(predicate);
        System.out.println(model.getSortedFilteredTutorList());
        return new CommandResult((
                String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getSortedFilteredTutorList().size())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByModuleCommand // instanceof handles nulls
                && predicate.equals(((FindByModuleCommand) other).predicate)); // state check
    }
}
