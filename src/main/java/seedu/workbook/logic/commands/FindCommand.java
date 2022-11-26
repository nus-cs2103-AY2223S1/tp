package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;

import java.util.function.Predicate;

import seedu.workbook.commons.core.Messages;
import seedu.workbook.model.Model;
import seedu.workbook.model.internship.Internship;

/**
 * Finds and lists all internships in WorkBook whose company, role or stage name contains
 * any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    /** Command word to execute the find command */
    public static final String COMMAND_WORD = "find";

    /** Help message to execute the find command */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all internships either by Company, Role or Stage using their respective prefix and "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_COMPANY + "COMPANY "
            + "| " + PREFIX_ROLE + "ROLE "
            + "| " + PREFIX_STAGE + "STAGE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_COMPANY + "Meta";

    /** Keyword to search for */
    private final Predicate<Internship> predicate;

    public FindCommand(Predicate<Internship> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                        && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
