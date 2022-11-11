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
import static seedu.address.logic.parser.CliSyntax.LABEL_MEMBER_EMAIL_KEYWORDS;
import static seedu.address.logic.parser.CliSyntax.LABEL_MEMBER_NAME_KEYWORDS;

import java.util.function.Predicate;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EmailContainsKeywordsPredicateConverter;
import seedu.address.logic.parser.NameContainsKeywordsPredicateConverter;
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
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to find a member in the current team.\n";

    public static final String MESSAGE_SUCCESS = "Showing all %1$d member(s) containing search string(s): %2$s\n"
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
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        model.updateFilteredMembersList(predicate.getPredicate());
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(), predicate.getKeywords()));
    }


    private static class Exclusive {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true, arity = "1",
                paramLabel = LABEL_MEMBER_NAME_KEYWORDS,
                parameterConsumer = NameContainsKeywordsPredicateConverter.class,
                description = FLAG_NAME_SEARCH_KEYWORDS_DESCRIPTION)
        private NameContainsKeywordsPredicate nameContainsKeywordsPredicate;

        @CommandLine.Option(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, required = true, arity = "1",
                paramLabel = LABEL_MEMBER_EMAIL_KEYWORDS,
                parameterConsumer = EmailContainsKeywordsPredicateConverter.class,
                description = FLAG_EMAIL_SEARCH_KEYWORDS_DESCRIPTION)
        private EmailContainsKeywordsPredicate emailContainsKeywordsPredicate;

        public Predicate<Person> getPredicate() {
            return nameContainsKeywordsPredicate == null
                    ? emailContainsKeywordsPredicate
                    : nameContainsKeywordsPredicate;
        }

        public String getKeywords() {
            return nameContainsKeywordsPredicate == null
                    ? emailContainsKeywordsPredicate.getKeywordsAsString()
                    : nameContainsKeywordsPredicate.getKeywordsAsString();
        }

    }
}
