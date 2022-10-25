package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.COMMAND_IDENTIFIER_MODULE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.LectureDetailsContainsKeywordsPredicate;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.TutorialDetailsContainsKeywordsPredicate;

/**
 * Finds and lists all modules in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindModuleCommand extends Command {

    public static final String COMMAND_TYPE = "find";
    public static final String COMMAND_WORD = COMMAND_TYPE + COMMAND_IDENTIFIER_MODULE;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Modules whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2100 Friday, 2pm";

    private final List<String> keywords;

    public FindModuleCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //filter by name first
        model.updateFilteredModuleList(new ModuleCodeContainsKeywordsPredicate(keywords));
        int current_Filter = 1;
        //while filtered list is still empty, keep trying new field
        while (current_Filter < 3) {
            if (model.getFilteredModuleList().size() != 0) {
                break;
            } else if (current_Filter == 1) {
                model.updateFilteredModuleList(new LectureDetailsContainsKeywordsPredicate(keywords));
                current_Filter++;
            } else {
                model.updateFilteredModuleList(new TutorialDetailsContainsKeywordsPredicate(keywords));
                current_Filter++;
            }
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModuleCommand // instanceof handles nulls
                && keywords.equals(((FindModuleCommand) other).keywords)); // state check
    }
}
