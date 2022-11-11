package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMPANY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COVERAGES;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_INCOME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_RISK_APPETITE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import longtimenosee.logic.commands.FindCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.predicate.AddressContainsInputPredicate;
import longtimenosee.model.person.predicate.BirthdayMatchesInputPredicate;
import longtimenosee.model.person.predicate.EmailMatchesInputPredicate;
import longtimenosee.model.person.predicate.IncomeMatchesInputPredicate;
import longtimenosee.model.person.predicate.NameContainsKeywordsPredicate;
import longtimenosee.model.person.predicate.PhoneContainsNumberPredicate;
import longtimenosee.model.person.predicate.PolicyCompanyMatchesInputPredicate;
import longtimenosee.model.person.predicate.PolicyCoverageMatchesInputPredicate;
import longtimenosee.model.person.predicate.PolicyTitleContainsKeywordsPredicate;
import longtimenosee.model.person.predicate.RiskAppetiteMatchesInputPredicate;
import longtimenosee.model.person.predicate.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL,
                        PREFIX_TAG, PREFIX_BIRTHDAY, PREFIX_INCOME, PREFIX_RISK_APPETITE, PREFIX_TITLE,
                        PREFIX_COVERAGES, PREFIX_COMPANY);

        if (!isAtLeastOnePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL,
                PREFIX_TAG, PREFIX_BIRTHDAY, PREFIX_INCOME, PREFIX_RISK_APPETITE, PREFIX_TITLE, PREFIX_COVERAGES,
                PREFIX_COMPANY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Person>> predicates = new ArrayList<Predicate<Person>>();

        checkNamePrefix(argMultimap, predicates);
        checkPhonePrefix(argMultimap, predicates);
        checkAddressPrefix(argMultimap, predicates);
        checkEmailPrefix(argMultimap, predicates);
        checkTagPrefix(argMultimap, predicates);
        checkBirthdayPrefix(argMultimap, predicates);
        checkIncomePrefix(argMultimap, predicates);
        checkRiskAppetitePrefix(argMultimap, predicates);
        checkPolicyTitlePrefix(argMultimap, predicates);
        checkPolicyCoveragePrefix(argMultimap, predicates);
        checkPolicyCompanyPrefix(argMultimap, predicates);

        return new FindCommand(predicates);
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

    private static void checkNamePrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName.trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

    private static void checkPhonePrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).value;
            predicates.add(new PhoneContainsNumberPredicate(phoneNumber));
        }
    }

    private static void checkAddressPrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).value;
            predicates.add(new AddressContainsInputPredicate(address));
        }
    }

    private static void checkEmailPrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()).value;
            predicates.add(new EmailMatchesInputPredicate(email));
        }
    }

    private static void checkTagPrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (!(argMultimap.getAllValues(PREFIX_TAG).isEmpty())) {
            List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
            List<String> parsedKeywords = new ArrayList<String>();
            for (String tagKeyword : tagKeywords) {
                parsedKeywords.add(ParserUtil.parseTag(tagKeyword).tagName.trim());
            }
            predicates.add(new TagContainsKeywordsPredicate(parsedKeywords));
        }
    }

    private static void checkBirthdayPrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            String birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get()).value;
            predicates.add(new BirthdayMatchesInputPredicate(birthday));
        }
    }

    private static void checkIncomePrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_INCOME).isPresent()) {
            String income = ParserUtil.parseIncome(argMultimap.getValue(PREFIX_INCOME).get()).value;
            predicates.add(new IncomeMatchesInputPredicate(income));
        }
    }

    private static void checkRiskAppetitePrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_RISK_APPETITE).isPresent()) {
            String riskAppetite = ParserUtil.parseRA(argMultimap.getValue(PREFIX_RISK_APPETITE).get()).value;
            predicates.add(new RiskAppetiteMatchesInputPredicate(riskAppetite));
        }
    }

    private static void checkPolicyTitlePrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            String trimmedArgs = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()).fullTitle.trim();
            String[] titleKeywords = trimmedArgs.split("\\s+");
            predicates.add(new PolicyTitleContainsKeywordsPredicate(List.of(titleKeywords)));
        }
    }

    private static void checkPolicyCoveragePrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (!(argMultimap.getAllValues(PREFIX_COVERAGES).isEmpty())) {
            List<String> coverageKeywords = argMultimap.getAllValues(PREFIX_COVERAGES);
            List<String> parsedKeywords = new ArrayList<String>();
            for (String coverageKeyword : coverageKeywords) {
                parsedKeywords.add(ParserUtil.parseCoverage(coverageKeyword).coverageType.trim());
            }
            predicates.add(new PolicyCoverageMatchesInputPredicate(parsedKeywords));
        }
    }

    private static void checkPolicyCompanyPrefix(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            String company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()).value;
            predicates.add(new PolicyCompanyMatchesInputPredicate(company));
        }
    }
}
