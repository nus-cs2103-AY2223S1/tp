package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_TAG;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.TagContainsKeywordsPredicate;

/**
 * Finds and list all TAs in TutHub who have tags matching the keywords searched.
 * Keyword matching is case insensitive.
 */
public class FindByTagCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors with tags matching "
            + "the keywords (case-insensitive) being searched"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + " friends";

    private final TagContainsKeywordsPredicate predicate;

    public FindByTagCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorList(predicate);
        System.out.println(model.getFilteredTutorList());
        return new CommandResult((
                String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByTagCommand // instanceof handles nulls
                && predicate.equals(((FindByTagCommand) other).predicate)); // state check
    }
}
