package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMPANY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COVERAGES;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import longtimenosee.logic.commands.FindPolicyCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.predicate.CompanyMatchesInputPredicate;
import longtimenosee.model.policy.predicate.CoverageMatchesInputPredicate;
import longtimenosee.model.policy.predicate.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPolicyCommand object
 */
public class FindPolicyCommandParser implements Parser<FindPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPolicyCommand
     * and returns a FindPolicyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_COMPANY, PREFIX_COVERAGES);

        if (!isAtLeastOnePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_COMPANY, PREFIX_COVERAGES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPolicyCommand.MESSAGE_USAGE));
        }

        List<Predicate<Policy>> predicates = new ArrayList<Predicate<Policy>>();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            String trimmedArgs = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()).fullTitle.trim();
            String[] titleKeywords = trimmedArgs.split("\\s+");
            predicates.add(new TitleContainsKeywordsPredicate(List.of(titleKeywords)));
        }

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            String company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()).value;
            predicates.add(new CompanyMatchesInputPredicate(company));
        }

        if (!(argMultimap.getAllValues(PREFIX_COVERAGES).isEmpty())) {
            List<String> coverageKeywords = argMultimap.getAllValues(PREFIX_COVERAGES);
            List<String> parsedKeywords = new ArrayList<String>();
            for (String coverageKeyword : coverageKeywords) {
                parsedKeywords.add(ParserUtil.parseCoverage(coverageKeyword).coverageType.trim());
            }
            predicates.add(new CoverageMatchesInputPredicate(parsedKeywords));
        }

        return new FindPolicyCommand(predicates);
    }

    /**
     * Checks if there is at least one of the specified prefixes is present in the argument multimap.
     *
     * @param argumentMultimap contains the tokenized arguments
     * @param prefixes to be checked
     * @return boolean to indicate if any prefix is present
     */
    private static boolean isAtLeastOnePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                return true;
            }
        }
        return false;
    }
}
