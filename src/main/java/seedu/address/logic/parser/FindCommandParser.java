package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.ArgumentMultimap.noPrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTHLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClientTagContainsKeywordsPredicate;
import seedu.address.model.person.FindPredicate;
import seedu.address.model.person.IncomeContainsKeywordsPredicate;
import seedu.address.model.person.MonthlyContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NormalTagContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PlanTagContainsKeywordsPredicate;
import seedu.address.model.person.RiskTagContainsKeywordsPredicate;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final String SPACE_REGEX = "\\s+";
    private static final String PLAN_REGEX = "(?<!\\G\\S+)\\s";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        List<FindPredicate> predicates = new ArrayList<>();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_RISKTAG,
                        PREFIX_PLANTAG, PREFIX_CLIENTTAG, PREFIX_TAG, PREFIX_INCOME, PREFIX_MONTHLY);

        if (noPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_RISKTAG,
                PREFIX_PLANTAG, PREFIX_CLIENTTAG, PREFIX_TAG, PREFIX_INCOME, PREFIX_MONTHLY)
                || !argMultimap.getPreamble().isEmpty()
                || arePrefixesPresent(argMultimap, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<Name> names = ParserUtil.parseAllSpaceSeparatedNames(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_NAME, SPACE_REGEX));
            predicates.add(new NameContainsKeywordsPredicate(names.stream()
                    .map(x -> x.toString()).collect(Collectors.toList())));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            List<Phone> phones = ParserUtil.parseAllSpaceSeparatedPhone(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_PHONE, SPACE_REGEX));
            predicates.add(new PhoneContainsKeywordsPredicate(phones.stream()
                    .map(x -> x.toString()).collect(Collectors.toList())));
        }
        if (argMultimap.getValue(PREFIX_RISKTAG).isPresent()) {
            List<RiskTag> riskTags = ParserUtil.parseAllSpaceSeparatedRiskTag(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_RISKTAG, SPACE_REGEX));
            predicates.add(new RiskTagContainsKeywordsPredicate(riskTags.stream()
                    .map(x -> x.tagName).collect(Collectors.toList())));
        }
        if (argMultimap.getValue(PREFIX_PLANTAG).isPresent()) {
            List<PlanTag> planTags = ParserUtil.parseAllSpaceSeparatedPlanTags(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_PLANTAG, PLAN_REGEX));
            predicates.add(new PlanTagContainsKeywordsPredicate(planTags.stream()
                    .map(x -> x.tagName).collect(Collectors.toList())));
        }
        if (argMultimap.getValue(PREFIX_CLIENTTAG).isPresent()) {
            List<ClientTag> clientTags = ParserUtil.parseAllSpaceSeparatedClientTags(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_CLIENTTAG, SPACE_REGEX));
            predicates.add(new ClientTagContainsKeywordsPredicate(clientTags.stream()
                    .map(x -> x.tagName).collect(Collectors.toList())));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<NormalTag> normalTags = ParserUtil.parseAllSpaceSeparatedNormalTags(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_TAG, SPACE_REGEX));
            predicates.add(new NormalTagContainsKeywordsPredicate(normalTags.stream()
                    .map(x -> x.tagName).collect(Collectors.toList())));
        }
        if (argMultimap.getValue(PREFIX_INCOME).isPresent()) {
            List<String> incomeLevels = ParserUtil.parseMonetaryValues(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_INCOME, SPACE_REGEX));
            String equalityPredicate = incomeLevels.get(0).substring(0, 1);
            incomeLevels.set(0, incomeLevels.get(0).substring(1));
            predicates.add(new IncomeContainsKeywordsPredicate(incomeLevels, equalityPredicate));
        }
        if (argMultimap.getValue(PREFIX_MONTHLY).isPresent()) {
            List<String> monthlySavings = ParserUtil.parseMonetaryValues(argMultimap
                    .getAllValuesSeparatedByRegex(PREFIX_MONTHLY, SPACE_REGEX));
            String equalityPredicate = monthlySavings.get(0).substring(0, 1);
            monthlySavings.set(0, monthlySavings.get(0).substring(1));
            predicates.add(new MonthlyContainsKeywordsPredicate(monthlySavings, equalityPredicate));
        }

        return new FindCommand(predicates);
    }
}
