package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.util.List;
import java.util.function.Predicate;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all members on the current team based on argument keywords.
 */
@CommandLine.Command(name = "member", aliases = {"m"})
public class FindMemberCommand extends Command {

    public static final String COMMAND_WORD = "find member";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all team members whose details contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "If names is used, returns all members with names that contain these keywords\n"
            + "If email is used, return all members with emails that contain a substring of these keywords\n"
            + "Parameters: "
            + "[-" + FLAG_NAME_STR + " NAME] "
            + "[-" + FLAG_EMAIL_STR + " EMAIL] \n"
            + "Example: " + COMMAND_WORD + " "
            + "-" + FLAG_NAME_STR + " Alex ";

    public static final String MESSAGE_ONE_FLAG = "Please supply only 1 flag by selecting name or email only.";

    @CommandLine.ArgGroup(exclusive = true, multiplicity = "1")
    private Exclusive predicate;

    static class Exclusive {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG},
                required = true, arity = "1..*")
        private String[] nameKeywords;

        @CommandLine.Option(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG},
                required = true, arity = "1..*")
        private String[] emailKeywords;

        Predicate<Person> getPredicate() {
            return nameKeywords == null
                    ? new EmailContainsKeywordsPredicate(List.of(emailKeywords))
                    : new NameContainsKeywordsPredicate(List.of(nameKeywords));
        }
    }

    public FindMemberCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredMembersList(predicate.getPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredMemberList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMemberCommand // instanceof handles nulls
                && predicate.equals(((FindMemberCommand) other).predicate)); // state check
    }
}
