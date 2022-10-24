package seedu.trackascholar.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_SCHOLARSHIP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.trackascholar.logic.commands.FindCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.NameContainsKeywordsPredicate;
import seedu.trackascholar.model.applicant.ScholarshipContainsKeywordsPredicate;
import seedu.trackascholar.model.major.MajorContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SCHOLARSHIP, PREFIX_MAJOR);

        if (!hasAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SCHOLARSHIP, PREFIX_MAJOR)
                || !argMultimap.getPreamble().isEmpty()
                || args.isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return parsePredicates(argMultimap);
    }

    /**
     * Parses the {@code String} of keywords retrieved from {@code ArgumentMultimap}
     * in the context of the FindCommand and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCommand parsePredicates(ArgumentMultimap argMultimap) throws ParseException {

        requireNonNull(argMultimap);
        List<Predicate<Applicant>> applicantPredicateList = new ArrayList<>();

        if (hasPrefixPresent(argMultimap, PREFIX_NAME)) {
            List<String> nameKeywords = getPartialKeywordsList(argMultimap, PREFIX_NAME);
            applicantPredicateList.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (hasPrefixPresent(argMultimap, PREFIX_SCHOLARSHIP)) {
            List<String> scholarshipKeywords = getPartialKeywordsList(argMultimap, PREFIX_SCHOLARSHIP);
            applicantPredicateList.add(new ScholarshipContainsKeywordsPredicate(scholarshipKeywords));
        }
        if (hasPrefixPresent(argMultimap, PREFIX_MAJOR)) {
            List<String> majorKeywords = getPartialKeywordsList(argMultimap, PREFIX_MAJOR);
            applicantPredicateList.add(new MajorContainsKeywordsPredicate(majorKeywords));
        }

        assert !applicantPredicateList.isEmpty();

        Predicate<Applicant> predicate = combinePredicateList(applicantPredicateList);
        return new FindCommand(predicate);
    }

    private static Predicate<Applicant> combinePredicateList(List<Predicate<Applicant>> applicantPredicateList) {
        return applicantPredicateList.stream().reduce(Predicate::or).orElse(x -> false);
    }

    /**
     * Returns true if any of the prefixes does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean hasAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if the prefix does not contain an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean hasPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    /**
     * Retrieves keyword(s) mapped by {@code Prefix} from {@code ArgumentMultimap} and splits them
     * by whitespaces to allow for partial searching.
     *
     * @return List of partial keywords.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private static List<String> getPartialKeywordsList(ArgumentMultimap argumentMultimap,
                                                       Prefix prefix) throws ParseException {
        if (argumentMultimap.getValue(prefix).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        List<String> keywords = argumentMultimap.getAllValues(prefix);
        List<String> partialKeywords = new ArrayList<>();

        for (int i = 0; i < keywords.size(); i++) {
            partialKeywords.addAll(Arrays.asList(keywords.get(i).split("\\s+")));
        }
        return partialKeywords;
    }

}
