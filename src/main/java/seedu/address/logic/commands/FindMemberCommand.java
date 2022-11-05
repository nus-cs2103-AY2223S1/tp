package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_SEARCH_KEYWORDS_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_SEARCH_KEYWORDS_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all members on the current team based on argument keywords.
 */
@CommandLine.Command(name = FindMemberCommand.COMMAND_WORD,
        aliases = {FindMemberCommand.ALIAS}, mixinStandardHelpOptions = true)
public class FindMemberCommand extends Command {
    public static final String COMMAND_WORD = "member";
    public static final String ALIAS = "m";
    public static final String FULL_COMMAND = FindCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND + ": Finds all team members whose details contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "If names is used, returns all members with names that contain these keywords\n"
            + "If email is used, return all members with emails that contain a substring of these keywords\n"
            + "Parameters: "
            + "[" + FLAG_NAME_STR + " NAME] "
            + "[" + FLAG_EMAIL_STR + " EMAIL] \n"
            + "Example: " + FULL_COMMAND + " "
            + FLAG_NAME_STR + " Alex ";

    public static final String MESSAGE_SUCCESS = "Showing all %1$d member(s) containing search string(s)%2$s. \n"
            + "Type `list members` to show all members again.";

    public static final String MESSAGE_ONE_FLAG = "Please supply only 1 flag by selecting name or email only.";

    @CommandLine.ArgGroup(multiplicity = "1")
    private Exclusive predicate;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public FindMemberCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        model.updateFilteredMembersList(predicate.getPredicate());
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(), predicate.keywordsToString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMemberCommand // instanceof handles nulls
                && predicate.equals(((FindMemberCommand) other).predicate)); // state check
    }

    private static class Exclusive {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true, arity = "1..*",
                description = FLAG_NAME_SEARCH_KEYWORDS_DESCRIPTION)
        private String[] nameKeywords;

        @CommandLine.Option(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, required = true, arity = "1..*",
                description = FLAG_EMAIL_SEARCH_KEYWORDS_DESCRIPTION)
        private String[] emailKeywords;

        Predicate<Person> getPredicate() {
            return nameKeywords == null
                    ? new EmailContainsKeywordsPredicate(List.of(emailKeywords))
                    : new NameContainsKeywordsPredicate(List.of(nameKeywords));
        }

        String keywordsToString() {
            return nameKeywords == null
                    ? Stream.of(emailKeywords).reduce("", (a, b) -> a + " " + b)
                    : Stream.of(nameKeywords).reduce("", (a, b) -> a + " " + b);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            } else if (other instanceof Exclusive) {
                Exclusive target = (Exclusive) other;
                return Arrays.equals(nameKeywords, target.nameKeywords)
                        && Arrays.equals(emailKeywords, target.emailKeywords);
            } else {
                return false;
            }
        }
    }
}
