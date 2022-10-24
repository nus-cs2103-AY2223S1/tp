package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.COMMAND_IDENTIFIER_PERSON;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose fields contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_TYPE = "find";
    public static final String COMMAND_WORD = COMMAND_TYPE + COMMAND_IDENTIFIER_PERSON;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice 97018232 cs2100";

    private final List<String> keywords;

    public FindPersonCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //filter by name first
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(keywords));
        int current_filter = 1;
        //while filtered list is still empty, keep trying new field
        while(current_filter < 4) {
            if(model.getFilteredPersonList().size() != 0) {
                break;
            } else if(current_filter == 1) {
                model.updateFilteredPersonList(new PersonModuleCodeContainsKeywordsPredicate(keywords));
                current_filter++;
            } else if(current_filter == 2){
                model.updateFilteredPersonList(new EmailContainsKeywordsPredicate(keywords));
                current_filter++;
            } else {
                model.updateFilteredPersonList(new PhoneContainsKeywordsPredicate(keywords));
                current_filter++;
            }
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && keywords.equals(((FindPersonCommand) other).keywords)); // state check
    }
}
