package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.PhoneContainsKeywordsPredicate;

/**
 * Finds and list all TAs in TutHub who have phone number matching the phone number being searched.
 */
public class FindByPhoneCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors whose phone number contains any of "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE + " 99999999";

    private final PhoneContainsKeywordsPredicate predicate;

    public FindByPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByPhoneCommand // instanceof handles null
                && predicate.equals(((FindByPhoneCommand) other).predicate)); // state check
    }
}
